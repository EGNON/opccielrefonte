package com.ged.entity.standard.revuecompte;

import com.ged.entity.Base;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_NumeroCompte", schema = "Nomenclature")
public class NumeroCompte extends Base {
    @EmbeddedId
    private CleNumeroCompte cleNumeroCompte;
    @ManyToOne
    @JoinColumn(name = "idPersonne")
    @MapsId("idPersonne")
    private Personne personne;
    @ManyToOne
    @JoinColumn(name = "idSousTypeCompte")
    @MapsId("idSousTypeCompte")
    private SousTypeCompte sousTypeCompte;
    private String numCompte;
    private String codeTeneurCompte;
    private String codeAgence;
    private String codeCategorieClient;
    private String codeSousTypeClient;
    private String codeSousTypeCompte;
    private String numeroOrdreCompte;
    private String cleControle;

    public NumeroCompte() {
    }

    public CleNumeroCompte getCleNumeroCompte() {
        return cleNumeroCompte;
    }

    public void setCleNumeroCompte(CleNumeroCompte cleNumeroCompte) {
        this.cleNumeroCompte = cleNumeroCompte;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public SousTypeCompte getSousTypeCompte() {
        return sousTypeCompte;
    }

    public void setSousTypeCompte(SousTypeCompte sousTypeCompte) {
        this.sousTypeCompte = sousTypeCompte;
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
