package com.app.module.userinfo.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import com.app.beans.StatusBean;
import com.app.beans.UserInfoBean;

public interface IUserInfoService {

	public String saveUserInfo(UserInfoBean userInfoBean);

	public String fetchUserInfo(Integer userinfoId) throws JSONException, JsonGenerationException, JsonMappingException, IOException;

	String fetchAllUserInfo() throws JsonGenerationException, JsonMappingException, IOException, JSONException;

	StatusBean updateUserInfo(UserInfoBean userInfoBean);
}
