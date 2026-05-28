package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IndicationChangementProjection {
    Long getNumero();
    String getPortefeuille();
    LocalDateTime getDateOp ();
    BigDecimal getQte_Ktal();
    BigDecimal getCours_interet ();
    BigDecimal getValeur_Rembours();
    BigDecimal getFrais();
    String getType ();
    String getNumerop ();
    String getLibelle_Numero();
}
