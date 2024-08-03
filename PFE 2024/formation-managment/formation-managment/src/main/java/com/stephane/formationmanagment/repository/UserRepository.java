package com.stephane.formationmanagment.repository;

import java.util.List;
import java.util.Optional;

import com.stephane.formationmanagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stephane.formationmanagment.enums.UserRole;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByRole(UserRole admin);

	Optional<User> findByEmail(String email);

	List<User> findAllByRole(UserRole student);

}
