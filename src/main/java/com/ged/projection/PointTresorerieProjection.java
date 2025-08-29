package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointTresorerieProjection {
    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    BigDecimal getSoldeEspece();
    LocalDateTime getdateEstimation();
}
