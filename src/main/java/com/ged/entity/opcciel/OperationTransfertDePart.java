package com.ged.entity.opcciel;

import com.ged.entity.standard.Personne;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_OperationTransfertDePart", schema = "Operation")
public class OperationTransfertDePart {
    @Id
    private String idOperation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDemandeur",referencedColumnName = "idPersonne")
    private Personne personneDemandeur;
    @Column(precision = 18, scale = 6)
    private BigDecimal qteInitialeD;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBeneficiaire",referencedColumnName = "idPersonne")
    private Personne personneBeneficiaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal qteInitialeB;
    @Column(precision = 18, scale = 6)
    private BigDecimal cumpEntre;
    @Column(precision = 18, scale = 6)
    private BigDecimal qteTransfert;
    private LocalDateTime dateOperation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;

    public OperationTransfertDePart() {
    }

    public String getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(String idOperation) {
        this.idOperation = idOperation;
    }

    public BigDecimal getQteInitialeD() {
        return qteInitialeD;
    }

    public void setQteInitialeD(BigDecimal qteInitialeD) {
        this.qteInitialeD = qteInitialeD;
    }

    public BigDecimal getQteInitialeB() {
        return qteInitialeB;
    }

    public void setQteInitialeB(BigDecimal qteInitialeB) {
        this.qteInitialeB = qteInitialeB;
    }

    public BigDecimal getCumpEntre() {
        return cumpEntre;
    }

    public void setCumpEntre(BigDecimal cumpEntre) {
        this.cumpEntre = cumpEntre;
    }

    public BigDecimal getQteTransfert() {
        return qteTransfert;
    }

    public void setQteTransfert(BigDecimal qteTransfert) {
        this.qteTransfert = qteTransfert;
    }

    public Personne getPersonneDemandeur() {
        return personneDemandeur;
    }

    public void setPersonneDemandeur(Personne personneDemandeur) {
        this.personneDemandeur = personneDemandeur;
    }

    public Personne getPersonneBeneficiaire() {
        return personneBeneficiaire;
    }

    public void setPersonneBeneficiaire(Personne personneBeneficiaire) {
        this.personneBeneficiaire = personneBeneficiaire;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }
}
