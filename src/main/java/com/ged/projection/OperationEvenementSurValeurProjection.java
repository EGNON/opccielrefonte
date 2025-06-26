package com.ged.projection;

import com.ged.dto.opcciel.OperationDetachementDto;
import com.ged.dto.opcciel.TransactionDto;
import com.ged.dto.standard.PersonneDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OperationEvenementSurValeurProjection {
    String getUserLogin();
    String getCodeNatureOperation();
    String getTypeEvenement();
    String getTypePayement();
    Long getIdIntervenant();
    BigDecimal getQteDetenue();
    BigDecimal getCouponDividendeUnitaire();
    BigDecimal getMontantBrut();
    BigDecimal getQuantiteAmortie();
    BigDecimal getNominalRemb();
    BigDecimal getCapitalRembourse();
    BigDecimal getMontantTotalARecevoir();
    Long getIdOpcvm();
    Boolean getEstPaye();
    LocalDateTime getDateReelle();
    Long getIdTransaction();
    Long getIdOperation();
    Opcvm getOpcvm();
    Personne getPersonne();
    String getIntervenant();
    BigDecimal getMontant();
    Boolean getEstOD();
    Boolean getEstExtournee();
    Boolean getEstOpExtournee();
    Boolean getEstVerifie1();
    Boolean getEstVerifie2();
    Titre getTitre();
    String getSymboleTitre();
    String getDesignationTitre();
    TransactionDto getTransaction();
    Long getIdSeance();
    NatureOperation getNatureOperation();
    String getLibelleNatureOperation();
    LocalDateTime getDateOperation();
    String getLibelleOperation();
    LocalDateTime getDateSaisie();
    LocalDateTime getDatePiece();
    LocalDateTime getDateValeur();
    String getReferencePiece();
    String getEcriture();
    String getType();
    OperationDetachement getOperationDetachement();
    BigDecimal getCommission();
    BigDecimal getIrvm();
    BigDecimal getInteretMoratoireSurCapital();
    BigDecimal getInteretMoratoireSurInteret();
    BigDecimal getCommissionSurInteretMoratoire();
    String getReferenceAvis();

}
