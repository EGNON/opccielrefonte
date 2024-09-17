package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_NormalAssimile", schema = "Titre")
public class NormalAssimile extends Base {
    @Id
    private String codeNormalAssimile;
    private String libelleNormalAssimile;

    public NormalAssimile() {
    }

    public NormalAssimile(String codeNormalAssimile, String libelleNormalAssimile) {
        this.codeNormalAssimile = codeNormalAssimile;
        this.libelleNormalAssimile = libelleNormalAssimile;
    }

    public String getCodeNormalAssimile() {
        return codeNormalAssimile;
    }

    public void setCodeNormalAssimile(String codeNormalAssimile) {
        this.codeNormalAssimile = codeNormalAssimile;
    }

    public String getLibelleNormalAssimile() {
        return libelleNormalAssimile;
    }

    public void setLibelleNormalAssimile(String libelleNormalAssimile) {
        this.libelleNormalAssimile = libelleNormalAssimile;
    }
}
