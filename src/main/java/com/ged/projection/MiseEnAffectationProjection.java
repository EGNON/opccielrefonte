package com.ged.projection;

import com.ged.entity.opcciel.comptabilite.Plan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface MiseEnAffectationProjection {
    Long getIdMiseEnAffectation();
    Long getIdOpcvm ();
    Long getIdSeance();
    LocalDateTime getDateMiseEnAffectation();
    String getCodeExercice();
    BigDecimal getResultat();
    BigDecimal getRegBeneInstAffectation();
    BigDecimal getBeneInstAffectation();
    BigDecimal getNbrePartEnCirculation();
    BigDecimal getCoupDivUnitaire();
    String getUserLogin();
    Boolean getSupprimer();
}
