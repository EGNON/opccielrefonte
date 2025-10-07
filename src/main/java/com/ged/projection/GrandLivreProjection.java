package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface GrandLivreProjection {

    LocalDateTime getDateOp();
    String getJournal();
    String getReference();
    String getNumCompteComptable();
    String getLibelleCompteComptable();
    String getCodeAnalytique();
    BigDecimal getDebit();
    BigDecimal getCredit();
    BigDecimal getSolde();
    String getSens();
    String getSoldeEtSens();
    String getLibelle();
    BigDecimal getNumClassement();
    Long getIdOpcvm();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
}
