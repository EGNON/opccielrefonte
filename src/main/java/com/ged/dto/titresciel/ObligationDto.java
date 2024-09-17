package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObligationDto extends TitreDto {
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datePremierPaiement;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateDernierPaiement;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateJouissance;
    private Integer dureeNbre;
    private String dureeUnite;
    private Integer periodiciteNbre;
    private String periodiciteUnite;
    private Integer usance;
    private Integer differeNbre;
    private String differeUnite;
    private ModeAmortissementDto modeAmortissement;
    private TypeAmortissementDto  typeAmortissement;
    private Double tauxBrut;
    private Double tauxNet;
    private Integer nombreTitre;
    private TypeObligationDto typeObligation;
    private Boolean estParAdjudication;
    private String numeroIdentification;
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

    public ModeAmortissementDto getModeAmortissement() {
        return modeAmortissement;
    }

    public void setModeAmortissement(ModeAmortissementDto modeAmortissement) {
        this.modeAmortissement = modeAmortissement;
    }

    public TypeAmortissementDto getTypeAmortissement() {
        return typeAmortissement;
    }

    public void setTypeAmortissement(TypeAmortissementDto typeAmortissement) {
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

    public TypeObligationDto getTypeObligation() {
        return typeObligation;
    }

    public void setTypeObligation(TypeObligationDto typeObligation) {
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
