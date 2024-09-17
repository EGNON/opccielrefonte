package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "T_TypeAffectation", schema = "Titre")
public class TypeAffectationTitre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeAffectation;
    private String libelleTypeAffectation;

    public TypeAffectationTitre() {
    }

    public TypeAffectationTitre(Long idTypeAffectation, String libelleTypeAffectation) {
        this.idTypeAffectation = idTypeAffectation;
        this.libelleTypeAffectation = libelleTypeAffectation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeAffectationTitre that = (TypeAffectationTitre) o;
        return Objects.equals(idTypeAffectation, that.idTypeAffectation) && Objects.equals(libelleTypeAffectation, that.libelleTypeAffectation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeAffectation, libelleTypeAffectation);
    }

    public Long getIdTypeAffectation() {
        return idTypeAffectation;
    }

    public void setIdTypeAffectation(Long idTypeAffectation) {
        this.idTypeAffectation = idTypeAffectation;
    }

    public String getLibelleTypeAffectation() {
        return libelleTypeAffectation;
    }

    public void setLibelleTypeAffectation(String libelleTypeAffectation) {
        this.libelleTypeAffectation = libelleTypeAffectation;
    }
}
