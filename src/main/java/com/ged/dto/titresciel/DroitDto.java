package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DroitDto extends TitreDto {
    private ActionDto actionLiee;
    private LocalDateTime dateDebutNegociation;
    private LocalDateTime dateFinNegociation;
    private ActionDto nouvelleAction;
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

    public ActionDto getActionLiee() {
        return actionLiee;
    }

    public void setActionLiee(ActionDto actionLiee) {
        this.actionLiee = actionLiee;
    }

    public ActionDto getNouvelleAction() {
        return nouvelleAction;
    }

    public void setNouvelleAction(ActionDto nouvelleAction) {
        this.nouvelleAction = nouvelleAction;
    }
}
