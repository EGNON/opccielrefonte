package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DegreDto {
    private Long idDegre;
    private String libelle;

    public Long getIdDegre() {
        return idDegre;
    }

    public void setIdDegre(Long idDegre) {
        this.idDegre = idDegre;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
