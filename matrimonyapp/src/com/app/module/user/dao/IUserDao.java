package com.app.module.user.dao;

import java.util.List;

import com.app.model.User;

public interface IUserDao {

	public User saveUser(User user);

	public User fetchUser(Integer userId);

	List<User> fetchAllUser();

	boolean updateUser(User user);
}
