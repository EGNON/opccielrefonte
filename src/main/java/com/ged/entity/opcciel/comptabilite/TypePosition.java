package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_TypePosition", schema = "Comptabilite")
public class TypePosition extends Base {
    @Id
    @Column(length = 250)
    private String codeTypePosition;
    private String libelleTypePosition;

    public TypePosition() {
    }

    public String getCodeTypePosition() {
        return codeTypePosition;
    }

    public void setCodeTypePosition(String codeTypePosition) {
        this.codeTypePosition = codeTypePosition;
    }

    public String getLibelleTypePosition() {
        return libelleTypePosition;
    }

    public void setLibelleTypePosition(String libelleTypePosition) {
        this.libelleTypePosition = libelleTypePosition;
    }
}
