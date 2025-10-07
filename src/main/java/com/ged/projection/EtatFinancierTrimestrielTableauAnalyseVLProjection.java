package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierTrimestrielTableauAnalyseVLProjection {

    String getDesignation();
    BigDecimal getMontantExoN();
	BigDecimal getMontantExoN2();
    Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
