package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_TypeRubrique", schema = "Comptabilite")
public class TypeRubrique extends Base {
    @Id
    @Column(length = 250)
    private String codeTypeRubrique;

    public TypeRubrique() {
    }

    public String getCodeTypeRubrique() {
        return codeTypeRubrique;
    }

    public void setCodeTypeRubrique(String codeTypeRubrique) {
        this.codeTypeRubrique = codeTypeRubrique;
    }
}
