package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "TJ_OperationCodeAnalytique", schema = "Comptabilite")
public class OperationCodeAnalytique extends Base implements Persistable<CleOperationCodeAnalytique> {
    @EmbeddedId
    private CleOperationCodeAnalytique idOperationCodeAnalytique;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOperation")
    @MapsId("idOperation")
    private Operation operation;

    public OperationCodeAnalytique() {
    }

    public CleOperationCodeAnalytique getIdOperationCodeAnalytique() {
        return idOperationCodeAnalytique;
    }

    public void setIdOperationCodeAnalytique(CleOperationCodeAnalytique idOperationCodeAnalytique) {
        this.idOperationCodeAnalytique = idOperationCodeAnalytique;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public CleOperationCodeAnalytique getId() {
        return idOperationCodeAnalytique;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
