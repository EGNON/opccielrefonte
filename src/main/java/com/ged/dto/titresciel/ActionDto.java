package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionDto extends TitreDto {
    private BigDecimal per;
    private TypeActionDto typeAction;
    private SousTypeActionDto sousTypeAction;
    private BigDecimal nominalNonVerse;

    public BigDecimal getPer() {
        return per;
    }

    public void setPer(BigDecimal per) {
        this.per = per;
    }

    public BigDecimal getNominalNonVerse() {
        return nominalNonVerse;
    }

    public void setNominalNonVerse(BigDecimal nominalNonVerse) {
        this.nominalNonVerse = nominalNonVerse;
    }

    public TypeActionDto getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(TypeActionDto typeAction) {
        this.typeAction = typeAction;
    }

    public SousTypeActionDto getSousTypeAction() {
        return sousTypeAction;
    }

    public void setSousTypeAction(SousTypeActionDto sousTypeAction) {
        this.sousTypeAction = sousTypeAction;
    }
}
