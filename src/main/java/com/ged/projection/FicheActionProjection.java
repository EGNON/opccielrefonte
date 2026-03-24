package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FicheActionProjection {
    Long getNumEcheance ();
    LocalDateTime getDateEcheance ();
    BigDecimal getNbreTitre ();
    BigDecimal getCapital ();
    BigDecimal getInteret();
    BigDecimal getNbreTitreAmorti();
    BigDecimal getMontantRembourse();
    BigDecimal getAnnuiteTotale();
    BigDecimal getMontantFin();
    String getSymbolTitre();
    String getDesignationTitre();
    String getIsin();
    BigDecimal getNominal();
    Long getIdTitre();
}
