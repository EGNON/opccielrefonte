package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierAnnexesActionAdmiseCoteProjection {

    Long getIdTitre();
    String getSymboleTitre();
    String getDesignationTitre();
    BigDecimal getCoursBoursier();
    BigDecimal getValeurBaseEvaluation();
    Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
