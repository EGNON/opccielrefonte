package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.PlanDto;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompteComptableDto extends Base {
    private String numCompteComptable;
    private PlanDto plan;
	private String libelleCompteComptable;
    private Character sensMvt;
    private boolean estMvt;
    private String bilanHorsBilan;
    private String type;

    public CompteComptableDto() {
    }

    public String getNumCompteComptable() {
        return numCompteComptable;
    }

    public void setNumCompteComptable(String numCompteComptable) {
        this.numCompteComptable = numCompteComptable;
    }

    public PlanDto getPlan() {
        return plan;
    }

    public void setPlan(PlanDto plan) {
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
