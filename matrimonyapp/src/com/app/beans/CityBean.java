package com.app.beans;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author piyush
 *
 */
@Getter @Setter @ToString
public class CityBean implements Serializable {

	private static final long serialVersionUID = 6695195154471729772L;
	private Integer cityId;
	private String cityName;
	private String active;
	private String isdefault;
}
