package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DocumentSeanceListeVerificationEcritureVdeProjection {

    Long getIdOpcvm();
    Long getIdActionnaire();
    Long getIdTitre();
    Long getIdTransaction();
    Long getIdSeance();
    String getCodeNatureOperation();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
    LocalDateTime getDateSaisie();
    LocalDateTime getDatePiece();
    LocalDateTime getDateValeur();
    String getReferencePiece();
    BigDecimal getMontant();
	String getEcriture();
    Boolean getEstOD();
    String getType();
    String getValeurFormule();
    String getValeurCodeAnalytique();
    Boolean getEstExtournee();
    Boolean getEstOpExtournee();
    Long getIdOpExtournee();
    Boolean getEstVerifie1();
    LocalDateTime getDateVerification1();
    String getUserLoginVerificateur1();
    Boolean getEstVerifie2();
    LocalDateTime getDateVerification2();
    String getUserLoginVerificateur2();
    Long getIdMvt();
    String getCodePlan();
    Long getNumeroOdreModele();
    String getCodeModeleEcriture();
    Long getNumeroOdreLigneMvt();
    String getNumCompteComptable();
    String getSensMvt();
    BigDecimal getValeur();
    BigDecimal getDebit();
    BigDecimal getCredit();
    String getTypeValeur();
    String getIB();
    String getRubrique();
    String getPosition();
    String getLibelleCompteComptable();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
    String getIdOperation();
}
