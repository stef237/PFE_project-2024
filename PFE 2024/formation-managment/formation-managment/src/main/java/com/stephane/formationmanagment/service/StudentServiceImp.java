package com.stephane.formationmanagment.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.stephane.formationmanagment.dto.*;
import com.stephane.formationmanagment.entities.*;
import com.stephane.formationmanagment.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.stephane.formationmanagment.enums.StudentPermitStatus;

import lombok.RequiredArgsConstructor;

import static com.stephane.formationmanagment.service.AdminServiceImp.getStudentDto;
import static com.stephane.formationmanagment.service.AdminServiceImp.getsStudentDto;

@Service
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService {
	
	private final UserRepository userRepository;

	private final StudentPermitRepository studentPermitRepository;

	private final CertificatRepository certificatRepository;

	private final FormationRepository formationRepository;

	private final SeanceRepository seanceRepository;




	@Override
	public SStudentDto getStudentById(long id) throws Exception {
		return getsStudentDto(id, userRepository);
	}


	@Override
	public StudentPermitDto applyPermit(StudentPermitDto studentPermitDto) {
		Optional<User> opt = userRepository.findById(studentPermitDto.getUserId());
		
		if(opt.isPresent()) {
			StudentPermit studentPermit = new StudentPermit();
			studentPermit.setName(studentPermit.getName());
			studentPermit.setSubject(studentPermitDto.getSubject());
			studentPermit.setBody(studentPermitDto.getBody());
			studentPermit.setDate(new Date());
			studentPermit.setStudentPermitStatus(StudentPermitStatus.Pending);
			studentPermit.setUser(opt.get());
			StudentPermit submittedStudentPermit = studentPermitRepository.save(studentPermit);
			StudentPermitDto studentPermitDto1 = new StudentPermitDto();
			studentPermitDto1.setId(submittedStudentPermit.getId());			
			return studentPermitDto1;
		}
		return null;
	}


	@Override
	public List<StudentPermitDto> getAllAppliedPermitsById(long studentId) {
		// TODO Auto-generated method stub
		return studentPermitRepository.findAllByUserId(studentId).stream().map(StudentPermit::getStudentPermitDto).collect(Collectors.toList());
	}


	@Override
	public StudentDto updateStudent(long studentId, StudentDto studentDto) throws Exception {
        return getStudentDto(studentId, studentDto, userRepository);

    }

	@Override
	public List<FormationDto> getAllFormations() {
		return formationRepository.findAll().stream().map(Formation::getFormationDto).collect(Collectors.toList());

	}

	@Override
	public FormationDto getFormationById(long formationId) {
		Optional<Certificat> opt = certificatRepository.findById(formationId);
		if(opt.isPresent()) {
			Certificat certificat = opt.get();
			Formation formation = new Formation();
			BeanUtils.copyProperties(certificat, formation);
			FormationDto formationDto = new FormationDto();
			BeanUtils.copyProperties(formation, formationDto);
			return formationDto;
		}
		return new FormationDto();
	}

	@Override
	public List<CertificatDto> getAllCertificats() {
		return certificatRepository.findAll().stream().map(Certificat::getCerticatDto).collect(Collectors.toList());

	}

	@Override
	public CertificatDto getCertificatById(long certificatId) {
		Optional<Certificat> opt = certificatRepository.findById(certificatId);
		if(opt.isPresent()) {
			Certificat certificat = opt.get();
			CertificatDto certificatDto = new CertificatDto();
			BeanUtils.copyProperties(certificat, certificatDto);
			return certificatDto;
		}
		return new CertificatDto();
	}
	@Override
	public List<SeanceDto> getAllSeances() {
		return seanceRepository.findAll().stream().map(Seance::getSeanceDto).collect(Collectors.toList());
	}

	@Override
	public SeanceDto getSeanceById(long seanceId) {
		Optional<Seance> opt = seanceRepository.findById(seanceId);
		if(opt.isPresent()) {
			Seance seance = opt.get();
			SeanceDto seanceDto = new SeanceDto();
			BeanUtils.copyProperties(seance, seanceDto);
			return seanceDto;
		}
		return new SeanceDto();
	}



}
	


