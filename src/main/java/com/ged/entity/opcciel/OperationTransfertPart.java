package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TRANS_PART")
//@PrimaryKeyJoinColumn(name = "idOperation")
@Table(name = "T_OperationTransfertPart", schema = "Operation")
public class OperationTransfertPart extends Operation {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDemandeur",referencedColumnName = "idPersonne")
    private Personne demandeur;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBeneficiaire",referencedColumnName = "idPersonne")
    private Personne beneficiaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal cumpEntre;
    @Column(precision = 18, scale = 6)
    private BigDecimal qteInitiale;
    @Column(precision = 18, scale = 6)
    private BigDecimal qteTransfert;
    private Long idOpDepart;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
    public OperationTransfertPart() {
        super();
    }

//    public Boolean getSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(Boolean supprimer) {
//        this.supprimer = supprimer;
//    }

    public Personne getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Personne demandeur) {
        this.demandeur = demandeur;
    }

    public Personne getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(Personne beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public BigDecimal getCumpEntre() {
        return cumpEntre;
    }

    public void setCumpEntre(BigDecimal cumpEntre) {
        this.cumpEntre = cumpEntre;
    }

    public BigDecimal getQteInitiale() {
        return qteInitiale;
    }

    public void setQteInitiale(BigDecimal qteInitiale) {
        this.qteInitiale = qteInitiale;
    }

    public BigDecimal getQteTransfert() {
        return qteTransfert;
    }

    public void setQteTransfert(BigDecimal qteTransfert) {
        this.qteTransfert = qteTransfert;
    }

    public Long getIdOpDepart() {
        return idOpDepart;
    }

    public void setIdOpDepart(Long idOpDepart) {
        this.idOpDepart = idOpDepart;
    }
}
