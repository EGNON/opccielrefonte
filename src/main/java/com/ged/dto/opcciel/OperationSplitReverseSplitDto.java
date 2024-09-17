package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationSplitReverseSplitDto extends OperationDto {
    private int pariteDebut;
    private int pariteFin;
    private Double qteActuelle;
    private Double qte_ApresOperation;

    public OperationSplitReverseSplitDto() {
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

    public Double getQteActuelle() {
        return qteActuelle;
    }

    public void setQteActuelle(Double qteActuelle) {
        this.qteActuelle = qteActuelle;
    }

    public Double getQte_ApresOperation() {
        return qte_ApresOperation;
    }

    public void setQte_ApresOperation(Double qte_ApresOperation) {
        this.qte_ApresOperation = qte_ApresOperation;
    }
}
