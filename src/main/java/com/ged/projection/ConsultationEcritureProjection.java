package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.entity.standard.Personne;

import java.time.LocalDateTime;

public interface ConsultationEcritureProjection {
    Long getIdOperation();
    Opcvm getOpcvm();
    Personne getActionnaire();
    Transaction getTransaction();
    NatureOperation getNatureOperation();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
}
