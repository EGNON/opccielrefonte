package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.titresciel.TitreDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationFusionAbsorptionDto extends OperationDto {
    private int pariteDebut;
    private int pariteFin;
    private TitreDto titreAbsorbe;
    private TitreDto titreAbsorbant;
    private Double qteActuelleTitreAbsorbe;
    private Double qteActuelleTitreAbsorbant;
    private Double qte_ApresOperation;
    private Double coursPariteDebut;
    private Double coursPariteFin;

    public OperationFusionAbsorptionDto() {
    }

    public int getPariteDebut() {
        return pariteDebut;
    }

    public void setPariteDebut(int pariteDebut) {
        this.pariteDebut = pariteDebut;
    }

    public int getPariteFin() {
        return pariteFin;
    }

    public void setPariteFin(int pariteFin) {
        this.pariteFin = pariteFin;
    }

    public TitreDto getTitreAbsorbe() {
        return titreAbsorbe;
    }

    public void setTitreAbsorbe(TitreDto titreAbsorbe) {
        this.titreAbsorbe = titreAbsorbe;
    }

    public TitreDto getTitreAbsorbant() {
        return titreAbsorbant;
    }

    public void setTitreAbsorbant(TitreDto titreAbsorbant) {
        this.titreAbsorbant = titreAbsorbant;
    }

    public Double getQteActuelleTitreAbsorbe() {
        return qteActuelleTitreAbsorbe;
    }

    public void setQteActuelleTitreAbsorbe(Double qteActuelleTitreAbsorbe) {
        this.qteActuelleTitreAbsorbe = qteActuelleTitreAbsorbe;
    }

    public Double getQteActuelleTitreAbsorbant() {
        return qteActuelleTitreAbsorbant;
    }

    public void setQteActuelleTitreAbsorbant(Double qteActuelleTitreAbsorbant) {
        this.qteActuelleTitreAbsorbant = qteActuelleTitreAbsorbant;
    }

    public Double getQte_ApresOperation() {
        return qte_ApresOperation;
    }

    public void setQte_ApresOperation(Double qte_ApresOperation) {
        this.qte_ApresOperation = qte_ApresOperation;
    }

    public Double getCoursPariteDebut() {
        return coursPariteDebut;
    }

    public void setCoursPariteDebut(Double coursPariteDebut) {
        this.coursPariteDebut = coursPariteDebut;
    }

    public Double getCoursPariteFin() {
        return coursPariteFin;
    }

    public void setCoursPariteFin(Double coursPariteFin) {
        this.coursPariteFin = coursPariteFin;
    }
}
