package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentSeanceListeVerificationEcritureChargeRequest {
    private Long idOpcvm;
    private String codeTypeOperation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Boolean estVerifie1;
    private Boolean estVerifie2;
    private String idOperation;
    private DatatableParameters datatableParameters;
    private String letterDate;

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getCodeTypeOperation() {
        return codeTypeOperation;
    }

    public void setCodeTypeOperation(String codeTypeOperation) {
        this.codeTypeOperation = codeTypeOperation;
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

    public Boolean getEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(Boolean estVerifie1) {
        this.estVerifie1 = estVerifie1;
    }

    public Boolean getEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(Boolean estVerifie2) {
        this.estVerifie2 = estVerifie2;
    }

    public String getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(String idOperation) {
        this.idOperation = idOperation;
    }

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }

    public String getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(String letterDate) {
        this.letterDate = letterDate;
    }
}
