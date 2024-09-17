package com.ged.dto.standard.revuecompte;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SousTypeCompteDto extends Base {
    private Long idSousTypeCompte;
    private String libelleSousTypeCompte;
    private String codeSousTypeCompte;
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

    public String getLibelleSousTypeCompte() {
        return libelleSousTypeCompte;
    }

    public void setLibelleSousTypeCompte(String libelleSousTypeCompte) {
        this.libelleSousTypeCompte = libelleSousTypeCompte;
    }

    public String getCodeSousTypeCompte() {
        return codeSousTypeCompte;
    }

    public void setCodeSousTypeCompte(String codeSousTypeCompte) {
        this.codeSousTypeCompte = codeSousTypeCompte;
    }
}