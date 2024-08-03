package com.stephane.formationmanagment.repository;

import com.stephane.formationmanagment.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
