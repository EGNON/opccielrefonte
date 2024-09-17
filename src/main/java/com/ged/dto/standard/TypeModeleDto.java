package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeModeleDto extends Base {
    private Long idTypeModele;

    private String libelleTypeModele;

    public TypeModeleDto() {
    }

    public TypeModeleDto(String libelleTypeModele) {
        this.libelleTypeModele = libelleTypeModele;
    }

    public Long getIdTypeModele() {
        return idTypeModele;
    }

    public void setIdTypeModele(Long idTypeModele) {
        this.idTypeModele = idTypeModele;
    }

    public String getLibelleTypeModele() {
        return libelleTypeModele;
    }

    public void setLibelleTypeModele(String libelleTypeModele) {
        this.libelleTypeModele = libelleTypeModele;
    }
}
