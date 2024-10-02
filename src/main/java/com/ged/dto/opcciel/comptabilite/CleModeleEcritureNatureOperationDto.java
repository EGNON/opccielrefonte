package com.ged.dto.opcciel.comptabilite;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

public class CleModeleEcritureNatureOperationDto {
    private String codeModeleEcriture;
    private String codeNatureOperation;
    private String codeTypeTitre;

    public CleModeleEcritureNatureOperationDto() {
    }

    public String getCodeModeleEcriture() {
        return codeModeleEcriture;
    }

    public void setCodeModeleEcriture(String codeModeleEcriture) {
        this.codeModeleEcriture = codeModeleEcriture;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public String getCodeTypeTitre() {
        return codeTypeTitre;
    }

    public void setCodeTypeTitre(String codeTypeTitre) {
        this.codeTypeTitre = codeTypeTitre;
    }
}
