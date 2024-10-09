package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Compartiments", schema = "Titre")
public class Compartiment extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompartiment;
    private String libelleCompartiment;

    public Compartiment() {
    }

    public Compartiment(String libelleCompartiment) {
        this.libelleCompartiment = libelleCompartiment;
    }

    public Long getIdCompartiment() {
        return idCompartiment;
    }

    public void setIdCompartiment(Long idCompartiment) {
        this.idCompartiment = idCompartiment;
    }

    public String getLibelleCompartiment() {
        return libelleCompartiment;
    }

    public void setLibelleCompartiment(String libelleCompartiment) {
        this.libelleCompartiment = libelleCompartiment;
    }
}
