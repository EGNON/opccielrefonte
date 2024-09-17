package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.CompteRenduDto;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProduitDto {
    private Long idProd;
    private String designation;
    private Set<CompteRenduDto> compteRendusProdASouscrireDtos = new HashSet<>();
    private Set<CompteRenduDto> compteRendusProdSouscritDtos = new HashSet<>();

    public ProduitDto() {
    }

    public ProduitDto(String designation) {
        this.designation = designation;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Set<CompteRenduDto> getCompteRendusProdASouscrireDtos() {
        return compteRendusProdASouscrireDtos;
    }

    public void setCompteRendusProdASouscrireDtos(Set<CompteRenduDto> compteRendusProdASouscrireDtos) {
        this.compteRendusProdASouscrireDtos = compteRendusProdASouscrireDtos;
    }

    public Set<CompteRenduDto> getCompteRendusProdSouscritDtos() {
        return compteRendusProdSouscritDtos;
    }

    public void setCompteRendusProdSouscritDtos(Set<CompteRenduDto> compteRendusProdSouscritDtos) {
        this.compteRendusProdSouscritDtos = compteRendusProdSouscritDtos;
    }

    @Override
    public String toString() {
        return "Produit [designation='" + designation + ']';
    }
}
