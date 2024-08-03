package com.stephane.formationmanagment.controller;

import java.util.List;

import com.stephane.formationmanagment.dto.SStudentDto;
import com.stephane.formationmanagment.dto.StudentDto;
import com.stephane.formationmanagment.dto.StudentPermitDto;
import com.stephane.formationmanagment.repository.CertificatRepository;
import com.stephane.formationmanagment.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

	private final StudentService studentService;
	private final CertificatRepository certificatRepository;


	
	@GetMapping("/{studentId}")
	public ResponseEntity<SStudentDto> getStudentById(@PathVariable long studentId) throws Exception{
		SStudentDto dto = studentService.getStudentById(studentId);
		
		if(dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/permit")
	public ResponseEntity<?> applyPermit(@RequestBody StudentPermitDto studentPermitDto) {
	    StudentPermitDto submittedStudentPermitDto = studentService.applyPermit(studentPermitDto);

	    if (submittedStudentPermitDto == null)
	        return new  ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);

		return ResponseEntity.status(HttpStatus.CREATED).body(submittedStudentPermitDto);
	}



	
	
	@GetMapping("/permit/{studentId}")
	public ResponseEntity<List<StudentPermitDto>> getAllAppliedPermitsByStudentId(@PathVariable long studentId) throws Exception{
		List<StudentPermitDto> studentPermitDto = studentService.getAllAppliedPermitsById(studentId);
		
		if(studentPermitDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(studentPermitDto);
	}
	
	
	@PutMapping("/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable long studentId, @RequestBody StudentDto studentDto) throws Exception{
		StudentDto createdDto = studentService.updateStudent(studentId, studentDto);
		
		if(createdDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}


}
