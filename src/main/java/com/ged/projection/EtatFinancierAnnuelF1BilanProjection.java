package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;

public interface EtatFinancierAnnuelF1BilanProjection {

    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    String getPays();

    Integer getAnneeN();
    BigDecimal getPortefeuilleTitreN();
    BigDecimal getActionAssimDroitN();
    BigDecimal getObligationAssimN();
    BigDecimal getAutresValeursN();
    BigDecimal getPlacementMonetDispositionN();
    BigDecimal getPlacementMonetaireN();
    BigDecimal getDisponibiliteN();
    BigDecimal getCreanceExploitationN();
    BigDecimal getAutreActifN();
    BigDecimal getTotalActifN();
    BigDecimal getOperateurCrediteurN();
    BigDecimal getAutreCrediteurDiversN();
    BigDecimal getTotalPassifN();
    BigDecimal getCapitalN();
    BigDecimal getSommeDistribuableN();
    BigDecimal getSommeDistribuableExoAntN();
    BigDecimal getSommeDistribuableExoEncN();
    BigDecimal getActifNetN();
    BigDecimal getTotalPassifActifNetN();

    BigDecimal getTitresOpcvmN();
    BigDecimal getPersonnelsDebiteursN();
    BigDecimal getAutresDebiteursN();
    BigDecimal getCompteDeRegularisationActifN();
    BigDecimal getImmoFinancieresN();
    BigDecimal getImmoCorporellesN();
    BigDecimal getAmortImmoN();
    BigDecimal getGestionnairesN();
    BigDecimal getDepositairesN();
    BigDecimal getSousAchTitAreglerN();
    BigDecimal getCourtierEtAutresInterN();
    BigDecimal getAutresOpCrediteursN();
    BigDecimal getComptesAffectPeriodChargesN();
    BigDecimal getActionnairesN();
    BigDecimal getPersonnelCrediteursN();
    BigDecimal getEtatN();
    BigDecimal getOrganismsSociauxN();
    BigDecimal getAutresCrediteursN();
    BigDecimal getCompteRegulPassifN();
    BigDecimal getCapitalSocialN();
    BigDecimal getSousRachN();
    BigDecimal getSommesNonDistribuableN();
    BigDecimal getFraisConstFusionApportN();
    BigDecimal getCommissionSousRachN();
    BigDecimal getFraisNegocTransAutresTitresN();
    BigDecimal getVariationVDEsurPortTitresN();
    BigDecimal getPlusOuMoinsValueSurTitresN();
    BigDecimal getRegulSomNonDistribExoEnCoursN();


    Integer getAnneeA();
    BigDecimal getPortefeuilleTitreA();
    BigDecimal getActionAssimDroitA();
    BigDecimal getObligationAssimA();
    BigDecimal getAutresValeursA();
    BigDecimal getPlacementMonetDispositionA();
    BigDecimal getPlacementMonetaireA();
    BigDecimal getDisponibiliteA();
    BigDecimal getCreanceExploitationA();
    BigDecimal getAutreActifA();
    BigDecimal getTotalActifA();
    BigDecimal getOperateurCrediteurA();
    BigDecimal getAutreCrediteurDiversA();
    BigDecimal getTotalPassifA();
    BigDecimal getCapitalA();
    BigDecimal getSommeDistribuableA();
    BigDecimal getSommeDistribuableExoAntA();
    BigDecimal getSommeDistribuableExoEncA();
    BigDecimal getActifNetA();
    BigDecimal getTotalPassifActifNetA();


    BigDecimal getTitresOpcvmA();
    BigDecimal getPersonnelsDebiteursA();
    BigDecimal getAutresDebiteursA();
    BigDecimal getCompteDeRegularisationActifA();
    BigDecimal getImmoFinancieresA();
    BigDecimal getImmoCorporellesA();
    BigDecimal getAmortImmoA();
    BigDecimal getGestionnairesA();
    BigDecimal getDepositairesA();
    BigDecimal getSousAchTitAreglerA();
    BigDecimal getCourtierEtAutresInterA();
    BigDecimal getAutresOpCrediteursA();
    BigDecimal getComptesAffectPeriodChargesA();
    BigDecimal getActionnairesA();
    BigDecimal getPersonnelCrediteursA();
    BigDecimal getEtatA();
    BigDecimal getOrganismsSociauxA();
    BigDecimal getAutresCrediteursA();
    BigDecimal getCompteRegulPassifA();
    BigDecimal getCapitalSocialA();
    BigDecimal getSousRachA();
    BigDecimal getSommesNonDistribuableA();
    BigDecimal getFraisConstFusionApportA();
    BigDecimal getCommissionSousRachA();
    BigDecimal getFraisNegocTransAutresTitresA();
    BigDecimal getVariationVDEsurPortTitresA();
    BigDecimal getPlusOuMoinsValueSurTitresA();
    BigDecimal getRegulSomNonDistribExoEnCoursA();}
