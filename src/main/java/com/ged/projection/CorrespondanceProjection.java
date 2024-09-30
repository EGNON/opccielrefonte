package com.ged.projection;

import com.ged.entity.opcciel.comptabilite.Ib;
import com.ged.entity.opcciel.comptabilite.Plan;

public interface CorrespondanceProjection {
    String getNumeroCompteComptable() ;
    String getLibelleCompteComptable() ;
    Double getTotalBlocage();
    Double getValeur();
    Plan getPlan();
    Ib getIb();
    String getCodeRubrique();
    String getCodePosition() ;
    String getLibellePosition() ;
}
