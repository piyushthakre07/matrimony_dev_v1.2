package com.app.module.registration.dao;

import com.app.model.Temporary;

public interface IUserRegistrationDao {

	public Temporary fetchTempInfo(Integer tempId,String contactNumber);
}
