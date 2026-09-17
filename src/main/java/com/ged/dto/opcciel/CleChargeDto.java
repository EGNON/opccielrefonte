package com.ged.dto.opcciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleChargeDto {
    private Long idOpcvm;
    private String codeCharge;



    public CleChargeDto() {
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }
}
