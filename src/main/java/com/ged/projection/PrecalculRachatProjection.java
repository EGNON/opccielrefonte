package com.ged.projection;

import java.math.BigDecimal;

public interface PrecalculRachatProjection {
    Long getIdSeance();
    String getIdActionnaire();
    Long getIdOpcvm();
    String getIdPersonne();
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
    String getNomSigle();
    String getPrenomRaison();
    String getNumCompteSgi();
}
