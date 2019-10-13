package com.app.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString @NoArgsConstructor
public class UserResponseBean implements Serializable{

	private static final long serialVersionUID = 6296929065364270721L;
	private Integer userId;
	private StatusBean statusResponse;


}
