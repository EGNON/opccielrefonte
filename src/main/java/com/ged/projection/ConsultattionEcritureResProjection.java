package com.ged.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.entity.standard.Personne;

import java.time.LocalDateTime;

public interface ConsultattionEcritureResProjection {

   Long getIdOperation();
   Long getIdActionnaire();
   Opcvm getOpcvm();
    Personne getActionnaire();
    Long getIdTransaction();
    Transaction getTransaction();
    Long getIdSeance() ;
    NatureOperation getNatureOperation();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
    LocalDateTime getDateValeur();
}
