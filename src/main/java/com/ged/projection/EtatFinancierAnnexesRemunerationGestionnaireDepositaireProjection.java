package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierAnnexesRemunerationGestionnaireDepositaireProjection {

    BigDecimal getActifNet();
    BigDecimal getMontantProvisionGest();
    BigDecimal getMontantCumuleGest();
    BigDecimal getMontantProvisionTC();
    BigDecimal getMontantCumuleTC();
    BigDecimal getMontantProvisionTrans();
    BigDecimal getMontantCumuleTrans();
    BigDecimal getMontantProvisionAutre();
    BigDecimal getMontantCumuleAutre();
    BigDecimal getMontantProvisionCOC();
    BigDecimal getMontantCumuleCOC();
    BigDecimal getMontantProvisionCREPMF();
    BigDecimal getMontantCumuleCREPMF();
    BigDecimal getMontantProvisionAFG();
    BigDecimal getMontantCumuleAFG();
    BigDecimal getMontantProvisionDCBR();
    BigDecimal getMontantCumuleDCBR();
    BigDecimal getActifNetGest();
    BigDecimal getActifNemontantCumuleGest();
    BigDecimal getActifNetDCBR();
    BigDecimal getActifNemontantCumuleDCBR();
    BigDecimal getActifNetTC();
    BigDecimal getActifNemontantCumuleTC();
    BigDecimal getActifNetTrans();
    BigDecimal getActifNemontantCumuleTrans();
    BigDecimal getActifNetAutre();
    BigDecimal getActifNemontantCumuleAutre();
    BigDecimal getActifNetCOC();
    BigDecimal getActifNemontantCumuleCOC();
    BigDecimal getActifNetCREPMF();
    BigDecimal getActifNemontantCumuleCREPMF();
    BigDecimal getActifNetAFG();
    BigDecimal getActifNemontantCumuleAFG();
    Long getIdOpcvm();
    LocalDateTime getDateEstimation();
}
