package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ValeurInventaireProjection {
    Long getNum();
    Long getAnnee1();
    BigDecimal getActif1();
    BigDecimal getPart1();
    Long getAnnee2();
    BigDecimal getActif2();
    BigDecimal getPart2();
    Long getAnnee3();
    BigDecimal getActif3();
    BigDecimal getPart3();
}
