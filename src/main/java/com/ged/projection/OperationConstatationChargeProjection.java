package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OperationConstatationChargeProjection {
    Long getIdOperation();
    Long getIdTransaction();
    Long getIdSeance();
    Long getIdSeancePaiement();
    Long getIdOpcvm();
    String getCodeNatureOperation();
    LocalDateTime getDateSolde();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
    String getDesignation();
    LocalDateTime getDateSaisie();
    LocalDateTime getDatePiece();
    LocalDateTime getDateValeur();
    String getReferencePiece();
    BigDecimal getMontant();
    BigDecimal getMontantCharge();
    String getCodeCharge();
    Boolean getEstPayee();
}
