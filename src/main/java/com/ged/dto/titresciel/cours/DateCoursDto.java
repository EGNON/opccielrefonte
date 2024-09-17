package com.ged.dto.titresciel.cours;

import java.time.LocalDateTime;

public class DateCoursDto {
    String codePlace;
    String libellePlace;
    LocalDateTime dateCours;

    public DateCoursDto() {
    }

    public DateCoursDto(String codePlace, String libellePlace, LocalDateTime dateCours) {
        this.codePlace = codePlace;
        this.libellePlace = libellePlace;
        this.dateCours = dateCours;
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

    public LocalDateTime getDateCours() {
        return dateCours;
    }

    public void setDateCours(LocalDateTime dateCours) {
        this.dateCours = dateCours;
    }
}
