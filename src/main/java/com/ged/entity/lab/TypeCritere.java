package com.ged.entity.lab;

import com.ged.entity.Base;
import com.ged.entity.standard.DiffusionAlerte;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "T_TypeCritere", schema = "Parametre")
public class TypeCritere extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeCritere;
    @Basic
    private String libelleTypeCritere;
    @OneToMany(mappedBy = "typeCritere")
    private Set<DiffusionAlerte> diffusionAlertes=new HashSet<>();
    public TypeCritere() {
    }

    public TypeCritere(Long idTypeCritere, String libelleTypeCritere) {
        this.idTypeCritere = idTypeCritere;
        this.libelleTypeCritere = libelleTypeCritere;
    }

    public Set<DiffusionAlerte> getDiffusionAlertes() {
        return diffusionAlertes;
    }

    public void setDiffusionAlertes(Set<DiffusionAlerte> diffusionAlertes) {
        this.diffusionAlertes = diffusionAlertes;
    }

    public Long getIdTypeCritere() {
        return idTypeCritere;
    }

    public void setIdTypeCritere(Long idTypeCritere) {
        this.idTypeCritere = idTypeCritere;
    }

    public String getLibelleTypeCritere() {
        return libelleTypeCritere;
    }

    public void setLibelleTypeCritere(String libelleTypeCritere) {
        this.libelleTypeCritere = libelleTypeCritere;
    }

    @Override
    public String toString() {
        return "TypeCritere [" +
                "idTypeCritere=" + idTypeCritere +
                ", libelleTypeCritere='" + libelleTypeCritere + '\'' +
                ']';
    }
}
