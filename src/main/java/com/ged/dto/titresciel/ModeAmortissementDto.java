package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeAmortissementDto {

    private Long idModeAmortissement;
    private String libelleModeAmortissement;

    public Long getIdModeAmortissement() {
        return idModeAmortissement;
    }

    public void setIdModeAmortissement(Long idModeAmortissement) {
        this.idModeAmortissement = idModeAmortissement;
    }

    public String getLibelleModeAmortissement() {
        return libelleModeAmortissement;
    }

    public void setLibelleModeAmortissement(String libelleModeAmortissement) {
        this.libelleModeAmortissement = libelleModeAmortissement;
    }
}
