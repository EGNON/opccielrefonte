package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PrevisionnelRemboursementsProjection {

    Long getIdTitre();
    Long getIdOpcvm();
    String getSymbolTitre();
    String getDesignationTitre();
    String getClasseTitre();
    LocalDateTime getDateEcheance();
    String getMoisAnnee();
    String getNumEcheance();
    Long getQuantite();
    BigDecimal getInteret();
    BigDecimal getAmortissement();
    String getModeAmortissement();
    Boolean getEchue();
    Boolean getDetache();
    Boolean getTraiter();
    BigDecimal getSolde();
    LocalDateTime getDatedebut();
    LocalDateTime getDatefin();
    BigDecimal getTotal();
}
