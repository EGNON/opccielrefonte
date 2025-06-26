package com.ged.projection;

import com.ged.entity.opcciel.comptabilite.Ib;
import com.ged.entity.opcciel.comptabilite.Plan;

import java.math.BigDecimal;

public interface DetailModeleProjection {
    String getNumCompteComptable() ;
    String getLibelleCompteComptable() ;
    String getSensMvt() ;
    String getLibelleFormule() ;
    String getType() ;
    Long getIdFormule() ;
    BigDecimal getDebit() ;
    BigDecimal getCredit() ;
}
