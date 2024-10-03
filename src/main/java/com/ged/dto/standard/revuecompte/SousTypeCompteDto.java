package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SousTypeCompteDto extends Base {
    private Long idSousTypeCompte;
    private String intitule;
    private String code;
    private TypeCompteDto typeCompte;
    public SousTypeCompteDto() {
    }

    public TypeCompteDto getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompteDto typeCompteDto) {
        this.typeCompte = typeCompteDto;
    }

    public Long getIdSousTypeCompte() {
        return idSousTypeCompte;
    }

    public void setIdSousTypeCompte(Long idSousTypeCompte) {
        this.idSousTypeCompte = idSousTypeCompte;
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