package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.entity.Base;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailProfilDto extends Base {
    private CleDetailProfilDto cleDetailProfil;
    private String codeProfil;
    private BigDecimal borneInferieur;
    private OpcvmDto opcvm;
    private double borneSuperieur;
    private double montantMinimum;
    private double taux;


    public DetailProfilDto() {
    }

    public CleDetailProfilDto getCleDetailProfil() {
        return cleDetailProfil;
    }

    public void setCleDetailProfil(CleDetailProfilDto cleDetailProfil) {
        this.cleDetailProfil = cleDetailProfil;
    }

    public String getCodeProfil() {
        return codeProfil;
    }

    public void setCodeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
    }

    public BigDecimal getBorneInferieur() {
        return borneInferieur;
    }

    public void setBorneInferieur(BigDecimal borneInferieur) {
        this.borneInferieur = borneInferieur;
    }

    public double getBorneSuperieur() {
        return borneSuperieur;
    }

    public void setBorneSuperieur(double borneSuperieur) {
        this.borneSuperieur = borneSuperieur;
    }

    public double getMontantMinimum() {
        return montantMinimum;
    }

    public void setMontantMinimum(double montantMinimum) {
        this.montantMinimum = montantMinimum;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }
}
