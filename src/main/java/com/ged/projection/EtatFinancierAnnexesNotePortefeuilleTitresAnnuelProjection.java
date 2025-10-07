package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierAnnexesNotePortefeuilleTitresAnnuelProjection {

    Long getIdTitre();
    String getGroupe1();
    String getGroup2();
    String getLibelleTypeTitre();
    String getDesignationTitre();
    Long getNombreTitres();
    BigDecimal getCoutAcquisition();
    BigDecimal getValeur();
    BigDecimal getActifNet();
    Long getIdOpcvm();
    LocalDateTime dateEstimation();
}
