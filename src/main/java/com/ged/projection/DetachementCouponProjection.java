package com.ged.projection;

import java.math.BigDecimal;

public interface DetachementCouponProjection {
    Long getIdDetachement();
    Long getIdDecision();
    Long getIdOpcvm();
    BigDecimal getMontantDistribue();
    BigDecimal getNombrePartEnCirculaion();
    BigDecimal getCouponUnitaire();
    BigDecimal getTotalDistribue();
    BigDecimal getReste();
    BigDecimal getRegBeneficeEnInstanceAffectation ();
    BigDecimal getTotalADistribuer();
    Long getIdSeance();
    Boolean getEstPaye();
    String typeArrondi ();
}
