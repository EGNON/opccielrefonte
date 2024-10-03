package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeCompteDto extends Base {

    private Long idTypeCompte;
    private String intitule;
    private String code;

    public TypeCompteDto() {
    }

    public Long getIdTypeCompte() {
        return idTypeCompte;
    }

    public void setIdTypeCompte(Long idTypeCompte) {
        this.idTypeCompte = idTypeCompte;
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