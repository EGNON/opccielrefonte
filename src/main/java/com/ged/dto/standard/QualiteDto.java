package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QualiteDto {
    private Long idQualite;
    private String libelleQualite;
    private Boolean estPH;
    private Boolean estPM;
    private Set<StatutPersonneDto> statutPersonneDtos;

    public QualiteDto(){

    }

    public QualiteDto(String libelleQualite, String userLogin) {
        this.libelleQualite = libelleQualite;
    }

    public Set<StatutPersonneDto> getStatutPersonneDtos() {
        return statutPersonneDtos;
    }

    public void setStatutPersonneDtos(Set<StatutPersonneDto> statutPersonneDtos) {
        this.statutPersonneDtos = statutPersonneDtos;
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

    public Boolean getEstPH() {
        return estPH;
    }

    public void setEstPH(Boolean estPH) {
        this.estPH = estPH;
    }

    public Boolean getEstPM() {
        return estPM;
    }

    public void setEstPM(Boolean estPM) {
        this.estPM = estPM;
    }
}
