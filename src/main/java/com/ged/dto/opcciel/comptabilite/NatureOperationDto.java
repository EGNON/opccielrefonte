package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NatureOperationDto {

    private String codeNatureOperation;
    private String libelleNatureOperation;
    private TypeOperationDto typeOperation;
    private JournalDto journal;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private String userLogin;
    private Long numLigne;
    private Boolean supprimer;
    private byte[] rowvers;

    public NatureOperationDto() {
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public String getLibelleNatureOperation() {
        return libelleNatureOperation;
    }

    public void setLibelleNatureOperation(String libelleNatureOperation) {
        this.libelleNatureOperation = libelleNatureOperation;
    }

    public TypeOperationDto getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperationDto typeOperation) {
        this.typeOperation = typeOperation;
    }

    public JournalDto getJournal() {
        return journal;
    }

    public void setJournal(JournalDto journal) {
        this.journal = journal;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    public Boolean getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public byte[] getRowvers() {
        return rowvers;
    }

    public void setRowvers(byte[] rowvers) {
        this.rowvers = rowvers;
    }
}
