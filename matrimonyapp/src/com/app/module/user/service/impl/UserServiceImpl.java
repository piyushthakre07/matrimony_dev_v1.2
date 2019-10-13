package com.app.module.user.service.impl;

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
import com.app.beans.UserBean;
import com.app.beans.UserResponseBean;
import com.app.model.User;
import com.app.module.user.dao.IUserDao;
import com.app.module.user.service.IUserService;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Override
	public String saveUser(UserBean userBean) {
		String message = "";
		StatusBean statusBean = null;
		JSONObject jsonResponse = new JSONObject();
		ObjectMapper mapperObj = new ObjectMapper();
		try {
			User user = new User();
			BeanUtils.copyProperties(user, userBean);
			// user.setPassword("");
			user = userDao.saveUser(user);
			if (user != null) {
				Integer userId = user.getUserId();
				UserResponseBean userResponseBean=new UserResponseBean();
				message = "Success";
				statusBean = new StatusBean(message, "200", true, "");
				userResponseBean.setStatusResponse(statusBean);
				userResponseBean.setUserId(userId);
				log.info("End Class UserServiceImpl Method saveUser ");
				return new Gson().toJson(userResponseBean);
			} else {
				String statusStr;
				message = "Error while processing";
				statusBean = new StatusBean(message, "400", false, "1001");
				statusStr = mapperObj.writeValueAsString(statusBean);
				jsonResponse.put("statusResponse", statusStr);
				return jsonResponse.toString();
			}
			// return statusBean;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public StatusBean updateUser(UserBean userBean) {
		log.info("Start Class UserServiceImpl Method saveUser ");
		String message = "";
		StatusBean statusBean = null;
		try {
			User user = new User();
			BeanUtils.copyProperties(user, userBean);
			userDao.updateUser(user);
			message = "Success";
			statusBean = new StatusBean(message, "200", true, "");
			log.info("End Class UserServiceImpl Method saveUser ");
			return statusBean;

		} catch (Exception e) {
			log.info("Error Class UserServiceImpl Method saveUser " + e);
			message = "Error While Processing.";
			statusBean = new StatusBean(message, "400", true, "1001");
			return statusBean;
		}
	}

	@Override
	public String fetchUser(Integer userId)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		JSONObject jsonResponse = new JSONObject();
		StatusBean statusBean = new StatusBean();
		ObjectMapper mapperObj = new ObjectMapper();
		try {
			List<UserBean> userBeanList = new ArrayList<UserBean>();
			UserBean userBean = new UserBean();
			User user = userDao.fetchUser(userId);
			if (user != null) {
				BeanUtils.copyProperties(userBean, user);
				statusBean.setStatus(true);
				statusBean.setMessage("success");
				userBeanList.add(userBean);
				String userStr = mapperObj.writeValueAsString(userBeanList);
				String statusStr = mapperObj.writeValueAsString(statusBean);
				jsonResponse.put("user", userStr);
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

			String message = "No Record Found";
			statusBean = new StatusBean(message, "400", false, "1001");
			String statusStr = mapperObj.writeValueAsString(statusBean);
			jsonResponse.put("statusResponse", statusStr);
			return jsonResponse.toString();
		}
	}

	@Override
	public String fetchAllUser() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		StatusBean statusBean = new StatusBean();
		ObjectMapper mapperObj = new ObjectMapper();
		JSONObject jsonResponse = new JSONObject();
		try {
			List<User> userList = userDao.fetchAllUser();
			if (userList != null && !userList.isEmpty()) {
				for (User user : userList) {
					UserBean userBean = new UserBean();
					BeanUtils.copyProperties(userBean, user);
					userBeanList.add(userBean);
				}
				statusBean.setStatus(true);
				statusBean.setMessage("success");
				String userStr = mapperObj.writeValueAsString(userBeanList);
				String statusStr = mapperObj.writeValueAsString(statusBean);
				jsonResponse.put("user", userStr);
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
