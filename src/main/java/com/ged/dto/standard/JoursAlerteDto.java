package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.entity.standard.CleJoursAlerte;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoursAlerteDto {

    private CleJoursAlerte idJoursAlerte;

    private JoursDto jours;

    private AlerteDto alerte;
    private Boolean etat = false;

    public JoursAlerteDto() {
    }

    public CleJoursAlerte getIdJoursAlerte() {
        return idJoursAlerte;
    }

    public void setIdJoursAlerte(CleJoursAlerte idJoursAlerte) {
        this.idJoursAlerte = idJoursAlerte;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public JoursDto getJours() {
        return jours;
    }

    public void setJours(JoursDto jours) {
        this.jours = jours;
    }

    public AlerteDto getAlerte() {
        return alerte;
    }

    public void setAlerte(AlerteDto alerte) {
        this.alerte = alerte;
    }

    @Override
    public String toString() {
        return "JoursAlerteDto{" +
                "idJoursAlerte=" + idJoursAlerte +
                ", jours=" + jours +
                ", alerte=" + alerte +
                ", etat=" + etat +
                '}';
    }
}
