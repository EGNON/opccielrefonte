package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeAmortissement", schema = "Titre")
public class TypeAmortissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeAmortissement;
    private String codeTypeAmortissement;
    private String libelleAmortissement;

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

    public String getLibelleAmortissement() {
        return libelleAmortissement;
    }

    public void setLibelleAmortissement(String libelleAmortissement) {
        this.libelleAmortissement = libelleAmortissement;
    }
}
