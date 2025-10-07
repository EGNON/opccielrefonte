package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ReleveDePartActionnaireProjection {

    LocalDateTime getDateOperation();
    String getLibelleOperation();
    BigDecimal getValeurUnitaire();
    BigDecimal getDebit();
    BigDecimal getCredit();
    BigDecimal getSolde();
    Long getIdOpcvm();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
}
