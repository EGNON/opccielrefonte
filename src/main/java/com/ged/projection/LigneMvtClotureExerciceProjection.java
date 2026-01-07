package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface LigneMvtClotureExerciceProjection {
    Long getIdOpcvm();
    Long getNumeroOrdeEtape();
    Long getEtape();
    String getNumeroCompteComptable();
    String getLibelleCompteComptable();
    Long getIdFormule();
    String getLibelleFormule();
    BigDecimal getDebit();
    BigDecimal getCredit();
    String getCodeNatureOperation();
    LocalDateTime getDateCloture();
    String getCodePlan();
    String getCodeExercice();
    String getCodeModele();
    String getCodeIb();
    String getCodeRubrique();
    String getCodePosition();
    String getCodeTypeFormule();
}
