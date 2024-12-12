package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationPaiementRachatDto2  {
    private Long idOperation;
    private Long idTransaction;
    private String nomSigle;
    private String prenomRaisonSociale;
    private Long idSeance;
    private Long idActionnaire;
    private Long idOpcvm;
    private String codeNatureOperation;
    private LocalDateTime dateOperation;
    private String libelleOperation;
    private LocalDateTime dateSaisie;
    private LocalDateTime datePiece;
    private LocalDateTime dateValeur;
    private String referencePiece;
    private BigDecimal montant;
    private String valeurFormule;
    private String valeurCodeAnalytique;
    private String userLogin;
    private LocalDateTime dateDernModifClient;

    public OperationPaiementRachatDto2() {
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public String getNomSigle() {
        return nomSigle;
    }

    public void setNomSigle(String nomSigle) {
        this.nomSigle = nomSigle;
    }

    public String getPrenomRaisonSociale() {
        return prenomRaisonSociale;
    }

    public void setPrenomRaisonSociale(String prenomRaisonSociale) {
        this.prenomRaisonSociale = prenomRaisonSociale;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    public LocalDateTime getDateDernModifClient() {
        return dateDernModifClient;
    }

    public void setDateDernModifClient(LocalDateTime dateDernModifClient) {
        this.dateDernModifClient = dateDernModifClient;
    }
}
