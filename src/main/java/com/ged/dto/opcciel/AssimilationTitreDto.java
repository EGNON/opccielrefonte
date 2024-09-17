package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.titresciel.TitreDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssimilationTitreDto extends OperationDto {

    private TitreDto titreAncien;
    private TitreDto titreAncienNouveau;
    private Double qteAncienTitre;
    private Double qteNouveauuTitre;
    private Double qteApresOperation;

    public AssimilationTitreDto() {
    }

    public TitreDto getTitreAncien() {
        return titreAncien;
    }

    public void setTitreAncien(TitreDto titreAncien) {
        this.titreAncien = titreAncien;
    }

    public TitreDto getTitreAncienNouveau() {
        return titreAncienNouveau;
    }

    public void setTitreAncienNouveau(TitreDto titreAncienNouveau) {
        this.titreAncienNouveau = titreAncienNouveau;
    }

    public Double getQteAncienTitre() {
        return qteAncienTitre;
    }

    public void setQteAncienTitre(Double qteAncienTitre) {
        this.qteAncienTitre = qteAncienTitre;
    }

    public Double getQteNouveauuTitre() {
        return qteNouveauuTitre;
    }

    public void setQteNouveauuTitre(Double qteNouveauuTitre) {
        this.qteNouveauuTitre = qteNouveauuTitre;
    }

    public Double getQteApresOperation() {
        return qteApresOperation;
    }

    public void setQteApresOperation(Double qteApresOperation) {
        this.qteApresOperation = qteApresOperation;
    }
}
