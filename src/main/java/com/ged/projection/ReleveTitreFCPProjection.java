package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ReleveTitreFCPProjection {
    Long getIdTitre();
    String getSymboleTitre();
    String getDesignationTitre();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
    BigDecimal getValeurUnitaire();
    BigDecimal getDebit();
    BigDecimal getCredit();
    BigDecimal getSolde();
    BigDecimal getSoldeFin();
}
