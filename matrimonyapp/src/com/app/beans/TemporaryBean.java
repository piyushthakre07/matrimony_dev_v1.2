package com.app.beans;

import java.io.Serializable;
import java.util.Date;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class TemporaryBean implements Serializable{
	private static final long serialVersionUID = -7768664678378873401L;
	private Integer tempId;
	private Integer userId;
	private String profileFor;
	private String firstName;
	private String lastName;
	private String gender;
	/* private Date dateOfBirth; */
	private String contactNumber;
	private String emailId;
	private String userName;
	private String password;
	private String confirmPasword;
	
	

}
