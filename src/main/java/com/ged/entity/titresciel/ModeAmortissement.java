package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_ModeAmortissement", schema = "Titre")
public class ModeAmortissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModeAmortissement;
    private String libelleModeAmortissement;

    public Long getIdModeAmortissement() {
        return idModeAmortissement;
    }

    public void setIdModeAmortissement(Long idModeAmortissement) {
        this.idModeAmortissement = idModeAmortissement;
    }

    public String getLibelleModeAmortissement() {
        return libelleModeAmortissement;
    }

    public void setLibelleModeAmortissement(String libelleModeAmortissement) {
        this.libelleModeAmortissement = libelleModeAmortissement;
    }
}
