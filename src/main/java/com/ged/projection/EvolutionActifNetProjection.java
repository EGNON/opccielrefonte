package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EvolutionActifNetProjection {
    String getOpcvm ();
    BigDecimal getActif_net1 ();
    BigDecimal getPart1 ();
    BigDecimal getActif_net2();
    BigDecimal getPart2();
    BigDecimal getActif_net3();
    BigDecimal getPart3();
    LocalDateTime getDate1();
    LocalDateTime getDate2 ();
    LocalDateTime getDate3();
    LocalDateTime getDateEstimation();
}
