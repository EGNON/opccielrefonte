package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoursDto {
    private long idJours;
    private String libelleJours;
    public JoursDto() {
    }

    public JoursDto(String libelleJours) {
        this.libelleJours = libelleJours;
    }

    public long getIdJours() {
        return idJours;
    }

    public void setIdJours(long idJours) {
        this.idJours = idJours;
    }

    public String getLibelleJours() {
        return libelleJours;
    }

    public void setLibelleJours(String libelleJours) {
        this.libelleJours = libelleJours;
    }

    @Override
    public String toString() {
        return "JoursDto{" +
                "idJours=" + idJours +
                ", libelleJours='" + libelleJours + '\'' +
                '}';
    }
}
