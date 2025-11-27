package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DeclarationCommissionActifProjection {

    Long getAnneeExo();
    BigDecimal getTaux();
    Long getUsance();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
    String getSgo();
    String getAgrementSGO();
    String getOpc();
    String getAgremenrOPC();
    String getDepositaire();
    String getAgrementDEPOSITAIRE();
    String getTrimestre();
    LocalDateTime getDateEstimation();
    BigDecimal getActions();
    BigDecimal getObligations();
    BigDecimal getPartOPC();
    BigDecimal getAutres();
    BigDecimal getTotalHorsPartOPC();
    BigDecimal getTotalGeneral();
    BigDecimal getCommission();
    Long getIdOpcvm();
    Long getNum();
}
