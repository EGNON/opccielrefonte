package com.ged.projection;

import java.math.BigDecimal;

public interface AfficherDetailsEcritureProjection {
    Long getIdOperation();
    String getCodeModele();
    Integer getNumeroOrdreModele();
    Integer getNumeroOrdreLigneMvt();
    String getCodeIb();
    String getCodeRubrique();
    String getCodePosition();
    String getNumeroCompte();
    String getLibelleCompteComptable();
    BigDecimal getDebit();
    BigDecimal getCredit();
    String getTypeValeur();
}
