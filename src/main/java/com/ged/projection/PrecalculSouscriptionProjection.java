package com.ged.projection;

import java.math.BigDecimal;

public interface PrecalculSouscriptionProjection {
    Long getIdActionnaire();
    Long getIdOpcvm();
    Long getIdPersonne();
    BigDecimal getMontantDepose();
    BigDecimal getQtePart();
    BigDecimal getMontantSousRachatALiquider();
    BigDecimal getCommission();
    BigDecimal getTafCommission();
    BigDecimal getCommissionRetrocession();
    BigDecimal getTafCommissionRetrocession();
    BigDecimal getCommissionRetrocedee();
    BigDecimal getSousRachatPart();
    BigDecimal getRegulResultatExoEnCours();
    BigDecimal getRegulSommeNonDistribuable();
    BigDecimal getRegulReportANouveau();
    BigDecimal getRegulautreResultatBeneficiaire();
    BigDecimal getRegulautreResultatDeficitaire();
    BigDecimal getRegulResultatEnInstanceBeneficiaire();
    BigDecimal getRegulResultatEnInstanceDeficitaire();
    BigDecimal getRegulExoDistribution();
    BigDecimal getReste();
    String getNomSigle();
    String getPrenomRaison();
    String getNumCompteSgi();
}
