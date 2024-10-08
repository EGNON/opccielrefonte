package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.CleExercice;
import com.ged.entity.opcciel.comptabilite.Plan;

import java.time.LocalDateTime;

public interface ExerciceProjection {
    String getCodeExercice();

   /* Opcvm getOpcvm();

    LocalDateTime getDateDebut();

    LocalDateTime getDateFin();

    Plan getPlan();

    boolean isEstCourant();

    boolean isEstFerme();

    boolean isEstBloque();

    LocalDateTime getDateCloture();

    Float getTauxBenefice();

    Double getMontantMinimum() ;

    String getDeclassement() ;*/
}
