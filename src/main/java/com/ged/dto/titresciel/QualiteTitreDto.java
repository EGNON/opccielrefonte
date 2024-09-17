package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QualiteTitreDto {
    private Long idQualite;
    private String libelleQualite;
    private ClasseTitreDto classeTitre;
    private Set<StatutTitreDto> statutTitres = new HashSet<>();

    public QualiteTitreDto(){

    }

    public QualiteTitreDto(String libelleQualite) {
        this.libelleQualite = libelleQualite;
    }

    public Set<StatutTitreDto> getStatutTitres() {
        return statutTitres;
    }

    public void setStatutTitres(Set<StatutTitreDto> statutTitres) {
        this.statutTitres = statutTitres;
    }

    public Long getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(Long idQualite) {
        this.idQualite = idQualite;
    }

    public String getLibelleQualite() {
        return libelleQualite;
    }

    public void setLibelleQualite(String libelleQualite) {
        this.libelleQualite = libelleQualite;
    }

    public ClasseTitreDto getClasseTitre() {
        return classeTitre;
    }

    public void setClasseTitre(ClasseTitreDto classeTitre) {
        this.classeTitre = classeTitre;
    }

    @Override
    public String toString() {
        return "Qualite{" +
                "idQualite=" + idQualite +
                ", libelleQualite='" + libelleQualite + '\'' +
                '}';
    }
}
