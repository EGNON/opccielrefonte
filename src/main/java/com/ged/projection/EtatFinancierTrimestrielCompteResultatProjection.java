package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierTrimestrielCompteResultatProjection {

    Long getIdOpcvm();
    String getSigleOpcvm();
    LocalDateTime getDateEstimation();
    String getDenominationOpcvm();
    Integer getAnnee();
    String getPays();

    LocalDateTime getDateMois1();
    BigDecimal getRevPortefeuilleTitre1();
    BigDecimal getDividende1();
    BigDecimal getRevObligationAssim1();
    BigDecimal getRevAutresValeurs1();
    BigDecimal getRevPlacementMonet1();
    BigDecimal getTotRevPlacementMonetaire1();
    BigDecimal getChargGesPlac1();
    BigDecimal getRevNetPlacement1();
    BigDecimal getAutreProduit1();
    BigDecimal getAutreCharge1();
    BigDecimal getResExploitation1();
    BigDecimal getRegulResExp1();
    BigDecimal getSomDistExo1();
    BigDecimal getRegulResExploiAnnulation1();
    BigDecimal getVarPluOuMoinsValue1();
    BigDecimal getPluOuMoinsValueReal1();
    BigDecimal getFraisNegoc1();
    BigDecimal getResNetExo1();

    BigDecimal getRevenuOpcvm1();
    BigDecimal getProduitSurImmo1();
    BigDecimal getServiceExt1();
    BigDecimal getImpotEtTaxe1();
    BigDecimal getChargePersonnel1();
    BigDecimal getDotationAmortplusValue1();
    BigDecimal getDotationFraisGestionBudget1();
    BigDecimal getCompteCharge1();
    BigDecimal getCompteProduit1();
    BigDecimal getProduitHAO1();
    BigDecimal getChargeHAO1();
    BigDecimal getRegulExoEnCours1();


    LocalDateTime getDateMois2();
    BigDecimal getRevPortefeuilleTitre2();
    BigDecimal getDividende2();
    BigDecimal getRevObligationAssim2();
    BigDecimal getRevAutresValeurs2();
    BigDecimal getRevPlacementMonet2();
    BigDecimal getTotRevPlacementMonetaire2();
    BigDecimal getChargGesPlac2();
    BigDecimal getRevNetPlacement2();
    BigDecimal getAutreProduit2();
    BigDecimal getAutreCharge2();
    BigDecimal getResExploitation2();
    BigDecimal getRegulResExp2();
    BigDecimal getSomDistExo2();
    BigDecimal getRegulResExploiAnnulation2();
    BigDecimal getVarPluOuMoinsValue2();
    BigDecimal getPluOuMoinsValueReal2();
    BigDecimal getFraisNegoc2();
    BigDecimal getResNetExo2();

    BigDecimal getRevenuOpcvm2();
    BigDecimal getProduitSurImmo2();
    BigDecimal getServiceExt2();
    BigDecimal getImpotEtTaxe2();
    BigDecimal getChargePersonnel2();
    BigDecimal getDotationAmortplusValue2();
    BigDecimal getDotationFraisGestionBudget2();
    BigDecimal getCompteCharge2();
    BigDecimal getCompteProduit2();
    BigDecimal getProduitHAO2();
    BigDecimal getChargeHAO2();
    BigDecimal getRegulExoEnCours2();


    LocalDateTime getDateMois3();
    BigDecimal getRevPortefeuilleTitre3();
    BigDecimal getDividende3();
    BigDecimal getRevObligationAssim3();
    BigDecimal getRevAutresValeurs3();
    BigDecimal getRevPlacementMonet3();
    BigDecimal getTotRevPlacementMonetaire3();
    BigDecimal getChargGesPlac3();
    BigDecimal getRevNetPlacement3();
    BigDecimal getAutreProduit3();
    BigDecimal getAutreCharge3();
    BigDecimal getResExploitation3();
    BigDecimal getRegulResExp3();
    BigDecimal getSomDistExo3();
    BigDecimal getRegulResExploiAnnulation3();
    BigDecimal getVarPluOuMoinsValue3();
    BigDecimal getPluOuMoinsValueReal3();
    BigDecimal getFraisNegoc3();
    BigDecimal getResNetExo3();

    BigDecimal getRevenuOpcvm3();
    BigDecimal getProduitSurImmo3();
    BigDecimal getServiceExt3();
    BigDecimal getImpotEtTaxe3();
    BigDecimal getChargePersonnel3();
    BigDecimal getDotationAmortplusValue3();
    BigDecimal getDotationFraisGestionBudget3();
    BigDecimal getCompteCharge3();
    BigDecimal getCompteProduit3();
    BigDecimal getProduitHAO3();
    BigDecimal getChargeHAO3();
    BigDecimal getRegulExoEnCours3();
}
