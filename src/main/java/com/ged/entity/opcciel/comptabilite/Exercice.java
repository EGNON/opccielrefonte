package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TJ_Exercice", schema = "Comptabilite")
public class Exercice extends Base {
    @EmbeddedId
    private CleExercice idExercie;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;
	private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlan")
    private Plan plan;
    private boolean estCourant;
    private boolean estFerme;
    private boolean estBloque;
    private LocalDateTime dateCloture;
    private Float tauxBenefice;
    private Double montantMinimum;
    private String declassement;

    public Exercice() {
    }

    public CleExercice getIdExercie() {
        return idExercie;
    }

    public void setIdExercie(CleExercice idExercie) {
        this.idExercie = idExercie;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public boolean isEstCourant() {
        return estCourant;
    }

    public void setEstCourant(boolean estCourant) {
        this.estCourant = estCourant;
    }

    public boolean isEstFerme() {
        return estFerme;
    }

    public void setEstFerme(boolean estFerme) {
        this.estFerme = estFerme;
    }

    public boolean isEstBloque() {
        return estBloque;
    }

    public void setEstBloque(boolean estBloque) {
        this.estBloque = estBloque;
    }

    public LocalDateTime getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDateTime dateCloture) {
        this.dateCloture = dateCloture;
    }

    public Float getTauxBenefice() {
        return tauxBenefice;
    }

    public void setTauxBenefice(Float tauxBenefice) {
        this.tauxBenefice = tauxBenefice;
    }

    public Double getMontantMinimum() {
        return montantMinimum;
    }

    public void setMontantMinimum(Double montantMinimum) {
        this.montantMinimum = montantMinimum;
    }

    public String getDeclassement() {
        return declassement;
    }

    public void setDeclassement(String declassement) {
        this.declassement = declassement;
    }
}
