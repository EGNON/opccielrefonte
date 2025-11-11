package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AvisTransfertPartProjection {

    String getIdOperationDepart();
    String getIdOperationArrivée();
    Long getIdDemandeur();
    Long getIdBeneficiaire();
    String getDemandeur();
    String getBeneficiaire();
    String getNumCompteDepositaireDemandeur();
    String getNumCompteDepositaireBeneficiaire();
    LocalDateTime getDateOperation();
    String getTypeFCP();
    BigDecimal qteTransfert();
    Long getIdOperation();
    Long getIdOpcvm();
    LocalDateTime getDateDeb();
    LocalDateTime getDateFin();
}
