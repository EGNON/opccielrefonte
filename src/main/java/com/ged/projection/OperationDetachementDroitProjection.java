package com.ged.projection;

import com.ged.dto.opcciel.TransactionDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OperationDetachementDroitProjection {
    String getUserLogin();
    String getCodeNatureOperation();
    BigDecimal getQteAction();
    BigDecimal getQteDroit();
    BigDecimal getMontantBrut();
    Long getIdOpcvm();
    Long getIdTransaction();
    Long getIdOperation();
    Opcvm getOpcvm();
    BigDecimal getMontant();
    Boolean getEstOD();
    Boolean getEstVerifie1();
    Boolean getEstVerifie2();
    Titre getTitre();
    String getSymboleTitre();
    String getDesignationTitre();
    TransactionDto getTransaction();
    Long getIdSeance();
    Long getIdAvis();
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
}
