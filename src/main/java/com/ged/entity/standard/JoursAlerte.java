package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_JoursAlerte",schema = "Notification")
public class JoursAlerte extends Base {
    @EmbeddedId
    private CleJoursAlerte idJoursAlerte;
    @ManyToOne
    @MapsId("idJours")
    @JoinColumn(name = "idJours")
    private Jours jours;
    @ManyToOne
    @MapsId("idAlerte")
    @JoinColumn(name = "idAlerte")
    //@JsonBackReference
    private Alerte alerte;
    @Transient
    private Boolean etat = false;

    public JoursAlerte() {
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

    public Jours getJours() {
        return jours;
    }

    public void setJours(Jours jours) {
        this.jours = jours;
    }

    public Alerte getAlerte() {
        return alerte;
    }

    public void setAlerte(Alerte alerte) {
        this.alerte = alerte;
    }

    @Override
    public String toString() {
        return "JoursAlerte{" +
                "idJoursAlerte=" + idJoursAlerte +
                ", jours=" + jours +
                ", alerte=" + alerte +
                ", etat=" + etat +
                '}';
    }
}
