package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FicheClientProjection {
    Long getIdPersonne();
    String getCivilte();
    String getSexe();
    String getNom();
    String getPrenom();
    LocalDateTime getDateNaissance();
    String getCodePaysNaissance();
    String getPaysNaissance();
    String getLieuNaissance();
    Boolean getEstMineur();
    String getNomDeJeuneFille();
    String getPhotoPersonnePhysique();
    String getSignaturePersonnePhysique();
    String getLibelleSecteurActivite();
    String getLibelleProfession();
    Boolean getEstActifPersonne();
    String getCodePays();
    String getLibellePays();
    String getTelephoneFixe();
    String getTelephoneMobile1();
    String getTelephoneMobile2();
    String getEmail();
    String getBoitePostale();
    String getAdresseComplete();
    String getTypePieceIdentite();
    String getNumeroPiece();
    LocalDateTime getDateExpirationPiece();
    String getNomPrenomMere();
    String getNumCompteSgi();
    String getNumeroAgrementPersonneMorale();
    String getNumeroINSAE();
    String getCodeSecteur();
    BigDecimal getCapitalSocial();
    String getSigle();
    String getRaisonSociale();
    String getCodeFormeJuridique();
    String getLibelleFormeJuridiqueOPC();
    LocalDateTime getDateCreationPM();
    BigDecimal getTauxRetroCourSous();
    BigDecimal getTauxRetroCourRach ();
}
