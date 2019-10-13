package com.app.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class EducationBean  implements Serializable{
	private static final long serialVersionUID = -1332595051326903719L;
	
	private Integer educationId;

	private String passingYear;

	private String active;

	private String higestEducation;

	private String description;
	
	private Integer userId;
	
	private Integer eduLevelId;
	
	private Integer courceId;

	private Integer sepciallazationId;

	private Integer universityId;

}
