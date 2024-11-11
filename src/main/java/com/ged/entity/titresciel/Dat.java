package com.ged.entity.titresciel;

import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name="idTitre")
@DiscriminatorValue("DAT")
@Table(name = "T_DAT", schema = "Titre")
public class Dat extends Titre {
    private LocalDateTime datePremierPaiement;
    private LocalDateTime dateDernierPaiement;
    private LocalDateTime dateJouissance;
    private Integer dureeNbre;
    private String dureeUnite;
    private Integer periodiciteNbre;
    private String periodiciteUnite;
    private Integer usance;
    private Double tauxBrut;
    private Double tauxNet;
    private String typeDAT;
    private String codeBanque;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBanque", referencedColumnName = "idPersonne")
    private PersonneMorale banque;

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

    public String getTypeDAT() {
        return typeDAT;
    }

    public void setTypeDAT(String typeDAT) {
        this.typeDAT = typeDAT;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public PersonneMorale getBanque() {
        return banque;
    }

    public void setBanque(PersonneMorale banque) {
        this.banque = banque;
    }
}
