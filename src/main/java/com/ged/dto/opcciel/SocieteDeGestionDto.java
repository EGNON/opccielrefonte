package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonneMoraleDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocieteDeGestionDto extends PersonneMoraleDto {
    //OPCCIEL 1
    private String codeSkype;
    private LocalDateTime dateAgrement;
    //FIN

    private String codeAgence;
    private long typeTeneurCompte;
    private String numeroOrdreTeneur;
    private LocalDateTime dateCreationServeur;
    
    private LocalDateTime dateDernModifServeur;
    
    private LocalDateTime dateDernModifClient;
    
    private long numLigne;

    private boolean supprimer;

    private LocalDateTime rowvers;
    
    private String userLogin;
    private BigDecimal tauxEpargne ;

    public SocieteDeGestionDto() {
    }

    public BigDecimal getTauxEpargne() {
        return tauxEpargne;
    }

    public void setTauxEpargne(BigDecimal tauxEpargne) {
        this.tauxEpargne = tauxEpargne;
    }

    @Override
    public void setDenomination(String denomination) {
        super.setDenomination(denomination);
    }

    @Override
    public LocalDateTime getDateAgrement() {
        return dateAgrement;
    }

    @Override
    public void setDateAgrement(LocalDateTime dateAgrement) {
        this.dateAgrement = dateAgrement;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public long getTypeTeneurCompte() {
        return typeTeneurCompte;
    }

    public void setTypeTeneurCompte(long typeTeneurCompte) {
        this.typeTeneurCompte = typeTeneurCompte;
    }

    public String getNumeroOrdreTeneur() {
        return numeroOrdreTeneur;
    }

    public void setNumeroOrdreTeneur(String numeroOrdreTeneur) {
        this.numeroOrdreTeneur = numeroOrdreTeneur;
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

    public long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(long numLigne) {
        this.numLigne = numLigne;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public LocalDateTime getRowvers() {
        return rowvers;
    }

    public void setRowvers(LocalDateTime rowvers) {
        this.rowvers = rowvers;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    //OPCCIEL1

    public String getCodeSkype() {
        return codeSkype;
    }

    public void setCodeSkype(String codeSkype) {
        this.codeSkype = codeSkype;
    }

    //FIN
}
