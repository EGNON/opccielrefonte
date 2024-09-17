package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDetachementDroitDto extends OperationDto {
    private Double qteAction;
    private Double qteDroit;

    public OperationDetachementDroitDto() {
    }

    public Double getQteAction() {
        return qteAction;
    }

    public void setQteAction(Double qteAction) {
        this.qteAction = qteAction;
    }

    public Double getQteDroit() {
        return qteDroit;
    }

    public void setQteDroit(Double qteDroit) {
        this.qteDroit = qteDroit;
    }
}
