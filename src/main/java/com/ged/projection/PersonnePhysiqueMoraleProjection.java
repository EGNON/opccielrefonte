package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PersonnePhysiqueMoraleProjection {
    Long getIdPersonne();
    String getNomSigle();
    String getPrenomRaison();
    String getNumCompteDepositaire();
    String getTypePersonne();
    BigDecimal getPartsDispo();
    LocalDateTime getDateFermetureCompte();
    String getMotifFermetureCompte();
    String getPieceFermetureCompte();
    String getStatutCompte();
    String getIntitule();
}
