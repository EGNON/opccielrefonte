package com.ged.entity.crm;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ged.entity.Base;
import com.ged.entity.standard.Personnel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "T_Affectation",schema = "Objectif")
public class Affectation extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAffectation;
    private LocalDateTime dateAffectation;
    private LocalDateTime dateSoumission;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonne")
    //@JsonBackReference
    private Personnel personnel;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "affectation")
    @JsonIgnore
    private Set<ObjectifReel> objectifReels;
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "affectation")
//    @JoinColumn(name = "idAffectation", referencedColumnName = "idAffectation")
    //@JsonManagedReference
    private Set<ObjectifAffecte> objectifAffectes;

    public Long getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Long idAffectation) {
        this.idAffectation = idAffectation;
    }

    public LocalDateTime getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(LocalDateTime dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public LocalDateTime getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(LocalDateTime dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Set<ObjectifReel> getObjectifReels() {
        return objectifReels;
    }

    public void setObjectifReels(Set<ObjectifReel> objectifReels) {
        this.objectifReels = objectifReels;
    }

    public Set<ObjectifAffecte> getObjectifAffectes() {
        return objectifAffectes;
    }

    public void setObjectifAffectes(Set<ObjectifAffecte> objectifAffectes) {
        this.objectifAffectes = objectifAffectes;
    }

    @Override
    public String toString() {
        return "Affectation{" +
                "idAffectation=" + idAffectation +
                ", dateAffectation=" + dateAffectation +
                ", personnel=" + personnel +
//                ", objectifReels=" + objectifReels +
//                ", objectifAffectes=" + objectifAffectes +
                '}';
    }
}
