package com.ged.entity.titresciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.*;

/*@Entity
@Table(name = "T_OperationFusionAbsorption", schema = "OperationCapital")*/
public class OperationFusionAbsorption extends Operation {
    private int pariteDebut;
    private int pariteFin;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTitreAbsorbe",referencedColumnName = "idTitre")
    private Titre titreAbsorbe;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTitreAbsorbant",referencedColumnName = "idTitre")
    private Titre titreAbsorbant;
    private Double qteActuelleTitreAbsorbe;
    private Double qteActuelleTitreAbsorbant;
    private Double qte_ApresOperation;
    private Double coursPariteDebut;
    private Double coursPariteFin;

    public OperationFusionAbsorption() {
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

    public Titre getTitreAbsorbe() {
        return titreAbsorbe;
    }

    public void setTitreAbsorbe(Titre titreAbsorbe) {
        this.titreAbsorbe = titreAbsorbe;
    }

    public Titre getTitreAbsorbant() {
        return titreAbsorbant;
    }

    public void setTitreAbsorbant(Titre titreAbsorbant) {
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
