package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategorieClientDto extends Base {
    private Long idCategorieClient;
    private String intitule;
    private String code;

    public CategorieClientDto() {
    }

    public Long getIdCategorieClient() {
        return idCategorieClient;
    }

    public void setIdCategorieClient(Long idCategorieClient) {
        this.idCategorieClient = idCategorieClient;
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