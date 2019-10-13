package com.app.beans;

import java.io.Serializable;

import lombok.Data;
@Data
public class OtpGenerationBean implements Serializable {

	private static final long serialVersionUID = -393953141349682525L;
	
	private String otp;
	private Integer length;

}
