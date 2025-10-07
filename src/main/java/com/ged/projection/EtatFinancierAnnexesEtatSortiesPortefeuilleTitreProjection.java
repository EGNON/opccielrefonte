package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierAnnexesEtatSortiesPortefeuilleTitreProjection {

    String getClasseTitre();
    BigDecimal getCout();
    BigDecimal getPrixCession();
    BigDecimal getPlusOuMoinValue();
    Long getIdOpcvm();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
}
