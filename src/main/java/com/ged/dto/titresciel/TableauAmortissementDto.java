package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.titresciel.CleTableauAmortissement;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TableauAmortissementDto {
    private CleTableauAmortissementDto idTabAmortissement;
    private TitreDto titre;
    private LocalDateTime dateEcheance;
    private Short numeroEcheance;
    private Double tauxAmortissement;
    private BigDecimal nombreTitre;
    private BigDecimal capital;
    private BigDecimal interet;
    private BigDecimal nombreTitreAmorti;
    private BigDecimal montantRembourse;
    private BigDecimal annuiteTotale;
    private BigDecimal montantFinPeriode;
    private Boolean estGenere;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private String userLogin;
    private Long numLigne;
    private Boolean supprimer;
    private byte[] rowvers;

    public CleTableauAmortissementDto getIdTabAmortissement() {
        return idTabAmortissement;
    }

    public void setIdTabAmortissement(CleTableauAmortissementDto idTabAmortissement) {
        this.idTabAmortissement = idTabAmortissement;
    }

    public TitreDto getTitre() {
        return titre;
    }

    public void setTitre(TitreDto titre) {
        this.titre = titre;
    }

    public LocalDateTime getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDateTime dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Short getNumeroEcheance() {
        return numeroEcheance;
    }

    public void setNumeroEcheance(Short numeroEcheance) {
        this.numeroEcheance = numeroEcheance;
    }

    public Double getTauxAmortissement() {
        return tauxAmortissement;
    }

    public void setTauxAmortissement(Double tauxAmortissement) {
        this.tauxAmortissement = tauxAmortissement;
    }

    public BigDecimal getNombreTitre() {
        return nombreTitre;
    }

    public void setNombreTitre(BigDecimal nombreTitre) {
        this.nombreTitre = nombreTitre;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getInteret() {
        return interet;
    }

    public void setInteret(BigDecimal interet) {
        this.interet = interet;
    }

    public BigDecimal getNombreTitreAmorti() {
        return nombreTitreAmorti;
    }

    public void setNombreTitreAmorti(BigDecimal nombreTitreAmorti) {
        this.nombreTitreAmorti = nombreTitreAmorti;
    }

    public BigDecimal getMontantRembourse() {
        return montantRembourse;
    }

    public void setMontantRembourse(BigDecimal montantRembourse) {
        this.montantRembourse = montantRembourse;
    }

    public BigDecimal getAnnuiteTotale() {
        return annuiteTotale;
    }

    public void setAnnuiteTotale(BigDecimal annuiteTotale) {
        this.annuiteTotale = annuiteTotale;
    }

    public BigDecimal getMontantFinPeriode() {
        return montantFinPeriode;
    }

    public void setMontantFinPeriode(BigDecimal montantFinPeriode) {
        this.montantFinPeriode = montantFinPeriode;
    }

    public Boolean getEstGenere() {
        return estGenere;
    }

    public void setEstGenere(Boolean estGenere) {
        this.estGenere = estGenere;
    }

    public LocalDateTime getDateCreationServeur() {
        return dateCreationServeur;
    }

    public void setDateCreationServeur(LocalDateTime dateCreationServeur) {
        this.dateCreationServeur = dateCreationServeur;
    }

    public LocalDateTime getDateDernModifServeur() {
        return dateDernModifServeur;
    }

    public void setDateDernModifServeur(LocalDateTime dateDernModifServeur) {
        this.dateDernModifServeur = dateDernModifServeur;
    }

    public LocalDateTime getDateDernModifClient() {
        return dateDernModifClient;
    }

    public void setDateDernModifClient(LocalDateTime dateDernModifClient) {
        this.dateDernModifClient = dateDernModifClient;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    public Boolean getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public byte[] getRowvers() {
        return rowvers;
    }

    public void setRowvers(byte[] rowvers) {
        this.rowvers = rowvers;
    }

    @Override
    public String toString() {
        return "TableauAmortissementDto{" +
                "idTabAmortissement=" + idTabAmortissement +
                '}';
    }
}
