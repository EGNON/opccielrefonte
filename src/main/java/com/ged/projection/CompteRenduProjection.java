package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.crm.RDV;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CompteRenduProjection {

    Long getIdCR();
    LocalDateTime getDateCR();
    LocalTime getHeureDebCR();
    LocalTime getHeureFinCR();
    String getObjetCR();
    String getAppreciation();
    String getDescription();
    LocalDateTime getDateProchainRDV();
    double getMontantPromesse();
    double getMontantRealisation();
    String getPromesse();
    String getRealisation();
    LocalDateTime getDateEffectivePromesse();
    Opcvm getOpcvmASouscrire();
    Opcvm getOpcvmSouscrit() ;
    RDV getRdv();
}
