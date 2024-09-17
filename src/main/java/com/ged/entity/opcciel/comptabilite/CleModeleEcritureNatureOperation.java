package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleModeleEcritureNatureOperation implements Serializable {
    private String codeModeleEcriture;
    private String codeNatureOperation;
    private String codeTypeTitre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleModeleEcritureNatureOperation that = (CleModeleEcritureNatureOperation) o;
        return Objects.equals(codeModeleEcriture, that.codeModeleEcriture) && Objects.equals(codeNatureOperation, that.codeNatureOperation) && Objects.equals(codeTypeTitre, that.codeTypeTitre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeModeleEcriture, codeNatureOperation, codeTypeTitre);
    }

    public CleModeleEcritureNatureOperation() {
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
