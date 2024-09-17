package com.ged.entity.crm;

import com.ged.entity.Base;
import com.ged.entity.standard.CategoriePersonne;
import com.ged.entity.standard.Periodicite;
import jakarta.persistence.*;

@Entity
@Table(name = "T_ObjectifReel",schema = "Objectif")
public class ObjectifReel extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObjReel;
    private float valeurReelleAtteinte = 0;
    @ManyToOne
    @JoinColumn(name = "idAffectation")
    private Affectation affectation;
    @ManyToOne
    @JoinColumn(name = "idIndicateur")
    private Indicateur indicateur;
    @ManyToOne
    @JoinColumn(name = "idPeriodicite")
    private Periodicite periodicite;
    @ManyToOne
    @JoinColumn(name = "idCategorie")
    private CategoriePersonne categoriePersonne;

    public ObjectifReel(){

    }

    public ObjectifReel(float valeurReelleAtteinte) {
        this.valeurReelleAtteinte = valeurReelleAtteinte;
    }

    public long getIdObjReel() {
        return idObjReel;
    }

    public void setIdObjReel(long idObjReel) {
        this.idObjReel = idObjReel;
    }

    public float getValeurReelleAtteinte() {
        return valeurReelleAtteinte;
    }

    public void setValeurReelleAtteinte(float valeurReelleAtteinte) {
        this.valeurReelleAtteinte = valeurReelleAtteinte;
    }

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    public Periodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
    }

    public CategoriePersonne getCategoriePersonne() {
        return categoriePersonne;
    }

    public void setCategoriePersonne(CategoriePersonne categoriePersonne) {
        this.categoriePersonne = categoriePersonne;
    }

    public Affectation getAffectation() {
        return affectation;
    }

    public void setAffectation(Affectation affectation) {
        this.affectation = affectation;
    }
}
