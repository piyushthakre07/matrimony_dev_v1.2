package com.app.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserRegistrationBean implements Serializable{
	
	private static final long serialVersionUID = 6806491122799046347L;
	private Integer tempId;
	private String contactNumber;
	private String otp;
	private String userType;
	private String functionality;

}

