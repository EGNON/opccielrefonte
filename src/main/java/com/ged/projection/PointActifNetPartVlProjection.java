package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointActifNetPartVlProjection {

    Long getIdSeance();
    LocalDateTime getDateValeur();
    BigDecimal getActifNet();
    BigDecimal getNpc();
    BigDecimal getVl();
    Long getIdOpcvm();
}
