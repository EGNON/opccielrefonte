package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PhasePaiementProjection {
  Long getIdDetachement();
  Long getIdOperation();
  LocalDateTime getDateOp();
  Long getIdOpcvm();
  Long getIdActionnaire();
  BigDecimal getMontantARecevoir();
  BigDecimal getNombrePart();
  BigDecimal getMontantPayer();
  BigDecimal getReste();
  Long getIdSeance();
  String getIntitule();
  String getNumCompteSgi ();
  Long getIdDividende();
  Long getIdOperationPaiement();

}
