package com.ged.entity.standard.revuecompte;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_CategorieClient", schema = "Nomenclature")
public class CategorieClient extends Base {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorieClient;
    private String libelleCategorieClient;
    @Column(length = 16)
    private String code;

    public CategorieClient() {
    }

    public Long getIdCategorieClient() {
        return idCategorieClient;
    }

    public void setIdCategorieClient(Long idCategorieClient) {
        this.idCategorieClient = idCategorieClient;
    }

    public String getLibelleCategorieClient() {
        return libelleCategorieClient;
    }

    public void setLibelleCategorieClient(String libelleCategorieClient) {
        this.libelleCategorieClient = libelleCategorieClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}