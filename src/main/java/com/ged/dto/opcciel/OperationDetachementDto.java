package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.standard.PersonneDto;
import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.entity.standard.Personne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDetachementDto extends OperationDto {
    private String codeNatureOperation;
    private String typeEvenement;
    private String typePayement;
    private Long idIntervenant;
    private PersonneDto intervenant;
    @Column(precision = 18, scale = 6)
    private BigDecimal qteDetenue;
    @Column(precision = 18, scale = 6)
    private BigDecimal couponDividendeUnitaire;
    private BigDecimal couponDividendeTotal;
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
    private String userLogin;
    private Boolean bNominalRemb;
    private Boolean bQtiteAMORT;
    private Boolean bMtantRemb;
    private Boolean bMtantRecevoir;
    private String libelleModeAmortissement;
    public OperationDetachementDto() {
    }

    public String getLibelleModeAmortissement() {
        return libelleModeAmortissement;
    }

    public void setLibelleModeAmortissement(String libelleModeAmortissement) {
        this.libelleModeAmortissement = libelleModeAmortissement;
    }

    public BigDecimal getCouponDividendeTotal() {
        return couponDividendeTotal;
    }

    public void setCouponDividendeTotal(BigDecimal couponDividendeTotal) {
        this.couponDividendeTotal = couponDividendeTotal;
    }

    public Boolean getbNominalRemb() {
        return bNominalRemb;
    }

    public void setbNominalRemb(Boolean bNominalRemb) {
        this.bNominalRemb = bNominalRemb;
    }

    public Boolean getbQtiteAMORT() {
        return bQtiteAMORT;
    }

    public void setbQtiteAMORT(Boolean bQtiteAMORT) {
        this.bQtiteAMORT = bQtiteAMORT;
    }

    public Boolean getbMtantRemb() {
        return bMtantRemb;
    }

    public void setbMtantRemb(Boolean bMtantRemb) {
        this.bMtantRemb = bMtantRemb;
    }

    public Boolean getbMtantRecevoir() {
        return bMtantRecevoir;
    }

    public void setbMtantRecevoir(Boolean bMtantRecevoir) {
        this.bMtantRecevoir = bMtantRecevoir;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public PersonneDto getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(PersonneDto intervenant) {
        this.intervenant = intervenant;
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
