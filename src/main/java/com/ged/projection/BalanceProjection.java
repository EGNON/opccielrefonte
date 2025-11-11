package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BalanceProjection {

    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
    String getCodePlan();
    String getLibellePlan();
    String getNumCompteComptable();
    String getLibelleCompteComptable();
    String getSensMvt();
    Boolean getEstMvt();
    BigDecimal getSoldeReel();
    BigDecimal getSoldeAbsolu();
    String getSensSolde();
    BigDecimal getDebutD();
    BigDecimal getDebutC();
    BigDecimal getMvtPeriodeD();
    BigDecimal getMvtPeriodeC();
    BigDecimal getDebit();
    BigDecimal getCredit();
}
