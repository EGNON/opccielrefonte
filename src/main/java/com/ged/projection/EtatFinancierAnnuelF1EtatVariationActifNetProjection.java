package com.ged.projection;

import java.math.BigDecimal;

public interface EtatFinancierAnnuelF1EtatVariationActifNetProjection {

    Long getIdOpcvm();
    String getSigleOpcvm ();
    String getDenominationOpcvm();
    String getPays();

    Integer getAnneeA();

    BigDecimal getVariationActifNetOpExA();
    BigDecimal getResExploitationA();
    BigDecimal getVarPluOuMoinsValueA();
    BigDecimal getPluOuMoinsValueRealA();
    BigDecimal getFraisNegocA();
    BigDecimal getDistributiondividendeA();
    BigDecimal getTransactionSurCapitalA();
    BigDecimal getSouscriptionA();
    BigDecimal getCapitalSousA();
    BigDecimal getRegulSommeNonDistribuableExoSousA();
    BigDecimal getRegulSommeDistribuableSousA();
    BigDecimal getDroitEntreSousA();
    BigDecimal getRachatA();
    BigDecimal getCapitalRachA();
    BigDecimal getRegulSommeNonDistribuableExoRachA();
    BigDecimal getRegulSommeDistribuableRachA();
    BigDecimal getDroitSortieRachA();
    BigDecimal getTauxRendementA();
    BigDecimal getVariationActifNetA();
    BigDecimal getActifNetA();
    BigDecimal getEnDebutExoActifNetA();
    BigDecimal getEnFinExoActifNetA();
    BigDecimal getNombrePartA();
    BigDecimal getEnDebutExoPartA();
    BigDecimal getEnFinExoPartA();
    BigDecimal getValeurLiquidativeA();



    Integer getAnneeN();
    BigDecimal getVariationActifNetOpExN();
    BigDecimal getResExploitationN();
    BigDecimal getVarPluOuMoinsValueN();
    BigDecimal getPluOuMoinsValueRealN();
    BigDecimal getFraisNegocN();
    BigDecimal getDistributiondividendeN();
    BigDecimal getTransactionSurCapitalN();
    BigDecimal getSouscriptionN();
    BigDecimal getCapitalSousN();
    BigDecimal getRegulSommeNonDistribuableExoSousN();
    BigDecimal getRegulSommeDistribuableSousN();
    BigDecimal getDroitEntreSousN();
    BigDecimal getRachatN();
    BigDecimal getCapitalRachN();
    BigDecimal getRegulSommeNonDistribuableExoRachN();
    BigDecimal getRegulSommeDistribuableRachN();
    BigDecimal getDroitSortieRachN();
    BigDecimal getTauxRendementN();
    BigDecimal getvariationActifNetN();
    BigDecimal getActifNetN();
    BigDecimal getEnDebutExoActifNetN();
    BigDecimal getEnFinExoActifNetN();
    BigDecimal getNombrePartN();
    BigDecimal getEnDebutExoPartN();
    BigDecimal getEnFinExoPartN();
    BigDecimal getValeurLiquidativeN();
}
