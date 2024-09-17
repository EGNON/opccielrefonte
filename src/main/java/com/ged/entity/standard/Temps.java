package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Temps",schema = "Notification")
public class Temps extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemps;
    private String libelle;

    public Temps() {
    }

    public Temps(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdTemps() {
        return idTemps;
    }

    public void setIdTemps(Long idTemps) {
        this.idTemps = idTemps;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
