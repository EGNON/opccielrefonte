package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AvisOperationProjection {

     Long getIdOperation();
     Long getIdActionnaire();
     String getNumCompteSgi();
     String getNomSigle();
     String getPrenomRaison();
     String getIntitule();
     String getMail();
     String getPhone();
     Long getIdOpcvm();
     String getDenominationOpcvm();
     Long getIdPersonne();
     String getCodeNatureOperation();
     LocalDateTime getDateOperation();
     LocalDateTime getDateValeur();
     BigDecimal getMontantSousALiquider();
     BigDecimal getSousRachatPart();
     BigDecimal getCommisiionSousRachat();
     BigDecimal getTafcommissionSousRachat();
     BigDecimal getRetrocessionSousRachat();
     BigDecimal getCommissionSousRachatRetrocedee();
     String getModeValeurLiquidative();
     BigDecimal getCoursVL();
     BigDecimal getNombrePartSousRachat();
     BigDecimal getMontantDepose();
}
