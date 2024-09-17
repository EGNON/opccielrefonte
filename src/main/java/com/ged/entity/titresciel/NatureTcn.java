package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_NatureTcn", schema = "Titre")
public class NatureTcn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNatureTcn;
    private String codeNatureTcn;
    private String libelleNatureTcn;

    public Long getIdNatureTcn() {
        return idNatureTcn;
    }

    public void setIdNatureTcn(Long idNatureTcn) {
        this.idNatureTcn = idNatureTcn;
    }

    public String getCodeNatureTcn() {
        return codeNatureTcn;
    }

    public void setCodeNatureTcn(String codeNatureTcn) {
        this.codeNatureTcn = codeNatureTcn;
    }

    public String getLibelleNatureTcn() {
        return libelleNatureTcn;
    }

    public void setLibelleNatureTcn(String libelleNatureTcn) {
        this.libelleNatureTcn = libelleNatureTcn;
    }
}
