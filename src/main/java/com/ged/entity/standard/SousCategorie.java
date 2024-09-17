package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_SousCategorie", schema = "Titre")
public class SousCategorie extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousCategorie;
    @Basic
    private String libelleSousCategorie;
    @ManyToOne
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    private CategoriePersonne categoriePersonne;

    public SousCategorie() {
    }

    public SousCategorie(String libelle) {
        this.libelleSousCategorie = libelle;
    }

    public Long getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(Long idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public String getLibelleSousCategorie() {
        return libelleSousCategorie;
    }

    public void setLibelleSousCategorie(String libelleSousCategorie) {
        this.libelleSousCategorie = libelleSousCategorie;
    }

    public CategoriePersonne getCategoriePersonne() {
        return categoriePersonne;
    }

    public void setCategoriePersonne(CategoriePersonne categoriePersonne) {
        this.categoriePersonne = categoriePersonne;
    }
}
