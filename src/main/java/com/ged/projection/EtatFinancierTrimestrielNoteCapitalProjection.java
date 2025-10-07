package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierTrimestrielNoteCapitalProjection {

    BigDecimal getMontant1();
    BigDecimal getNombreTitres1();
    BigDecimal getNombreActionnaire1();
    BigDecimal getMontant2();
    BigDecimal getNombreTitres2();
    BigDecimal getNombreActionnaire2();
    BigDecimal getMontant3();
    BigDecimal getNombreTitres3();
    BigDecimal getNombreActionnaire3();
    BigDecimal getMontant4();
    BigDecimal getNombreTitres4();
    BigDecimal getNombreActionnaire4();
    Long getIdOpcvm();
    LocalDateTime dateEstimation();
}
