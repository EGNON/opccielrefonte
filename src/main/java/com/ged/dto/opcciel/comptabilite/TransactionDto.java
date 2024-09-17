package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {
    private Long idTransaction;
    private Long idOcc;
    private OpcvmDto opcvm;
	private long idSeance;
    private LocalDateTime dateTransaction;
    private NatureOperationDto natureOperation;
    private boolean estVerifie;

    public TransactionDto() {
    }

    public long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(long idOcc) {
        this.idOcc = idOcc;
    }

    public long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(long idSeance) {
        this.idSeance = idSeance;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public NatureOperationDto getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperationDto natureOperation) {
        this.natureOperation = natureOperation;
    }

    public boolean isEstVerifie() {
        return estVerifie;
    }

    public void setEstVerifie(boolean estVerifie) {
        this.estVerifie = estVerifie;
    }
}
