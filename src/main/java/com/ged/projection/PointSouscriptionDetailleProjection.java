package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointSouscriptionDetailleProjection {
                    LocalDateTime getDateOperation();
					Long getIdActionnaire();
					String getPersonne();
					String getLibelleTypePersonne();
					BigDecimal getMontantDepose();
					BigDecimal getNombrePartSousRachat();
					BigDecimal getValeurLiquidative();
					BigDecimal getMontantSousALiquider();
					BigDecimal getCommisiionSousRachat();
					BigDecimal getRetrocessionSousRachat();
					Long getIdPersonne();
					String getRaisonSociale();
					Long getIdOpcvm();
					String getDenominationOpcvm();
					LocalDateTime getDateDebut();
					LocalDateTime getDateFin();
}
