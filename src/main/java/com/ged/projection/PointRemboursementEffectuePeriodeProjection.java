package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointRemboursementEffectuePeriodeProjection {

    Long getIdOperation();
    Long getIdTransaction();
    Long getIdSeance();
    String getCodeNatureOperation();
    LocalDateTime getDateOperation();
    LocalDateTime getDateSaisie();
    LocalDateTime getDateValeur();
    LocalDateTime getDatePiece();
    String getReferencePiece();
    BigDecimal getMontant();
    String getEcriture();
    String getLibelleOperation();
    Boolean getEstOD();
    String getType();
    String getTypeEvenement();
    String getTypePayement();
    Long getIdIntervenant();
    String getDenomination();
    Long getIdTitre();
    String getSymboleTitre();
    String getDesignationTitre();
    BigDecimal getQteDetenue();
    BigDecimal getCouponDividendeUnitaire();
    BigDecimal getMontantBrut();
    BigDecimal getCommission();
    BigDecimal getIrvm();
    BigDecimal getQuantiteAmortie();
    BigDecimal getNominalRemb();
    BigDecimal getCapitalRembourse();
    BigDecimal getInteretMoratoireSurCapital();
    BigDecimal getInteretMoratoireSurInteret();
    BigDecimal getCommissionSurInteretMoratoire();
    BigDecimal getMontantTotalARecevoir();
    Long getIdOpcvm();
    Long getIdDetachement();
    String getReferenceAvis();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
}
