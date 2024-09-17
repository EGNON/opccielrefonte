package com.ged.entity.crm;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_ModelObjectif", schema = "Objectif")
public class ModeleObjectif extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModelObj;
    private String nomModele;

    @OneToMany(orphanRemoval = true, cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "modelObj")
    //@JsonManagedReference
    private Set<DetailObjectif> detailObjectifs = new HashSet<>();

    public void ajouterDetail(DetailObjectif detailObjectif)
    {
        this.detailObjectifs.add(detailObjectif);
        detailObjectif.setModelObj(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModeleObjectif that = (ModeleObjectif) o;
        return Objects.equals(idModelObj, that.idModelObj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModelObj);
    }

    public Long getIdModelObj() {
        return idModelObj;
    }

    public void setIdModelObj(Long idModelObj) {
        this.idModelObj = idModelObj;
    }

    public Set<DetailObjectif> getDetailObjectifs() {
        return detailObjectifs;
    }

    public void setDetailObjectifs(Set<DetailObjectif> detailObjectifs) {
        this.detailObjectifs = detailObjectifs;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    @Override
    public String toString() {
        return "ModeleObjectif{" +
                "idModelObj=" + idModelObj +
                ", detailObjectifs=" + detailObjectifs +
                '}';
    }
}
