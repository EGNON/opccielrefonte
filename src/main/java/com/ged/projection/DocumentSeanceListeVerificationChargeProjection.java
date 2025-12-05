package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DocumentSeanceListeVerificationChargeProjection {

    Long getIdOperation();
    Long getIdTransaction();
    Long getIdSeance();
    Long getIdActionnaire();
    Long getIdOpcvm();
    String getCodeNatureOperation();
    String getLibelleNatureOperation();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
    LocalDateTime getDateSaisie();
    LocalDateTime getDatePiece();
    LocalDateTime getDateValeur();
    String getReferencePiece();
    BigDecimal getMontantCharge();
    BigDecimal getActifBrut();
    String getCodeCharge();
    Long getNbreJour();
    BigDecimal getUsance();
    String getTypeCharge();
    String getDesignation();
    BigDecimal getMontantTaux();
    String getCodeModele();
    Boolean getEstVerifie1();
    LocalDateTime getDateVerification1();
    String getUserLoginVerificateur1();
    Boolean getEstVerifie2();
    LocalDateTime getDateVerification2();
    String getUserLoginVerificateur2();
}
