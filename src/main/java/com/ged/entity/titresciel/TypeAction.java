package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeAction", schema = "Titre")
public class TypeAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeAction;
    private String codeTypeAction;
    private String libelleTypeAction;

    public Long getIdTypeAction() {
        return idTypeAction;
    }

    public void setIdTypeAction(Long idTypeAction) {
        this.idTypeAction = idTypeAction;
    }

    public String getCodeTypeAction() {
        return codeTypeAction;
    }

    public void setCodeTypeAction(String codeTypeAction) {
        this.codeTypeAction = codeTypeAction;
    }

    public String getLibelleTypeAction() {
        return libelleTypeAction;
    }

    public void setLibelleTypeAction(String libelleTypeAction) {
        this.libelleTypeAction = libelleTypeAction;
    }
}
