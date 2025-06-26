package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "idAvis",referencedColumnName = "idOperation")
@Table(name = "T_OperationDetachementDroit", schema = "OperationCapital")
public class OperationDetachementDroit extends Operation {
    @Column(precision = 18, scale = 6)
    private BigDecimal qteAction;
    @Column(precision = 18, scale = 6)
    private BigDecimal qteDroit;
    private String codeNatureOperation;
    private Long idOpcvm;

//    private Boolean supprimer;
    public OperationDetachementDroit() {
    }

    public BigDecimal getQteAction() {
        return qteAction;
    }

    public void setQteAction(BigDecimal qteAction) {
        this.qteAction = qteAction;
    }

    public BigDecimal getQteDroit() {
        return qteDroit;
    }

    public void setQteDroit(BigDecimal qteDroit) {
        this.qteDroit = qteDroit;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }
}
