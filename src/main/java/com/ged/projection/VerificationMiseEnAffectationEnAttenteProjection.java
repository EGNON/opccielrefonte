package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface VerificationMiseEnAffectationEnAttenteProjection {
    Long getIdMiseEnAffectation();
    Long getIdOpcvm ();
    Long getIdSeance ();
    LocalDateTime getDateMiseEnAffectation();
    String getCodeExercice ();
    BigDecimal getResultat();
    BigDecimal getRegBeneInstAffectation();
    BigDecimal getBeneInstAffectation();
    BigDecimal getNbrePartEnCirculation ();
    BigDecimal getCoupDivUnitaire ();
}
