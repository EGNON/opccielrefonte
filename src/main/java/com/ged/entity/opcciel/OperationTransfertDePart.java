package com.ged.entity.opcciel;

import com.ged.entity.standard.Personne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

/*@Entity
@Table(name = "T_OperationTransfertDePart", schema = "Operation")*/
public class OperationTransfertDePart {
    @Id
    private String idOperation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDemandeur",referencedColumnName = "idPersonne")
    private Personne personneDemandeur;
    private Double qteInitialeD;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBeneficiaire",referencedColumnName = "idPersonne")
    private Personne personneBeneficiaire;
    private Double qteInitialeB;
    private Double cumpEntre;
    private Double qteTransfert;
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

    public Personne getPersonneDemandeur() {
        return personneDemandeur;
    }

    public void setPersonneDemandeur(Personne personneDemandeur) {
        this.personneDemandeur = personneDemandeur;
    }

    public Double getQteInitialeD() {
        return qteInitialeD;
    }

    public void setQteInitialeD(Double qteInitialeD) {
        this.qteInitialeD = qteInitialeD;
    }

    public Personne getPersonneBeneficiaire() {
        return personneBeneficiaire;
    }

    public void setPersonneBeneficiaire(Personne personneBeneficiaire) {
        this.personneBeneficiaire = personneBeneficiaire;
    }

    public Double getQteInitialeB() {
        return qteInitialeB;
    }

    public void setQteInitialeB(Double qteInitialeB) {
        this.qteInitialeB = qteInitialeB;
    }

    public Double getCumpEntre() {
        return cumpEntre;
    }

    public void setCumpEntre(Double cumpEntre) {
        this.cumpEntre = cumpEntre;
    }

    public Double getQteTransfert() {
        return qteTransfert;
    }

    public void setQteTransfert(Double qteTransfert) {
        this.qteTransfert = qteTransfert;
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
