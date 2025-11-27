package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DocumentSeanceListeVerificationVdeProjection {

    Long getIdSeance();
    Long getIdTitre();
    String getSymbolTitre();
    String getDesignationTitre();
    String getCodeClasseTitre();
    Long getIdOpcvm();
    BigDecimal getQteDetenue();
    String getCours();
    BigDecimal getCumpT();
    BigDecimal getCumpReel();
    BigDecimal getPlusOuMoinsValue();
    BigDecimal getNbreJourCourus();
    BigDecimal getInteretCourus();
    BigDecimal getValeurVDECours();
    BigDecimal getValeurVDEInteret();
    BigDecimal getIdOpCours();
    BigDecimal getIdOpInteret();
    BigDecimal getIrvm();
    Boolean getEstVerifie();
    LocalDateTime getDateVerification();
    String getUserLoginVerificateur();
    String getNomUtilisateur();
    Long getNumLigne();
    LocalDateTime getDateCreationServeur();
    LocalDateTime getDateDernModifServeur();
    LocalDateTime getDateDernModifClient();
    String getUserLogin();
    Boolean getSupprimer();
    Boolean getEstVerifie1();
    LocalDateTime getDateVerification1();
    String getUserLoginVerificateur1();
    Boolean getEstVerifie2();
    LocalDateTime getDateVerification2();
    String getUserLoginVerificateur2();
}
