package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.entity.Base;
import com.ged.entity.standard.FormeJuridique;
import com.ged.entity.titresciel.TypeGarant;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GarantDto extends PersonneMoraleDto {
//public class Garant extends Base {
    //OPCCIEL 1

    private Long idGarant;
    private Long idPersonne;
    //OPCCIEL1
    private String libelleTypePersonne;
    private String CodePays;
    private String CodeLangue;
    private String codeCategorieClient;
    private String codeSousTypeClient;
    //FIN OPCCIEL1
    private String numeroAgrementPersonneMorale;
    private String numeroINSAE;
    private String numRegistre;
    private String codeSecteur;
    //@Column(nullable = true)
    //private BigDecimal CapitalSocial;
    private String siglePersonneMorale;
    private String raisonSociale;
    private FormeJuridiqueDto formeJuridique;
    private String codeTypeGarant;

    private TypeGarantDto typeGarant;
    //FIN
    private LocalDateTime dateCreationServeur;

    private LocalDateTime dateDernModifServeur;

    private LocalDateTime dateDernModifClient;

    @Basic
    private String sigle;
    @Basic
    private String siteWeb;
    //FIN

    public GarantDto() {
    }

    public LocalDateTime getDateCreationServeur() {
        return dateCreationServeur;
    }

    public void setDateCreationServeur(LocalDateTime dateCreationServeur) {
        this.dateCreationServeur = dateCreationServeur;
    }

    public LocalDateTime getDateDernModifServeur() {
        return dateDernModifServeur;
    }

    public void setDateDernModifServeur(LocalDateTime dateDernModifServeur) {
        this.dateDernModifServeur = dateDernModifServeur;
    }

    public LocalDateTime getDateDernModifClient() {
        return dateDernModifClient;
    }

    public void setDateDernModifClient(LocalDateTime dateDernModifClient) {
        this.dateDernModifClient = dateDernModifClient;
    }

    public Long getIdGarant() {
        return idGarant;
    }

    public void setIdGarant(Long idGarant) {
        this.idGarant = idGarant;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getLibelleTypePersonne() {
        return libelleTypePersonne;
    }

    public void setLibelleTypePersonne(String libelleTypePersonne) {
        this.libelleTypePersonne = libelleTypePersonne;
    }

    public String getCodePays() {
        return CodePays;
    }

    public void setCodePays(String codePays) {
        CodePays = codePays;
    }

    public String getCodeLangue() {
        return CodeLangue;
    }

    public void setCodeLangue(String codeLangue) {
        CodeLangue = codeLangue;
    }

    public String getCodeCategorieClient() {
        return codeCategorieClient;
    }

    public void setCodeCategorieClient(String codeCategorieClient) {
        this.codeCategorieClient = codeCategorieClient;
    }

    public String getCodeSousTypeClient() {
        return codeSousTypeClient;
    }

    public void setCodeSousTypeClient(String codeSousTypeClient) {
        this.codeSousTypeClient = codeSousTypeClient;
    }

    public String getNumeroAgrementPersonneMorale() {
        return numeroAgrementPersonneMorale;
    }

    public void setNumeroAgrementPersonneMorale(String numeroAgrementPersonneMorale) {
        this.numeroAgrementPersonneMorale = numeroAgrementPersonneMorale;
    }

    public String getNumeroINSAE() {
        return numeroINSAE;
    }

    public void setNumeroINSAE(String numeroINSAE) {
        this.numeroINSAE = numeroINSAE;
    }

    public String getNumRegistre() {
        return numRegistre;
    }

    public void setNumRegistre(String numRegistre) {
        this.numRegistre = numRegistre;
    }

    public String getCodeSecteur() {
        return codeSecteur;
    }

    public void setCodeSecteur(String codeSecteur) {
        this.codeSecteur = codeSecteur;
    }

//    public BigDecimal getCapitalSocial() {
//        return CapitalSocial;
//    }
//
//    public void setCapitalSocial(BigDecimal capitalSocial) {
//        CapitalSocial = capitalSocial;
//    }

    public String getSiglePersonneMorale() {
        return siglePersonneMorale;
    }

    public void setSiglePersonneMorale(String siglePersonneMorale) {
        this.siglePersonneMorale = siglePersonneMorale;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }


    public String getCodeTypeGarant() {
        return codeTypeGarant;
    }

    public void setCodeTypeGarant(String codeTypeGarant) {
        this.codeTypeGarant = codeTypeGarant;
    }

    public FormeJuridiqueDto getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(FormeJuridiqueDto formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public TypeGarantDto getTypeGarant() {
        return typeGarant;
    }

    public void setTypeGarant(TypeGarantDto typeGarant) {
        this.typeGarant = typeGarant;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    //OPCCIEL1

    //FIN
}
