package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.titresciel.TypeAmortissementDto;
import com.ged.entity.Base;
import com.ged.entity.opcciel.CleCharge;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DecisionDistributionDto extends Base {
    private Long idDecision;
    private Long  idMiseEnAffectation;
    private Long idOpcvm ;
    private Long idOperation ;
    private LocalDateTime dateDecision;
    private LocalDateTime dateDetachement;
    private BigDecimal resultat;
    private BigDecimal aDistribueEnpourcentage;
    private BigDecimal montantDistribue;
    private BigDecimal montantNonDistribue;
    private String codeExercice;
    private BigDecimal nbrePartEnCirculation;
    private BigDecimal coupUnitaireADistribuer ;
    private Boolean estApplique;
    private Long idSeance;
    private String statutMontNonDistribue;
    private String userLogin;

    public DecisionDistributionDto() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getIdDecision() {
        return idDecision;
    }

    public void setIdDecision(Long idDecision) {
        this.idDecision = idDecision;
    }

    public Long getIdMiseEnAffectation() {
        return idMiseEnAffectation;
    }

    public void setIdMiseEnAffectation(Long idMiseEnAffectation) {
        this.idMiseEnAffectation = idMiseEnAffectation;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public LocalDateTime getDateDecision() {
        return dateDecision;
    }

    public void setDateDecision(LocalDateTime dateDecision) {
        this.dateDecision = dateDecision;
    }

    public LocalDateTime getDateDetachement() {
        return dateDetachement;
    }

    public void setDateDetachement(LocalDateTime dateDetachement) {
        this.dateDetachement = dateDetachement;
    }

    public BigDecimal getResultat() {
        return resultat;
    }

    public void setResultat(BigDecimal resultat) {
        this.resultat = resultat;
    }

    public BigDecimal getaDistribueEnpourcentage() {
        return aDistribueEnpourcentage;
    }

    public void setaDistribueEnpourcentage(BigDecimal aDistribueEnpourcentage) {
        this.aDistribueEnpourcentage = aDistribueEnpourcentage;
    }

    public BigDecimal getMontantDistribue() {
        return montantDistribue;
    }

    public void setMontantDistribue(BigDecimal montantDistribue) {
        this.montantDistribue = montantDistribue;
    }

    public BigDecimal getMontantNonDistribue() {
        return montantNonDistribue;
    }

    public void setMontantNonDistribue(BigDecimal montantNonDistribue) {
        this.montantNonDistribue = montantNonDistribue;
    }

    public String getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(String codeExercice) {
        this.codeExercice = codeExercice;
    }

    public BigDecimal getNbrePartEnCirculation() {
        return nbrePartEnCirculation;
    }

    public void setNbrePartEnCirculation(BigDecimal nbrePartEnCirculation) {
        this.nbrePartEnCirculation = nbrePartEnCirculation;
    }

    public BigDecimal getCoupUnitaireADistribuer() {
        return coupUnitaireADistribuer;
    }

    public void setCoupUnitaireADistribuer(BigDecimal coupUnitaireADistribuer) {
        this.coupUnitaireADistribuer = coupUnitaireADistribuer;
    }

    public Boolean getEstApplique() {
        return estApplique;
    }

    public void setEstApplique(Boolean estApplique) {
        this.estApplique = estApplique;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public String getStatutMontNonDistribue() {
        return statutMontNonDistribue;
    }

    public void setStatutMontNonDistribue(String statutMontNonDistribue) {
        this.statutMontNonDistribue = statutMontNonDistribue;
    }
}
