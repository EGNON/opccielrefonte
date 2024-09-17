package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "TJ_NbreJoursAlerte",schema = "Notification")
public class NbreJoursAlerte extends Base {
    @EmbeddedId
    private CleNbreJoursAlerte idNbreJoursAlerte;
    @ManyToOne
    @MapsId("idNbreJours")
    @JoinColumn(name = "idNbreJours")
    private NbreJours nbreJours;
    @ManyToOne
    @MapsId("idAlerte")
    @JoinColumn(name = "idAlerte")
    //@JsonBackReference
    private Alerte alerte;
    private Time heureJoursAlerte;

    public NbreJoursAlerte() {
    }

    public NbreJoursAlerte(CleNbreJoursAlerte idNbreJoursAlerte, Time heureJoursAlerte) {
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

    public NbreJours getNbreJours() {
        return nbreJours;
    }

    public void setNbreJours(NbreJours nbreJours) {
        this.nbreJours = nbreJours;
    }

    public Alerte getAlerte() {
        return alerte;
    }

    public void setAlerte(Alerte alerte) {
        this.alerte = alerte;
    }
}
