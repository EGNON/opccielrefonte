package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;

public interface EtatFinancierAnnuelF1NotesRevenusPortefeuilleTitreProjection {

    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    String getPays();

    Integer getAnneeN();
    BigDecimal getdividendeN();
    BigDecimal getrevenusActionCoteN();
    BigDecimal getrevenusActionNonCoteN();
    BigDecimal getrevenusOpcvmN();
    BigDecimal getrevenusObligationN();
    BigDecimal getinteretsN();
    BigDecimal getprimesRemboursementN();
    BigDecimal getrevenusTCsurMFN();
    BigDecimal gettotalN();


    Integer getAnneeA();
    BigDecimal getdividendeA();
    BigDecimal getrevenusActionCoteA();
    BigDecimal getrevenusActionNonCoteA();
    BigDecimal getrevenusOpcvmA();
    BigDecimal getrevenusObligationA();
    BigDecimal getinteretsA();
    BigDecimal getprimesRemboursementA();
    BigDecimal getrevenusTCsurMFA();
    BigDecimal gettotalA();}
