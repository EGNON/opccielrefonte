package com.ged.entity.standard.revuecompte;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeCompte", schema = "Nomenclature")
public class TypeCompte extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeCompte;
    private String libelleTypeCompte;
    private String code;

    public TypeCompte() {
    }

    public Long getIdTypeCompte() {
        return idTypeCompte;
    }

    public void setIdTypeCompte(Long idTypeCompte) {
        this.idTypeCompte = idTypeCompte;
    }

    public String getLibelleTypeCompte() {
        return libelleTypeCompte;
    }

    public void setLibelleTypeCompte(String libelleTypeCompte) {
        this.libelleTypeCompte = libelleTypeCompte;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}