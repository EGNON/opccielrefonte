package com.ged.entity.crm;

import com.ged.entity.Base;
import com.ged.entity.standard.CategoriePersonne;
import com.ged.entity.standard.Periodicite;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_ObjectifAffecte",schema = "Objectif")
public class ObjectifAffecte extends Base {
    @EmbeddedId
    CleObjectifAffecte idObjectifAffecte = new CleObjectifAffecte();
    private Float valeurAffecte;
    @Column(nullable = true)
    private Float valeurReelle;
    @ManyToOne
    @MapsId("idAffectation")
    @JoinColumn(name = "idAffectation")
    //@JsonBackReference
    private Affectation affectation;
    @ManyToOne
    @MapsId("idIndicateur")
    @JoinColumn(name = "idIndicateur")
    //@JsonBackReference
    private Indicateur indicateur;
    @ManyToOne
    @MapsId("idPeriodicite")
    @JoinColumn(name = "idPeriodicite")
    //@JsonBackReference
    private Periodicite periodicite;
    @ManyToOne
    @MapsId("idCategorie")
    @JoinColumn(name = "idCategorie")
    //@JsonBackReference
    private CategoriePersonne categoriePersonne;

    public ObjectifAffecte(){

    }

    public ObjectifAffecte(CleObjectifAffecte objectifAffecte, float valeur) {
        this.idObjectifAffecte = objectifAffecte;
        this.valeurAffecte = valeur;
    }

    public CleObjectifAffecte getIdObjectifAffecte() {
        return idObjectifAffecte;
    }

    public void setIdObjectifAffecte(CleObjectifAffecte idObjectifAffecte) {
        this.idObjectifAffecte = idObjectifAffecte;
    }

    public Float getValeurReelle() {
        return valeurReelle;
    }

    public void setValeurReelle(Float valeurReelle) {
        this.valeurReelle = valeurReelle;
    }

    public Float getValeurAffecte() {
        return valeurAffecte;
    }

    public void setValeurAffecte(Float valeurAffecte) {
        this.valeurAffecte = valeurAffecte;
    }

    public Affectation getAffectation() {
        return affectation;
    }

    public void setAffectation(Affectation affectation) {
        this.affectation = affectation;
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

    @Override
    public String toString() {
        return "ObjectifAffecte{" +
                ", valeurAffecte=" + valeurAffecte +
                ", affectation=" + affectation +
                ", indicateur=" + indicateur +
                ", periodicite=" + periodicite +
                ", categoriePersonne=" + categoriePersonne +
                '}';
    }
}
