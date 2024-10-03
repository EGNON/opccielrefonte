package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SousTypeClientDto extends Base {
    private Long idSousTypeClient;
    private String intitule;
    private String code;
    private TypeClientDto typeClient;

    public SousTypeClientDto() {
    }

    public TypeClientDto getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(TypeClientDto typeClientDto) {
        this.typeClient = typeClientDto;
    }

    public Long getIdSousTypeClient() {
        return idSousTypeClient;
    }

    public void setIdSousTypeClient(Long idSousTypeClient) {
        this.idSousTypeClient = idSousTypeClient;
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