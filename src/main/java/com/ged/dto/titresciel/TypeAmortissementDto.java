package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeAmortissementDto {
    private Long idTypeAmortissement;
    private String codeTypeAmortissement;
    private String libelleAmortissement;

    public Long getIdTypeAmortissement() {
        return idTypeAmortissement;
    }

    public void setIdTypeAmortissement(Long idTypeAmortissement) {
        this.idTypeAmortissement = idTypeAmortissement;
    }

    public String getCodeTypeAmortissement() {
        return codeTypeAmortissement;
    }

    public void setCodeTypeAmortissement(String codeTypeAmortissement) {
        this.codeTypeAmortissement = codeTypeAmortissement;
    }

    public String getLibelleAmortissement() {
        return libelleAmortissement;
    }

    public void setLibelleAmortissement(String libelleAmortissement) {
        this.libelleAmortissement = libelleAmortissement;
    }
}
