package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OperationDifferenceEstimationProjection {
    Long getIdSeance ();
    Long getIdTitre ();
    String getSymbolTitre ();
    String getDesignationTitre ();
    String getCodeClasseTitre ();
    Long getIdOpcvm ();
    BigDecimal getQteDetenue ();
    BigDecimal getCours ();
    BigDecimal getCumpT ();
    BigDecimal getCumpReel ();
    BigDecimal getPlusOuMoinsValue ();
    BigDecimal getNbreJourCourus ();
    BigDecimal getInteretCourus ();
    BigDecimal getValeurVDECours ();
    BigDecimal getSommeCours ();
    BigDecimal getSommeInteret ();
    BigDecimal getValeurVDEInteret ();
    Long getIdOpCours ();
	Long getIdOpInteret ();
	BigDecimal getIrvm();
    Boolean getEstVerifie ();
    LocalDateTime getDateVerification ();
    String getUserLoginVerificateur ();
    String getUtilisateurVerificateur ();
    Boolean getEstVerifie1  ();
    LocalDateTime getDateVerification1   ();
    String getUserLoginVerificateur1 ();
    Boolean getEstVerifie2  ();
    LocalDateTime getDateVerification2 ();
    String getUserLoginVerificateur2 ();
}
