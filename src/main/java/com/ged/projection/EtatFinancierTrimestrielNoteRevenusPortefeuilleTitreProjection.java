package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierTrimestrielNoteRevenusPortefeuilleTitreProjection {

    Long getIdOpcvm();
    String getsigleOpcvm();
    String getdenominationOpcvm();
    String getpays();
    LocalDateTime getDateEstimation();

    LocalDateTime getDateMois1();
    BigDecimal getDividende1();
    BigDecimal getRevenusActionCote1();
    BigDecimal getRevenusActionNonCote1();
    BigDecimal getRevenusOpcvm1();
    BigDecimal getRevenusObligation1();
    BigDecimal getInterets1();
    BigDecimal getPrimesRemboursement1();
    BigDecimal getRevenusTCsurMF1();
    BigDecimal getTotal1();


    LocalDateTime getDateMois2();
    BigDecimal getDividende2();
    BigDecimal getRevenusActionCote2();
    BigDecimal getRevenusActionNonCote2();
    BigDecimal getRevenusOpcvm2();
    BigDecimal getRevenusObligation2();
    BigDecimal getInterets2();
    BigDecimal getPrimesRemboursement2();
    BigDecimal getRevenusTCsurMF2();
    BigDecimal getTotal2();
}
