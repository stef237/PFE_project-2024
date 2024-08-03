package com.stephane.formationmanagment.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.stephane.formationmanagment.dto.StudentPermitDto;
import com.stephane.formationmanagment.enums.StudentPermitStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class StudentPermit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String subject;
	
	private String body;
	
	private Date date;
	
	private StudentPermitStatus studentPermitStatus;
	
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;


	public StudentPermitDto getStudentPermitDto() {
		StudentPermitDto permitDto = new StudentPermitDto();
		permitDto.setId(id);
		permitDto.setName(name);
		permitDto.setSubject(subject);
		permitDto.setBody(body);
		permitDto.setDate(date);
		permitDto.setStudentPermitStatus(studentPermitStatus);
		permitDto.setId(user.getId());
		
		return permitDto;
		
	}
	

}
