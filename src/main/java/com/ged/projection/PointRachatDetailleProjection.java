package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PointRachatDetailleProjection {
                   LocalDateTime getDateOperation();
                   Long getIdActionnaire();
				   String getNomPersonnePhysique();
				   String getLibelleTypePersonne();
				   BigDecimal getNombrePartSousRachat();
				   BigDecimal getValeurLiquidative();
				   BigDecimal getMontantSousALiquider();
				   BigDecimal getCommisiionSousRachat();
				   BigDecimal getRetrocessionSousRachat();
				   String getRaisonSociale();
				   Long getIdOpcvm();
				   String getDenominationOpcvm();
				   Long getIdPersonne();
				   LocalDateTime getDateDebut();
				   LocalDateTime getDateFin();
}
