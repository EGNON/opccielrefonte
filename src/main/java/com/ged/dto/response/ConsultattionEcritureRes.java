package com.ged.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.entity.standard.Personne;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultattionEcritureRes {
    private Long idOperation;
    private Long idActionnaire;
    private Opcvm opcvm;
    private Personne actionnaire;
    private Long idTransaction;
    private Transaction transaction;
    private Long idSeance;
    private NatureOperation natureOperation;
    private LocalDateTime dateOperation;
    private String libelleOperation;
    private LocalDateTime dateValeur;

    public ConsultattionEcritureRes() {
    }

    public ConsultattionEcritureRes(
            Long idOperation, Long idActionnaire,
            Opcvm opcvm, Personne actionnaire,
            Long idTransaction, Transaction transaction,
            Long idSeance, NatureOperation natureOperation,
            LocalDateTime dateOperation, String libelleOperation,
            LocalDateTime dateValeur) {
        this.idOperation = idOperation;
        this.idActionnaire = idActionnaire;
//        this.opcvm = opcvm;
//        this.actionnaire = actionnaire;
        this.idTransaction = idTransaction;
//        this.transaction = transaction;
        this.idSeance = idSeance;
//        this.natureOperation = natureOperation;
        this.dateOperation = dateOperation;
        this.libelleOperation = libelleOperation;
        this.dateValeur = dateValeur;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public Personne getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(Personne actionnaire) {
        this.actionnaire = actionnaire;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public NatureOperation getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperation natureOperation) {
        this.natureOperation = natureOperation;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getLibelleOperation() {
        return libelleOperation;
    }

    public void setLibelleOperation(String libelleOperation) {
        this.libelleOperation = libelleOperation;
    }

    public LocalDateTime getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(LocalDateTime dateValeur) {
        this.dateValeur = dateValeur;
    }
}
