package com.app.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class EducaionBean implements Serializable{

	private static final long serialVersionUID = -182988441402199332L;
	
	private Integer educationId;
	
	private String education;
	
	private String active;
	
	
}
