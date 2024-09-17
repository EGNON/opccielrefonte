package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeModele", schema = "Parametre")
public class TypeModele extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeModele;
    @Basic
    private String libelleTypeModele;

    public TypeModele() {
    }

    public Long getIdTypeModele() {
        return idTypeModele;
    }

    public void setIdTypeModele(Long idTypeModele) {
        this.idTypeModele = idTypeModele;
    }

    public String getLibelleTypeModele() {
        return libelleTypeModele;
    }

    public void setLibelleTypeModele(String libelleTypeModele) {
        this.libelleTypeModele = libelleTypeModele;
    }
}
