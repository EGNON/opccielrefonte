package com.ged.entity.crm;

import com.ged.entity.Base;
import com.ged.entity.standard.CategoriePersonne;
import com.ged.entity.standard.Periodicite;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_DetailObjectif", schema = "Objectif")
public class DetailObjectif extends Base {
    @EmbeddedId
    private CleDetailObjectif idDetail;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCategorie")
    @JoinColumn(name = "idCategorie")
    //@JsonBackReference
    private CategoriePersonne categoriePersonne;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPeriodicite")
    @JoinColumn(name = "idPeriodicite")
    //@JsonBackReference
    private Periodicite periodicite;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idIndicateur")
    @JoinColumn(name = "idIndicateur")
    //@JsonBackReference
    private Indicateur indicateur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idModelObj")
    //@JsonBackReference
    private ModeleObjectif modelObj;

    public DetailObjectif() {
    }

    public DetailObjectif(CleDetailObjectif idDetail, CategoriePersonne categoriePersonne, Periodicite periodicite, Indicateur indicateur) {
        this.idDetail = idDetail;
        this.categoriePersonne = categoriePersonne;
        this.periodicite = periodicite;
        this.indicateur = indicateur;
    }

    public CleDetailObjectif getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(CleDetailObjectif idDetail) {
        this.idDetail = idDetail;
    }

    public CategoriePersonne getCategoriePersonne() {
        return categoriePersonne;
    }

    public void setCategoriePersonne(CategoriePersonne categoriePersonne) {
        this.categoriePersonne = categoriePersonne;
    }

    public Periodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
    }

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    public ModeleObjectif getModelObj() {
        return modelObj;
    }

    public void setModelObj(ModeleObjectif modelObj) {
        this.modelObj = modelObj;
    }

    @Override
    public String toString() {
        return "DetailObjectif{" +
                "categoriePersonne=" + categoriePersonne +
                ", periodicite=" + periodicite +
                ", indicateur=" + indicateur +
                ", modelObj=" + modelObj +
                '}';
    }
}
