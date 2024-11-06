package com.ged.entity.opcciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeOrdre", schema = "OrdreDeBourse")
public class TypeOrdre extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTypeOrdre;
	private String libelleTypeOrdre;

    public TypeOrdre() {
    }

    public long getIdTypeOrdre() {
        return idTypeOrdre;
    }

    public void setIdTypeOrdre(long idTypeOrdre) {
        this.idTypeOrdre = idTypeOrdre;
    }

    public String getLibelleTypeOrdre() {
        return libelleTypeOrdre;
    }

    public void setLibelleTypeOrdre(String libelleTypeOrdre) {
        this.libelleTypeOrdre = libelleTypeOrdre;
    }
}
