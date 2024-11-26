package com.ged.entity.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_TypeFormule", schema = "Comptabilite")
public class TypeFormule extends Base {
    @Id
    @Column(length = 16)
    private String codeTypeFormule;
    @OneToMany(
            mappedBy = "typeFormule",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<Formule> formules = new HashSet<>();

    public TypeFormule() {
    }

    public String getCodeTypeFormule() {
        return codeTypeFormule;
    }

    public void setCodeTypeFormule(String codeTypeFormule) {
        this.codeTypeFormule = codeTypeFormule;
    }

    public Set<Formule> getFormules() {
        return formules;
    }

    public void setFormules(Set<Formule> formules) {
        this.formules = formules;
    }
}
