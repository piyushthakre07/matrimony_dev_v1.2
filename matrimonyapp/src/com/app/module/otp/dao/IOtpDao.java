package com.app.module.otp.dao;

import com.app.beans.RequestOtpVerificationBean;
import com.app.model.OtpVerification;

public interface IOtpDao {

	public OtpVerification verifyOtpDao(RequestOtpVerificationBean RequestBean);

	public boolean updateOtpVerification(OtpVerification otpVerification);

	OtpVerification fetchOtpVerification(Integer otpVerificationId);
}
