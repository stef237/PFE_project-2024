package com.stephane.formationmanagment.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.stephane.formationmanagment.dto.*;
import com.stephane.formationmanagment.entities.*;
import com.stephane.formationmanagment.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stephane.formationmanagment.enums.StudentPermitStatus;
import com.stephane.formationmanagment.enums.UserRole;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService{
	
	private final UserRepository userRepository;
	
	private final FeeRepository feeRepository;
	
	private final StudentPermitRepository studentPermitRepository;
	
	private final TeacherRepository teacherRepository;

	private final CertificatRepository certificatRepository;

	private final FormationRepository formationRepository;

	private final SeanceRepository seanceRepository;



	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		
		if(adminAccount == null) {
			User admin = new User();
			admin.setEmail("admin@admin.com");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			admin.setName("Admin");
			admin.setRole(UserRole.ADMIN);
			userRepository.save(admin);
		}

		
	}


	@Override
	public StudentDto postStudent(StudentDto studentDto) {
		Optional<User> opt = userRepository.findByEmail(studentDto.getEmail());
		if(opt.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(studentDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setRole(UserRole.STUDENT);
			User createdUser = userRepository.save(user);
			StudentDto createdDto = new StudentDto();
			createdDto.setId(createdUser.getId());
			createdDto.setEmail(createdUser.getEmail());
			
			return createdDto;
		}
		return null;
	}


	@Override
	public List<StudentDto> getAllStudents() {
		
		return userRepository.findAllByRole(UserRole.STUDENT).stream().map(User::getStudentDto).collect(Collectors.toList());
	}


	@Override
	public void deleteStudent(long studentId) {
		userRepository.deleteById(studentId);
		
	}


	@Override
	public SStudentDto getStudentById(long id) throws Exception {
		return getsStudentDto(id, userRepository);
	}

	static SStudentDto getsStudentDto(long id, UserRepository userRepository) throws Exception {
		Optional<User> opt = userRepository.findById(id);

		if(opt.isPresent()) {
			SStudentDto sDto = new SStudentDto();
			sDto.setStudentDto(opt.get().getStudentDto());
			return sDto;
		}
		throw new Exception("User not found with this "+ id+" id");
	}


	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) throws Exception {
		return getStudentDto(studentId, studentDto, userRepository);

	}

	static StudentDto getStudentDto(Long studentId, StudentDto studentDto, UserRepository userRepository) throws Exception {
		Optional<User> opt = userRepository.findById(studentId);

		if(opt.isPresent()) {
			User user = opt.get();
			user.setName(studentDto.getName());
			user.setAddress(studentDto.getAddress());
			user.setBirthDate(studentDto.getBirthDate());
			user.setEmail(studentDto.getEmail());
			user.setGender(studentDto.getGender());
			user.setStudentClass(studentDto.getStudentClass());

			User updatedUser = userRepository.save(user);
			StudentDto updatedDto = new StudentDto();
			updatedDto.setId(updatedUser.getId());

			return updatedDto;
		}

		throw new Exception("User doesn't exist");
	}


	@Override
	public FeeDto payFee(long studentId, FeeDto feeDto) {
		Optional<User> opt = userRepository.findById(studentId);
		
		if(opt.isPresent()) {
			Fee fee = new Fee();
			fee.setAmount(feeDto.getAmount());
			fee.setCreatedAt(new Date());
			fee.setMonth(feeDto.getMonth());
			fee.setDescription(feeDto.getDescription());
			fee.setGivenBy(feeDto.getGivenBy());
			fee.setUser(opt.get());
			Fee paidFee = feeRepository.save(fee);
			FeeDto fDto = new FeeDto();
			fDto.setId(paidFee.getId());
			
			return fDto;
		}
		return null;
	}


	@Override
	public List<StudentPermitDto> getAllAppliedPermits() {
		return studentPermitRepository.findAll().stream().map(StudentPermit::getStudentPermitDto).collect(Collectors.toList());
	}


	@Override
	public StudentPermitDto changePermitStatus(Long permitId, String status) {
		Optional<StudentPermit> opt = studentPermitRepository.findById(permitId);
		
		if(opt.isPresent()) {
			StudentPermit permit = opt.get();
			if(Objects.equals(status, "Approved")) {
				permit.setStudentPermitStatus(StudentPermitStatus.Approved);
			}else {
				permit.setStudentPermitStatus(StudentPermitStatus.Disapproved);
			}
			 StudentPermit updatedPermit=studentPermitRepository.save(permit);
			 StudentPermitDto updatedDto = new StudentPermitDto();
			 updatedDto.setId(updatedPermit.getId());
			 
			 return updatedDto;
		}
		return null;
	}


	@Override
	public TeacherDto postTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherDto, teacher);
		Teacher createdTeacher = teacherRepository.save(teacher);
		TeacherDto createdDto = new TeacherDto();
		createdDto.setId(createdTeacher.getId());
		
		return createdDto;
	}


	@Override
	public List<TeacherDto> getAllTeachers() {
		// TODO Auto-generated method stub
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
	}


	@Override
	public void deleteTeacher(long teacherId) {
		teacherRepository.deleteById(teacherId);
		
	}


	@Override
	public STeacherDto getTeacherById(long teacherId) throws Exception {
		Optional<Teacher> opt = teacherRepository.findById(teacherId);
		
		if(opt.isPresent()) {
			STeacherDto tDto = new STeacherDto();
			tDto.setTeacherDto(opt.get().getTeacherDto());
			return tDto;
		}
		throw new Exception("Teacher not found with this "+ teacherId + "id");
	  }


	@Override
	public TeacherDto updateTeacher(long teacherId, TeacherDto teacherDto) throws Exception {
         Optional<Teacher> opt = teacherRepository.findById(teacherId);
		
		if(opt.isPresent()) {
			Teacher teacher = opt.get();
			teacher.setName(teacherDto.getName());
			teacher.setDepartment(teacherDto.getDepartment());
			teacher.setQualification(teacherDto.getQualification());
			teacher.setAddress(teacherDto.getAddress());
			teacher.setBirthDate(teacherDto.getBirthDate());
			teacher.setGender(teacherDto.getGender());
			
			Teacher updatedTeacher = teacherRepository.save(teacher);
			TeacherDto updatedDto = new TeacherDto();
			updatedDto.setId(updatedTeacher.getId());
			
			return updatedDto;
		}
		
		throw new Exception("Teacher doesn't exist");

	}



	@Override
	public CertificatDto postCertificat(CertificatDto certificatDto) {

		Certificat certificat = new Certificat();
		BeanUtils.copyProperties(certificatDto, certificat);

		Certificat createdCertificat = certificatRepository.save(certificat);
		CertificatDto createdDto = new CertificatDto();
		createdDto.setId(createdCertificat.getId());

		return createdDto;

	}

	@Override
	public List<CertificatDto> getAllCertificats() {
		return certificatRepository.findAll().stream().map(Certificat::getCerticatDto).collect(Collectors.toList());

	}


	@Override
	public SCertificatDto getCertificatById(long certificatId) throws Exception {
		Optional<Certificat> opt = certificatRepository.findById(certificatId);

		if(opt.isPresent()) {
			SCertificatDto sDto = new SCertificatDto();
			sDto.setCertificatDto(opt.get().getCerticatDto());
			return sDto;
		}
		throw new Exception("Certificat not found with this "+ certificatId + "id");
	}


	@Override
	public CertificatDto updateCertificat(long certificatId, CertificatDto certificatDto)throws Exception{
		Optional<Certificat> opt = certificatRepository.findById(certificatId);
		if(opt.isPresent()) {
			Certificat certificat = opt.get();
			certificatDto.setName(certificatDto.getName());
			certificatDto.setDescription(certificatDto.getDescription());
			certificatDto.setObtenu(certificatDto.getObtenu());
			certificatDto.setValidite(certificatDto.getValidite());

			Certificat updatedCertificat = certificatRepository.save(certificat);
			CertificatDto updateDto = new CertificatDto();
			updateDto.setId(updatedCertificat.getId());
			return updateDto;

		}
		throw new Exception("Certificat not found ");
	}


	@Override
	public void deleteCertificat(long certificatId) {certificatRepository.deleteById(certificatId);}

	@Override
	public FormationDto postFormation(FormationDto formationDto) {

		Formation formation = new Formation();
		BeanUtils.copyProperties(formationDto, formation);
		Formation createdFormation = formationRepository.save(formation);
		FormationDto createdDto = new FormationDto();
		createdDto.setId(createdFormation.getId());

		return createdDto;
	}


	@Override
	public List<FormationDto> getAllFormations() {
		return formationRepository.findAll().stream().map(Formation::getFormationDto).collect(Collectors.toList());

	}

	@Override
	public SFormationDto getFormationById(long formationId) throws Exception {
		Optional<Formation> opt = formationRepository.findById(formationId);
		if(opt.isPresent()) {
			SFormationDto dto = new SFormationDto();
			dto.setFormationDto(opt.get().getFormationDto());
			return dto;
		}
		throw new Exception("Formation not found with this" + formationId + "id");
	}



	@Override
	public FormationDto updateFormation(long formationId, FormationDto formationDto) throws Exception {
		Optional<Formation> opt = formationRepository.findById(formationId);
		if(opt.isPresent()) {
			Formation formation = opt.get();
			formation.setName(formationDto.getName());
			formation.setDescription(formationDto.getDescription());
			formation.setObtenu(formationDto.getObtenu());
			formation.setValidite(formationDto.getValidite());
			formation.setDomaine(formation.getDomaine());
			formation.setFile(formation.getFile());
			formation.setCode(formation.getCode());
			formation.setDuree(formation.getDuree());
			formation.setPrix(formation.getPrix());
			formation.setTheme(formation.getTheme());

			Formation updatedFormation = formationRepository.save(formation);
			FormationDto updatedDto = new FormationDto();
			BeanUtils.copyProperties(updatedFormation, updatedDto);
			return updatedDto;
		}
		throw new Exception("Formation not found ");

		}

	@Override
	public void deleteFormation(long formationId) {
		formationRepository.deleteById(formationId);
	}

	@Override
	public SeanceDto postSeance(SeanceDto seanceDto) {

		Seance seance = new Seance();
		BeanUtils.copyProperties(seanceDto, seance);
		Seance createdSeance = seanceRepository.save(seance);
		SeanceDto createdDto = new SeanceDto();
		createdDto.setId(createdSeance.getId());
		return createdDto;
	}

	@Override
	public List<SeanceDto> getAllSeances() {
		return seanceRepository.findAll().stream().map(Seance::getSeanceDto).collect(Collectors.toList());
	}

	@Override
	public SSeanceDto getSeanceById(long seanceId) throws Exception {
		Optional<Seance> opt = seanceRepository.findById(seanceId);
		if(opt.isPresent()) {
			SSeanceDto createdDto = new SSeanceDto();
			createdDto.setSeanceDto(opt.get().getSeanceDto());
			return createdDto;
		}
		throw new Exception("Seance not found with this" + seanceId + "id");
	}


	@Override
	public SeanceDto updateSeance(long seanceId, SeanceDto seanceDto) throws Exception {
		Optional<Seance> opt = seanceRepository.findById(seanceId);
		if(opt.isPresent()) {
			Seance seance = opt.get();
			seance.setName(seanceDto.getName());
			seance.setDescription(seanceDto.getDescription());
			seance.setObtenu(seanceDto.getObtenu());
			seance.setValidite(seanceDto.getValidite());
			seanceRepository.save(seance);
			SeanceDto createdDto = new SeanceDto();
			createdDto.setId(seance.getId());
			return createdDto;

		}
		throw new Exception("Seance not found ");
	}


	@Override
	public void deleteSeance(long seanceId) {
		seanceRepository.deleteById(seanceId);
	}



}

