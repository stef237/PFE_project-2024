package com.stephane.formationmanagment.repository;


import com.stephane.formationmanagment.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {

    // Ajoutez des méthodes spécifiques si nécessaire
}
