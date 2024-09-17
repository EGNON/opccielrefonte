package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BanqueDto {

    private Long idBanque;
    private String codeBanque;
    private String nomBanque;

    public Long getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(Long idBanque) {
        this.idBanque = idBanque;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }
}
