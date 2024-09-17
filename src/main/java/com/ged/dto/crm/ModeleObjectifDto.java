package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeleObjectifDto {
    private Long idModelObj;
    private String nomModele;
    //@JsonManagedReference
    private Set<DetailObjectifDto> detailObjectifs = new HashSet<>();

    public Long getIdModelObj() {
        return idModelObj;
    }

    public void setIdModelObj(Long idModelObj) {
        this.idModelObj = idModelObj;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public Set<DetailObjectifDto> getDetailObjectifs() {
        return detailObjectifs;
    }

    public void setDetailObjectifs(Set<DetailObjectifDto> detailObjectifs) {
        this.detailObjectifs = detailObjectifs;
    }

    @Override
    public String toString() {
        return "ModeleObjectifDto{" +
                "idModelObj=" + idModelObj +
                ", nomModele='" + nomModele + '\'' +
                ", detailObjectifs=" + detailObjectifs +
                '}';
    }
}
