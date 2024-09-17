package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "T_Quartier",schema = "Parametre")
public class Quartier extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idQuartier;
    private String libelleQuartier;
    @ManyToOne
    @JoinColumn(name = "idArrondissement")
    private Arrondissement arrondissement;
    @OneToMany(mappedBy = "quartier")
    //@JsonManagedReference
    private Set<Personne> personnes;

    public Quartier() {
    }

    public Quartier(String libelleQuartier) {
        this.libelleQuartier = libelleQuartier;
    }

    public Set<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }

    public long getIdQuartier() {
        return idQuartier;
    }

    public void setIdQuartier(long idQuartier) {
        this.idQuartier = idQuartier;
    }

    public String getLibelleQuartier() {
        return libelleQuartier;
    }

    public void setLibelleQuartier(String libelleQuartier) {
        this.libelleQuartier = libelleQuartier;
    }

    public Arrondissement getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(Arrondissement arrondissement) {
        this.arrondissement = arrondissement;
    }
}
