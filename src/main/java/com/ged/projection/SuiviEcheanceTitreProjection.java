package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SuiviEcheanceTitreProjection {

    Long getIdTitre();
    String getSymbole();
    String getDesignationTitre();
    Long getMaturite();
    Long getNbreJoursCourus();
    BigDecimal getDureeRestant();
    Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
