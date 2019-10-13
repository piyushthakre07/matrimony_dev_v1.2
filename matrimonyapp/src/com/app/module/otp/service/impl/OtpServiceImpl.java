package com.app.module.otp.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.beans.RequestOtpVerificationBean;
import com.app.beans.StatusBean;
import com.app.model.OtpVerification;
import com.app.module.otp.dao.IOtpDao;
import com.app.module.otp.iservice.IOtpService;

/**
 * @author narayan thakare
 *
 */
@Service
public class OtpServiceImpl implements IOtpService {

	@Autowired
	IOtpDao otpDao;

	@Value("${max_otp_attempt_count}")
	String maxOtpCount;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.module.otp.iservice.IOtpService#generateOTP(int)
	 */
	public String generateOTP(int length) {
		String numbers = "1234567890";
		Random random = new Random();
		char[] otp = new char[length];

		for (int i = 0; i < length; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}

		return new String(otp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @Author asmita thakre
	 * 
	 * @created date 07-09-2019
	 * 
	 * @see com.app.module.otp.iservice.IOtpService#verifyOtp(com.app.beans.
	 * RequestOtpVerificationBean)
	 */
	@Override
	@Transactional
	public StatusBean verifyOtp(RequestOtpVerificationBean requestBean) {
		StatusBean statusBean = new StatusBean();
		try {
			OtpVerification otpVerification = otpDao.verifyOtpDao(requestBean);

			String message = "";
			if (otpVerification == null) {
				message = "Mobile Number not Exist";
			} else if (otpVerification.isVerifiedStatus()) {
				message = "Otp is already verified";
			} else if (otpVerification.isMaxOtpAttemptStatus() && otpVerification.getOtpLockTime() != null
					&& otpVerification.getOtpLockTime().before(increaseTime(15))) {
				message = "You have enter maximum Otp attempt.";
			} /*
				 * else if (otpVerification.getOtpLockTime() != null &&
				 * otpVerification.getOtpLockTime().after(increaseTime(15))) {
				 * otpVerification.setMaxOtpAttemptStatus(false);
				 * otpVerification.setCount(0);
				 * otpVerification.setOtpLockTime(null);
				 * otpDao.updateOtpVerification(otpVerification); }
				 */
			else if (!otpVerification.isActive()) {
				message = "Account is not active";
			} else if (!(otpVerification.getOtp().equals(requestBean.getOtp()))) {
				otpVerification.setCount(otpVerification.getCount() + 1);
				if (otpVerification.getCount() >= Integer.parseInt(maxOtpCount)) {
					otpVerification.setMaxOtpAttemptStatus(true);
					otpVerification.setOtpLockTime(new Date());

				}
				otpDao.updateOtpVerification(otpVerification);
				message = "Invaild Otp";
			} else {
				Integer OtpVerificationId = otpVerification.getOtpVerificationId();
				otpVerification = otpDao.fetchOtpVerification(OtpVerificationId);
				otpVerification.setActive(false);
				otpVerification.setVerifiedStatus(true);
				otpDao.updateOtpVerification(otpVerification);
				message = "Otp Successfully Verified!";
				statusBean.setMessage(message);
				statusBean.setSatusCode("200");
				statusBean.setStatus(true);
				return statusBean;
			}
			statusBean.setMessage(message);
			statusBean.setSatusCode("400");
			statusBean.setStatus(false);
			statusBean.setErrorCode("1001");
		} catch (Exception e) {
			String message = "Error While Processing.";
			statusBean.setMessage(message);
			statusBean.setSatusCode("400");
			statusBean.setStatus(false);
			statusBean.setErrorCode("1001");
			return statusBean;
		}
		return statusBean;

	}

	public static Date increaseTime(Integer timeIncreamentInMinute) throws ParseException {
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MINUTE, timeIncreamentInMinute);
		Date date = cal.getTime();
		return date;
	}

}
