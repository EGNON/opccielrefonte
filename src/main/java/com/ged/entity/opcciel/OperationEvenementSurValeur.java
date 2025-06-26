package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "idAvis",referencedColumnName = "idOperation")
@Table(name = "T_OperationEvenementSurValeur", schema = "EvenementSurValeur")
public class OperationEvenementSurValeur extends Operation {

    private String codeNatureOperation;
    private String typeEvenement;
    private String typePayement;
    private Long idIntervenant;
    @ManyToOne
    @JoinColumn(name = "idIntervenant_New",referencedColumnName = "idPersonne")
    private Personne intervenant;

    @Column(precision = 18, scale = 6)
    private BigDecimal qteDetenue;
    @Column(precision = 18, scale = 6)
    private BigDecimal couponDividendeUnitaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal montantBrut;
    @Column(precision = 18, scale = 6)
    private BigDecimal quantiteAmortie;
    @Column(precision = 18, scale = 6)
    private BigDecimal nominalRemb;
    @Column(precision = 18, scale = 6)
    private BigDecimal capitalRembourse;
    @Column(precision = 18, scale = 6)
    private BigDecimal montantTotalARecevoir;
    private Long idOpcvm;
    private Boolean estPaye;
    private LocalDateTime dateReelle;
    private BigDecimal commission;
    private BigDecimal irvm;
    private BigDecimal interetMoratoireSurCapital;
    private BigDecimal interetMoratoireSurInteret;
    private BigDecimal commissionSurInteretMoratoire;
    @ManyToOne
    @JoinColumn(name = "idDetachement",referencedColumnName = "idOperation")
    private OperationDetachement operationDetachement;
	private String referenceAvis;

//    private Boolean supprimer;
    public OperationEvenementSurValeur() {
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getIrvm() {
        return irvm;
    }

    public void setIrvm(BigDecimal irvm) {
        this.irvm = irvm;
    }

    public BigDecimal getInteretMoratoireSurCapital() {
        return interetMoratoireSurCapital;
    }

    public void setInteretMoratoireSurCapital(BigDecimal interetMoratoireSurCapital) {
        this.interetMoratoireSurCapital = interetMoratoireSurCapital;
    }

    public BigDecimal getInteretMoratoireSurInteret() {
        return interetMoratoireSurInteret;
    }

    public void setInteretMoratoireSurInteret(BigDecimal interetMoratoireSurInteret) {
        this.interetMoratoireSurInteret = interetMoratoireSurInteret;
    }

    public BigDecimal getCommissionSurInteretMoratoire() {
        return commissionSurInteretMoratoire;
    }

    public void setCommissionSurInteretMoratoire(BigDecimal commissionSurInteretMoratoire) {
        this.commissionSurInteretMoratoire = commissionSurInteretMoratoire;
    }

    public OperationDetachement getOperationDetachement() {
        return operationDetachement;
    }

    public void setOperationDetachement(OperationDetachement operationDetachement) {
        this.operationDetachement = operationDetachement;
    }

    public String getReferenceAvis() {
        return referenceAvis;
    }

    public void setReferenceAvis(String referenceAvis) {
        this.referenceAvis = referenceAvis;
    }

    public Personne getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Personne intervenant) {
        this.intervenant = intervenant;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    public String getTypePayement() {
        return typePayement;
    }

    public void setTypePayement(String typePayement) {
        this.typePayement = typePayement;
    }

    public Long getIdIntervenant() {
        return idIntervenant;
    }

    public void setIdIntervenant(Long idIntervenant) {
        this.idIntervenant = idIntervenant;
    }

    public BigDecimal getQteDetenue() {
        return qteDetenue;
    }

    public void setQteDetenue(BigDecimal qteDetenue) {
        this.qteDetenue = qteDetenue;
    }

    public BigDecimal getCouponDividendeUnitaire() {
        return couponDividendeUnitaire;
    }

    public void setCouponDividendeUnitaire(BigDecimal couponDividendeUnitaire) {
        this.couponDividendeUnitaire = couponDividendeUnitaire;
    }

    public BigDecimal getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(BigDecimal montantBrut) {
        this.montantBrut = montantBrut;
    }

    public BigDecimal getQuantiteAmortie() {
        return quantiteAmortie;
    }

    public void setQuantiteAmortie(BigDecimal quantiteAmortie) {
        this.quantiteAmortie = quantiteAmortie;
    }

    public BigDecimal getNominalRemb() {
        return nominalRemb;
    }

    public void setNominalRemb(BigDecimal nominalRemb) {
        this.nominalRemb = nominalRemb;
    }

    public BigDecimal getCapitalRembourse() {
        return capitalRembourse;
    }

    public void setCapitalRembourse(BigDecimal capitalRembourse) {
        this.capitalRembourse = capitalRembourse;
    }

    public BigDecimal getMontantTotalARecevoir() {
        return montantTotalARecevoir;
    }

    public void setMontantTotalARecevoir(BigDecimal montantTotalARecevoir) {
        this.montantTotalARecevoir = montantTotalARecevoir;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Boolean getEstPaye() {
        return estPaye;
    }

    public void setEstPaye(Boolean estPaye) {
        this.estPaye = estPaye;
    }

    public LocalDateTime getDateReelle() {
        return dateReelle;
    }

    public void setDateReelle(LocalDateTime dateReelle) {
        this.dateReelle = dateReelle;
    }
}
