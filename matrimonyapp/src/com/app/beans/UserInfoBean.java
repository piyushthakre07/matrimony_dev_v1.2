package com.app.beans;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8331970370619754373L;
    private Integer userinfoId;
	private String firstName;
	private String lastName;
	private String gender;
	private Date dateOfBirth;
	private String birthPlace; 
	private String contactNumber;
	private String alternateNumber;
	private String emailId;
	private String height;
	private String description;
	private String bloodGroup;
	private String faherName;
	private String motherName;
	private String nativePlace;
	private String userCast;
	private String userSubCast;
	private String mamKul;
	private String active;
	private String divorcee;
	private Integer userId;
	
}
