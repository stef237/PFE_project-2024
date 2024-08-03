package com.stephane.formationmanagment.entities;

import com.stephane.formationmanagment.dto.SeanceDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "seance")
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date obtenu;
    private Date validite;
    private Boolean status;

    @ManyToOne
    private Formation formation;

    public SeanceDto getSeanceDto() {
        SeanceDto seanceDto = new SeanceDto();
        seanceDto.setId(id);
        seanceDto.setName(name);
        seanceDto.setDescription(description);
        seanceDto.setObtenu(obtenu);
        seanceDto.setValidite(validite);
        seanceDto.setStatus(String.valueOf(status));

        return seanceDto;
    }






}
