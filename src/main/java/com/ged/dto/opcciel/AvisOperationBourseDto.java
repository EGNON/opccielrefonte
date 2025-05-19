package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvisOperationBourseDto extends Base {
    private Long idAvis;
	private Long idTransaction;
	private Long idSeance;
	private NatureOperation natureOperation;
	private LocalDateTime dateOperation;
	private LocalDateTime dateSaisie;
	private LocalDateTime dateValeur;
	private LocalDateTime datePiece;
	private String referencePiece;
	private BigDecimal montant;
	private String ecriture;
	private String libelleOperation;
	private Boolean estOD;
	private String type;
	private String referenceAvis;
	private OrdreDto ordre;
	private LocalDateTime dateReceptionLivraisonPrevu;
	private BigDecimal quantiteLimite;
	private BigDecimal coursLimite;
	private BigDecimal commissionPlace;
	private BigDecimal commissionDepositaire;
	private BigDecimal commissionSGI;
	private BigDecimal tAF;
	private BigDecimal iRVM;
	private BigDecimal interet;
	private BigDecimal interetPrecompte;
	private BigDecimal plusOuMoinsValue;
	private BigDecimal montantBrut;
	private BigDecimal montantNet;
	private BigDecimal cump;
	private Long idOperationRL;
	private Long numLigne;
	private LocalDateTime dateDernModifClient;
	private String userLogin;
	private String valeurFormule;
	private String valeurCodeAnalytique;
	public AvisOperationBourseDto() {
	}

	public BigDecimal getMontantNet() {
		return montantNet;
	}

	public void setMontantNet(BigDecimal montantNet) {
		this.montantNet = montantNet;
	}

	public BigDecimal getCump() {
		return cump;
	}

	public void setCump(BigDecimal cump) {
		this.cump = cump;
	}

	public BigDecimal getInteretPrecompte() {
		return interetPrecompte;
	}

	public void setInteretPrecompte(BigDecimal interetPrecompte) {
		this.interetPrecompte = interetPrecompte;
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

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Long getIdAvis() {
		return idAvis;
	}

	public void setIdAvis(Long idAvis) {
		this.idAvis = idAvis;
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

	public NatureOperation getNatureOperation() {
		return natureOperation;
	}

	public void setNatureOperation(NatureOperation natureOperation) {
		this.natureOperation = natureOperation;
	}

	public LocalDateTime getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(LocalDateTime dateOperation) {
		this.dateOperation = dateOperation;
	}

	public LocalDateTime getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(LocalDateTime dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public OrdreDto getOrdre() {
		return ordre;
	}

	public void setOrdre(OrdreDto ordre) {
		this.ordre = ordre;
	}

	public LocalDateTime getDateValeur() {
		return dateValeur;
	}

	public void setDateValeur(LocalDateTime dateValeur) {
		this.dateValeur = dateValeur;
	}

	public LocalDateTime getDatePiece() {
		return datePiece;
	}

	public void setDatePiece(LocalDateTime datePiece) {
		this.datePiece = datePiece;
	}

	public String getReferencePiece() {
		return referencePiece;
	}

	public void setReferencePiece(String referencePiece) {
		this.referencePiece = referencePiece;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getEcriture() {
		return ecriture;
	}

	public void setEcriture(String ecriture) {
		this.ecriture = ecriture;
	}

	public String getLibelleOperation() {
		return libelleOperation;
	}

	public void setLibelleOperation(String libelleOperation) {
		this.libelleOperation = libelleOperation;
	}

	public Boolean getEstOD() {
		return estOD;
	}

	public void setEstOD(Boolean estOD) {
		this.estOD = estOD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReferenceAvis() {
		return referenceAvis;
	}

	public void setReferenceAvis(String referenceAvis) {
		this.referenceAvis = referenceAvis;
	}



	public LocalDateTime getDateReceptionLivraisonPrevu() {
		return dateReceptionLivraisonPrevu;
	}

	public void setDateReceptionLivraisonPrevu(LocalDateTime dateReceptionLivraisonPrevu) {
		this.dateReceptionLivraisonPrevu = dateReceptionLivraisonPrevu;
	}

	public BigDecimal getQuantiteLimite() {
		return quantiteLimite;
	}

	public void setQuantiteLimite(BigDecimal quantiteLimite) {
		this.quantiteLimite = quantiteLimite;
	}

	public BigDecimal getCoursLimite() {
		return coursLimite;
	}

	public void setCoursLimite(BigDecimal coursLimite) {
		this.coursLimite = coursLimite;
	}

	public BigDecimal getCommissionPlace() {
		return commissionPlace;
	}

	public void setCommissionPlace(BigDecimal commissionPlace) {
		this.commissionPlace = commissionPlace;
	}

	public BigDecimal getCommissionDepositaire() {
		return commissionDepositaire;
	}

	public void setCommissionDepositaire(BigDecimal commissionDepositaire) {
		this.commissionDepositaire = commissionDepositaire;
	}

	public BigDecimal getCommissionSGI() {
		return commissionSGI;
	}

	public void setCommissionSGI(BigDecimal commissionSGI) {
		this.commissionSGI = commissionSGI;
	}

	public BigDecimal gettAF() {
		return tAF;
	}

	public void settAF(BigDecimal tAF) {
		this.tAF = tAF;
	}

	public BigDecimal getiRVM() {
		return iRVM;
	}

	public void setiRVM(BigDecimal iRVM) {
		this.iRVM = iRVM;
	}

	public BigDecimal getInteret() {
		return interet;
	}

	public void setInteret(BigDecimal interet) {
		this.interet = interet;
	}

	public BigDecimal getPlusOuMoinsValue() {
		return plusOuMoinsValue;
	}

	public void setPlusOuMoinsValue(BigDecimal plusOuMoinsValue) {
		this.plusOuMoinsValue = plusOuMoinsValue;
	}

	public BigDecimal getMontantBrut() {
		return montantBrut;
	}

	public void setMontantBrut(BigDecimal montantBrut) {
		this.montantBrut = montantBrut;
	}

	public Long getIdOperationRL() {
		return idOperationRL;
	}

	public void setIdOperationRL(Long idOperationRL) {
		this.idOperationRL = idOperationRL;
	}

	public Long getNumLigne() {
		return numLigne;
	}

	public void setNumLigne(Long numLigne) {
		this.numLigne = numLigne;
	}

	@Override
	public LocalDateTime getDateDernModifClient() {
		return dateDernModifClient;
	}

	@Override
	public void setDateDernModifClient(LocalDateTime dateDernModifClient) {
		this.dateDernModifClient = dateDernModifClient;
	}
}
