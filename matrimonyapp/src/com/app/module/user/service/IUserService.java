package com.app.module.user.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import com.app.beans.StatusBean;
import com.app.beans.UserBean;

public interface IUserService {

	public String saveUser(UserBean userBean);

	public String fetchUser(Integer userId) throws JSONException, JsonGenerationException, JsonMappingException, IOException;

	String fetchAllUser() throws JsonGenerationException, JsonMappingException, IOException, JSONException;

	StatusBean updateUser(UserBean userBean);
}
