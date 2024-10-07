package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.PlanDto;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciceDto {
    private String codeExercice;
    private OpcvmDto opcvm;
	private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private PlanDto plan;
    private boolean estCourant;
    private boolean estFerme;
    private boolean estBloque;
    private LocalDateTime dateCloture;
    private Float tauxBenefice;
    private Double montantMinimum;
    private String declassement;

    public ExerciceDto() {
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
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


    public String getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(String codeExercice) {
        this.codeExercice = codeExercice;
    }

    public PlanDto getPlan() {
        return plan;
    }

    public void setPlan(PlanDto plan) {
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
