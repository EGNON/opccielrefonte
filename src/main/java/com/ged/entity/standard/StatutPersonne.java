package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_StatutPersonne",schema = "Parametre")
public class StatutPersonne extends Base {
    @EmbeddedId
    private CleStatutPersonne idStatutPersonne;
    @ManyToOne
    @JoinColumn(name = "idPersonne")
    @MapsId("idPersonne")
    //@JsonBackReference
    private Personne personne;
    @ManyToOne
    @JoinColumn(name = "idQualite")
    @MapsId("idQualite")
    private Qualite qualite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonnel", referencedColumnName = "idPersonne")
    //@JsonBackReference
    private Personnel personnel;

    public StatutPersonne() {
    }

    public StatutPersonne(Personne personne, Qualite qualite) {
        this.personne = personne;
        this.qualite = qualite;
    }

    public StatutPersonne(Personne personne, Qualite qualite, Personnel personnel) {
        this.personne = personne;
        this.qualite = qualite;
        this.personnel = personnel;
    }

    public CleStatutPersonne getIdStatutPersonne() {
        return idStatutPersonne;
    }

    public void setIdStatutPersonne(CleStatutPersonne idStatutPersonne) {
        this.idStatutPersonne = idStatutPersonne;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Qualite getQualite() {
        return qualite;
    }

    public void setQualite(Qualite qualite) {
        this.qualite = qualite;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public String toString() {
        return "StatutPersonne{" +
                "idStatutPersonne=" + idStatutPersonne +
                ", personne=" + personne +
                ", qualite=" + qualite +
                ", personnel=" + personnel +
                '}';
    }
}
