package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FT_DepotRachatProjection {
    Long getIdOperation() ;
    Long getIdDepotRachat() ;
    String getReferencePiece();
    String getLibelleOperation();
    LocalDateTime getDateOperation();
    String getNomSigle();
    String getCodeNatureOperation();
    String getPrenomRaison();
    BigDecimal getMontantDepose();
    BigDecimal getMontantSouscrit();
    BigDecimal getMontantBrut();
    BigDecimal getQtePart();
    Long getIdSeance();
    String getIdActionnaire();
    Long getIdOpcvm();
    String getDenominationOpcvm();
    LocalDateTime getDateOuv();
    LocalDateTime getDateFerm();
    String getIdPersonne();
    String getDistributeur();
    Boolean getEstVerifie1();
    Boolean getEstVerifier();
    LocalDateTime getDateVerification1();
    String getUserLoginVerificateur1();
    Boolean getEstVerifie2();
    LocalDateTime getDateVerification2();
    String getUserLoginVerificateur2();
    Long getIdTitre();
    BigDecimal getQte();
    BigDecimal getCours();
    BigDecimal getMontant();
    BigDecimal getCommission();
    BigDecimal getInteretCouru();
    BigDecimal getInteretPrecompte();
    String getSymbolTitre();
}
