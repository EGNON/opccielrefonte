package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_AdjudicationTcn", schema = "Adjudication")
public class AdjudicationTcn extends Base {
    @EmbeddedId
    private CleAdjudicationTcn idAdjudicationTcn;
    @ManyToOne
    @JoinColumn(name = "idEmetteurNew")
//    @MapsId("idEmetteurNew")
    private Personne emetteur;
    private LocalDateTime dateValeur;
    private LocalDateTime dateLimite;
    private LocalDateTime dateEcheance;
    private double valeurNominalUnitaire;
    private double valeurGlobale;
    private int duree;
    private String uniteDuree;
    private String denominationEmission;
    private Long idDepositaire;
    @ManyToOne
    @JoinColumn(name = "idDepositaireNew")
    private Personne depositaire;

    public AdjudicationTcn() {
    }

    public CleAdjudicationTcn getIdAdjudicationTcn() {
        return idAdjudicationTcn;
    }

    public void setIdAdjudicationTcn(CleAdjudicationTcn idAdjudicationTcn) {
        this.idAdjudicationTcn = idAdjudicationTcn;
    }

    public LocalDateTime getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(LocalDateTime dateValeur) {
        this.dateValeur = dateValeur;
    }

    public LocalDateTime getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDateTime dateLimite) {
        this.dateLimite = dateLimite;
    }

    public LocalDateTime getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDateTime dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public double getValeurNominalUnitaire() {
        return valeurNominalUnitaire;
    }

    public void setValeurNominalUnitaire(double valeurNominalUnitaire) {
        this.valeurNominalUnitaire = valeurNominalUnitaire;
    }

    public double getValeurGlobale() {
        return valeurGlobale;
    }

    public void setValeurGlobale(double valeurGlobale) {
        this.valeurGlobale = valeurGlobale;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getUniteDuree() {
        return uniteDuree;
    }

    public void setUniteDuree(String uniteDuree) {
        this.uniteDuree = uniteDuree;
    }

    public String getDenominationEmission() {
        return denominationEmission;
    }

    public void setDenominationEmission(String denominationEmission) {
        this.denominationEmission = denominationEmission;
    }

    public Long getIdDepositaire() {
        return idDepositaire;
    }

    public void setIdDepositaire(Long idDepositaire) {
        this.idDepositaire = idDepositaire;
    }

    public Personne getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Personne emetteur) {
        this.emetteur = emetteur;
    }

    public Personne getDepositaire() {
        return depositaire;
    }

    public void setDepositaire(Personne depositaire) {
        this.depositaire = depositaire;
    }
}
