package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointSouscriptionGlobalProjection {

                    BigDecimal getMontantDepose();
					BigDecimal getNombrePartSousRachat();
					BigDecimal getMontantSousALiquider();
					BigDecimal getCommisiionSousRachat();
					BigDecimal getRetrocessionSousRachat();
					String getRaisonSociale();
					Long getIdPersonne();
					String getLibelleTypePersonne();
					String getDenominationOpcvm();
					Long getIdOpcvm();
					LocalDateTime getDateDebut();
					LocalDateTime getDateFin();
}
