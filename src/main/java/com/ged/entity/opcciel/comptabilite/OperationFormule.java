package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_OperationFormule", schema = "Comptabilite")
public class OperationFormule extends Base {
    @EmbeddedId
    private CleOperationFormule idOperationFormule;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOperation")
    @MapsId("idOperation")
    private Operation operation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFormule")
    @MapsId("idFormule")
    private Formule formule;
    private Double valeur;

    public OperationFormule() {
    }

    public CleOperationFormule getIdOperationFormule() {
        return idOperationFormule;
    }

    public void setIdOperationFormule(CleOperationFormule idOperationFormule) {
        this.idOperationFormule = idOperationFormule;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Formule getFormule() {
        return formule;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
