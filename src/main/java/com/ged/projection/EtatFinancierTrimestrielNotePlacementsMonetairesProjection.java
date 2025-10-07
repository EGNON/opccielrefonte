package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierTrimestrielNotePlacementsMonetairesProjection {

    String getDesignationTitre();
    Long getNombre();
    BigDecimal getCoutAcquisition();
    BigDecimal getValeur();
    BigDecimal getActifNet();
    BigDecimal getActifnetpourcent();
    Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
