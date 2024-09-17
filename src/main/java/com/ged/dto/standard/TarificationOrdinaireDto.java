package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.titresciel.ClasseTitreDto;
import com.ged.dto.titresciel.PlaceDto;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TarificationOrdinaireDto extends Base {
    private Long idTarificationOrdinaire;
    private String codeRole;
    private ClasseTitreDto classeTitre;
    private PlaceDto place;
    private PersonneDto depositaire;
    private OpcvmDto opcvm;
    private PersonneDto registraire;
    private double borneInferieur;
    private double borneSuperieur;
    private double taux;
    private double forfait;

    public TarificationOrdinaireDto() {
    }

    public Long getIdTarificationOrdinaire() {
        return idTarificationOrdinaire;
    }

    public void setIdTarificationOrdinaire(Long idTarificationOrdinaire) {
        this.idTarificationOrdinaire = idTarificationOrdinaire;
    }

    public String getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    public ClasseTitreDto getClasseTitre() {
        return classeTitre;
    }

    public void setClasseTitre(ClasseTitreDto classeTitre) {
        this.classeTitre = classeTitre;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    public PersonneDto getDepositaire() {
        return depositaire;
    }

    public void setDepositaire(PersonneDto depositaire) {
        this.depositaire = depositaire;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public PersonneDto getRegistraire() {
        return registraire;
    }

    public void setRegistraire(PersonneDto registraire) {
        this.registraire = registraire;
    }

    public double getBorneInferieur() {
        return borneInferieur;
    }

    public void setBorneInferieur(double borneInferieur) {
        this.borneInferieur = borneInferieur;
    }

    public double getBorneSuperieur() {
        return borneSuperieur;
    }

    public void setBorneSuperieur(double borneSuperieur) {
        this.borneSuperieur = borneSuperieur;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getForfait() {
        return forfait;
    }

    public void setForfait(double forfait) {
        this.forfait = forfait;
    }
}
