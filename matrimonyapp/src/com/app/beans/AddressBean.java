package com.app.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class AddressBean implements Serializable{
	private static final long serialVersionUID = 6257476426696405503L;
	private Integer addressId;
	private String address;
	private String active;
	private String isCurrentAddress;
	private String isPermenantAddress;
	private Integer cityMasterId;
	private Integer stateMasterId;
	private Integer countryMasterId;
	private Integer userInfoId;

}
