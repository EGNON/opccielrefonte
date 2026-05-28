package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IndicationMouvementProjection {
    Long getNum();
    String getDonnees();
    BigDecimal getSoldeDebut();
    BigDecimal getAugmentation();
    BigDecimal getDiminution();
    BigDecimal getSoldeFin();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
}
