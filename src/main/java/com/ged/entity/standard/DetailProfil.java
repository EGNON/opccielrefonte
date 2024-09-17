package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TJ_DetailProfil", schema = "Tarification")
public class DetailProfil extends Base {
    @EmbeddedId
    private CleDetailProfil cleDetailProfil;
    @Column(insertable = false,updatable = false)
    private String codeProfil;
    @Column(insertable = false,updatable = false, precision = 18, scale = 6)
    private BigDecimal borneInferieur;
    @ManyToOne()
    @JoinColumn(name = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;
    private double borneSuperieur;
    private double montantMinimum;
    private double taux;


    public DetailProfil() {
    }

    public CleDetailProfil getCleDetailProfil() {
        return cleDetailProfil;
    }

    public void setCleDetailProfil(CleDetailProfil cleDetailProfil) {
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

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }
}
