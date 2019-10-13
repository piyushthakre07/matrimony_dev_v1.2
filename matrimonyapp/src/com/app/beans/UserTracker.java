package com.app.beans;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;

import lombok.Data;

@Data
@Scope("session")
public class UserTracker implements Serializable {
	private static final long serialVersionUID = -8742910083664380102L;
	private Integer userId;
	private String contactNumber;
	private String emailId;
	private String userName;
	private String userType;
	private Integer tempId;

}
