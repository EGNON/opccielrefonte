package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface RegistreActionnaireProjection {
    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    Long getIdActionnaire();
    String getNomSigle();
    String getPrenomRaison();
    String getNumCompteSgi();
    LocalDateTime getDateEstimation();
    BigDecimal getNombrePartDebut();
    BigDecimal getNombrePartActuel();
    BigDecimal getValeurLiquidativeActuelle();
    BigDecimal getValorisation();
    BigDecimal getSoldeEspece();
    Integer getCump();
    Integer getPlusMoinsValue();
    String getTypePersonne();
}
