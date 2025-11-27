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
    Long getCodeNatureOperation();
    Long getLibelleNatureOperation();
    LocalDateTime getDateOperation();
    Long getLibelleOperation();
    LocalDateTime getDateSaisie();
    LocalDateTime getDatePiece();
    LocalDateTime getDateValeur();
    Long getReferencePiece();
    BigDecimal getMontantCharge();
    BigDecimal getActifBrut();
    Long getCodeCharge();
    Long getNbreJour();
    Long getUsance();
    Long getTypeCharge();
    Long getDesignation();
    BigDecimal getMontantTaux();
    Long getCodeModele();
    Boolean getEstVerifie1();
    LocalDateTime getDateVerification1();
    Long getUserLoginVerificateur1();
    Boolean getEstVerifie2();
    LocalDateTime getDateVerification2();
    Long getUserLoginVerificateur2();
}
