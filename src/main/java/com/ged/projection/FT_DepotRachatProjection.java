package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FT_DepotRachatProjection {
    Long getIdOperation() ;
    Long getIdDepotRachat() ;
    String getReferencePiece();
    LocalDateTime getDateOperation();
    String getNomSigle();
    String getPrenomRaison();
    BigDecimal getMontantDepose();
    BigDecimal getMontantSouscrit();
    BigDecimal getQtePart();
    Long getIdSeance();
    String getIdActionnaire();
    Long getIdOpcvm();
    String getDenominationOpcvm();
    LocalDateTime getDateOuv();
    LocalDateTime getDateFerm();
    String getIdPersonne();
    String getDistributeur();
    boolean isEstVerifie1();
    boolean isEstVerifier();
    LocalDateTime getDateVerification1();
    String getUserLoginVerificateur1();
    boolean isEstVerifie2();
    LocalDateTime getDateVerification2();
    String getUserLoginVerificateur2();
    Long getIdTitre();
    BigDecimal getQte();
    BigDecimal getCours();
    BigDecimal getCommission();
    BigDecimal getInteretCouru();
    BigDecimal getInteretPrecompte();
    String getSymbolTitre();
}
