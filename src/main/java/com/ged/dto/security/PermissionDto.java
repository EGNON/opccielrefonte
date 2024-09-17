package com.ged.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionDto {
    private Long idPermis;
    private String codePermis;
    private Boolean estPrincipale;
    private Boolean estParDefaut;
    private String libellePermis;
    private String description;

    public PermissionDto() {
    }

    public Long getId() {
        return idPermis;
    }

    public Long getIdPermis() {
        return idPermis;
    }

    public String getCodePermis() {
        return codePermis;
    }

    public void setCodePermis(String codePermis) {
        if(!codePermis.contains("ALLOW_"))
            codePermis = "ALLOW_" + codePermis;
        this.codePermis = codePermis;
    }

    public void setIdPermis(Long idPermis) {
        this.idPermis = idPermis;
    }

    public Boolean getEstPrincipale() {
        return estPrincipale;
    }

    public void setEstPrincipale(Boolean estPrincipale) {
        this.estPrincipale = estPrincipale;
    }

    public Boolean getEstParDefaut() {
        return estParDefaut;
    }

    public void setEstParDefaut(Boolean estParDefaut) {
        this.estParDefaut = estParDefaut;
    }

    public String getLibellePermis() {
        return libellePermis;
    }

    public void setLibellePermis(String libellePermis) {
        this.libellePermis = libellePermis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PermissionDto{" +
                "idPermis=" + idPermis +
                ", codePermis='" + codePermis + '\'' +
                ", estPrincipale=" + estPrincipale +
                ", libellePermis='" + libellePermis + '\'' +
                '}';
    }
}
