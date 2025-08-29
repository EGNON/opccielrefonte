package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatsSuiviClientProjection {
    LocalDateTime getDateEddition();
    Long getIdOpcvm();
    String getDenominationOpcvm();
    Long getIdActionnaire();
    String getPersonne();
    LocalDateTime getDateOperation();
    BigDecimal getMontant();
    BigDecimal getPart();
    BigDecimal getValeurLiquidative();
    BigDecimal getPartACtuel();
    BigDecimal getCump();
    BigDecimal getPrixDeRevient();
    BigDecimal getCoursVL();
    BigDecimal getPrixSouscription();
    BigDecimal getValorisation();
    BigDecimal getPlusOuMoinsValue();
    String getTitre();
    String getBeneficiaireDonateur();
}
