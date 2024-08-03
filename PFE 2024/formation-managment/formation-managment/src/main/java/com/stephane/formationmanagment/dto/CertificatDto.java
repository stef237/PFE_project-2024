package com.stephane.formationmanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class CertificatDto {
    private Long id;
    private String name;
    private String description;
    private Date obtenu;
    private Date validite;

}
