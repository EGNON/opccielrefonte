package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.entity.standard.Personne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ConsultationEcriturePrintProjection {
    Long getIdOperation();
    String getCodeIb();
    String getCodeRubrique();
    String getCodePosition ();
    String getNumeroCompte();
    String getLibelleCompteComptable();
    BigDecimal getDebit();
    BigDecimal getCredit();
    String getTypeValeur();
    LocalDateTime getDateOperation();
    LocalDateTime getDateValeur();
    String getLibelleOperation();
    Long getIdTransaction();
}
