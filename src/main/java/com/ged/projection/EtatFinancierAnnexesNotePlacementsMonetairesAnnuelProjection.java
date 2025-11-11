package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierAnnexesNotePlacementsMonetairesAnnuelProjection {

    String getDesignationTitre();
    Long getNombre();
    BigDecimal getCoutAcquisition();
    BigDecimal getValeur();
    BigDecimal getActifNet();
    BigDecimal getActifNetPourCent();
	Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
