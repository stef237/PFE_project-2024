package com.stephane.formationmanagment.service;

import java.util.List;

import com.stephane.formationmanagment.dto.*;

public interface AdminService {

	
	void createAdminAccount();

	StudentDto postStudent(StudentDto studentDto);

	List<StudentDto> getAllStudents();
	
	void deleteStudent(long studentId);
	
	SStudentDto getStudentById(long id) throws Exception;
	
	StudentDto updateStudent(Long studentId, StudentDto studentDto) throws Exception;

	FeeDto payFee(long studentId, FeeDto feeDto);

	List<StudentPermitDto> getAllAppliedPermits();

	StudentPermitDto changePermitStatus(Long permitId, String status);


	TeacherDto postTeacher(TeacherDto teacherDto);

	List<TeacherDto> getAllTeachers();

	void deleteTeacher(long teacherId);

	STeacherDto getTeacherById(long teacherId)throws Exception;

	TeacherDto updateTeacher(long teacherId, TeacherDto teacherDto) throws Exception;


	CertificatDto postCertificat(CertificatDto certificatDto);

	List<CertificatDto> getAllCertificats();

	SCertificatDto getCertificatById(long certificatId) throws Exception;

	CertificatDto updateCertificat(long certificatId, CertificatDto certificatDto)throws Exception;

	void deleteCertificat(long certificatId);


	FormationDto postFormation(FormationDto formationDto);

	List<FormationDto> getAllFormations();

	SFormationDto getFormationById(long formationId) throws Exception;

	FormationDto updateFormation(long formationId, FormationDto formationDto) throws Exception;

	void deleteFormation(long formationId);




	SeanceDto postSeance(SeanceDto seanceDto);

	List<SeanceDto> getAllSeances();

	SSeanceDto getSeanceById(long seanceId) throws Exception;

	SeanceDto updateSeance(long seanceId, SeanceDto seanceDto) throws Exception;

	void deleteSeance(long seanceId);





}
