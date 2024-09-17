package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name="idTitre")
@DiscriminatorValue("TCN")
@Table(name = "T_TCN", schema = "Titre")
public class Tcn extends Titre{
    private LocalDateTime datePremierPaiement;
    private LocalDateTime dateDernierPaiement;
    private LocalDateTime dateJouissance;
    private Integer dureeNbre;
    private String dureeUnite;
    private Integer periodiciteNbre;
    private String periodiciteUnite;
    private Integer usance;
    private Integer differeNbre;
    private String differeUnite;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModeAmortissement", referencedColumnName = "idModeAmortissement")
    private ModeAmortissement modeAmortissement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeAmortissement", referencedColumnName = "idTypeAmortissement")
    private TypeAmortissement typeAmortissement;
    private Double tauxBrut;
    private Double tauxNet;
    private Integer nombreTitre;
    private Boolean estParAdjudication;
    private String numeroIdentification;
    private String denominationEmission;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idNatureTcn", referencedColumnName = "idNatureTcn")
    private NatureTcn natureTcn;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModeCalculInteret", referencedColumnName = "idModeCalculInteret")
    private ModeCalculInteret formulePrecomptee;

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

    public NatureTcn getNatureTcn() {
        return natureTcn;
    }

    public void setNatureTcn(NatureTcn natureTcn) {
        this.natureTcn = natureTcn;
    }

    public ModeCalculInteret getFormulePrecomptee() {
        return formulePrecomptee;
    }

    public void setFormulePrecomptee(ModeCalculInteret formulePrecomptee) {
        this.formulePrecomptee = formulePrecomptee;
    }
}
