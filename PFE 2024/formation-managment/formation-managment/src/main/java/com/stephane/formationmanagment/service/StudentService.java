package com.stephane.formationmanagment.service;

import java.util.List;

import com.stephane.formationmanagment.dto.*;

public interface StudentService {

	SStudentDto getStudentById(long id) throws Exception;

	StudentPermitDto applyPermit(StudentPermitDto studentPermitDto);

	List<StudentPermitDto> getAllAppliedPermitsById(long studentId);

	StudentDto updateStudent(long studentId, StudentDto studentDto) throws Exception;

	List<FormationDto> getAllFormations();

	FormationDto getFormationById(long formationId) ;


	List<CertificatDto> getAllCertificats();

	CertificatDto getCertificatById(long certificatId) ;

	List<SeanceDto> getAllSeances();

	SeanceDto getSeanceById(long seanceId);


}
