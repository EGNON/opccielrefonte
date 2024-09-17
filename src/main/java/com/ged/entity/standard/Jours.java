package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Jours",schema = "Notification")
public class Jours extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJours;
    private String libelleJours;

    public Jours() {
    }

    public Jours(String libelleJours) {
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
        return "Jours{" +
                "idJours=" + idJours +
                ", libelleJours='" + libelleJours + '\'' +
                '}';
    }
}
