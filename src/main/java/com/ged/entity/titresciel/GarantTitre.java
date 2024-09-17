package com.ged.entity.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_GarantTitre", schema = "Titre")
public class GarantTitre extends Base {
    @EmbeddedId
    private CleGarantTitre idGarantTitre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTitre")
    @MapsId("idTitre")
    @JsonIgnore
    private Titre titre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGarant")
    @MapsId("idGarant")
    @JsonIgnore
    private Garant garant;
    private double pourcentage;

    public GarantTitre() {
    }

    public CleGarantTitre getIdGarantTitre() {
        return idGarantTitre;
    }

    public void setIdGarantTitre(CleGarantTitre idGarantTitre) {
        this.idGarantTitre = idGarantTitre;
    }

    public Garant getGarant() {
        return garant;
    }

    public void setGarant(Garant garant) {
        this.garant = garant;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }
}
