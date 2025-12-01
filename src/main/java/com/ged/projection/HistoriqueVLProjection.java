package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface HistoriqueVLProjection {

    Long getIdSeance();
    LocalDateTime getDateOuvertureSeance();
    LocalDateTime getDateFermetureSeance();
    BigDecimal getValeurLiquidative();
    BigDecimal getActifNet();
}
