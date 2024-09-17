package com.ged.entity.standard;

import com.ged.entity.Base;

import jakarta.persistence.*;

@Entity
@Table(name = "T_Arrondissement", schema = "Parametre")
public class Arrondissement extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArrondissement;
    private String libelleArrondissement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCommune", referencedColumnName = "idCommune")
    //@JsonBackReference
    private Commune commune;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVille", referencedColumnName = "idVille")
    private Ville ville;

    public Arrondissement() {
    }

    public Arrondissement(String libelleArrondissement, Commune commune) {
        this.libelleArrondissement = libelleArrondissement;
        this.commune = commune;
    }

    public Long getIdArrondissement() {
        return idArrondissement;
    }

    public void setIdArrondissement(Long idArrondissement) {
        this.idArrondissement = idArrondissement;
    }

    public String getLibelleArrondissement() {
        return libelleArrondissement;
    }

    public void setLibelleArrondissement(String libelleArrondissement) {
        this.libelleArrondissement = libelleArrondissement;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    @Override
    public String toString() {
        return "Arrondissement [" +
                "idArrondissement=" + idArrondissement +
                ", libelleArrondissement='" + libelleArrondissement + ']';
    }
}
