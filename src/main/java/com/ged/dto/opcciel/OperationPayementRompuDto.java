package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationPayementRompuDto extends OperationDto {
    private Double nombreRompu;
    private Double coutDeSession;
    private Double montantSession;

    public OperationPayementRompuDto() {
    }

    public Double getNombreRompu() {
        return nombreRompu;
    }

    public void setNombreRompu(Double nombreRompu) {
        this.nombreRompu = nombreRompu;
    }

    public Double getCoutDeSession() {
        return coutDeSession;
    }

    public void setCoutDeSession(Double coutDeSession) {
        this.coutDeSession = coutDeSession;
    }

    public Double getMontantSession() {
        return montantSession;
    }

    public void setMontantSession(Double montantSession) {
        this.montantSession = montantSession;
    }
}
