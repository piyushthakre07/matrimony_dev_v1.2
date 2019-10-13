package com.app.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBean implements Serializable {
	private static final long serialVersionUID = -8590185689664258377L;
	private Integer userId;
	private String userName;
	private String password;
	private String userType;
	private String active;
}
