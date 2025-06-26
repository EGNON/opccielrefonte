package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonneMoraleDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EcritureManuelDto {
    private Boolean actionnaire;
    private Boolean banque;

    public EcritureManuelDto() {
    }

    public Boolean getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(Boolean actionnaire) {
        this.actionnaire = actionnaire;
    }

    public Boolean getBanque() {
        return banque;
    }

    public void setBanque(Boolean banque) {
        this.banque = banque;
    }

//FIN
}
