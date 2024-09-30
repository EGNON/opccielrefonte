package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TJ_Correspondance", schema = "Comptabilite")
public class Correspondance extends Base {
    @EmbeddedId
    private CleCorrespondance idCorrespondance;
    @Column(precision = 18, scale = 6)
    private BigDecimal totalBlocage;
    @Column(precision = 18, scale = 6)
    private BigDecimal valeur;
    @Column(insertable = false,updatable = false)
    private String codeRubrique;
    @Column(insertable = false,updatable = false)
    private String codePosition;
    @ManyToOne()
    @JoinColumn(name = "codePlan")
    @MapsId("codePlan")
    private Plan plan;
    @ManyToOne()
    @JoinColumn(name = "codeIB")
    @MapsId("codeIB")
    private Ib ib;
    @Column(insertable = false,updatable = false)
    //@MapsId("numeroCompteComptable")
    private String numeroCompteComptable;

    public Correspondance() {
    }

    public CleCorrespondance getIdCorrespondance() {
        return idCorrespondance;
    }

    public void setIdCorrespondance(CleCorrespondance idCorrespondance) {
        this.idCorrespondance = idCorrespondance;
    }

    public BigDecimal getTotalBlocage() {
        return totalBlocage;
    }

    public void setTotalBlocage(BigDecimal totalBlocage) {
        this.totalBlocage = totalBlocage;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Ib getIb() {
        return ib;
    }

    public void setIb(Ib ib) {
        this.ib = ib;
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

    public String getNumeroCompteComptable() {
        return numeroCompteComptable;
    }

    public void setNumeroCompteComptable(String numeroCompteComptable) {
        this.numeroCompteComptable = numeroCompteComptable;
    }
}
