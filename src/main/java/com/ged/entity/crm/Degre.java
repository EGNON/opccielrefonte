package com.ged.entity.crm;

import com.ged.entity.Base;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "T_Degre", schema = "Parametre")
public class Degre extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDegre;
    @Basic
    private String libelle;
    @OneToMany(mappedBy = "degre", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<Personne> personnes = new HashSet<>();

    public Degre() {
    }

    public Degre(Long idDegre, String libelle) {
        this.idDegre = idDegre;
        this.libelle = libelle;
    }

    public Long getIdDegre() {
        return idDegre;
    }

    public void setIdDegre(Long idDegre) {
        this.idDegre = idDegre;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }

    @Override
    public String toString() {
        return "Degre{" +
                "idDegre=" + idDegre +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
