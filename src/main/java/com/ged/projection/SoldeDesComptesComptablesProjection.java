package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SoldeDesComptesComptablesProjection {

    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    LocalDateTime getDateEstimation();
    String getCodePlan();
    String getLibellePlan();
    String getNumCompteComptable();
    String getLibelleCompteComptable();
    String getSensMvt();
    Boolean getEstMvt();
    BigDecimal getSoldeReel();
    BigDecimal getSoldeAbsolu();
    String getSensSolde();
    BigDecimal getDebit();
    BigDecimal getCredit();
}
