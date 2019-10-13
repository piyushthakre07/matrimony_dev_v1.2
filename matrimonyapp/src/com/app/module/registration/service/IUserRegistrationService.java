package com.app.module.registration.service;

import com.app.beans.StatusBean;
import com.app.beans.TemporaryBean;
import com.app.beans.UserRegistrationBean;
import com.app.beans.UserResponseBean;

public interface IUserRegistrationService {

	public StatusBean registerUser(UserRegistrationBean userRegistrationBean);
	public TemporaryBean fetchTempInfo(Integer tempId,String contactNumber);
	UserResponseBean saveUserByApi(TemporaryBean temporaryBean);
	UserResponseBean saveUserInfoByApi(TemporaryBean temporaryBean);
}
