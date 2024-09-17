package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_FormeJuridiqueOPC", schema = "Titre")
public class FormeJuridiqueOpc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormeJuridiqueOpc;
    private String codeFormeJuridiqueOpc;
    private String libelleFormeJuridiqueOpc;

    public Long getIdFormeJuridiqueOpc() {
        return idFormeJuridiqueOpc;
    }

    public void setIdFormeJuridiqueOpc(Long idFormeJuridiqueOpc) {
        this.idFormeJuridiqueOpc = idFormeJuridiqueOpc;
    }

    public String getCodeFormeJuridiqueOpc() {
        return codeFormeJuridiqueOpc;
    }

    public void setCodeFormeJuridiqueOpc(String codeFormeJuridiqueOpc) {
        this.codeFormeJuridiqueOpc = codeFormeJuridiqueOpc;
    }

    public String getLibelleFormeJuridiqueOpc() {
        return libelleFormeJuridiqueOpc;
    }

    public void setLibelleFormeJuridiqueOpc(String libelleFormeJuridiqueOpc) {
        this.libelleFormeJuridiqueOpc = libelleFormeJuridiqueOpc;
    }
}
