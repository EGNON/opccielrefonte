package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name="idTitre")
@DiscriminatorValue("DRT")
@Table(name = "T_Droit", schema = "Titre")
public class Droit extends Titre{
//    private BigDecimal idActionLiee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idActionLiee", referencedColumnName = "idTitre")
    private Action actionLiee;
    private LocalDateTime dateDebutNegociation;
    private LocalDateTime dateFinNegociation;
//    private BigDecimal idNouvelleAction;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idNouvelleAction", referencedColumnName = "idTitre")
    private Action nouvelleAction;
    private LocalDateTime dateJouissance;
    private Integer pariteAncienNbre;
    private BigDecimal pariteAncienCours;
    private Integer pariteNouveauNbre;
    private BigDecimal pariteNouveauCours;
    private BigDecimal coursTheorique;
    private BigDecimal prixUnitaireSouscription;
    private BigDecimal coursActionExDroit;

    public LocalDateTime getDateDebutNegociation() {
        return dateDebutNegociation;
    }

    public void setDateDebutNegociation(LocalDateTime dateDebutNegociation) {
        this.dateDebutNegociation = dateDebutNegociation;
    }

    public LocalDateTime getDateFinNegociation() {
        return dateFinNegociation;
    }

    public void setDateFinNegociation(LocalDateTime dateFinNegociation) {
        this.dateFinNegociation = dateFinNegociation;
    }

    public LocalDateTime getDateJouissance() {
        return dateJouissance;
    }

    public void setDateJouissance(LocalDateTime dateJouissance) {
        this.dateJouissance = dateJouissance;
    }

    public Integer getPariteAncienNbre() {
        return pariteAncienNbre;
    }

    public void setPariteAncienNbre(Integer pariteAncienNbre) {
        this.pariteAncienNbre = pariteAncienNbre;
    }

    public BigDecimal getPariteAncienCours() {
        return pariteAncienCours;
    }

    public void setPariteAncienCours(BigDecimal pariteAncienCours) {
        this.pariteAncienCours = pariteAncienCours;
    }

    public Integer getPariteNouveauNbre() {
        return pariteNouveauNbre;
    }

    public void setPariteNouveauNbre(Integer pariteNouveauNbre) {
        this.pariteNouveauNbre = pariteNouveauNbre;
    }

    public BigDecimal getPariteNouveauCours() {
        return pariteNouveauCours;
    }

    public void setPariteNouveauCours(BigDecimal pariteNouveauCours) {
        this.pariteNouveauCours = pariteNouveauCours;
    }

    public BigDecimal getCoursTheorique() {
        return coursTheorique;
    }

    public void setCoursTheorique(BigDecimal coursTheorique) {
        this.coursTheorique = coursTheorique;
    }

    public BigDecimal getPrixUnitaireSouscription() {
        return prixUnitaireSouscription;
    }

    public void setPrixUnitaireSouscription(BigDecimal prixUnitaireSouscription) {
        this.prixUnitaireSouscription = prixUnitaireSouscription;
    }

    public BigDecimal getCoursActionExDroit() {
        return coursActionExDroit;
    }

    public void setCoursActionExDroit(BigDecimal coursActionExDroit) {
        this.coursActionExDroit = coursActionExDroit;
    }

    public Action getActionLiee() {
        return actionLiee;
    }

    public void setActionLiee(Action actionLiee) {
        this.actionLiee = actionLiee;
    }

    public Action getNouvelleAction() {
        return nouvelleAction;
    }

    public void setNouvelleAction(Action nouvelleAction) {
        this.nouvelleAction = nouvelleAction;
    }
}
