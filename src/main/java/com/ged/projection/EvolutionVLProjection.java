package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EvolutionVLProjection {
    Long getIdOpcvm();
    String getDenominationOpcvm();
    BigDecimal getVlOrigine();
    BigDecimal getVlmois1();
    BigDecimal getVlmois2();
    BigDecimal getRendementMois();
    BigDecimal getRendementOrigine();

}
