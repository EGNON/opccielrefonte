package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_PaysLangue",schema = "Parametre")
public class PaysLangue extends Base {
    @EmbeddedId
    private ClePaysLangue idPaysLangue;
    @ManyToOne
    @JoinColumn(name = "idLangue")
    @MapsId("idLangue")
    private Langue langue;
    @ManyToOne
    @JoinColumn(name = "idPays")
    @MapsId("idPays")
    //@JsonBackReference
    private Pays pays;

    public PaysLangue() {
    }

    public ClePaysLangue getIdPaysLangue() {
        return idPaysLangue;
    }

    public void setIdPaysLangue(ClePaysLangue idPaysLangue) {
        this.idPaysLangue = idPaysLangue;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

}
