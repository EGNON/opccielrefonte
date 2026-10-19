package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ControlProfilProjection {
    String getIdTitre();
    String getTypeCritère();
    String getDetailCritere();
    String getPortefeuille();
    String getTauxreference();
    BigDecimal getMontant();
    BigDecimal getPourcentageActifNet();
    String getObservation();
    String getActifNet();
    BigDecimal getInf();
    BigDecimal getSup();
    Boolean getEstInf();
    Boolean getEstSup();
}
