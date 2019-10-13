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
public class ProfileForBean implements Serializable{
	private static final long serialVersionUID = -1276811286782333361L;
	private Integer profileId;
	private String profileName;
	private String active;
}
