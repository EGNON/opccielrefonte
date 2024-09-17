package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationCodeAnalytique {
    private String codeAnalytique;
    private String typeCodeAnalytique;
    private OperationDto operationDto;

    public OperationCodeAnalytique() {
    }

    public String getCodeAnalytique() {
        return codeAnalytique;
    }

    public void setCodeAnalytique(String codeAnalytique) {
        this.codeAnalytique = codeAnalytique;
    }

    public String getTypeCodeAnalytique() {
        return typeCodeAnalytique;
    }

    public void setTypeCodeAnalytique(String typeCodeAnalytique) {
        this.typeCodeAnalytique = typeCodeAnalytique;
    }

    public OperationDto getOperationDto() {
        return operationDto;
    }

    public void setOperationDto(OperationDto operationDto) {
        this.operationDto = operationDto;
    }

}
