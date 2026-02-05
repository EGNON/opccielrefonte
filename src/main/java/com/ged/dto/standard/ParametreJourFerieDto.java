package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParametreJourFerieDto extends Base {
    private Long numLigne;
    private String code;
    private LocalDateTime date;
	private String description;
	private String userLogin;
    private Boolean estAnnuel;

    public ParametreJourFerieDto() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEstAnnuel() {
        return estAnnuel;
    }

    public void setEstAnnuel(Boolean estAnnuel) {
        this.estAnnuel = estAnnuel;
    }

    //FIN
}
