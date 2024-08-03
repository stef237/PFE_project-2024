package com.stephane.formationmanagment.dto;


import lombok.Data;

import java.sql.Date;


@Data
public class FormationDto {
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

}
