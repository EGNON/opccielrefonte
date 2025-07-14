package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FT_GenererChargeProjection {

     Long getIdOpcvm ();
     Long getIdSeance ();
     BigDecimal getActifBrut ();
     String getTypeCharge ();
     String getCodeCharge ();
     String getDenominationOpcvm ();
     String getDesignation ();
     BigDecimal getMontantTaux ();
     int getNbreJour();
     int getUsance ();
     BigDecimal getMontantCharge ();
     String getCodeModele();
     Long getIdOperation ();
     Long getIdTransaction ();
//     Long getIdSeance ();
     Long getIdActionnaire ();
//	Long getIdOpcvm();
     String getCodeNatureOperation();
     String getLibelleNatureOperation ();
     LocalDateTime getDateOperation ();
     LocalDateTime getDateOuv ();
     LocalDateTime getDateFerm ();
     String getLibelleOperation();
     LocalDateTime getDateSaisie ();
     LocalDateTime getDatePiece ();
     LocalDateTime getDateValeur ();
     String getReferencePiece ();
     BigDecimal getMontant ();
     Boolean getEstVerifie1 ();
     LocalDateTime getDateVerification1();
     String getUserLoginVerificateur1();
     Boolean getEstVerifie2  ();
     LocalDateTime getDateVerification2 ();
     String getUserLoginVerificateur2();
}
