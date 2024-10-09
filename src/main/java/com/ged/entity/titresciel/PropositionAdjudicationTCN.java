package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_PropositionAdjudicationTCN", schema = "Adjudication")
public class PropositionAdjudicationTCN extends Base {
    @EmbeddedId
    private ClePropositionAdjudicationTCN idPropositionAdjudicationTCN;
    @ManyToOne
    @JoinColumn(name = "idEmetteurNew")
//    @MapsId("idEmetteur")
    private Emetteur emetteur;

	private double interetUnitaire;
    private double montantAPayerUnitaire;
    private double tauxRendementPeriodique;
    private double tauxRendementAn;
    private double quantite;
    private double interetTotal;
    private double montantAPayerTotal;

    public PropositionAdjudicationTCN() {
    }

    public Emetteur getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Emetteur emetteur) {
        this.emetteur = emetteur;
    }

    public ClePropositionAdjudicationTCN getIdPropositionAdjudicationTCN() {
        return idPropositionAdjudicationTCN;
    }

    public void setIdPropositionAdjudicationTCN(ClePropositionAdjudicationTCN idPropositionAdjudicationTCN) {
        this.idPropositionAdjudicationTCN = idPropositionAdjudicationTCN;
    }

    public double getInteretUnitaire() {
        return interetUnitaire;
    }

    public void setInteretUnitaire(double interetUnitaire) {
        this.interetUnitaire = interetUnitaire;
    }

    public double getMontantAPayerUnitaire() {
        return montantAPayerUnitaire;
    }

    public void setMontantAPayerUnitaire(double montantAPayerUnitaire) {
        this.montantAPayerUnitaire = montantAPayerUnitaire;
    }

    public double getTauxRendementPeriodique() {
        return tauxRendementPeriodique;
    }

    public void setTauxRendementPeriodique(double tauxRendementPeriodique) {
        this.tauxRendementPeriodique = tauxRendementPeriodique;
    }

    public double getTauxRendementAn() {
        return tauxRendementAn;
    }

    public void setTauxRendementAn(double tauxRendementAn) {
        this.tauxRendementAn = tauxRendementAn;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getInteretTotal() {
        return interetTotal;
    }

    public void setInteretTotal(double interetTotal) {
        this.interetTotal = interetTotal;
    }

    public double getMontantAPayerTotal() {
        return montantAPayerTotal;
    }

    public void setMontantAPayerTotal(double montantAPayerTotal) {
        this.montantAPayerTotal = montantAPayerTotal;
    }
}
