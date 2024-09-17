package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatutTitreDto {
    private CleStatutTitreDto idStatutTitre;
    private TitreDto titre;
    private QualiteTitreDto qualiteTitre;

    public StatutTitreDto() {
    }

    public CleStatutTitreDto getIdStatutTitre() {
        return idStatutTitre;
    }

    public void setIdStatutTitre(CleStatutTitreDto idStatutTitre) {
        this.idStatutTitre = idStatutTitre;
    }

    public TitreDto getTitre() {
        return titre;
    }

    public void setTitre(TitreDto titre) {
        this.titre = titre;
    }

    public QualiteTitreDto getQualiteTitre() {
        return qualiteTitre;
    }

    public void setQualiteTitre(QualiteTitreDto qualiteTitre) {
        this.qualiteTitre = qualiteTitre;
    }
}
