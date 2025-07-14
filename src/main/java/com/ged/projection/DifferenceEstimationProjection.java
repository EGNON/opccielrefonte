package com.ged.projection;

import java.math.BigDecimal;

public interface DifferenceEstimationProjection {
    Long getIdSeance();
    Long getIdTitre();
    String getSymbolTitre();
    String getDesignationTitre();
    Long getIdOpcvm();
    BigDecimal getQteDetenue();
    BigDecimal getCours();
    BigDecimal getCumpT();
    BigDecimal getCumpReel();
    BigDecimal getPlusOuMoinsValue();
    BigDecimal getNbreJourCourus();
    BigDecimal getInteretCourus();
    BigDecimal getValeurVDECours();
    BigDecimal getValeurVDEInteret();
    BigDecimal getIdOpCours();
    BigDecimal getIdOpInteret();
    BigDecimal getIrvm();

}
