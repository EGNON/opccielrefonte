package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CoursTitreProjection {
    Long getIdTitre ();
    String getSymbolTitre ();
    String getDesignationTitre ();
    String getCodePlace ();
    String getLibellePlace ();
    LocalDateTime getDateCours ();
    BigDecimal getCoursVeille ();
    BigDecimal getOuverture ();
    BigDecimal getHaut ();
    BigDecimal getBas ();
    BigDecimal getCoursSeance();
    BigDecimal getVariation ();
    BigDecimal getNbreTrans ();
    BigDecimal getVolTransiger ();
    BigDecimal getValTransiger ();
    BigDecimal getResteOffre ();
    BigDecimal getResteDemande ();
    BigDecimal getCoursReference ();
    Boolean getEstValider ();
    String getUserLogin ();
    Long getNumLigne ();
    Boolean getSupprimer ();
    String getLibelleSecteurBoursier ();
    Boolean getEstVerifie1 ();
    LocalDateTime getDateVerification1 ();
    String getUserLoginVerificateur1 ();
    Boolean getEstVerifie2 ();
    LocalDateTime getDateVerification2 ();
    String getUserLoginVerificateur2();
}
