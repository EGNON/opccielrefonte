package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeClientDto extends Base {

    private Long idTypeClient;
    private String libelleTypeClient;
    private String codeTypeClient;

    public TypeClientDto() {
    }

    public Long getIdTypeClient() {
        return idTypeClient;
    }

    public void setIdTypeClient(Long idTypeClient) {
        this.idTypeClient = idTypeClient;
    }

    public String getLibelleTypeClient() {
        return libelleTypeClient;
    }

    public void setLibelleTypeClient(String libelleTypeClient) {
        this.libelleTypeClient = libelleTypeClient;
    }

    public String getCodeTypeClient() {
        return codeTypeClient;
    }

    public void setCodeTypeClient(String codeTypeClient) {
        this.codeTypeClient = codeTypeClient;
    }
}