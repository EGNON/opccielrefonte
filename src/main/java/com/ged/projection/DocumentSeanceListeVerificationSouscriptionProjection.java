package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DocumentSeanceListeVerificationSouscriptionProjection {

    Long getIdOperation();
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
    Boolean getEstVerifie1();
    LocalDateTime getDateVerification1();
    String getUserLoginVerificateur1();
    Boolean getEstVerifie2();
    LocalDateTime getDateVerification2();
    String getUserLoginVerificateur2();
    Long getIdTitre();
    BigDecimal getQte();
    BigDecimal getCours();
    BigDecimal getCommission();
    BigDecimal getInteretCouru();
    BigDecimal getInteretPrecompte();
    String getSymbolTitre();
    String getCodeNatureOperation();
    Boolean getNiveau1();
    Boolean getNiveau2();
}
