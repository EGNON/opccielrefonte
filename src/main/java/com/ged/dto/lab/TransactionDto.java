package com.ged.dto.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {
    private long IdOperation;
    private String sigleOpcvm;
    private String denominationOpcvm;
    private String denomination;
    private String codeNatureOperation;
    private double montant;
    private double qtePart;
    private String nomPays;
    private LocalDateTime dateOperation;
    private String libelleNatureOperation;
    public TransactionDto() {
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getLibelleNatureOperation() {
        return libelleNatureOperation;
    }

    public void setLibelleNatureOperation(String libelleNatureOperation) {
        this.libelleNatureOperation = libelleNatureOperation;
    }

    public long getIdOperation() {
        return IdOperation;
    }

    public void setIdOperation(long idOperation) {
        IdOperation = idOperation;
    }

    public String getSigleOpcvm() {
        return sigleOpcvm;
    }

    public void setSigleOpcvm(String sigleOpcvm) {
        this.sigleOpcvm = sigleOpcvm;
    }

    public String getDenominationOpcvm() {
        return denominationOpcvm;
    }

    public void setDenominationOpcvm(String denominationOpcvm) {
        this.denominationOpcvm = denominationOpcvm;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getQtePart() {
        return qtePart;
    }

    public void setQtePart(double qtePart) {
        this.qtePart = qtePart;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }
}
