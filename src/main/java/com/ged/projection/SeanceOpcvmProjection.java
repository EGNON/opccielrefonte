package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SeanceOpcvmProjection {
    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    Long getIdSeance();
    String getLibelleSeance();
    LocalDateTime getDateOuverture();
    LocalDateTime getDateFermeture();

}
