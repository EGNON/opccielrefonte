package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.titresciel.TypeTitreDto;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeleEcritureNatureOperationDto {
    private CleModeleEcritureNatureOperationDto idModeleEcritureNatureOperation;
    private ModeleEcritureDto modeleEcriture;
    private NatureOperationDto natureOperation;
    private TypeTitreDto typeTitre;
	private int numeroOrdre;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private String userLogin;
    private Long numLigne;
    private Boolean supprimer;
    private byte[] rowvers;
    public ModeleEcritureNatureOperationDto() {
    }

    public CleModeleEcritureNatureOperationDto getIdModeleEcritureNatureOperation() {
        return idModeleEcritureNatureOperation;
    }

    public void setIdModeleEcritureNatureOperation(CleModeleEcritureNatureOperationDto idModeleEcritureNatureOperation) {
        this.idModeleEcritureNatureOperation = idModeleEcritureNatureOperation;
    }

    public ModeleEcritureDto getModeleEcriture() {
        return modeleEcriture;
    }

    public void setModeleEcriture(ModeleEcritureDto modeleEcriture) {
        this.modeleEcriture = modeleEcriture;
    }

    public NatureOperationDto getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperationDto natureOperation) {
        this.natureOperation = natureOperation;
    }

    public TypeTitreDto getTypeTitre() {
        return typeTitre;
    }

    public void setTypeTitre(TypeTitreDto typeTitre) {
        this.typeTitre = typeTitre;
    }

    public int getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(int numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
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
