package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeClientDto extends Base {

    private Long idTypeClient;
    private String intitule;
    private String code;

    public TypeClientDto() {
    }

    public Long getIdTypeClient() {
        return idTypeClient;
    }

    public void setIdTypeClient(Long idTypeClient) {
        this.idTypeClient = idTypeClient;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}