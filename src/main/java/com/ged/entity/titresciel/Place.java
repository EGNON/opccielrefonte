package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Place", schema = "Titre")
public class Place extends Base {
    @Id
    private String codePlace;
    private String libellePlace;

    public Place() {
    }

    public Place(String codePlace, String libellePlace) {
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
