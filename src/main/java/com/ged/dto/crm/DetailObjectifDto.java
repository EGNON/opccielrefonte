package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ged.dto.standard.CategoriePersonneDto;
import com.ged.dto.standard.PeriodiciteDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailObjectifDto {
    private CategoriePersonneDto categoriePersonne;
    private PeriodiciteDto periodicite;
    private IndicateurDto indicateur;
//    @JsonBackReference
    private ModeleObjectifDto modelObj;

    public DetailObjectifDto() {
    }

    public DetailObjectifDto(CategoriePersonneDto categoriePersonneDto, PeriodiciteDto periodiciteDto, IndicateurDto indicateurDto) {
        this.categoriePersonne = categoriePersonneDto;
        this.periodicite = periodiciteDto;
        this.indicateur = indicateurDto;
    }

    public CategoriePersonneDto getCategoriePersonne() {
        return categoriePersonne;
    }

    public void setCategoriePersonne(CategoriePersonneDto categoriePersonne) {
        this.categoriePersonne = categoriePersonne;
    }

    public PeriodiciteDto getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(PeriodiciteDto periodicite) {
        this.periodicite = periodicite;
    }

    public IndicateurDto getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(IndicateurDto indicateur) {
        this.indicateur = indicateur;
    }

    public ModeleObjectifDto getModelObj() {
        return modelObj;
    }

    public void setModelObj(ModeleObjectifDto modelObj) {
        this.modelObj = modelObj;
    }

    @Override
    public String toString() {
        return "DetailObjectifDto{" +
                "categoriePersonne=" + categoriePersonne +
                ", periodicite=" + periodicite +
                ", indicateur=" + indicateur +
                ", modelObj=" + modelObj +
                '}';
    }
}
