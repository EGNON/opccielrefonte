package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "T_ClasseIb", schema = "Comptabilite")
public class ClasseIb extends Base {
    @Id
    @Column(length = 250)
    private String codeClasseIb;
	private String libelleClasseIb;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClasseIb classeIb = (ClasseIb) o;
        return Objects.equals(codeClasseIb, classeIb.codeClasseIb) && Objects.equals(libelleClasseIb, classeIb.libelleClasseIb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeClasseIb, libelleClasseIb);
    }

    public ClasseIb() {
    }

    public String getCodeClasseIb() {
        return codeClasseIb;
    }

    public void setCodeClasseIb(String codeClasseIb) {
        this.codeClasseIb = codeClasseIb;
    }

    public String getLibelleClasseIb() {
        return libelleClasseIb;
    }

    public void setLibelleClasseIb(String libelleClasseIb) {
        this.libelleClasseIb = libelleClasseIb;
    }
}
