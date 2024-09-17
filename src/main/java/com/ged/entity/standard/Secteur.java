package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "T_Secteur", schema = "Parametre")
public class Secteur extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSecteur;
    @Basic
    private String libelleSecteur;
    @OneToMany(mappedBy = "secteur", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<Personne> personnes = new HashSet<>();
    @OneToMany(mappedBy = "secteurEmp", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<PersonnePhysique> secteurEmpPersonnePhysiques = new HashSet<>();

    public Secteur() {
    }

    public Secteur(Long idSecteur, String libelleSecteur) {
        this.idSecteur = idSecteur;
        this.libelleSecteur = libelleSecteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secteur secteur = (Secteur) o;
        return Objects.equals(idSecteur, secteur.idSecteur) && Objects.equals(libelleSecteur, secteur.libelleSecteur) && Objects.equals(personnes, secteur.personnes) && Objects.equals(secteurEmpPersonnePhysiques, secteur.secteurEmpPersonnePhysiques);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSecteur, libelleSecteur, personnes, secteurEmpPersonnePhysiques);
    }

    public Long getIdSecteur() {
        return idSecteur;
    }

    public void setIdSecteur(Long idSecteur) {
        this.idSecteur = idSecteur;
    }

    public String getLibelleSecteur() {
        return libelleSecteur;
    }

    public void setLibelleSecteur(String libelleSecteur) {
        this.libelleSecteur = libelleSecteur;
    }

    public Set<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }

    public Set<PersonnePhysique> getSecteurEmpPersonnePhysiques() {
        return secteurEmpPersonnePhysiques;
    }

    public void setSecteurEmpPersonnePhysiques(Set<PersonnePhysique> secteurEmpPersonnePhysiques) {
        this.secteurEmpPersonnePhysiques = secteurEmpPersonnePhysiques;
    }
}
