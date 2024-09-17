package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_Banque", schema = "Parametre")
public class Banque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBanque;
    private String codeBanque;
    private String nomBanque;

    public Long getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(Long idBanque) {
        this.idBanque = idBanque;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }
}
