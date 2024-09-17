package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class PersonnePhysiqueMoraleDto {
    private Long idPersonne;
    private String denomination;
    private String ifu;
    private String mobile1;
    private String mobile2;
    private String fixe1;
    private String fixe2;
    private String bp;
    private String emailPerso;
    private String emailPro;
    private String domicile;
    private String numeroPiece;
    private String typePiece;
    private Date dateExpirationPiece;
    private String modeEtablissement;


    public PersonnePhysiqueMoraleDto() {
    }

    public PersonnePhysiqueMoraleDto(Long idPersonne, String denomination) {
        this.idPersonne = idPersonne;
        this.denomination = denomination;

    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
    public String getIfu() {
        return ifu;
    }

    public void setIfu(String ifu) {
        this.ifu = ifu;
    }

    public String getMobile1() {
        return mobile1;
   }
  public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getFixe1() {
        return fixe1;
    }

    public void setFixe1(String fixe1) {
        this.fixe1 = fixe1;
    }

    public String getFixe2() {
        return fixe2;
    }

    public void setFixe2(String fixe2) {
        this.fixe2 = fixe2;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getEmailPerso() {
        return emailPerso;
    }

    public void setEmailPerso(String emailPerso) {
        this.emailPerso = emailPerso;
    }

    public String getEmailPro() {
        return emailPro;
    }

    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public String getTypePiece() {
        return typePiece;
    }

   public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public Date getDateExpirationPiece() {
        return dateExpirationPiece;
    }

    public void setDateExpirationPiece(Date dateExpirationPiece) {
        this.dateExpirationPiece = dateExpirationPiece;
    }

    public String getModeEtablissement() {
        return modeEtablissement;
    }

    public void setModeEtablissement(String modeEtablissement) {
        this.modeEtablissement = modeEtablissement;
    }

}
