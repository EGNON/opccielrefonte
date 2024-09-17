package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Journal", schema = "Comptabilite")
public class Journal extends Base {
    @Id
    private String codeJournal;
    private String libelleJournal;
    @ManyToOne()
    @JoinColumn(name = "codePlan")
    private Plan plan;
    private String numCompteComptable;

    public Journal() {
    }

    public String getCodeJournal() {
        return codeJournal;
    }

    public void setCodeJournal(String codeJournal) {
        this.codeJournal = codeJournal;
    }

    public String getLibelleJournal() {
        return libelleJournal;
    }

    public void setLibelleJournal(String libelleJournal) {
        this.libelleJournal = libelleJournal;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getNumCompteComptable() {
        return numCompteComptable;
    }

    public void setNumCompteComptable(String numCompteComptable) {
        this.numCompteComptable = numCompteComptable;
    }
}
