package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonneDto;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NumeroCompteDto extends Base {
    private CleNumeroCompteDto cleNumeroCompte;
    private PersonneDto personne;
    private SousTypeCompteDto sousTypeCompte;
    private String numCompte;
    private String codeTeneurCompte;
    private String codeAgence;
    private String codeCategorieClient;
    private String codeSousTypeClient;
    private String codeSousTypeCompte;
    private String numeroOrdreCompte;
    private String cleControle;

    public NumeroCompteDto() {
    }

    public CleNumeroCompteDto getCleNumeroCompte() {
        return cleNumeroCompte;
    }

    public void setCleNumeroCompte(CleNumeroCompteDto cleNumeroCompte) {
        this.cleNumeroCompte = cleNumeroCompte;
    }

    public SousTypeCompteDto getSousTypeCompte() {
        return sousTypeCompte;
    }

    public void setSousTypeCompte(SousTypeCompteDto sousTypeCompte) {
        this.sousTypeCompte = sousTypeCompte;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public String getCodeTeneurCompte() {
        return codeTeneurCompte;
    }

    public void setCodeTeneurCompte(String codeTeneurCompte) {
        this.codeTeneurCompte = codeTeneurCompte;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
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

    public String getCodeSousTypeCompte() {
        return codeSousTypeCompte;
    }

    public void setCodeSousTypeCompte(String codeSousTypeCompte) {
        this.codeSousTypeCompte = codeSousTypeCompte;
    }

    public String getNumeroOrdreCompte() {
        return numeroOrdreCompte;
    }

    public void setNumeroOrdreCompte(String numeroOrdreCompte) {
        this.numeroOrdreCompte = numeroOrdreCompte;
    }

    public String getCleControle() {
        return cleControle;
    }

    public void setCleControle(String cleControle) {
        this.cleControle = cleControle;
    }
}
