package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name="idTitre")
@DiscriminatorValue("OBL")
@Table(name = "T_Obligation", schema = "Titre")
public class Obligation extends Titre{
    @Column(name = "datePremierPaiement")
    private LocalDateTime datePremierPaiement;
    @Column(name = "dateDernierPaiement")
    private LocalDateTime dateDernierPaiement;
    @Column(name = "dateJouissance")
    private LocalDateTime dateJouissance;
    @Column(name = "dureeNbre")
    private Integer dureeNbre;
    @Column(name = "dureeUnite")
    private String dureeUnite;
    @Column(name = "periodiciteNbre")
    private Integer periodiciteNbre;
    @Column(name = "periodiciteUnite")
    private String periodiciteUnite;
    @Column(name = "usance")
    private Integer usance;
    @Column(name = "differeNbre")
    private Integer differeNbre;
    @Column(name = "differeUnite")
    private String differeUnite;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModeAmortissement", referencedColumnName = "idModeAmortissement")
    private ModeAmortissement modeAmortissement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeAmortissement", referencedColumnName = "idTypeAmortissement")
    private TypeAmortissement typeAmortissement;
    @Column(name = "tauxBrut")
    private Double tauxBrut;
    @Column(name = "tauxNet")
    private Double tauxNet;
    @Column(name = "nombreTitre")
    private Integer nombreTitre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeObligation", referencedColumnName = "idTypeObligation")
    private TypeObligation typeObligation;
    @Column(name = "estParAdjudication")
    private Boolean estParAdjudication;
    @Column(name = "numeroIdentification")
    private String numeroIdentification;
    @Column(name = "denominationEmission")
    private String denominationEmission;

    public LocalDateTime getDatePremierPaiement() {
        return datePremierPaiement;
    }

    public void setDatePremierPaiement(LocalDateTime datePremierPaiement) {
        this.datePremierPaiement = datePremierPaiement;
    }

    public LocalDateTime getDateDernierPaiement() {
        return dateDernierPaiement;
    }

    public void setDateDernierPaiement(LocalDateTime dateDernierPaiement) {
        this.dateDernierPaiement = dateDernierPaiement;
    }

    public LocalDateTime getDateJouissance() {
        return dateJouissance;
    }

    public void setDateJouissance(LocalDateTime dateJouissance) {
        this.dateJouissance = dateJouissance;
    }

    public Integer getDureeNbre() {
        return dureeNbre;
    }

    public void setDureeNbre(Integer dureeNbre) {
        this.dureeNbre = dureeNbre;
    }

    public String getDureeUnite() {
        return dureeUnite;
    }

    public void setDureeUnite(String dureeUnite) {
        this.dureeUnite = dureeUnite;
    }

    public Integer getPeriodiciteNbre() {
        return periodiciteNbre;
    }

    public void setPeriodiciteNbre(Integer periodiciteNbre) {
        this.periodiciteNbre = periodiciteNbre;
    }

    public String getPeriodiciteUnite() {
        return periodiciteUnite;
    }

    public void setPeriodiciteUnite(String periodiciteUnite) {
        this.periodiciteUnite = periodiciteUnite;
    }

    public Integer getUsance() {
        return usance;
    }

    public void setUsance(Integer usance) {
        this.usance = usance;
    }

    public Integer getDiffereNbre() {
        return differeNbre;
    }

    public void setDiffereNbre(Integer differeNbre) {
        this.differeNbre = differeNbre;
    }

    public String getDiffereUnite() {
        return differeUnite;
    }

    public void setDiffereUnite(String differeUnite) {
        this.differeUnite = differeUnite;
    }

    public ModeAmortissement getModeAmortissement() {
        return modeAmortissement;
    }

    public void setModeAmortissement(ModeAmortissement modeAmortissement) {
        this.modeAmortissement = modeAmortissement;
    }

    public TypeAmortissement getTypeAmortissement() {
        return typeAmortissement;
    }

    public void setTypeAmortissement(TypeAmortissement typeAmortissement) {
        this.typeAmortissement = typeAmortissement;
    }

    public Double getTauxBrut() {
        return tauxBrut;
    }

    public void setTauxBrut(Double tauxBrut) {
        this.tauxBrut = tauxBrut;
    }

    public Double getTauxNet() {
        return tauxNet;
    }

    public void setTauxNet(Double tauxNet) {
        this.tauxNet = tauxNet;
    }

    public Integer getNombreTitre() {
        return nombreTitre;
    }

    public void setNombreTitre(Integer nombreTitre) {
        this.nombreTitre = nombreTitre;
    }

    public TypeObligation getTypeObligation() {
        return typeObligation;
    }

    public void setTypeObligation(TypeObligation typeObligation) {
        this.typeObligation = typeObligation;
    }

    public Boolean getEstParAdjudication() {
        return estParAdjudication;
    }

    public void setEstParAdjudication(Boolean estParAdjudication) {
        this.estParAdjudication = estParAdjudication;
    }

    public String getNumeroIdentification() {
        return numeroIdentification;
    }

    public void setNumeroIdentification(String numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }

    public String getDenominationEmission() {
        return denominationEmission;
    }

    public void setDenominationEmission(String denominationEmission) {
        this.denominationEmission = denominationEmission;
    }
}
