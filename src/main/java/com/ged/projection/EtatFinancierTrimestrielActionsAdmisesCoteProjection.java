package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierTrimestrielActionsAdmisesCoteProjection {

    Long getIdTitre();
    String getSymboleTitre();
    String getDesignationTitre();
    BigDecimal getCoursBoursier();
    BigDecimal getValeurBaseEvaluation();
    Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
