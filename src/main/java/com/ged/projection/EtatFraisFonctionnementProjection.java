package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFraisFonctionnementProjection {
    Long getIdOpcvm();
    String getDenominationOpcvm();
    BigDecimal getProvisionDCBR();
    BigDecimal getReelDCBR();
    BigDecimal getEcartDCBR();
    BigDecimal getProvisionGestionnaire();
    BigDecimal getReelGestionnaire();
    BigDecimal getEcartGestionnaire();
    BigDecimal getProvisionCREPMF();
    BigDecimal getReelCREPMF();
    BigDecimal getEcartCREPMF();
    BigDecimal getProvisionComSurActif();
    BigDecimal getReelComSurActif();
    BigDecimal getEcartComSurActif();
    BigDecimal getProvisionCAC();
    BigDecimal getReelCAC();
    BigDecimal getEcartCAC();
    LocalDateTime getDateDeb();
    LocalDateTime getDateFin();
}
