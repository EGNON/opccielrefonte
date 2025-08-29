package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProcedureComptableProjection {
    String getCodeTypeOperation();
    String getLibelleTypeOperation ();
    String getCodeNatureOperation();
    String getLibelleNatureOperation();
    String getCodeTypeTitre();
    String getLibelleTypeTitre();
    String getCodeModeleEcriture();
    String getLibelleModeleEcriture();
    String getDebit();
    String getCredit();
    String getLibelleCompteComptable();
    String getLibelleFormule();
}
