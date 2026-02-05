package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PhaseDetachementProjection {
    BigDecimal getMontantDistribue();
    BigDecimal getNombrePartEnCirculaion();
    BigDecimal getCouponUnitaire();
    Long getIdActionnaire();
    String getNumCompteSgi();
    String getActionnaire();
    BigDecimal getRegul();
    BigDecimal getMontantARecevoir();
    BigDecimal getNombrePart();
}
