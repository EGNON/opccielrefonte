package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.CategoriePersonneDto;
import com.ged.dto.standard.PeriodiciteDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectifAffecteEtatDto {
    private float valeurAffecte = 0;
    private float valeurReelle=0;
    @CreationTimestamp
    private LocalDateTime dateCreationServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifClient;
    private LocalDateTime rowvers;
    private Long numLigne;
    private boolean supprimer;
    private String userLogin;
//    @JsonBackReference
    private AffectationDto affectation;
    private IndicateurDto indicateur;
    private PeriodiciteDto periodicite;
    private CategoriePersonneDto categoriePersonne;

    public float getValeurAffecte() {
        return valeurAffecte;
    }

    public void setValeurAffecte(float valeurAffecte) {
        this.valeurAffecte = valeurAffecte;
    }

    public float getValeurReelle() {
        return valeurReelle;
    }

    public void setValeurReelle(float valeurReelle) {
        this.valeurReelle = valeurReelle;
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

    public LocalDateTime getRowvers() {
        return rowvers;
    }

    public void setRowvers(LocalDateTime rowvers) {
        this.rowvers = rowvers;
    }

    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public AffectationDto getAffectation() {
        return affectation;
    }

    public void setAffectation(AffectationDto affectation) {
        this.affectation = affectation;
    }

    public IndicateurDto getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(IndicateurDto indicateur) {
        this.indicateur = indicateur;
    }

    public PeriodiciteDto getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(PeriodiciteDto periodicite) {
        this.periodicite = periodicite;
    }

    public CategoriePersonneDto getCategoriePersonne() {
        return categoriePersonne;
    }

    public void setCategoriePersonne(CategoriePersonneDto categoriePersonne) {
        this.categoriePersonne = categoriePersonne;
    }

    @Override
    public String toString() {
        return "ObjectifAffecteDto{" +
                "valeurAffecte=" + valeurAffecte +
                ", dateCreationServeur=" + dateCreationServeur +
                ", dateDernModifServeur=" + dateDernModifServeur +
                ", dateDernModifClient=" + dateDernModifClient +
//                ", rowvers=" + Arrays.toString(rowvers) +
                ", numLigne=" + numLigne +
                ", supprimer=" + supprimer +
                ", userLogin='" + userLogin + '\'' +
                ", affectation=" + affectation +
                ", indicateur=" + indicateur +
                ", periodicite=" + periodicite +
                ", categoriePersonne=" + categoriePersonne +
                '}';
    }
}
