package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeObligation", schema = "Titre")
public class TypeObligation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeObligation;
    private String codeTypeObligation;
    private String libelleTypeObligation;

    public Long getIdTypeObligation() {
        return idTypeObligation;
    }

    public void setIdTypeObligation(Long idTypeObligation) {
        this.idTypeObligation = idTypeObligation;
    }

    public String getCodeTypeObligation() {
        return codeTypeObligation;
    }

    public void setCodeTypeObligation(String codeTypeObligation) {
        this.codeTypeObligation = codeTypeObligation;
    }

    public String getLibelleTypeObligation() {
        return libelleTypeObligation;
    }

    public void setLibelleTypeObligation(String libelleTypeObligation) {
        this.libelleTypeObligation = libelleTypeObligation;
    }
}
