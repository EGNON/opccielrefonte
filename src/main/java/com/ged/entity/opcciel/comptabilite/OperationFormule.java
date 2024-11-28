package com.ged.entity.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.entity.Base;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.math.BigDecimal;

@Entity
@Table(name = "TJ_OperationFormule", schema = "Comptabilite")
public class OperationFormule extends Base implements Persistable<CleOperationFormule> {
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
    @Column(precision = 18, scale = 6)
    private BigDecimal valeur;
    @Transient
    private boolean isNew = true;

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

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    /*@PrePersist
    @PostLoad
    public void markNotNew(){
        this.isNew = false;
    }*/

    @Override
    public CleOperationFormule getId() {
        return idOperationFormule;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
