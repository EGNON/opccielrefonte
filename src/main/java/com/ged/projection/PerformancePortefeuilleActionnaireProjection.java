package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PerformancePortefeuilleActionnaireProjection {
    String getDateDebut();
    String getDateFin ();
    Long getIdActionnaire();
    String getIntitule();
    Long getIdOpcvm();
    String getDenominationOpcvm();
    String getLibelle();
    LocalDateTime getDateOp();
    BigDecimal getSouscription();
    BigDecimal getRachat();
    Long getPeriode();
    Long getNbreJours();
    BigDecimal getMontantPondere();
    BigDecimal getRevenuNet();
    BigDecimal getApportNet();
    BigDecimal getPerformance();
    BigDecimal getRendementAnnuel();
}
