package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.crm.DetailObjectif;
import com.ged.entity.crm.ObjectifAffecte;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "T_CategoriePersonne", schema = "Parametre")
public class CategoriePersonne extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorie;
    @Basic
    private String libelle;
    @OneToMany(mappedBy = "categoriePersonne")
    //@JsonManagedReference
    private Set<DetailObjectif> detailObjectifs = new HashSet<>();
    @OneToMany(mappedBy = "categoriePersonne")
    //@JsonManagedReference
    private Set<ObjectifAffecte> objectifAffectes;

    public CategoriePersonne() {
    }

    public CategoriePersonne(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<DetailObjectif> getDetailObjectifs() {
        return detailObjectifs;
    }

    public void setDetailObjectifs(Set<DetailObjectif> detailObjectifs) {
        this.detailObjectifs = detailObjectifs;
    }

    public Set<ObjectifAffecte> getObjectifAffectes() {
        return objectifAffectes;
    }

    public void setObjectifAffectes(Set<ObjectifAffecte> objectifAffectes) {
        this.objectifAffectes = objectifAffectes;
    }

    @Override
    public String toString() {
        return "CategoriePersonne [" +
                "idCategorie=" + idCategorie +
                ", libelle='" + libelle + '\'' +
                ']';
    }
}
