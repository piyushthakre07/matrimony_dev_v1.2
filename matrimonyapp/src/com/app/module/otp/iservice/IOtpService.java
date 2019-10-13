package com.app.module.otp.iservice;

import com.app.beans.RequestOtpVerificationBean;
import com.app.beans.StatusBean;

public interface IOtpService {

	public String generateOTP(int length) ;

	public StatusBean verifyOtp(RequestOtpVerificationBean requestBean);
}
