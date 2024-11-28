package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("SOUS")
@PrimaryKeyJoinColumn(name = "idOperation")
@Table(name = "T_OperationSouscriptionRachat", schema = "Operation")
public class OperationSouscriptionRachat extends Operation {
//    private Long idOcc;
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
    @Column(precision = 18, scale = 6)
    private BigDecimal montantSousALiquider;
    @Column(precision = 18, scale = 6)
    private BigDecimal SousRachatPart;
    @Column(precision = 18, scale = 6)
    private BigDecimal commisiionSousRachat;
    @Column(precision = 18, scale = 6)
    private BigDecimal TAFCommissionSousRachat;
    @Column(precision = 18, scale = 6)
    private BigDecimal retrocessionSousRachat;
    @Column(precision = 18, scale = 6)
    private BigDecimal TAFRetrocessionSousRachat;
    @Column(precision = 18, scale = 6)
    private BigDecimal commissionSousRachatRetrocedee;
    private String modeValeurLiquidative;
    @Column(precision = 18, scale = 6)
    private BigDecimal coursVL;
    @Column(precision = 18, scale = 6)
    private BigDecimal nombrePartSousRachat;
    @Column(precision = 18, scale = 6)
    private BigDecimal regulResultatExoEnCours;
    @Column(precision = 18, scale = 6)
    private BigDecimal regulSommeNonDistribuable;
    @Column(precision = 18, scale = 6)
    private BigDecimal regulReportANouveau;
    @Column(precision = 18, scale = 6)
    private BigDecimal regulautreResultatBeneficiaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal regulautreResultatDeficitaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal regulResultatEnInstanceBeneficiaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal regulResultatEnInstanceDeficitaire;
    @Column(precision = 18, scale = 6)
    private BigDecimal regulExoDistribution;
    @Column(precision = 18, scale = 6)
    private BigDecimal fraisSouscriptionRachat;
    @Column(precision = 18, scale = 6)
    private BigDecimal reste;
    private Long quantiteSouhaite;
    @Column(precision = 18, scale = 6)
    private BigDecimal montantDepose;
    @Column(precision = 18, scale = 6)
    private BigDecimal montantConvertiEnPart;
    private Boolean estRetrocede;
    private Boolean resteRembourse;
    private Boolean rachatPaye;
//    private String ecriture;

    public OperationSouscriptionRachat() {
    }

    /*@Override
    public Long getIdTransaction() {
        return idTransaction;
    }

    @Override
    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Override
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

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }*/

//    public Transaction getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(Transaction transaction) {
//        this.transaction = transaction;
//    }
//
//    public long getIdSeance() {
//        return idSeance;
//    }
//
//    public void setIdSeance(long idSeance) {
//        this.idSeance = idSeance;
//    }
//
//    public Personne getPersonneActionnaiare() {
//        return personneActionnaiare;
//    }
//
//    public void setPersonneActionnaiare(Personne personneActionnaiare) {
//        this.personneActionnaiare = personneActionnaiare;
//    }
//
//    public Opcvm getOpcvm() {
//        return opcvm;
//    }
//
//    public void setOpcvm(Opcvm opcvm) {
//        this.opcvm = opcvm;
//    }
//
//    public Personne getPersonne() {
//        return personne;
//    }
//
//    public void setPersonne(Personne personne) {
//        this.personne = personne;
//    }
//
//    public NatureOperation getNatureOperation() {
//        return natureOperation;
//    }
//
//    public void setNatureOperation(NatureOperation natureOperation) {
//        this.natureOperation = natureOperation;
//    }
//
//    public LocalDateTime getDateOperation() {
//        return dateOperation;
//    }

//    public void setDateOperation(LocalDateTime dateOperation) {
//        this.dateOperation = dateOperation;
//    }
//
//    public String getLibelleOperation() {
//        return libelleOperation;
//    }
//
//    public void setLibelleOperation(String libelleOperation) {
//        this.libelleOperation = libelleOperation;
//    }
//
//    public LocalDateTime getDateSaisie() {
//        return dateSaisie;
//    }
//
//    public void setDateSaisie(LocalDateTime dateSaisie) {
//        this.dateSaisie = dateSaisie;
//    }
//
//    public LocalDateTime getDatePiece() {
//        return datePiece;
//    }
//
//    public void setDatePiece(LocalDateTime datePiece) {
//        this.datePiece = datePiece;
//    }
//
//    public LocalDateTime getDateValeur() {
//        return dateValeur;
//    }
//
//    public void setDateValeur(LocalDateTime dateValeur) {
//        this.dateValeur = dateValeur;
//    }
//
//    public String getReferencePiece() {
//        return referencePiece;
//    }
//
//    public void setReferencePiece(String referencePiece) {
//        this.referencePiece = referencePiece;
//    }

    public BigDecimal getMontantSousALiquider() {
        return montantSousALiquider;
    }

    public void setMontantSousALiquider(BigDecimal montantSousALiquider) {
        this.montantSousALiquider = montantSousALiquider;
    }

    public BigDecimal getSousRachatPart() {
        return SousRachatPart;
    }

    public void setSousRachatPart(BigDecimal sousRachatPart) {
        SousRachatPart = sousRachatPart;
    }

    public BigDecimal getCommisiionSousRachat() {
        return commisiionSousRachat;
    }

    public void setCommisiionSousRachat(BigDecimal commisiionSousRachat) {
        this.commisiionSousRachat = commisiionSousRachat;
    }

    public BigDecimal getTAFCommissionSousRachat() {
        return TAFCommissionSousRachat;
    }

    public void setTAFCommissionSousRachat(BigDecimal TAFCommissionSousRachat) {
        this.TAFCommissionSousRachat = TAFCommissionSousRachat;
    }

    public BigDecimal getRetrocessionSousRachat() {
        return retrocessionSousRachat;
    }

    public void setRetrocessionSousRachat(BigDecimal retrocessionSousRachat) {
        this.retrocessionSousRachat = retrocessionSousRachat;
    }

    public BigDecimal getTAFRetrocessionSousRachat() {
        return TAFRetrocessionSousRachat;
    }

    public void setTAFRetrocessionSousRachat(BigDecimal TAFRetrocessionSousRachat) {
        this.TAFRetrocessionSousRachat = TAFRetrocessionSousRachat;
    }

    public BigDecimal getCommissionSousRachatRetrocedee() {
        return commissionSousRachatRetrocedee;
    }

    public void setCommissionSousRachatRetrocedee(BigDecimal commissionSousRachatRetrocedee) {
        this.commissionSousRachatRetrocedee = commissionSousRachatRetrocedee;
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

    public String getModeValeurLiquidative() {
        return modeValeurLiquidative;
    }

    public void setModeValeurLiquidative(String modeValeurLiquidative) {
        this.modeValeurLiquidative = modeValeurLiquidative;
    }

    public Long getQuantiteSouhaite() {
        return quantiteSouhaite;
    }

    public void setQuantiteSouhaite(Long quantiteSouhaite) {
        this.quantiteSouhaite = quantiteSouhaite;
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

//    public String getEcriture() {
//        return ecriture;
//    }
//
//    public void setEcriture(String ecriture) {
//        this.ecriture = ecriture;
//    }
}
