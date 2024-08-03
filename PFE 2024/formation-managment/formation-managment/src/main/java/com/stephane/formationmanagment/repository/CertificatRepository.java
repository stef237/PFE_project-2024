package com.stephane.formationmanagment.repository;


import com.stephane.formationmanagment.entities.Certificat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificatRepository extends JpaRepository<Certificat, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
}