package com.stephane.formationmanagment.entities;

import com.stephane.formationmanagment.dto.CertificatDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

@Transactional
@CrossOrigin("*")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "certificat")
public class Certificat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date obtenu;
    private Date validite;

    @OneToOne
    private Formation formation;


    public CertificatDto getCerticatDto() {
        CertificatDto certificatDto = new CertificatDto();
        certificatDto.setId(id);
        certificatDto.setName(name);
        certificatDto.setDescription(description);
        certificatDto.setObtenu(obtenu);
        certificatDto.setValidite(validite);

        return certificatDto;
    }
}
