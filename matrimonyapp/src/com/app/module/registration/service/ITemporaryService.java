package com.app.module.registration.service;

import com.app.beans.StatusBean;
import com.app.beans.TemporaryBean;
import com.app.beans.UserRegistrationBean;

public interface ITemporaryService {

	public StatusBean saveTempDetails(TemporaryBean temporaryBean);
	

}
