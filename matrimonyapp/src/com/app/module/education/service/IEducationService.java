 package com.app.module.education.service;

import com.app.beans.EducationBean;

public interface IEducationService {
	
	public Boolean insertEducation(EducationBean educationBean);
	
	public Boolean updateEducation(EducationBean educationBean);
	
	public Boolean deleteEducation(Integer educationId);
	
	public EducationBean findByEducationId(Integer educationId);
}
