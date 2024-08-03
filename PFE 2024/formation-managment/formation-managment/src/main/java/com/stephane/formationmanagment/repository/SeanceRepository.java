package com.stephane.formationmanagment.repository;


import com.stephane.formationmanagment.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SeanceRepository extends JpaRepository<Seance, Long> {

}