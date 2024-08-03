package com.stephane.formationmanagment.dto;


import lombok.Data;
import java.util.Date;


@Data
public class SeanceDto {
    private Long id;
    private String name;
    private String description;
    private Date obtenu;
    private Date validite;
    private String status;


}
