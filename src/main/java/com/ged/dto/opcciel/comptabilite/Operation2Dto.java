package com.ged.dto.opcciel.comptabilite;

import java.time.LocalDateTime;

public class Operation2Dto {
    private long idOperation;
    private String denomination;
    private String denominationOpcvm;
    private double montantDepose;
    private double total;
    private LocalDateTime datePremiereSouscription;
    private LocalDateTime dateOperation;

    public Operation2Dto() {
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(long idOperation) {
        this.idOperation = idOperation;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDenominationOpcvm() {
        return denominationOpcvm;
    }

    public void setDenominationOpcvm(String denominationOpcvm) {
        this.denominationOpcvm = denominationOpcvm;
    }

    public double getMontantDepose() {
        return montantDepose;
    }

    public void setMontantDepose(double montantDepose) {
        this.montantDepose = montantDepose;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getDatePremiereSouscription() {
        return datePremiereSouscription;
    }

    public void setDatePremiereSouscription(LocalDateTime datePremiereSouscription) {
        this.datePremiereSouscription = datePremiereSouscription;
    }
}
