package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_SousTypeAction", schema = "Titre")
public class SousTypeAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousTypeAction;
    private String codeSousTypeAction;
    private String libelleSousTypeAction;

    public Long getIdSousTypeAction() {
        return idSousTypeAction;
    }

    public void setIdSousTypeAction(Long idSousTypeAction) {
        this.idSousTypeAction = idSousTypeAction;
    }

    public String getCodeSousTypeAction() {
        return codeSousTypeAction;
    }

    public void setCodeSousTypeAction(String codeSousTypeAction) {
        this.codeSousTypeAction = codeSousTypeAction;
    }

    public String getLibelleSousTypeAction() {
        return libelleSousTypeAction;
    }

    public void setLibelleSousTypeAction(String libelleSousTypeAction) {
        this.libelleSousTypeAction = libelleSousTypeAction;
    }
}
