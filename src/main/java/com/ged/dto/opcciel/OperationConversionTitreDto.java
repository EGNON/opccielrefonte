package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationConversionTitreDto extends OperationDto {
    private Double nombreDroit;
    private Double qteAttribue;
    private Double nombreRompu;

    public OperationConversionTitreDto() {
    }

    public Double getNombreDroit() {
        return nombreDroit;
    }

    public void setNombreDroit(Double nombreDroit) {
        this.nombreDroit = nombreDroit;
    }

    public Double getQteAttribue() {
        return qteAttribue;
    }

    public void setQteAttribue(Double qteAttribue) {
        this.qteAttribue = qteAttribue;
    }

    public Double getNombreRompu() {
        return nombreRompu;
    }

    public void setNombreRompu(Double nombreRompu) {
        this.nombreRompu = nombreRompu;
    }
}
