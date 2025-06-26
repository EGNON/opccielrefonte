package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OperationRegulEcartSoldeProjection {
    Long getIdOperation();
    Long getIdTransaction();
    Long getIdSeance();
    Long getIdOpcvm();
    String getCodeNatureOperation();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
    String getDesignation();
    LocalDateTime getDateSaisie();
    LocalDateTime getDatePiece();
    LocalDateTime getDateValeur();
    String getReferencePiece();
    BigDecimal getMontant();
    BigDecimal getSoldeEspeceDepositaire();
}
