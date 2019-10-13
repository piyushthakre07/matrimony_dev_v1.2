package com.app.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author piyush
 *
 */
@Getter @Setter @ToString
public class StateBean {

	private Integer stateId;
	private String stateName;
	private String active;
	private String isdefault;
}
