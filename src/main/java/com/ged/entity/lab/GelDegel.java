package com.ged.entity.lab;

import com.ged.entity.Base;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GelDegel", schema = "Parametre")
public class  GelDegel extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGelDegel;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean estGele;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonne")
    private Personne personne;

    public GelDegel() {
    }

    public GelDegel(Long idGelDegel, LocalDateTime dateDebut, boolean estGele, Personne personne) {
        this.idGelDegel = idGelDegel;
        this.dateDebut = dateDebut;
        this.estGele = estGele;
        this.personne = personne;
    }

    public Long getIdGelDegel() {
        return idGelDegel;
    }

    public void setIdGelDegel(Long idGelDegel) {
        this.idGelDegel = idGelDegel;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isEstGele() {
        return estGele;
    }

    public void setEstGele(boolean estGele) {
        this.estGele = estGele;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "GelDegel [" +
                "idGelDegel=" + idGelDegel +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", estGele=" + estGele +
                ", personne=" + personne +
                ']';
    }
}
