package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonneDtoEJ {
    private Long idPersonne;

    private PaysDto paysResidence;
    private String denomination;

    private Set<DocumentDto> documents = new HashSet<>();
    private Set<StatutPersonneDto> statutPersonnes = new HashSet<>();
    private boolean estExpose;
    private boolean estJuge;


    public Set<DocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDto> documents) {
        this.documents = documents;
    }

    public Set<StatutPersonneDto> getStatutPersonnes() {
        return statutPersonnes;
    }

    public void setStatutPersonnes(Set<StatutPersonneDto> statutPersonnes) {
        this.statutPersonnes = statutPersonnes;
    }

    public boolean isEstExpose() {
        return estExpose;
    }

    public void setEstExpose(boolean estExpose) {
        this.estExpose = estExpose;
    }

    public boolean isEstJuge() {
        return estJuge;
    }

    public void setEstJuge(boolean estJuge) {
        this.estJuge = estJuge;
    }


    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }


    public PaysDto getPaysResidence() {
        return paysResidence;
    }

    public void setPaysResidence(PaysDto paysResidence) {
        this.paysResidence = paysResidence;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

}
