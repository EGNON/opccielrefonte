package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.standard.PersonneDto;
import com.ged.dto.titresciel.TitreDto;
import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.TypeOrdre;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdreDto extends Base {
    private Long idOrdre;
    private OpcvmDto opcvm;
    private Long idTitre;
    private Long idIntervenant;
    private TitreDto titre;
    private String role;
    private LocalDateTime dateOrdre;
    private String statut;
    private TypeOrdreDto typeOrdre;
    private BigDecimal quantiteLimite;
    private BigDecimal quantiteDisponible;
    private PersonneDto personne;
    private LocalDateTime dateEnvoi;
    private LocalDateTime dateLimite;
    private BigDecimal coursLimite;
    private Boolean accepterPerte;
    private Boolean estEnvoye;
    private BigDecimal commissionPlace;
    private BigDecimal commissionSGI;
    private BigDecimal commissionDepositaire;
    private BigDecimal tAF;
    private BigDecimal iRVM;
    private Double interet;
    private BigDecimal plusOuMoinsValue;
    private BigDecimal quantiteExecute;
    private BigDecimal montantNet;
    private BigDecimal montantBrut;
    private String commentaires;
    private Long idOperation;
    private Long idSeance;
    private NatureOperationDto natureOperation;
    private String libelleOperation;
    private String valeurFormule;
    private String valeurCodeAnalytique;
    private String userLogin;
    public OrdreDto() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getIdIntervenant() {
        return idIntervenant;
    }

    public void setIdIntervenant(Long idIntervenant) {
        this.idIntervenant = idIntervenant;
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

    public Long getIdOrdre() {
        return idOrdre;
    }

    public void setIdOrdre(Long idOrdre) {
        this.idOrdre = idOrdre;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public TitreDto getTitre() {
        return titre;
    }

    public void setTitre(TitreDto titre) {
        this.titre = titre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getDateOrdre() {
        return dateOrdre;
    }

    public void setDateOrdre(LocalDateTime dateOrdre) {
        this.dateOrdre = dateOrdre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public TypeOrdreDto getTypeOrdre() {
        return typeOrdre;
    }

    public void setTypeOrdre(TypeOrdreDto typeOrdre) {
        this.typeOrdre = typeOrdre;
    }

    public BigDecimal getQuantiteLimite() {
        return quantiteLimite;
    }

    public void setQuantiteLimite(BigDecimal quantiteLimite) {
        this.quantiteLimite = quantiteLimite;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    public BigDecimal getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(BigDecimal quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public LocalDateTime getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDateTime dateLimite) {
        this.dateLimite = dateLimite;
    }

    public BigDecimal getCoursLimite() {
        return coursLimite;
    }

    public void setCoursLimite(BigDecimal coursLimite) {
        this.coursLimite = coursLimite;
    }

    public Boolean getAccepterPerte() {
        return accepterPerte;
    }

    public void setAccepterPerte(Boolean accepterPerte) {
        this.accepterPerte = accepterPerte;
    }

    public Boolean getEstEnvoye() {
        return estEnvoye;
    }

    public void setEstEnvoye(Boolean estEnvoye) {
        this.estEnvoye = estEnvoye;
    }

    public BigDecimal getCommissionPlace() {
        return commissionPlace;
    }

    public void setCommissionPlace(BigDecimal commissionPlace) {
        this.commissionPlace = commissionPlace;
    }

    public BigDecimal getCommissionSGI() {
        return commissionSGI;
    }

    public void setCommissionSGI(BigDecimal commissionSGI) {
        this.commissionSGI = commissionSGI;
    }

    public BigDecimal getCommissionDepositaire() {
        return commissionDepositaire;
    }

    public void setCommissionDepositaire(BigDecimal commissionDepositaire) {
        this.commissionDepositaire = commissionDepositaire;
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

    public Double getInteret() {
        return interet;
    }

    public void setInteret(Double interet) {
        this.interet = interet;
    }

    public BigDecimal getPlusOuMoinsValue() {
        return plusOuMoinsValue;
    }

    public void setPlusOuMoinsValue(BigDecimal plusOuMoinsValue) {
        this.plusOuMoinsValue = plusOuMoinsValue;
    }

    public BigDecimal getQuantiteExecute() {
        return quantiteExecute;
    }

    public void setQuantiteExecute(BigDecimal quantiteExecute) {
        this.quantiteExecute = quantiteExecute;
    }

    public BigDecimal getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
    }

    public BigDecimal getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(BigDecimal montantBrut) {
        this.montantBrut = montantBrut;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public NatureOperationDto getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperationDto natureOperation) {
        this.natureOperation = natureOperation;
    }

    public String getLibelleOperation() {
        return libelleOperation;
    }

    public void setLibelleOperation(String libelleOperation) {
        this.libelleOperation = libelleOperation;
    }
}
