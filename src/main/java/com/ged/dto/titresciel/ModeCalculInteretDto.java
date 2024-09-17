package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeCalculInteretDto {
    private Long idModeCalculInteret;
    private String codeModeCalculInteret;
    private String libelleModeCalculInteret;

    public ModeCalculInteretDto() {
    }

    public Long getIdModeCalculInteret() {
        return idModeCalculInteret;
    }

    public void setIdModeCalculInteret(Long idModeCalculInteret) {
        this.idModeCalculInteret = idModeCalculInteret;
    }

    public String getCodeModeCalculInteret() {
        return codeModeCalculInteret;
    }

    public void setCodeModeCalculInteret(String codeModeCalculInteret) {
        this.codeModeCalculInteret = codeModeCalculInteret;
    }

    public String getLibelleModeCalculInteret() {
        return libelleModeCalculInteret;
    }

    public void setLibelleModeCalculInteret(String libelleModeCalculInteret) {
        this.libelleModeCalculInteret = libelleModeCalculInteret;
    }
}
