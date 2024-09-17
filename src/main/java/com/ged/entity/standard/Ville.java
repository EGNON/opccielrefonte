package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Ville",schema = "Parametre")
public class Ville extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVille;
    private String libelleVille;
    @ManyToOne
    @JoinColumn(name = "idCommune")
    //@JsonBackReference
    private Commune commune;

    public Ville() {
    }

    public Ville(String libelleVille) {
        this.libelleVille = libelleVille;
    }

    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }

    public String getLibelleVille() {
        return libelleVille;
    }

    public void setLibelleVille(String libelleVille) {
        this.libelleVille = libelleVille;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }
}
