package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_OperationJournal", schema = "Comptabilite")
public class OperationJournal extends Base {
    @EmbeddedId
    private CleOperationJournal idOperationJournal;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOperation")
    @MapsId("idOperation")
    private Operation operation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeJournal")
    @MapsId("codeJournal")
    private Journal journal;

    public OperationJournal() {
    }

    public CleOperationJournal getIdOperationJournal() {
        return idOperationJournal;
    }

    public void setIdOperationJournal(CleOperationJournal idOperationJournal) {
        this.idOperationJournal = idOperationJournal;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
