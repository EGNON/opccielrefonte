package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_ModeEtablissement", schema = "Parametre")
public class ModeEtablissement extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModeEtablissement;
    @Basic
    private String libelle;

    public ModeEtablissement() {
    }

    public ModeEtablissement(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdModeEtablissement() {
        return idModeEtablissement;
    }

    public void setIdModeEtablissement(Long idModeEtablissement) {
        this.idModeEtablissement = idModeEtablissement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
