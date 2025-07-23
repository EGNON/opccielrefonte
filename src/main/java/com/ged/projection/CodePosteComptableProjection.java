package com.ged.projection;

import com.ged.entity.opcciel.comptabilite.Plan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CodePosteComptableProjection {
    Plan getPlan();
     Long getIdOpcvm ();
     Long getIdSeance ();
     String getCodePosteComptable ();
	String getLibellePosteComptable ();
     String getCodePlan ();
     String getFormuleSysteme ();
	LocalDateTime getDateValeur ();
	BigDecimal getValeur ();
     Boolean getEstVerifie1 ();
     LocalDateTime getDateVerification1();
     String getUserLoginVerificateur1();
     Boolean getEstVerifie2  ();
     LocalDateTime getDateVerification2 ();
     String getUserLoginVerificateur2();
}
