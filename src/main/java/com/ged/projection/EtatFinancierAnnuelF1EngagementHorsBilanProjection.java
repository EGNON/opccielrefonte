package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.time.LocalDateTime;

public interface EtatFinancierAnnuelF1EngagementHorsBilanProjection {

    String getTypeTitre();
    String getDesignationTitre();
    String getQte1();
    String getQte2();
    String getDenominationOpcvm();
    String getPays();
    Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
