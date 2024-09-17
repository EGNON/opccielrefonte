package com.ged.entity.opcciel.comptabilite;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleModeleEcritureFormule implements Serializable {
    private String codeModeleEcriture;
    private Long idFormule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleModeleEcritureFormule that = (CleModeleEcritureFormule) o;
        return Objects.equals(codeModeleEcriture, that.codeModeleEcriture) && Objects.equals(idFormule, that.idFormule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeModeleEcriture, idFormule);
    }

    public CleModeleEcritureFormule() {
    }

    public String getCodeModeleEcriture() {
        return codeModeleEcriture;
    }

    public void setCodeModeleEcriture(String codeModeleEcriture) {
        this.codeModeleEcriture = codeModeleEcriture;
    }

    public Long getIdFormule() {
        return idFormule;
    }

    public void setIdFormule(Long idFormule) {
        this.idFormule = idFormule;
    }
}
