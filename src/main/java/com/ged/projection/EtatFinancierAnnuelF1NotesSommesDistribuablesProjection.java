package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;

public interface EtatFinancierAnnuelF1NotesSommesDistribuablesProjection {

    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    String getPays();

    Integer getAnneeN();
    BigDecimal getresulatDistribN();
    BigDecimal getregulResultatDistribN();
    BigDecimal getTotalN();



    Integer getAnneeA();
    BigDecimal getresulatDistribA();
    BigDecimal getregulResultatDistribA();
    BigDecimal getTotalA();
}
