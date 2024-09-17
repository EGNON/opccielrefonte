package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ged.entity.Base;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "TJ_TempsAlerte",schema = "Notification")
public class TempsAlerte extends Base {
    @EmbeddedId
    private CleTempsAlerte idTempsAlerte;
    private int frequence;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime heureDebut;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime heureFin;
    @ManyToOne
    @MapsId("idTemps")
    @JoinColumn(name = "idTemps")
    private Temps temps;
    @ManyToOne
    @MapsId("idAlerte")
    @JoinColumn(name = "idAlerte")
    //@JsonBackReference
    private Alerte alerte;

    public TempsAlerte() {
    }

    public TempsAlerte(int frequence, LocalTime heureDebut, LocalTime heureFin) {
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

    public Temps getTemps() {
        return temps;
    }

    public void setTemps(Temps temps) {
        this.temps = temps;
    }

    public Alerte getAlerte() {
        return alerte;
    }

    public void setAlerte(Alerte alerte) {
        this.alerte = alerte;
    }
}
