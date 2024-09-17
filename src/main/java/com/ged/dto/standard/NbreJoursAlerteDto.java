package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.entity.standard.CleNbreJoursAlerte;

import java.sql.Time;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NbreJoursAlerteDto {
    private CleNbreJoursAlerte idNbreJoursAlerte;

    private NbreJoursDto nbreJours;

    private AlerteDto alerte;
    private Time heureJoursAlerte;

    public NbreJoursAlerteDto() {
    }

    public NbreJoursAlerteDto(CleNbreJoursAlerte idNbreJoursAlerte, Time heureJoursAlerte) {
        this.idNbreJoursAlerte = idNbreJoursAlerte;
        this.heureJoursAlerte = heureJoursAlerte;
    }

    public CleNbreJoursAlerte getIdNbreJoursAlerte() {
        return idNbreJoursAlerte;
    }

    public void setIdNbreJoursAlerte(CleNbreJoursAlerte idNbreJoursAlerte) {
        this.idNbreJoursAlerte = idNbreJoursAlerte;
    }

    public Time getHeureJoursAlerte() {
        return heureJoursAlerte;
    }

    public void setHeureJoursAlerte(Time heureJoursAlerte) {
        this.heureJoursAlerte = heureJoursAlerte;
    }

    public NbreJoursDto getNbreJours() {
        return nbreJours;
    }

    public void setNbreJours(NbreJoursDto nbreJours) {
        this.nbreJours = nbreJours;
    }

    public AlerteDto getAlerte() {
        return alerte;
    }

    public void setAlerte(AlerteDto alerte) {
        this.alerte = alerte;
    }

    @Override
    public String toString() {
        return "NbreJoursAlerteDto{" +
                "idNbreJoursAlerte=" + idNbreJoursAlerte +
                ", nbreJours=" + nbreJours +
                ", alerte=" + alerte +
                ", heureJoursAlerte=" + heureJoursAlerte +
                '}';
    }
}
