package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.PlanDto;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorrespondanceDto extends Base {
    private String numeroCompteComptable;
    private String libelleCompteComptable;
    private String codeRubrique;
    private String codePosition;
    private String libellePosition;
    private Double totalBlocage;
    private Double valeur;
    private PlanDto plan;
    private IbDto ib;

    public CorrespondanceDto() {
    }

    public Double getTotalBlocage() {
        return totalBlocage;
    }

    public void setTotalBlocage(Double totalBlocage) {
        this.totalBlocage = totalBlocage;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public PlanDto getPlan() {
        return plan;
    }

    public void setPlan(PlanDto plan) {
        this.plan = plan;
    }

    public IbDto getIb() {
        return ib;
    }

    public void setIb(IbDto ib) {
        this.ib = ib;
    }

    public String getNumeroCompteComptable() {
        return numeroCompteComptable;
    }

    public void setNumeroCompteComptable(String numeroCompteComptable) {
        this.numeroCompteComptable = numeroCompteComptable;
    }

    public String getCodeRubrique() {
        return codeRubrique;
    }

    public void setCodeRubrique(String codeRubrique) {
        this.codeRubrique = codeRubrique;
    }

    public String getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(String codePosition) {
        this.codePosition = codePosition;
    }

    public String getLibelleCompteComptable() {
        return libelleCompteComptable;
    }

    public void setLibelleCompteComptable(String libelleCompteComptable) {
        this.libelleCompteComptable = libelleCompteComptable;
    }

    public String getLibellePosition() {
        return libellePosition;
    }

    public void setLibellePosition(String libellePosition) {
        this.libellePosition = libellePosition;
    }
}
