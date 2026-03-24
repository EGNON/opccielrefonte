package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.entity.standard.Personne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ListeOrdreDeBourseProjection {
    Long getIdOperation();
    Long getIdTransaction();
    Opcvm getOpcvm();
    Personne getActionnaire();
    Transaction getTransaction();
    NatureOperation getNatureOperation();
    LocalDateTime getDateOperation();
    LocalDateTime getDateValeur();
    String getLibelleOperation();
    Long getIdOpcvm();
    Long getIdActionnaire();
    Long getIdTitre();
    Long getIdSeance();
    String getCodeNatureOperation();
    LocalDateTime getDateSaisie();
    LocalDateTime getDatePiece();
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
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
    String getLibelleNatureOperation();
    String getLibelleTypeOperation();
	Long getIdOrdre ();
	LocalDateTime getDateLimite ();
}
