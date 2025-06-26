package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "idDetachement",referencedColumnName = "idOperation")
@Table(name = "T_OperationDetachement", schema = "EvenementSurValeur")
public class OperationDetachement extends Operation {
    private String codeNatureOperation;
    private String typeEvenement;
    private String typePayement;
    private Long idIntervenant;
    @ManyToOne
    @JoinColumn(name = "idIntervenant_New",referencedColumnName = "idPersonne")
    private Personne intervenant;
//    @ManyToOne
//    @JoinColumn(name = "idTitre")
//    @Column(insertable = false,updatable = false)
//    private Titre titre;
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

//    private Boolean supprimer;
    public OperationDetachement() {
    }

//    public Boolean getSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

//    @Override
//    public Titre getTitre() {
//        return titre;
//    }
//
//    @Override
//    public void setTitre(Titre titre) {
//        this.titre = titre;
//    }

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
