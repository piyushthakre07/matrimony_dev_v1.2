package com.app.module.registration.service.impl;

import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.app.beans.StatusBean;
import com.app.beans.TemporaryBean;
import com.app.beans.UserBean;
import com.app.beans.UserRegistrationBean;
import com.app.beans.UserResponseBean;
import com.app.beans.UserTracker;
import com.app.model.Temporary;
import com.app.module.registration.dao.IUserRegistrationDao;
import com.app.module.registration.service.IUserRegistrationService;
import com.google.gson.Gson;

@Service
public class UserRegistrationServiceImpl implements IUserRegistrationService {

	@Autowired
	IUserRegistrationDao userRegistrationDao;

	@Value("${verify_otp_url}")
	String verifyOtpUrl;

	@Value("${save_user_url}")
	String saveUserUrl;

	@Value("${save_user_info_url}")
	String saveUserInfoUrl;

	@Value("${otp_registration_functionality}")
	String functionality;

	@Value("${user_type}")
	String userType;
	
	@Autowired
	UserTracker userTracker;

	@Transactional
	@Override
	public StatusBean registerUser(UserRegistrationBean userRegistrationBean) {
		userRegistrationBean.setContactNumber(userTracker.getContactNumber());
		userRegistrationBean.setFunctionality(functionality);
		userRegistrationBean.setUserType(userType);
		userRegistrationBean.setTempId(userTracker.getTempId());
		StatusBean statusBean = new StatusBean();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<UserRegistrationBean> entity = new HttpEntity<UserRegistrationBean>(userRegistrationBean, headers);
		ResponseEntity<StatusBean> result = restTemplate.exchange(verifyOtpUrl, HttpMethod.POST, entity,
				StatusBean.class);
		statusBean = result.getBody();
		return statusBean;
	}

	@Transactional
	@Override
	public TemporaryBean fetchTempInfo(Integer tempId, String contactNumber) {
		TemporaryBean temporaryBean = new TemporaryBean();
		try {
			Temporary temporary = userRegistrationDao.fetchTempInfo(tempId, contactNumber);
			if (temporary != null) {
				BeanUtils.copyProperties(temporaryBean, temporary);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		return temporaryBean;
	}

	@Transactional
	@Override
	public UserResponseBean saveUserByApi(TemporaryBean temporaryBean) {
		try {
			UserBean userRequestBean = new UserBean();
			userRequestBean.setUserName(temporaryBean.getUserName());
			userRequestBean.setPassword(temporaryBean.getPassword());
			userRequestBean.setActive("Y");
			userRequestBean.setUserType(userType);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(userRequestBean), headers);

			ResponseEntity<UserResponseBean> result = restTemplate.exchange(saveUserUrl, HttpMethod.POST, entity,
					UserResponseBean.class);
			UserResponseBean userResponseBean = result.getBody();
			return userResponseBean;
		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		return null;
	}

	@Transactional
	@Override
	public UserResponseBean saveUserInfoByApi(TemporaryBean temporaryBean) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<TemporaryBean> entity = new HttpEntity<TemporaryBean>(temporaryBean, headers);
			ResponseEntity<UserResponseBean> result = restTemplate.exchange(saveUserInfoUrl, HttpMethod.POST, entity,
					UserResponseBean.class);
			UserResponseBean userResponseBean = result.getBody();
			StatusBean statusBean=userResponseBean.getStatusResponse();
			statusBean.setMessage("Congratulation...You have been successfully register.");
			return userResponseBean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
