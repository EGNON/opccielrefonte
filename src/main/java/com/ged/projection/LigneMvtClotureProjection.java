package com.ged.projection;

import java.math.BigDecimal;

public interface LigneMvtClotureProjection {
    Long getIdOpcvm();
    Long getIdActionnaire();
    String getCodeNatureOperation();
    String getLibelleNatureOperation();
    String getCodeModeleEcriture();
    String getLibelleModeleEcriture();
    int getNumeroOrdreModele();
    int getNumeroOrdreLigneMvt();
    String getCodeIb();
    String getCodeRubrique();
    String getCodePosition();
    String getLibellePosition();
    String getNumCompteComptable();
    String getLibelleCompteComptable();
    String getCodePlan();
    String getSensMvt();
    String getIdFormule();
    BigDecimal getValeur();
    String getCodeTypeFormule();
}
