package com.stephane.formationmanagment.dto;

import java.util.Date;

import com.stephane.formationmanagment.enums.StudentPermitStatus;

import lombok.Data;

@Data
public class StudentPermitDto {
	
    private Long id;

	private String name;
	
	private String subject;
	
	private String body;
	
	private Date date;
	
	private StudentPermitStatus studentPermitStatus;
	
	private Long userId;

}
