package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointInvestissementProjection {

    Long getIdOperation();
    String getCodeNatureOperation();
    String getDesignationTitre();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
    BigDecimal getQuantiteLimite();
    BigDecimal getCoursLimite();
    BigDecimal getMontantBrut();
    BigDecimal getCommissionPlace();
    BigDecimal getCommissionDepositaire();
    BigDecimal getCommissionSGI();
    BigDecimal getTaf();
    BigDecimal getIrvm();
    BigDecimal getInteret();
    BigDecimal getPlusOuMoinsValue();
    BigDecimal getMontant();
    String getDenominationOpcvm();
    LocalDateTime getDateDeb();
    LocalDateTime getDateFin();
    Long getIdOpcvm();
    String getTypeOp();
}
