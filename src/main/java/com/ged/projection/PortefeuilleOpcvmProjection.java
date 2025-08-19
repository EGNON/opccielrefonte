package com.ged.projection;

import java.math.BigDecimal;

public interface PortefeuilleOpcvmProjection {
    Long getIdOpcvm();
    String getIbOpcvm();
    String getTypeRubrique();
    Long getIdTitre();
    String getClasseTitre();
    String getCodeTypeTitre();
    String getTypeTitre();
    String getSymboleTitre();
    String getDesignationTitre();
    Long getNbJoursCourus();
    BigDecimal getCouponCouru();
    BigDecimal getSoldeEspece();
    BigDecimal getMontantBloque();
    BigDecimal getSoldeEspReel();
    BigDecimal getSoldeQte();
    BigDecimal getQteBloquee();
    BigDecimal getSoldeQteReel();
    BigDecimal getSoldeEspAttente();
    BigDecimal getSoldeQteContrep();
    BigDecimal getQteALivrer();
    BigDecimal getQteARecevoir();
    BigDecimal getQteNantie();
    BigDecimal getCaMreel();
    BigDecimal getCaMopcvm();
    BigDecimal getCours();
    BigDecimal getPlusOuMoinsValue();
    BigDecimal getValorisationReelle();
    BigDecimal getValorisationOpcvm();
    BigDecimal getPrixDeRevientReel();
    BigDecimal getPrixDeRevientOpcvm();
    BigDecimal getValoPortefeuilleReel();
    BigDecimal getValoPortefeuilleOpcvm();
    BigDecimal getPourcentageReel();
    BigDecimal getPourcentageOpcvm();
    BigDecimal getNominal();
}
