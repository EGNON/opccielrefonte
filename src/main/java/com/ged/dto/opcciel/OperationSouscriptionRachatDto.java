package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationSouscriptionRachatDto extends OperationDto {
//    private Long idTransaction;
//    private Long idSeance;
    private Long idOpcvm;
//    private Long idActionnaire;
    private String codeNatureOperation;
//    private LocalDateTime dateOperation;
//    private String libelleOperation;
//    private LocalDateTime dateSaisie;
//    private LocalDateTime datePiece;
//    private LocalDateTime dateValeur;
//    private String referencePiece;
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

    public OperationSouscriptionRachatDto() {
    }

    /*@Override
    public Long getIdTransaction() {
        return idTransaction;
    }

    @Override
    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }*/

    /*@Override
    public Long getIdSeance() {
        return idSeance;
    }

    @Override
    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }*/

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    /*@Override
    public Long getIdActionnaire() {
        return idActionnaire;
    }

    @Override
    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }*/

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    /*@Override
    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    @Override
    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    @Override
    public String getLibelleOperation() {
        return libelleOperation;
    }

    @Override
    public void setLibelleOperation(String libelleOperation) {
        this.libelleOperation = libelleOperation;
    }

    @Override
    public LocalDateTime getDateSaisie() {
        return dateSaisie;
    }

    @Override
    public void setDateSaisie(LocalDateTime dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    @Override
    public LocalDateTime getDatePiece() {
        return datePiece;
    }

    @Override
    public void setDatePiece(LocalDateTime datePiece) {
        this.datePiece = datePiece;
    }

    @Override
    public LocalDateTime getDateValeur() {
        return dateValeur;
    }

    @Override
    public void setDateValeur(LocalDateTime dateValeur) {
        this.dateValeur = dateValeur;
    }

    @Override
    public String getReferencePiece() {
        return referencePiece;
    }

    @Override
    public void setReferencePiece(String referencePiece) {
        this.referencePiece = referencePiece;
    }
*/
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

    @Override
    public String getEcriture() {
        return ecriture;
    }

    @Override
    public void setEcriture(String ecriture) {
        this.ecriture = ecriture;
    }
}
