package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class NatureTcnDto {
    private Long idNatureTcn;
    private String codeNatureTcn;
    private String libelleNatureTcn;

    public Long getIdNatureTcn() {
        return idNatureTcn;
    }

    public void setIdNatureTcn(Long idNatureTcn) {
        this.idNatureTcn = idNatureTcn;
    }

    public String getCodeNatureTcn() {
        return codeNatureTcn;
    }

    public void setCodeNatureTcn(String codeNatureTcn) {
        this.codeNatureTcn = codeNatureTcn;
    }

    public String getLibelleNatureTcn() {
        return libelleNatureTcn;
    }

    public void setLibelleNatureTcn(String libelleNatureTcn) {
        this.libelleNatureTcn = libelleNatureTcn;
    }
}
