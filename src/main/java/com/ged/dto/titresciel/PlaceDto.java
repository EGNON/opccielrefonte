package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.BaseDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDto extends BaseDto {
    private String codePlace;

    private String libellePlace;

    public PlaceDto() {
    }

    public PlaceDto(String codePlace, String libellePlace) {
        this.codePlace = codePlace;
        this.libellePlace = libellePlace;
    }

    public String getCodePlace() {
        return codePlace;
    }

    public void setCodePlace(String codePlace) {
        this.codePlace = codePlace;
    }

    public String getLibellePlace() {
        return libellePlace;
    }

    public void setLibellePlace(String libellePlace) {
        this.libellePlace = libellePlace;
    }
}
