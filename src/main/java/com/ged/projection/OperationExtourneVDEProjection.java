package com.ged.projection;

import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.TransactionDto;
import com.ged.dto.titresciel.TitreDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OperationExtourneVDEProjection {
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
