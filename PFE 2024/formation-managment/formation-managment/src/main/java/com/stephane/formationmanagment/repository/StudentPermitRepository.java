package com.stephane.formationmanagment.repository;

import java.util.List;

import com.stephane.formationmanagment.entities.StudentPermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPermitRepository extends JpaRepository<StudentPermit, Long> {

	List<StudentPermit> findAllByUserId(long userId);

}
