package com.app.module.userinfo.dao;

import java.util.List;

import com.app.model.UserInfo;

public interface IUserInfoDao {

	public UserInfo saveUserInfo(UserInfo userInfo);

	public UserInfo fetchUserInfo(Integer userinfoId);

	List<UserInfo> fetchAllUserInfo();

	boolean updateUSerInfo(UserInfo userInfo);
}
