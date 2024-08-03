package com.stephane.formationmanagment.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stephane.formationmanagment.dto.FormationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "formation")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String domaine;
    private String description;
    private int duree;
    private String theme;
    private String file;
    private int prix;
    private String code;
    private Date obtenu;
    private Date validite;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Seance> seances = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Certificat> certificats = new ArrayList<>() ;

    public void addSeance(Seance seance) {
        seance.setFormation(this);
        this.seances.add(seance);
    }

    public void addCertificat(Certificat certificat) {
        certificat.setFormation(this);
        this.certificats.add(certificat);
    }

    public FormationDto getFormationDto(){

      FormationDto formationDto = new FormationDto();
      formationDto.setId(id);
      formationDto.setName(name);
      formationDto.setDescription(description);
      formationDto.setDuree(duree);
      formationDto.setTheme(theme);
      formationDto.setPrix(prix);
      formationDto.setFile(file);
      formationDto.setCode(code);
      formationDto.setObtenu(obtenu);
      formationDto.setValidite(validite);
      formationDto.setDomaine(domaine);

      return formationDto;
    }


}
