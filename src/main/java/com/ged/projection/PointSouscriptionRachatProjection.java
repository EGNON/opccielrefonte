package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointSouscriptionRachatProjection {
    Long getIdOpcvm();
    String getDesignationOpcvm();
    BigDecimal getPp();
    BigDecimal getPm ();
    BigDecimal getPartpp();
    BigDecimal getPartpm();
    BigDecimal getCom();
    String getType();
    LocalDateTime getDateDeb();
    LocalDateTime getDateFin();

}
