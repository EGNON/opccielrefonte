package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointRachatGlobalProjection {

                    BigDecimal getNombrePartSousRachat();
					BigDecimal getMontantSousALiquider();
					BigDecimal getCommisiionSousRachat();
					BigDecimal getRetrocessionSousRachat();
					Long getIdPersonne();
					String getRaisonSociale();
					String getLibelleTypePersonne();
					String getDenominationOpcvm();
					Long getIdOpcvm();
					Long getDateDebut();
					Long getDateFin();
}
