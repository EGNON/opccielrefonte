package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "T_Monnaie", schema = "Parametre")
public class Monnaie extends Base {
    @Id
    @Column(nullable = false, length = 8)
    private String codeMonnaie;
    @Basic
    private String nom;
    @OneToMany(mappedBy = "monnaie", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<Pays> pays = new HashSet<>();

    public Monnaie() {}

    public Monnaie(String codeMonnaie, String nom) {
        this.codeMonnaie = codeMonnaie;
        this.nom = nom;
    }

    public String getCodeMonnaie() {
        return codeMonnaie;
    }

    public void setCodeMonnaie(String codeMonnaie) {
        this.codeMonnaie = codeMonnaie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Pays> getPays() {
        return pays;
    }

    public void setPays(Set<Pays> pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Monnaie codeMonnaie='" + codeMonnaie + "'";
    }
}
