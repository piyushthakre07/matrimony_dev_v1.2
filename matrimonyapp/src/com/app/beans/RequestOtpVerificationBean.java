package com.app.beans;

import java.io.Serializable;

import lombok.Data;

@Data

public class RequestOtpVerificationBean implements Serializable {

	
	private static final long serialVersionUID = -9176231514939359933L;
	private String otp;
	private String contactNumber;
	private String functionality;

}
