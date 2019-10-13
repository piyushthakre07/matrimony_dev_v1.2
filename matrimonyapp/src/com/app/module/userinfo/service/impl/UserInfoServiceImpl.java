package com.app.module.userinfo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.StatusBean;
import com.app.beans.UserInfoBean;
import com.app.beans.UserResponseBean;
import com.app.model.User;
import com.app.model.UserInfo;
import com.app.module.userinfo.dao.IUserInfoDao;
import com.app.module.userinfo.service.IUserInfoService;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	IUserInfoDao userInfoDao;

	@Override
	public String saveUserInfo(UserInfoBean userInfoBean) {

		log.info("Start Class UserInfoServiceImpl Method saveUserInfo ");
		String message = "";
		StatusBean statusBean = null;
		JSONObject jsonResponse = new JSONObject();
		ObjectMapper mapperObj = new ObjectMapper();
		try {
			UserInfo userInfo = new UserInfo();
			BeanUtils.copyProperties(userInfo, userInfoBean);
			User user=new User();
			user.setUserId(userInfoBean.getUserId());
			userInfo.setUser(user);
			userInfo.setActive("1");
			userInfo=userInfoDao.saveUserInfo(userInfo);
			if(userInfo!=null){
				message = "Success";
				statusBean = new StatusBean(message, "200", true, "");	
			/*	Integer userInfoId=userInfo.getUserinfoId();
				String userInfoStr = mapperObj.writeValueAsString(userInfoId);
				String statusStr = mapperObj.writeValueAsString(statusBean);
				jsonResponse.put("userId", userInfoStr);
				*/
				//jsonResponse.put("statusResponse", statusStr);
				UserResponseBean userResponseBean=new UserResponseBean();
				userResponseBean.setUserId(userInfoBean.getUserId());
				userResponseBean.setStatusResponse(statusBean);
				log.info("End Class UserServiceImpl Method saveUser ");
				return new Gson().toJson(userResponseBean);
			}
			String statusStr;
			message = "Error while processing";
			statusBean = new StatusBean(message, "400", false, "1001");
			statusStr = mapperObj.writeValueAsString(statusBean);
			jsonResponse.put("statusResponse", statusStr);
			return jsonResponse.toString();
		} catch (Exception e) {
			log.info("Error Class UserInfoServiceImpl Method saveUserInfo " + e);
			return null;
		}
	}
	
	@Override
	public StatusBean updateUserInfo(UserInfoBean userInfoBean) {
		log.info("Start Class UserInfoServiceImpl Method saveUserInfo ");
		String message = "";
		StatusBean statusBean = null;
		try {
			UserInfo userInfo = new UserInfo();
			BeanUtils.copyProperties(userInfo, userInfoBean);
			userInfoDao.updateUSerInfo(userInfo);
			message = "Success";
			statusBean = new StatusBean(message, "200", true, "");
			log.info("End Class UserInfoServiceImpl Method saveUserInfo ");
			return statusBean;

		} catch (Exception e) {
			log.info("Error Class UserInfoServiceImpl Method saveUserInfo " + e);
			message = "Error While Processing.";
			statusBean = new StatusBean(message, "400", true, "1001");
			return statusBean;
		}
	}

	@Override
	public String fetchUserInfo(Integer userinfoId)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		JSONObject jsonResponse = new JSONObject();
		StatusBean statusBean = new StatusBean();
		ObjectMapper mapperObj = new ObjectMapper();
		try {
			List<UserInfoBean> userInfoBeanList = new ArrayList<UserInfoBean>();
			UserInfoBean userInfoBean = new UserInfoBean();
			UserInfo userInfo = userInfoDao.fetchUserInfo(userinfoId);
			if (userInfo != null) {
				BeanUtils.copyProperties(userInfoBean, userInfo);
				statusBean.setStatus(true);
				statusBean.setMessage("success");
				userInfoBeanList.add(userInfoBean);
				String userInfoStr = mapperObj.writeValueAsString(userInfoBeanList);
				String statusStr = mapperObj.writeValueAsString(statusBean);
				jsonResponse.put("userInfo", userInfoStr);
				jsonResponse.put("statusResponse", statusStr);
				return jsonResponse.toString();
			} else {
				String message = "No Record Found";
				statusBean = new StatusBean(message, "400", false, "1001");
				String statusStr = mapperObj.writeValueAsString(statusBean);
				jsonResponse.put("statusResponse", statusStr);
				return jsonResponse.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();

			String message = "Error while processing";
			statusBean = new StatusBean(message, "400", false, "1001");
			String statusStr = mapperObj.writeValueAsString(statusBean);
			jsonResponse.put("statusResponse", statusStr);
			return jsonResponse.toString();
		}
	}

	@Override
	public String fetchAllUserInfo() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		List<UserInfoBean> userInfoBeanList = new ArrayList<UserInfoBean>();
		StatusBean statusBean = new StatusBean();
		ObjectMapper mapperObj = new ObjectMapper();
		JSONObject jsonResponse = new JSONObject();
		try {
			List<UserInfo> userInfoList = userInfoDao.fetchAllUserInfo();
			if (userInfoList != null && !userInfoList.isEmpty()) {
				for (UserInfo userInfo : userInfoList) {
					UserInfoBean userInfoBean = new UserInfoBean();
					BeanUtils.copyProperties(userInfoBean, userInfo);
					userInfoBeanList.add(userInfoBean);
				}
				statusBean.setStatus(true);
				statusBean.setMessage("success");
				String userInfoStr = mapperObj.writeValueAsString(userInfoBeanList);
				String statusStr = mapperObj.writeValueAsString(statusBean);
				jsonResponse.put("userInfo", userInfoStr);
				jsonResponse.put("statusResponse", statusStr);
				return jsonResponse.toString();
			} else {
				String message = "No Record Found.";
				statusBean = new StatusBean(message, "400", false, "1001");
				String statusStr = mapperObj.writeValueAsString(statusBean);
				jsonResponse.put("statusResponse", statusStr);
				return jsonResponse.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Error While Processing.";
			statusBean = new StatusBean(message, "400", false, "1001");
			String statusStr = mapperObj.writeValueAsString(statusBean);
			jsonResponse.put("statusResponse", statusStr);
			return jsonResponse.toString();
		}
	}

}
