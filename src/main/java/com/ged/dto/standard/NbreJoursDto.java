package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NbreJoursDto {

    private long idNbreJours;

    public NbreJoursDto() {
    }

    public NbreJoursDto(long id) {
        this.idNbreJours = id;
    }

    public long getIdNbreJours() {
        return idNbreJours;
    }

    public void setIdNbreJours(long idNbreJours) {
        this.idNbreJours = idNbreJours;
    }

    @Override
    public String toString() {
        return "NbreJoursDto{" +
                "idNbreJours=" + idNbreJours +
                '}';
    }
}
