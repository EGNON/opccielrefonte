package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface JournalProjection2 {

    Long getIdOpcvm();
    String getDenominationOpcvm();
    String getCodeJournal();
    String getLibelleJournal();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
    LocalDateTime getDateValeur();
    Long getIdOperation();
    String getReferenceOper();
    String getCodePlan();
    String getNumeroCompteComptable();
    String getCodeAnalytique();
    String getLibelle();
    String getLibop();
    BigDecimal getDebit();
    BigDecimal getCredit();
}
