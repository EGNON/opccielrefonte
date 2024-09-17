package com.ged.entity.opcciel;

import com.ged.entity.Base;
import com.ged.entity.opcciel.comptabilite.Ib;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_BanqueIBRUBPOS", schema = "Operation")
public class BanqueIBRUBPOS extends Base {
    @EmbeddedId
    private CleBanqueIBRUBPOS idBanqueIBRUBPOS;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeIB")
    @MapsId("codeIB")
    private Ib ib;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "codeRubrique")
//    @MapsId("codeRubrique")
//    private Rubrique rubrique;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "codePosition")
//    @MapsId("codePosition")
//    private Position position;

    public BanqueIBRUBPOS() {
    }

    public CleBanqueIBRUBPOS getIdBanqueIBRUBPOS() {
        return idBanqueIBRUBPOS;
    }

    public void setIdBanqueIBRUBPOS(CleBanqueIBRUBPOS idBanqueIBRUBPOS) {
        this.idBanqueIBRUBPOS = idBanqueIBRUBPOS;
    }

    public Ib getIb() {
        return ib;
    }

    public void setIb(Ib ib) {
        this.ib = ib;
    }
}
