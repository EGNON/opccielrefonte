package com.ged.projection;

import java.math.BigDecimal;

public interface EtatFinancierAnnuelF2ResultatProjection {

    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    String getPays();

    Integer getAnneeN();

    BigDecimal getRevPortefeuilleTitreN();

    BigDecimal getDividendeN();

    BigDecimal getRevObligationAssimN();

    BigDecimal getRevAutresValeursN();

    BigDecimal getRevPlacementMonetN();

    BigDecimal getTotRevPlacementMonetaireN();

    BigDecimal getChargGesPlacN();

    BigDecimal getRevNetPlacementN();

    BigDecimal getAutreProduitN();

    BigDecimal getAutreChargeN();

    BigDecimal getResExploitationN();

    BigDecimal getRegulResExpN();

    BigDecimal getSomDistExoN();

    BigDecimal getRegulResExploiAnnulationN();

    BigDecimal getVarPluOuMoinsValueN();

    BigDecimal getPluOuMoinsValueRealN();

    BigDecimal getFraisNegocN();

    BigDecimal getResNetExoN();


    BigDecimal getRevenuOpcvmN();

    BigDecimal getProduitSurImmoN();

    BigDecimal getServiceExtN();

    BigDecimal getImpotEtTaxeN();

    BigDecimal getChargePersonnelN();

    BigDecimal getDotationAmortplusValueN();

    BigDecimal getDotationFraisGestionBudgetN();

    BigDecimal getCompteChargeN();

    BigDecimal getCompteProduitN();





    Integer getAnneeA();
    BigDecimal getRevPortefeuilleTitreA();

    BigDecimal getDividendeA();

    BigDecimal getRevObligationAssimA();

    BigDecimal getRevAutresValeursA();

    BigDecimal getRevPlacementMonetA();

    BigDecimal getTotRevPlacementMonetaireA();

    BigDecimal getChargGesPlacA();

    BigDecimal getRevNetPlacementA();

    BigDecimal getAutreProduitA();

    BigDecimal getAutreChargeA();

    BigDecimal getResExploitationA();

    BigDecimal getRegulResExpA();

    BigDecimal getSomDistExoA();

    BigDecimal getRegulResExploiAnnulationA();

    BigDecimal getVarPluOuMoinsValueA();

    BigDecimal getPluOuMoinsValueRealA();

    BigDecimal getFraisNegocA();

    BigDecimal getResNetExoA();


    BigDecimal getRevenuOpcvmA();

    BigDecimal getProduitSurImmoA();

    BigDecimal getServiceExtA();

    BigDecimal getImpotEtTaxeA();

    BigDecimal getChargePersonnelA();

    BigDecimal getDotationAmortplusValueA();

    BigDecimal getDotationFraisGestionBudgetA();

    BigDecimal getCompteChargeA();

    BigDecimal getCompteProduitA();
}
