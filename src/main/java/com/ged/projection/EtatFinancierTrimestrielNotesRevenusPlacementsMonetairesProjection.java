package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierTrimestrielNotesRevenusPlacementsMonetairesProjection {

    Long getIdOpcvm();
    String getsigleOpcvm();
    String getdenominationOpcvm();
    String getpays();
    LocalDateTime getDateEstimation();

    LocalDateTime getDateMois1();
    BigDecimal getRevenusBonTreso1();
    BigDecimal getRevenusBilletsTreso1();
    BigDecimal getRevenusCertifcatDepo1();
    BigDecimal getRvenusDat1();
    BigDecimal getTotal1();



    LocalDateTime getDateMois2();
    BigDecimal getRevenusBonTreso2();
    BigDecimal getRevenusBilletsTreso2();
    BigDecimal getRevenusCertifcatDepo2();
    BigDecimal getRevenusDat2();
    BigDecimal gettotal2();
}
