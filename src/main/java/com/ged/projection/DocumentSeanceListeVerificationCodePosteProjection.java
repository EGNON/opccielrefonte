package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DocumentSeanceListeVerificationCodePosteProjection {

    String getCodePosteComptable();
    String getLibellePosteComptable();
    String getCodePlan();
    Long getIdOpcvm();
    Long getIdSeance();
    String getfFormuleSysteme();
    LocalDateTime getDateValeur();
    BigDecimal valeur();
    Boolean getEstVerifie1();
    LocalDateTime getDateVerification1();
    String getUserLoginVerificateur1();
    Boolean getEstVerifie2();
    LocalDateTime getDateVerification2();
    String getUserLoginVerificateur2();
}
