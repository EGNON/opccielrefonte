package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SousCategorieDto extends Base {
    private Long idSousCategorie;
    private String libelleSousCategorie;
    private CategoriePersonneDto categoriePersonne;

    public SousCategorieDto() {
    }

    public SousCategorieDto(String libelle) {
        this.libelleSousCategorie = libelle;
    }

    public Long getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(Long idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public String getLibelleSousCategorie() {
        return libelleSousCategorie;
    }

    public void setLibelleSousCategorie(String libelleSousCategorie) {
        this.libelleSousCategorie = libelleSousCategorie;
    }

    public CategoriePersonneDto getCategoriePersonne() {
        return categoriePersonne;
    }

    public void setCategoriePersonne(CategoriePersonneDto categoriePersonne) {
        this.categoriePersonne = categoriePersonne;
    }
}
