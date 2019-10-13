package com.app.module.education.dao;

import com.app.model.Education;

public interface IEducationDao {

	Boolean insertEducation(Education countryMaster);
	
	public Boolean updateEducation(Education countryMaster);
	
	public Boolean deleteEducation(Education countryMaster);
	
	public Education findByEducationId(Integer countryId);
}
