package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AvisPaiementProjection {

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
     BigDecimal getSousRachatPart();
     BigDecimal getCommisiionSousRachat();
     BigDecimal getCouponUnitaire();
     BigDecimal getMontant();
     BigDecimal getQte();
}
