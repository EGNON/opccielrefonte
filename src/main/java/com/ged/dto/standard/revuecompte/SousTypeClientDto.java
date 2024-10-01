package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SousTypeClientDto extends Base {
    private Long idSousTypeClient;
    private String libelleSousTypeClient;
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

    public String getLibelleSousTypeClient() {
        return libelleSousTypeClient;
    }

    public void setLibelleSousTypeClient(String libelleSousTypeClient) {
        this.libelleSousTypeClient = libelleSousTypeClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}