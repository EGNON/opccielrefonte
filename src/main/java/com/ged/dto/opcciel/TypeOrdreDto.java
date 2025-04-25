package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;
import jakarta.persistence.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeOrdreDto extends Base {
    private long idTypeOrdre;
	private String libelleTypeOrdre;

    public TypeOrdreDto() {
    }

    public long getIdTypeOrdre() {
        return idTypeOrdre;
    }

    public void setIdTypeOrdre(long idTypeOrdre) {
        this.idTypeOrdre = idTypeOrdre;
    }

    public String getLibelleTypeOrdre() {
        return libelleTypeOrdre;
    }

    public void setLibelleTypeOrdre(String libelleTypeOrdre) {
        this.libelleTypeOrdre = libelleTypeOrdre;
    }
}
