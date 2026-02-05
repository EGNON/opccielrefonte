package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DecisionDistributionProjection {
     Long getIdDecision();
     Long  getIdMiseEnAffectation();
     Long getIdOpcvm() ;
     Long getIdOperation() ;
     LocalDateTime getDateDecision();
     LocalDateTime getDateDetachement();
     BigDecimal getResultat();
     BigDecimal getaDistribueEnpourcentage();
     BigDecimal getMontantDistribue();
     BigDecimal getMontantNonDistribue();
     String getCodeExercice();
     BigDecimal getNbrePartEnCirculation();
     BigDecimal getCoupUnitaireADistribuer() ;
     Boolean getEstApplique();
     Long getIdSeance();
     String getStatutMontNonDistribue();
}
