package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_TypeOperation", schema = "Comptabilite")
public class TypeOperation extends Base {
    @Id
    @Column(length = 16)
    private String codeTypeOperation;
    private String libelleTypeOperation;

    public TypeOperation() {
    }

    public String getCodeTypeOperation() {
        return codeTypeOperation;
    }

    public void setCodeTypeOperation(String codeTypeOperation) {
        this.codeTypeOperation = codeTypeOperation;
    }

    public String getLibelleTypeOperation() {
        return libelleTypeOperation;
    }

    public void setLibelleTypeOperation(String libelleTypeOperation) {
        this.libelleTypeOperation = libelleTypeOperation;
    }
}
