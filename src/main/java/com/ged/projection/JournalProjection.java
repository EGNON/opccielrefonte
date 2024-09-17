package com.ged.projection;

import com.ged.entity.opcciel.comptabilite.Plan;

public interface JournalProjection {
    String getCodeJournal();
    String getLibelleJournal();
    Plan getPlan();
    String getNumCompteComptable() ;

}
