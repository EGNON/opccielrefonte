package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_CompteComptable", schema = "Comptabilite")
public class CompteComptable extends Base {
    @EmbeddedId
    private CleCompteComptable idCompteComptable;
    @ManyToOne()
    @JoinColumn(name = "codePlan", referencedColumnName = "codePlan")
    @MapsId("codePlan")
    private Plan plan;
    @Column(insertable = false,updatable = false)
    private String numCompteComptable;
	private String libelleCompteComptable;
    private Character sensMvt;
    private boolean estMvt;
    private String bilanHorsBilan;
    private String type;

    public CompteComptable() {
    }

    public CleCompteComptable getIdCompteComptable() {
        return idCompteComptable;
    }

    public void setIdCompteComptable(CleCompteComptable idCompteComptable) {
        this.idCompteComptable = idCompteComptable;
    }

    public String getNumCompteComptable() {
        return numCompteComptable;
    }

    public void setNumCompteComptable(String numCompteComptable) {
        this.numCompteComptable = numCompteComptable;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getLibelleCompteComptable() {
        return libelleCompteComptable;
    }

    public void setLibelleCompteComptable(String libelleCompteComptable) {
        this.libelleCompteComptable = libelleCompteComptable;
    }

    public Character getSensMvt() {
        return sensMvt;
    }

    public void setSensMvt(Character sensMvt) {
        this.sensMvt = sensMvt;
    }

    public boolean isEstMvt() {
        return estMvt;
    }

    public void setEstMvt(boolean estMvt) {
        this.estMvt = estMvt;
    }

    public String getBilanHorsBilan() {
        return bilanHorsBilan;
    }

    public void setBilanHorsBilan(String bilanHorsBilan) {
        this.bilanHorsBilan = bilanHorsBilan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
