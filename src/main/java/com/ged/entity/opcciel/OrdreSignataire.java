package com.ged.entity.opcciel;

import com.ged.entity.Base;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.TypeAmortissement;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TJ_OrdreSignataire", schema = "OrdreDeBourse")
public class OrdreSignataire extends Base {
    @EmbeddedId
    private CleOrdre idOrdre;
    @ManyToOne()
    @JoinColumn(name = "idOrdre")
    @MapsId("idOrdre")
    private Ordre ordre;
    @ManyToOne()
    @JoinColumn(name = "idPersonne")
    @MapsId("idPersonne")
    private Personne personne;
    private String userLogin;
    public OrdreSignataire() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public CleOrdre getIdOrdre() {
        return idOrdre;
    }

    public void setIdOrdre(CleOrdre idOrdre) {
        this.idOrdre = idOrdre;
    }

    public Ordre getOrdre() {
        return ordre;
    }

    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
}
