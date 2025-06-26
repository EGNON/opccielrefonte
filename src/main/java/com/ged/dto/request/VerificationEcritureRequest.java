package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.opcciel.comptabilite.TypeOperationDto;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VerificationEcritureRequest {
    private Long idSeance;
    private Long idOpcvm;
    private Long idOperation;
    private Long idTransaction;
    private TypeOperationDto typeOperationDto;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Boolean estVerifie1;
    private Boolean estVerifie2;
    private Boolean verificationNiveau;
    private String codeTypeOperation;
    private DatatableParameters datatableParameters;

    public String getCodeTypeOperation() {
        return codeTypeOperation;
    }

    public void setCodeTypeOperation(String codeTypeOperation) {
        this.codeTypeOperation = codeTypeOperation;
    }

    public Boolean getEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(Boolean estVerifie1) {
        this.estVerifie1 = estVerifie1;
    }

    public Boolean getVerificationNiveau() {
        return verificationNiveau;
    }

    public void setVerificationNiveau(Boolean verificationNiveau) {
        this.verificationNiveau = verificationNiveau;
    }

    public Boolean getEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(Boolean estVerifie2) {
        this.estVerifie2 = estVerifie2;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public TypeOperationDto getTypeOperationDto() {
        return typeOperationDto;
    }

    public void setTypeOperationDto(TypeOperationDto typeOperationDto) {
        this.typeOperationDto = typeOperationDto;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }
}
