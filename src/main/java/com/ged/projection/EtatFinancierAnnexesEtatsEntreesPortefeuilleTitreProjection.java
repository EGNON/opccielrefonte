package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreProjection {

    String getClasseTitre();
    BigDecimal getCout();
    String getEntete1();
    String getEntete2();
    Long getIdOpcvm();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
}
