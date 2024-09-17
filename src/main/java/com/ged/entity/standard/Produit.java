package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ged.entity.Base;
import com.ged.entity.crm.CompteRendu;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Produit", schema = "Parametre")
public class Produit extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProd;
    private String designation;
    @OneToMany(mappedBy = "produitASouscrire", fetch = FetchType.LAZY)
    //@JsonManagedReference(value = "compteRendusProdASouscrire")
    private Set<CompteRendu> compteRendusProdASouscrire = new HashSet<>();
    @OneToMany(mappedBy = "produitSouscrit", fetch = FetchType.LAZY)
    //@JsonManagedReference(value = "compteRendusProdSouscrit")
    private Set<CompteRendu> compteRendusProdSouscrit = new HashSet<>();

    public Produit() {
    }

    public Produit(String designation) {
        this.designation = designation;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Set<CompteRendu> getCompteRendusProdASouscrire() {
        return compteRendusProdASouscrire;
    }

    public void setCompteRendusProdASouscrire(Set<CompteRendu> compteRendusProdASouscrire) {
        this.compteRendusProdASouscrire = compteRendusProdASouscrire;
    }

    public Set<CompteRendu> getCompteRendusProdSouscrit() {
        return compteRendusProdSouscrit;
    }

    public void setCompteRendusProdSouscrit(Set<CompteRendu> compteRendusProdSouscrit) {
        this.compteRendusProdSouscrit = compteRendusProdSouscrit;
    }

    @Override
    public String toString() {
        return "Produit [designation='" + designation + ']';
    }
}
