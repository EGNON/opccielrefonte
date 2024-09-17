package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "T_Commune", schema = "Parametre")
public class Commune extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommune;
    private String libelleCommune;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDepartement", referencedColumnName = "idDepartement")
    //@JsonBackReference
    private Departement departement;
    @OneToMany(mappedBy = "commune", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<Arrondissement> arrondissements = new HashSet<>();

    @OneToMany(mappedBy = "commune", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<Ville> villes = new HashSet<>();

    public Commune() {
    }

    public Commune(String libelleCommune, Departement departement) {
        this.libelleCommune = libelleCommune;
        this.departement = departement;
    }

    public Long getIdCommune() {
        return idCommune;
    }

    public void setIdCommune(Long idCommune) {
        this.idCommune = idCommune;
    }

    public String getLibelleCommune() {
        return libelleCommune;
    }

    public void setLibelleCommune(String libelleCommune) {
        this.libelleCommune = libelleCommune;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Set<Arrondissement> getArrondissements() {
        return arrondissements;
    }

    public void setArrondissements(Set<Arrondissement> arrondissements) {
        this.arrondissements = arrondissements;
    }

    public Set<Ville> getVilles() {
        return villes;
    }

    public void setVilles(Set<Ville> villes) {
        this.villes = villes;
    }

    @Override
    public String toString() {
        return "Commune [" +
                "libelleCommune='" + libelleCommune + '\'' +
                ", departement=" + departement +
                ']';
    }
}
