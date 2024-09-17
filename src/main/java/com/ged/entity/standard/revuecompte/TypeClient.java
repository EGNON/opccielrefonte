package com.ged.entity.standard.revuecompte;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeClient", schema = "Nomenclature")
public class TypeClient extends Base {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeClient;
    private String libelleTypeClient;
    private String code;

    public TypeClient() {
    }

    public Long getIdTypeClient() {
        return idTypeClient;
    }

    public void setIdTypeClient(Long idTypeClient) {
        this.idTypeClient = idTypeClient;
    }

    public String getLibelleTypeClient() {
        return libelleTypeClient;
    }

    public void setLibelleTypeClient(String libelleTypeClient) {
        this.libelleTypeClient = libelleTypeClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}