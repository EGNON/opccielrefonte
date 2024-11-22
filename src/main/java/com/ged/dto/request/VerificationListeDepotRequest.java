package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.SeanceOpcvmDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VerificationListeDepotRequest {
    private SeanceOpcvmDto seanceOpcvmDto;
    private OpcvmDto opcvmDto;
    private DatatableParameters datatableParameters;
    private Boolean estVerifier;
    private Boolean estVerifie1;
    private Boolean estVerifie2;

    public SeanceOpcvmDto getSeanceOpcvmDto() {
        return seanceOpcvmDto;
    }

    public void setSeanceOpcvmDto(SeanceOpcvmDto seanceOpcvmDto) {
        this.seanceOpcvmDto = seanceOpcvmDto;
    }

    public OpcvmDto getOpcvmDto() {
        return opcvmDto;
    }

    public void setOpcvmDto(OpcvmDto opcvmDto) {
        this.opcvmDto = opcvmDto;
    }

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }

    public Boolean getEstVerifier() {
        return estVerifier;
    }

    public void setEstVerifier(Boolean estVerifier) {
        this.estVerifier = estVerifier;
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
}
