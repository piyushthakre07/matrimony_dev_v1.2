package com.app.module.otp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.OtpGenerationBean;
import com.app.beans.RequestOtpVerificationBean;
import com.app.beans.StatusBean;
import com.app.module.otp.iservice.IOtpService;

@RestController
@RequestMapping(path = "/otp", produces = "application/json", consumes = "application/json")
public class OtpController {
	
	@Autowired
	IOtpService otpService;

	@RequestMapping(value = "/generateotp", method = RequestMethod.POST)
	public  ResponseEntity<?> generateOtp(@RequestBody OtpGenerationBean otpGenerationBean){
		String otp=otpService.generateOTP(otpGenerationBean.getLength());
		otpGenerationBean.setOtp(otp);
		
		return new ResponseEntity<OtpGenerationBean>(otpGenerationBean, HttpStatus.OK); 
		
	}
	
	@RequestMapping(value = "/verifyotp", method = RequestMethod.POST)
	public ResponseEntity<?> verifyOtp(@RequestBody RequestOtpVerificationBean requestBean) {
		StatusBean statusBean = otpService.verifyOtp(requestBean);
		return new ResponseEntity<StatusBean>(statusBean, HttpStatus.OK);


	}

}
