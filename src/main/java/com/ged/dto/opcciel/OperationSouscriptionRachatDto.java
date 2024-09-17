package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.standard.PersonneDto;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationSouscriptionRachatDto {
    private long idOperation;
    private TransactionDto transaction;
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
    private String referencePiece;
    private Double montantSousALiquider;
    private Double SousRachatPart;
    private Double commisiionSousRachat;
    private Double TAFCommissionSousRachat;
    private Double retrocessionSousRachat;
    private Double TAFRetrocessionSousRachat;
    private Double commissionSousRachatRetrocedee;
    private String modeValeurLiquidative;
    private Double coursVL;
    private Double nombrePartSousRachat;
    private Double regulResultatExoEnCours;
    private Double regulSommeNonDistribuable;
    private Double regulReportANouveau;
    private Double regulautreResultatBeneficiaire;
    private Double regulautreResultatDeficitaire;
    private Double regulResultatEnInstanceBeneficiaire;
    private Double regulResultatEnInstanceDeficitaire;
    private Double regulExoDistribution;
    private Double fraisSouscriptionRachat;
    private Double reste;
    private Long quantiteSouhaite;
    private Double montantDepose;
    private Double montantConvertiEnPart;
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

    public TransactionDto getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDto transaction) {
        this.transaction = transaction;
    }

    public long getIdSeance() {
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

    public Double getMontantSousALiquider() {
        return montantSousALiquider;
    }

    public void setMontantSousALiquider(Double montantSousALiquider) {
        this.montantSousALiquider = montantSousALiquider;
    }

    public Double getSousRachatPart() {
        return SousRachatPart;
    }

    public void setSousRachatPart(Double sousRachatPart) {
        SousRachatPart = sousRachatPart;
    }

    public Double getCommisiionSousRachat() {
        return commisiionSousRachat;
    }

    public void setCommisiionSousRachat(Double commisiionSousRachat) {
        this.commisiionSousRachat = commisiionSousRachat;
    }

    public Double getTAFCommissionSousRachat() {
        return TAFCommissionSousRachat;
    }

    public void setTAFCommissionSousRachat(Double TAFCommissionSousRachat) {
        this.TAFCommissionSousRachat = TAFCommissionSousRachat;
    }

    public Double getRetrocessionSousRachat() {
        return retrocessionSousRachat;
    }

    public void setRetrocessionSousRachat(Double retrocessionSousRachat) {
        this.retrocessionSousRachat = retrocessionSousRachat;
    }

    public Double getTAFRetrocessionSousRachat() {
        return TAFRetrocessionSousRachat;
    }

    public void setTAFRetrocessionSousRachat(Double TAFRetrocessionSousRachat) {
        this.TAFRetrocessionSousRachat = TAFRetrocessionSousRachat;
    }

    public Double getCommissionSousRachatRetrocedee() {
        return commissionSousRachatRetrocedee;
    }

    public void setCommissionSousRachatRetrocedee(Double commissionSousRachatRetrocedee) {
        this.commissionSousRachatRetrocedee = commissionSousRachatRetrocedee;
    }

    public String getModeValeurLiquidative() {
        return modeValeurLiquidative;
    }

    public void setModeValeurLiquidative(String modeValeurLiquidative) {
        this.modeValeurLiquidative = modeValeurLiquidative;
    }

    public Double getCoursVL() {
        return coursVL;
    }

    public void setCoursVL(Double coursVL) {
        this.coursVL = coursVL;
    }

    public Double getNombrePartSousRachat() {
        return nombrePartSousRachat;
    }

    public void setNombrePartSousRachat(Double nombrePartSousRachat) {
        this.nombrePartSousRachat = nombrePartSousRachat;
    }

    public Double getRegulResultatExoEnCours() {
        return regulResultatExoEnCours;
    }

    public void setRegulResultatExoEnCours(Double regulResultatExoEnCours) {
        this.regulResultatExoEnCours = regulResultatExoEnCours;
    }

    public Double getRegulSommeNonDistribuable() {
        return regulSommeNonDistribuable;
    }

    public void setRegulSommeNonDistribuable(Double regulSommeNonDistribuable) {
        this.regulSommeNonDistribuable = regulSommeNonDistribuable;
    }

    public Double getRegulReportANouveau() {
        return regulReportANouveau;
    }

    public void setRegulReportANouveau(Double regulReportANouveau) {
        this.regulReportANouveau = regulReportANouveau;
    }

    public Double getRegulautreResultatBeneficiaire() {
        return regulautreResultatBeneficiaire;
    }

    public void setRegulautreResultatBeneficiaire(Double regulautreResultatBeneficiaire) {
        this.regulautreResultatBeneficiaire = regulautreResultatBeneficiaire;
    }

    public Double getRegulautreResultatDeficitaire() {
        return regulautreResultatDeficitaire;
    }

    public void setRegulautreResultatDeficitaire(Double regulautreResultatDeficitaire) {
        this.regulautreResultatDeficitaire = regulautreResultatDeficitaire;
    }

    public Double getRegulResultatEnInstanceBeneficiaire() {
        return regulResultatEnInstanceBeneficiaire;
    }

    public void setRegulResultatEnInstanceBeneficiaire(Double regulResultatEnInstanceBeneficiaire) {
        this.regulResultatEnInstanceBeneficiaire = regulResultatEnInstanceBeneficiaire;
    }

    public Double getRegulResultatEnInstanceDeficitaire() {
        return regulResultatEnInstanceDeficitaire;
    }

    public void setRegulResultatEnInstanceDeficitaire(Double regulResultatEnInstanceDeficitaire) {
        this.regulResultatEnInstanceDeficitaire = regulResultatEnInstanceDeficitaire;
    }

    public Double getRegulExoDistribution() {
        return regulExoDistribution;
    }

    public void setRegulExoDistribution(Double regulExoDistribution) {
        this.regulExoDistribution = regulExoDistribution;
    }

    public Double getFraisSouscriptionRachat() {
        return fraisSouscriptionRachat;
    }

    public void setFraisSouscriptionRachat(Double fraisSouscriptionRachat) {
        this.fraisSouscriptionRachat = fraisSouscriptionRachat;
    }

    public Double getReste() {
        return reste;
    }

    public void setReste(Double reste) {
        this.reste = reste;
    }

    public Long getQuantiteSouhaite() {
        return quantiteSouhaite;
    }

    public void setQuantiteSouhaite(Long quantiteSouhaite) {
        this.quantiteSouhaite = quantiteSouhaite;
    }

    public Double getMontantDepose() {
        return montantDepose;
    }

    public void setMontantDepose(Double montantDepose) {
        this.montantDepose = montantDepose;
    }

    public Double getMontantConvertiEnPart() {
        return montantConvertiEnPart;
    }

    public void setMontantConvertiEnPart(Double montantConvertiEnPart) {
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

    public String getEcriture() {
        return ecriture;
    }

    public void setEcriture(String ecriture) {
        this.ecriture = ecriture;
    }
}
