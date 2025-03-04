package com.ged.projection;

import java.math.BigDecimal;

public interface SoldeToutCompteProjection {
    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    String getDateEstimation();
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
