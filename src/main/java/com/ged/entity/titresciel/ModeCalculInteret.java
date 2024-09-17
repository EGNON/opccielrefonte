package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_ModeCalculInteret", schema = "Titre")
public class ModeCalculInteret extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModeCalculInteret;
    @Column(name = "codeModeCalculInteret")
    private String codeModeCalculInteret;
    @Column(name = "libelleModeCalculInteret")
    private String libelleModeCalculInteret;

    public ModeCalculInteret() {
    }

    public Long getIdModeCalculInteret() {
        return idModeCalculInteret;
    }

    public void setIdModeCalculInteret(Long idModeCalculInteret) {
        this.idModeCalculInteret = idModeCalculInteret;
    }

    public String getCodeModeCalculInteret() {
        return codeModeCalculInteret;
    }

    public void setCodeModeCalculInteret(String codeModeCalculInteret) {
        this.codeModeCalculInteret = codeModeCalculInteret;
    }

    public String getLibelleModeCalculInteret() {
        return libelleModeCalculInteret;
    }

    public void setLibelleModeCalculInteret(String libelleModeCalculInteret) {
        this.libelleModeCalculInteret = libelleModeCalculInteret;
    }
}
