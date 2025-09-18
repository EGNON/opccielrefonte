package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointRepartitionPortefeuilleProjection {
    Long getIdOpcvm ();
    String getDenominationOpcvm();
    BigDecimal getActionsMontant();
    BigDecimal getActionsPC();
    BigDecimal getObligationCoteMontant();
    BigDecimal getObligationCotePC();
    BigDecimal getObligationNcoteMontant();
    BigDecimal getObligationNcotePC();
    BigDecimal getBotMontant();
    BigDecimal getBotPC();
    BigDecimal getSoldeEspece();
    BigDecimal getSoldeEspecePC();
    LocalDateTime getDateEstimation ();
}
