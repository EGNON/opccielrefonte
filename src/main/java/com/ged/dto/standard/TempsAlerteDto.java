package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.entity.standard.CleTempsAlerte;

import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TempsAlerteDto {
    private CleTempsAlerte idTempsAlerte;
    private int frequence;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private TempsDto temps;
    private AlerteDto alerte;
    public TempsAlerteDto() {
    }

    public TempsAlerteDto(int frequence, LocalTime heureDebut, LocalTime heureFin) {
        this.frequence = frequence;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public CleTempsAlerte getIdTempsAlerte() {
        return idTempsAlerte;
    }

    public void setIdTempsAlerte(CleTempsAlerte idTempsAlerte) {
        this.idTempsAlerte = idTempsAlerte;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public TempsDto getTemps() {
        return temps;
    }

    public void setTemps(TempsDto temps) {
        this.temps = temps;
    }

    public AlerteDto getAlerte() {
        return alerte;
    }

    public void setAlerte(AlerteDto alerte) {
        this.alerte = alerte;
    }

    @Override
    public String toString() {
        return "TempsAlerteDto{" +
                "idTempsAlerte=" + idTempsAlerte +
                ", frequence=" + frequence +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", temps=" + temps +
                ", alerte=" + alerte +
                '}';
    }
}
