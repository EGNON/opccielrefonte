package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CompositionDetailleActifProjection {

    String getCategorie();
    String getlibelle();
    Long getQuantite();
    BigDecimal getCours();
    BigDecimal getValeur();
	BigDecimal getActifNet();
    BigDecimal getpPart();
    BigDecimal getVl();
    BigDecimal getTotalpercentActifNet();
    BigDecimal getArrondiActifNet();
    Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
