package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.standard.PersonneDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationSouscriptionRachatDto extends OperationDto {
    private long idOperation;
    /*private TransactionDto transaction;
    private long idSeance;
    private PersonneDto personneActionnaiare;
    private OpcvmDto opcvm;
    private PersonneDto personne;
    private NatureOperationDto natureOperation;
    private LocalDateTime dateOperation;
    private String libelleOperation  ;
    private LocalDateTime dateSaisie;
    private LocalDateTime datePiece;
    private LocalDateTime dateValeur;
    private String referencePiece;*/
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
    private boolean estRetrocede;
    private boolean resteRembourse;
    private boolean rachatPaye;
    private String ecriture;

    public OperationSouscriptionRachatDto() {
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(long idOperation) {
        this.idOperation = idOperation;
    }

   /* public TransactionDto getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDto transaction) {
        this.transaction = transaction;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(long idSeance) {
        this.idSeance = idSeance;
    }

    public PersonneDto getPersonneActionnaiare() {
        return personneActionnaiare;
    }

    public void setPersonneActionnaiare(PersonneDto personneActionnaiare) {
        this.personneActionnaiare = personneActionnaiare;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    public NatureOperationDto getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperationDto natureOperation) {
        this.natureOperation = natureOperation;
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
        sousRachatPart = sousRachatPart;
    }

    public BigDecimal getCommisiionSousRachat() {
        return commisiionSousRachat;
    }

    public void setCommisiionSousRachat(BigDecimal commisiionSousRachat) {
        this.commisiionSousRachat = commisiionSousRachat;
    }

    public BigDecimal getTAFCommissionSousRachat() {
        return tAFCommissionSousRachat;
    }

    public void setTAFCommissionSousRachat(BigDecimal TAFCommissionSousRachat) {
        this.tAFCommissionSousRachat = TAFCommissionSousRachat;
    }

    public BigDecimal getRetrocessionSousRachat() {
        return retrocessionSousRachat;
    }

    public void setRetrocessionSousRachat(BigDecimal retrocessionSousRachat) {
        this.retrocessionSousRachat = retrocessionSousRachat;
    }

    public BigDecimal getTAFRetrocessionSousRachat() {
        return tAFRetrocessionSousRachat;
    }

    public void setTAFRetrocessionSousRachat(BigDecimal TAFRetrocessionSousRachat) {
        this.tAFRetrocessionSousRachat = TAFRetrocessionSousRachat;
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

    public boolean isEstRetrocede() {
        return estRetrocede;
    }

    public void setEstRetrocede(boolean estRetrocede) {
        this.estRetrocede = estRetrocede;
    }

    public boolean isResteRembourse() {
        return resteRembourse;
    }

    public void setResteRembourse(boolean resteRembourse) {
        this.resteRembourse = resteRembourse;
    }

    public boolean isRachatPaye() {
        return rachatPaye;
    }

    public void setRachatPaye(boolean rachatPaye) {
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
