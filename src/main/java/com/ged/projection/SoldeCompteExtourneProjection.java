package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SoldeCompteExtourneProjection {
    LocalDateTime getDateOp ();
    String getJournal ();
    String getReference ();
    String getNumCompteComptable  ();
    String getLibelleCompteComptable ();
    String getCodeAnalytique ();
    BigDecimal getDebit ();
    BigDecimal getCredit ();
    BigDecimal getSolde();
    String getSens ();
    String getSoldeEtSens ();
    String getLibelle();
    BigDecimal getNumClassement();
}
