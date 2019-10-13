package com.app.beans;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author piyush
 *
 */
@Getter @Setter @ToString
public class CountryBean implements Serializable {
	private static final long serialVersionUID = 8264284453288689450L;
	private Integer countryId;
	private String countryName;
	private String active;
	private String isdefault;
	List<StateBean> StateBeanList;

}
