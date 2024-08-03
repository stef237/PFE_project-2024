package com.stephane.formationmanagment.controller;

import java.util.List;

import com.stephane.formationmanagment.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephane.formationmanagment.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	
	private final AdminService adminService;
	
	
	@PostMapping("/student")
	public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto){
		StudentDto createdDto = adminService.postStudent(studentDto);
		
		if(createdDto == null) {
			return new ResponseEntity<>("Someting went wrong",HttpStatus.BAD_REQUEST);
			
			
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}
	
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents(){
		List<StudentDto> students = adminService.getAllStudents();
		
		return ResponseEntity.ok(students);
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable long id){
		adminService.deleteStudent(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<SStudentDto> getStudentById(@PathVariable long studentId) throws Exception{
		SStudentDto dto = adminService.getStudentById(studentId);
		
		if(dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}
	
	
	@PutMapping("/student/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable long studentId, @RequestBody StudentDto studentDto) throws Exception{
		StudentDto createdDto = adminService.updateStudent(studentId, studentDto);
		
		if(createdDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}

	
	@PostMapping("/fee/{studentId}")
	public ResponseEntity<?> addFee(@RequestBody FeeDto feeDto,@PathVariable long studentId){
		FeeDto paidFeeDto = adminService.payFee(studentId,feeDto);
		
		if(paidFeeDto == null) {
			return new ResponseEntity<>("Someting went wrong",HttpStatus.BAD_REQUEST);
			
			
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(paidFeeDto);
	}
	
	@GetMapping("/permits")
	public ResponseEntity<List<StudentPermitDto>> getAllApliedPermitsByStudentId() throws Exception{
		List<StudentPermitDto> studentPermitDtos = adminService.getAllAppliedPermits();
		if(studentPermitDtos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(studentPermitDtos);
	}
	
	@GetMapping("/permit/{permitId}/{status}")
	public ResponseEntity<?> changePermitStatus(@PathVariable Long permitId,@PathVariable String status) throws Exception{
		StudentPermitDto studentPermitDto = adminService.changePermitStatus(permitId,status);
		if(studentPermitDto == null) {
			return  new ResponseEntity<>("Someting went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(studentPermitDto);
	}
	
	
	@PostMapping("/teacher")
	public ResponseEntity<?> addTeacher(@RequestBody TeacherDto teacherDto){
		TeacherDto createdDto = adminService.postTeacher(teacherDto);
		
		if(createdDto == null) {
			return new ResponseEntity<>("Someting went wrong",HttpStatus.BAD_REQUEST);
			
			
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}
	
	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers(){
		List<TeacherDto> teachers = adminService.getAllTeachers();
		
		return ResponseEntity.ok(teachers);
	}
	
	@DeleteMapping("/teacher/{teacherId}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable long teacherId){
		adminService.deleteTeacher(teacherId);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<STeacherDto> getTeacherById(@PathVariable long teacherId) throws Exception{
		STeacherDto dto = adminService.getTeacherById(teacherId);
		
		if(dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/teacher/{teacherId}")
	public ResponseEntity<?> updateTeacher(@PathVariable long teacherId, @RequestBody TeacherDto teacherDto) throws Exception{
		TeacherDto createdDto = adminService.updateTeacher(teacherId, teacherDto);
		
		if(createdDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}


	@GetMapping("/certificat/{certificatId}")
	public ResponseEntity<SCertificatDto> getCertificatById(@PathVariable long certificatId) throws Exception{
		SCertificatDto sCertificatDto= adminService.getCertificatById(certificatId);
		if(sCertificatDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(sCertificatDto);
}
	@PostMapping("/certificat")
	public ResponseEntity<?> addCertificat(@RequestBody CertificatDto CertificatDto) {
		CertificatDto certificatDto = adminService.postCertificat(CertificatDto);
		if(certificatDto == null) {
			return new ResponseEntity<>("Someting went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(certificatDto);
	}

	@GetMapping("/certificats")
	public ResponseEntity < List<CertificatDto>> getAllCertificats() {

		List<CertificatDto> certificats = adminService.getAllCertificats();
		return ResponseEntity.ok(certificats);
	}


	@PutMapping("/certificat/{certificatId}")
	public ResponseEntity<?> updateCertificat(
			@PathVariable long certificatId,
			@RequestBody CertificatDto CertificatDto) throws Exception {

		CertificatDto createdDto = adminService.updateCertificat( certificatId, CertificatDto);
		if(createdDto == null) {
			return  new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);

	}



	@DeleteMapping("/certificat/{certificatId}")
	public ResponseEntity<Void> deleteCertificat(@PathVariable long certificatId) {
		adminService.deleteCertificat(certificatId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/formation")
	public ResponseEntity<?> addFormation(@RequestBody FormationDto formationDto) {
		FormationDto createdDto = adminService.postFormation(formationDto);
		if(createdDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}

	@GetMapping("/formations")
	public ResponseEntity<List<FormationDto> > getAllFormations() {

		List<FormationDto> formations = adminService.getAllFormations();
		return ResponseEntity.ok(formations);
	}

	@DeleteMapping("/formation/{formationId}")
	public ResponseEntity<Void> deleteFormation(@PathVariable long formationId) {
		adminService.deleteFormation(formationId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/formation/{formationId}")
	public ResponseEntity<?> updateFormation(@PathVariable long formationId, @RequestBody FormationDto formationDto) throws Exception {
		FormationDto updatedDto = adminService.updateFormation(formationId, formationDto);
		if(updatedDto == null) {
			return  new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedDto);
	}

	@GetMapping("/formation/{formationId}")
	public ResponseEntity<SFormationDto> getFormationById(@PathVariable long formationId) throws Exception {
		SFormationDto dto = adminService.getFormationById(formationId);
		if(dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}




	@PostMapping("/seance")
	public ResponseEntity<?> addSeance(@RequestBody SeanceDto seanceDto) {
		SeanceDto createdDto = adminService.postSeance(seanceDto);
		if(createdDto == null) {
			return  new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}

	@GetMapping("/seances")
	public ResponseEntity<List<SeanceDto>> getAllSeances() {
		List<SeanceDto> seances = adminService.getAllSeances();
		return ResponseEntity.ok(seances);
	}


	@PutMapping("/seance/{seanceId}")
	public ResponseEntity<?> updateSeance(@PathVariable long seanceId, @RequestBody SeanceDto seanceDto) throws Exception {
		SeanceDto createdDto = adminService.updateSeance(seanceId, seanceDto);
		if(createdDto == null) {
			return  new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);

	}

	@DeleteMapping("/seance/{seanceId}")
	public ResponseEntity<Void> deleteSeance(@PathVariable long seanceId) {
		adminService.deleteSeance(seanceId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/seance/{seanceId}")
	public ResponseEntity<SSeanceDto> getSeanceById(@PathVariable long seanceId) throws Exception {
		SSeanceDto seanceDto= adminService.getSeanceById(seanceId);
		if(seanceDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(seanceDto);
	}




}
