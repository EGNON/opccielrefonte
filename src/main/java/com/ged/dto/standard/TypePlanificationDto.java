package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypePlanificationDto {

    private Long idTypePlanification;

    private String libelleTypePlanification;
    public TypePlanificationDto() {
    }

    public TypePlanificationDto(String libelleTypePlanification) {
        this.libelleTypePlanification = libelleTypePlanification;
    }

    public Long getIdTypePlanification() {
        return idTypePlanification;
    }

    public void setIdTypePlanification(Long idTypePlanification) {
        this.idTypePlanification = idTypePlanification;
    }

    public String getLibelleTypePlanification() {
        return libelleTypePlanification;
    }

    public void setLibelleTypePlanification(String libelleTypePlanification) {
        this.libelleTypePlanification = libelleTypePlanification;
    }
}
