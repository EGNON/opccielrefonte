package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeAmortissement", schema = "Titre")
public class TypeAmortissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeAmortissement;
    private String codeTypeAmortissement;
    private String libelleTypeAmortissement;

    public Long getIdTypeAmortissement() {
        return idTypeAmortissement;
    }

    public void setIdTypeAmortissement(Long idTypeAmortissement) {
        this.idTypeAmortissement = idTypeAmortissement;
    }

    public String getCodeTypeAmortissement() {
        return codeTypeAmortissement;
    }

    public void setCodeTypeAmortissement(String codeTypeAmortissement) {
        this.codeTypeAmortissement = codeTypeAmortissement;
    }

    public String getLibelleTypeAmortissement() {
        return libelleTypeAmortissement;
    }

    public void setLibelleTypeAmortissement(String libelleTypeAmortissement) {
        this.libelleTypeAmortissement = libelleTypeAmortissement;
    }
}
