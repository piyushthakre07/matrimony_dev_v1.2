package com.app.module.registration.service.impl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.beans.OtpGenerationBean;
import com.app.beans.StatusBean;
import com.app.beans.TemporaryBean;
import com.app.beans.UserTracker;
import com.app.model.OtpVerification;
import com.app.model.Temporary;
import com.app.module.registration.dao.ITemporaryDao;
import com.app.module.registration.service.ITemporaryService;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class TemporaryServiceImpl implements ITemporaryService {

	@Autowired
	ITemporaryDao temporaryDao;

	@Autowired
	private Environment environment;

	@Value("${otp_expire_time_minute}")
	private Integer expireTime;

	@Value("${otp_registration_functionality}")
	private String otpRegistrationFunctionality;

	@Value("${generate_otp_url}")
	String generateOtpUrl;
	
	@Autowired
	UserTracker userTracker;

	@Transactional
	public StatusBean saveTempDetails(TemporaryBean temporaryBean) {
		log.info("Start Class TemporaryServiceImpl Method saveTempDetails ");
		String message = "";
		StatusBean statusBean = null;
		try {
			Temporary temporary = new Temporary();
			BeanUtils.copyProperties(temporary, temporaryBean);
			temporaryDao.save(temporary);
			OtpGenerationBean otpGenerationBean = getOtoGenerationBean(
					Integer.parseInt(environment.getRequiredProperty("otp_length")));
			OtpVerification otpVerification = setOtpVerification(temporaryBean.getContactNumber(),
					otpGenerationBean.getOtp());
			temporaryDao.save(otpVerification);
			message = "Success";
			userTracker.setContactNumber(temporaryBean.getContactNumber());
			userTracker.setUserName(temporaryBean.getUserName());
			userTracker.setEmailId(temporaryBean.getEmailId());
			userTracker.setTempId(temporary.getTempId());
			statusBean = new StatusBean(message, "200", true, "");
			log.info("End Class TemporaryServiceImpl Method saveTempDetails ");
			return statusBean;

		} catch (Exception e) {
			log.info("Error Class TemporaryServiceImpl Method saveTempDetails " + e);
			message = "Error While Processing.";
			statusBean = new StatusBean(message, "400", true, "1001");
			return statusBean;
		}

	}

	public OtpGenerationBean getOtoGenerationBean(int length) {
		OtpGenerationBean OtpGenerationRequestBean = new OtpGenerationBean();
		OtpGenerationRequestBean.setLength(length);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<OtpGenerationBean> entity = new HttpEntity<OtpGenerationBean>(OtpGenerationRequestBean, headers);
		ResponseEntity<OtpGenerationBean> result = restTemplate.exchange(generateOtpUrl, HttpMethod.POST, entity,
				OtpGenerationBean.class);
		OtpGenerationBean responseResult = result.getBody();
		return responseResult;
	}

	public static Date increaseTimeInMinute(Integer timeIncreamentInMinute) throws ParseException {
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MINUTE, timeIncreamentInMinute);
		Date date = cal.getTime();
		return date;
	}

	public OtpVerification setOtpVerification(String contactNumber,String otp) throws ParseException {
		OtpVerification otpVerification = new OtpVerification();
		otpVerification.setContactNumber(contactNumber);
		otpVerification.setOtp(otp);
		otpVerification.setOtpSendingTime(new Date());
		Date expireDate = increaseTimeInMinute(expireTime);
		otpVerification.setOtpExpiringTime(expireDate);
		otpVerification.setCount(1);
		otpVerification.setFunctionality(otpRegistrationFunctionality);
		otpVerification.setVerifiedStatus(false);
		otpVerification.setActive(true);
		otpVerification.setMaxOtpAttemptStatus(false);
		return otpVerification;
	}

}
