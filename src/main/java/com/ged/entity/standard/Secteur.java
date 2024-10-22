package com.ged.entity.standard;

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
