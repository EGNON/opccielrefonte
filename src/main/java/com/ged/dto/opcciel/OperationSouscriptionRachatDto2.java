package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationSouscriptionRachatDto2  {
    private Long idOperation;
    private Long idTransaction;
    private Long idSeance;
    private Long idActionnaire;
    private Long idOpcvm;
    private Long idPersonne;
    private String codeNatureOperation;
    private LocalDateTime dateOperation;
    private String libelleOperation;
    private LocalDateTime dateSaisie;
    private LocalDateTime datePiece;
    private LocalDateTime dateValeur;
    private String referencePiece;
    private BigDecimal montantSousALiquider;
    private BigDecimal sousRachatPart;
    private BigDecimal commisiionSousRachat;
    private BigDecimal tAFCommissionSousRachat;
    private BigDecimal retrocessionSousRachat;
    private BigDecimal tAFRetrocessionSousRachat;
    private BigDecimal commissionSousRachatRetrocedee;
    private String modeValeurLiquidative;
    private BigDecimal coursVL;
    private BigDecimal nombrePartSousRachat;
    private BigDecimal regulResultatExoEnCours;
    private BigDecimal regulSommeNonDistribuable;
    private BigDecimal regulReportANouveau;
    private BigDecimal regulautreResultatBeneficiaire;
    private BigDecimal regulautreResultatDeficitaire;
    private BigDecimal regulResultatEnInstanceBeneficiaire;
    private BigDecimal regulResultatEnInstanceDeficitaire;
    private BigDecimal regulExoDistribution;
    private BigDecimal fraisSouscriptionRachat;
    private BigDecimal reste;
    private Long quantiteSouhaite;
    private BigDecimal montantDepose;
    private BigDecimal montantConvertiEnPart;
    private Boolean estRetrocede;
    private Boolean resteRembourse;
    private Boolean rachatPaye;
    private String ecriture;
    private String valeurFormule;
    private String valeurCodeAnalytique;
    private String userLogin;
    public OperationSouscriptionRachatDto2() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Boolean getEstRetrocede() {
        return estRetrocede;
    }

    public void setEstRetrocede(Boolean estRetrocede) {
        this.estRetrocede = estRetrocede;
    }

    public Boolean getResteRembourse() {
        return resteRembourse;
    }

    public void setResteRembourse(Boolean resteRembourse) {
        this.resteRembourse = resteRembourse;
    }

    public Boolean getRachatPaye() {
        return rachatPaye;
    }

    public void setRachatPaye(Boolean rachatPaye) {
        this.rachatPaye = rachatPaye;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getLibelleOperation() {
        return libelleOperation;
    }

    public void setLibelleOperation(String libelleOperation) {
        this.libelleOperation = libelleOperation;
    }

    public LocalDateTime getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(LocalDateTime dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public LocalDateTime getDatePiece() {
        return datePiece;
    }

    public void setDatePiece(LocalDateTime datePiece) {
        this.datePiece = datePiece;
    }

    public LocalDateTime getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(LocalDateTime dateValeur) {
        this.dateValeur = dateValeur;
    }

    public String getReferencePiece() {
        return referencePiece;
    }

    public void setReferencePiece(String referencePiece) {
        this.referencePiece = referencePiece;
    }

    public BigDecimal getMontantSousALiquider() {
        return montantSousALiquider;
    }

    public void setMontantSousALiquider(BigDecimal montantSousALiquider) {
        this.montantSousALiquider = montantSousALiquider;
    }

    public BigDecimal getSousRachatPart() {
        return sousRachatPart;
    }

    public void setSousRachatPart(BigDecimal sousRachatPart) {
        this.sousRachatPart = sousRachatPart;
    }

    public BigDecimal getCommisiionSousRachat() {
        return commisiionSousRachat;
    }

    public void setCommisiionSousRachat(BigDecimal commisiionSousRachat) {
        this.commisiionSousRachat = commisiionSousRachat;
    }

    public BigDecimal gettAFCommissionSousRachat() {
        return tAFCommissionSousRachat;
    }

    public void settAFCommissionSousRachat(BigDecimal tAFCommissionSousRachat) {
        this.tAFCommissionSousRachat = tAFCommissionSousRachat;
    }

    public BigDecimal getRetrocessionSousRachat() {
        return retrocessionSousRachat;
    }

    public void setRetrocessionSousRachat(BigDecimal retrocessionSousRachat) {
        this.retrocessionSousRachat = retrocessionSousRachat;
    }

    public BigDecimal gettAFRetrocessionSousRachat() {
        return tAFRetrocessionSousRachat;
    }

    public void settAFRetrocessionSousRachat(BigDecimal tAFRetrocessionSousRachat) {
        this.tAFRetrocessionSousRachat = tAFRetrocessionSousRachat;
    }

    public BigDecimal getCommissionSousRachatRetrocedee() {
        return commissionSousRachatRetrocedee;
    }

    public void setCommissionSousRachatRetrocedee(BigDecimal commissionSousRachatRetrocedee) {
        this.commissionSousRachatRetrocedee = commissionSousRachatRetrocedee;
    }

    public String getModeValeurLiquidative() {
        return modeValeurLiquidative;
    }

    public void setModeValeurLiquidative(String modeValeurLiquidative) {
        this.modeValeurLiquidative = modeValeurLiquidative;
    }

    public BigDecimal getCoursVL() {
        return coursVL;
    }

    public void setCoursVL(BigDecimal coursVL) {
        this.coursVL = coursVL;
    }

    public BigDecimal getNombrePartSousRachat() {
        return nombrePartSousRachat;
    }

    public void setNombrePartSousRachat(BigDecimal nombrePartSousRachat) {
        this.nombrePartSousRachat = nombrePartSousRachat;
    }

    public BigDecimal getRegulResultatExoEnCours() {
        return regulResultatExoEnCours;
    }

    public void setRegulResultatExoEnCours(BigDecimal regulResultatExoEnCours) {
        this.regulResultatExoEnCours = regulResultatExoEnCours;
    }

    public BigDecimal getRegulSommeNonDistribuable() {
        return regulSommeNonDistribuable;
    }

    public void setRegulSommeNonDistribuable(BigDecimal regulSommeNonDistribuable) {
        this.regulSommeNonDistribuable = regulSommeNonDistribuable;
    }

    public BigDecimal getRegulReportANouveau() {
        return regulReportANouveau;
    }

    public void setRegulReportANouveau(BigDecimal regulReportANouveau) {
        this.regulReportANouveau = regulReportANouveau;
    }

    public BigDecimal getRegulautreResultatBeneficiaire() {
        return regulautreResultatBeneficiaire;
    }

    public void setRegulautreResultatBeneficiaire(BigDecimal regulautreResultatBeneficiaire) {
        this.regulautreResultatBeneficiaire = regulautreResultatBeneficiaire;
    }

    public BigDecimal getRegulautreResultatDeficitaire() {
        return regulautreResultatDeficitaire;
    }

    public void setRegulautreResultatDeficitaire(BigDecimal regulautreResultatDeficitaire) {
        this.regulautreResultatDeficitaire = regulautreResultatDeficitaire;
    }

    public BigDecimal getRegulResultatEnInstanceBeneficiaire() {
        return regulResultatEnInstanceBeneficiaire;
    }

    public void setRegulResultatEnInstanceBeneficiaire(BigDecimal regulResultatEnInstanceBeneficiaire) {
        this.regulResultatEnInstanceBeneficiaire = regulResultatEnInstanceBeneficiaire;
    }

    public BigDecimal getRegulResultatEnInstanceDeficitaire() {
        return regulResultatEnInstanceDeficitaire;
    }

    public void setRegulResultatEnInstanceDeficitaire(BigDecimal regulResultatEnInstanceDeficitaire) {
        this.regulResultatEnInstanceDeficitaire = regulResultatEnInstanceDeficitaire;
    }

    public BigDecimal getRegulExoDistribution() {
        return regulExoDistribution;
    }

    public void setRegulExoDistribution(BigDecimal regulExoDistribution) {
        this.regulExoDistribution = regulExoDistribution;
    }

    public BigDecimal getFraisSouscriptionRachat() {
        return fraisSouscriptionRachat;
    }

    public void setFraisSouscriptionRachat(BigDecimal fraisSouscriptionRachat) {
        this.fraisSouscriptionRachat = fraisSouscriptionRachat;
    }

    public BigDecimal getReste() {
        return reste;
    }

    public void setReste(BigDecimal reste) {
        this.reste = reste;
    }

    public Long getQuantiteSouhaite() {
        return quantiteSouhaite;
    }

    public void setQuantiteSouhaite(Long quantiteSouhaite) {
        this.quantiteSouhaite = quantiteSouhaite;
    }

    public BigDecimal getMontantDepose() {
        return montantDepose;
    }

    public void setMontantDepose(BigDecimal montantDepose) {
        this.montantDepose = montantDepose;
    }

    public BigDecimal getMontantConvertiEnPart() {
        return montantConvertiEnPart;
    }

    public void setMontantConvertiEnPart(BigDecimal montantConvertiEnPart) {
        this.montantConvertiEnPart = montantConvertiEnPart;
    }

    public String getEcriture() {
        return ecriture;
    }

    public void setEcriture(String ecriture) {
        this.ecriture = ecriture;
    }

    public String getValeurFormule() {
        return valeurFormule;
    }

    public void setValeurFormule(String valeurFormule) {
        this.valeurFormule = valeurFormule;
    }

    public String getValeurCodeAnalytique() {
        return valeurCodeAnalytique;
    }

    public void setValeurCodeAnalytique(String valeurCodeAnalytique) {
        this.valeurCodeAnalytique = valeurCodeAnalytique;
    }
}
