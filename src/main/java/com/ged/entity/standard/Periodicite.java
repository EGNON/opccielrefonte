package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.crm.DetailObjectif;
import com.ged.entity.crm.ObjectifAffecte;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Periodicite", schema = "Parametre")
public class Periodicite extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeriodicite;
    @Basic
    private String libelle;
    @OneToMany(mappedBy = "periodicite")
    //@JsonManagedReference(value = "periodicite")
    private Set<DetailObjectif> detailObjectifs = new HashSet<>();
    @OneToMany(mappedBy = "periodicite")
    //@JsonManagedReference(value = "periodicite_Affecte")
    private Set<ObjectifAffecte> objectifAffectes;
    public Periodicite() {
    }

    public Periodicite(String libelle) {
        this.libelle = libelle;
    }

    public Set<ObjectifAffecte> getObjectifAffectes() {
        return objectifAffectes;
    }

    public void setObjectifAffectes(Set<ObjectifAffecte> objectifAffectes) {
        this.objectifAffectes = objectifAffectes;
    }

    public Long getIdPeriodicite() {
        return idPeriodicite;
    }

    public void setIdPeriodicite(Long idPeriodicite) {
        this.idPeriodicite = idPeriodicite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<DetailObjectif> getDetailObjectifs() {
        return detailObjectifs;
    }

    public void setDetailObjectifs(Set<DetailObjectif> detailObjectifs) {
        this.detailObjectifs = detailObjectifs;
    }

    @Override
    public String toString() {
        return "Periodicite [" +
                "idPeriodicite=" + idPeriodicite +
                ", libelle='" + libelle + '\'' +
                ']';
    }
}
