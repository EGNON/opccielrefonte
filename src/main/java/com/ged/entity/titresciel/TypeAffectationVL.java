package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "T_TypeAffectationVL", schema = "Titre")
public class TypeAffectationVL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeAffectation;
    private String libelleTypeAffectationVL;

    public TypeAffectationVL() {
    }

    public Long getIdTypeAffectation() {
        return idTypeAffectation;
    }

    public void setIdTypeAffectation(Long idTypeAffectation) {
        this.idTypeAffectation = idTypeAffectation;
    }

    public String getLibelleTypeAffectationVL() {
        return libelleTypeAffectationVL;
    }

    public void setLibelleTypeAffectationVL(String libelleTypeAffectationVL) {
        this.libelleTypeAffectationVL = libelleTypeAffectationVL;
    }
}
