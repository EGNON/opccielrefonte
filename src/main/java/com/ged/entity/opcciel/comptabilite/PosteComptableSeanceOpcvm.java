package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TJ_PosteComptableSeanceOpcvm", schema = "Comptabilite")
public class PosteComptableSeanceOpcvm extends Base {
    @EmbeddedId
    private ClePosteComptableSeanceOpcvm idPosteComptableSeanceOpcvm;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlan")
    @MapsId("codePlan")
    private Plan plan;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;
	private String formuleSysteme;
    private Date dateValeur;
    private Double valeur;
    private boolean estVerifie1;
    private LocalDateTime dateVerification1;
    private String userLoginVerificateur1;
    private boolean estVerifie2;
    private LocalDateTime dateVerification2;
    private String userLoginVerificateur2;

    public PosteComptableSeanceOpcvm() {
    }

    public ClePosteComptableSeanceOpcvm getIdPosteComptableSeanceOpcvm() {
        return idPosteComptableSeanceOpcvm;
    }

    public void setIdPosteComptableSeanceOpcvm(ClePosteComptableSeanceOpcvm idPosteComptableSeanceOpcvm) {
        this.idPosteComptableSeanceOpcvm = idPosteComptableSeanceOpcvm;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public String getFormuleSysteme() {
        return formuleSysteme;
    }

    public void setFormuleSysteme(String formuleSysteme) {
        this.formuleSysteme = formuleSysteme;
    }

    public Date getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(Date dateValeur) {
        this.dateValeur = dateValeur;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public boolean isEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(boolean estVerifie1) {
        this.estVerifie1 = estVerifie1;
    }

    public LocalDateTime getDateVerification1() {
        return dateVerification1;
    }

    public void setDateVerification1(LocalDateTime dateVerification1) {
        this.dateVerification1 = dateVerification1;
    }

    public String getUserLoginVerificateur1() {
        return userLoginVerificateur1;
    }

    public void setUserLoginVerificateur1(String userLoginVerificateur1) {
        this.userLoginVerificateur1 = userLoginVerificateur1;
    }

    public boolean isEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(boolean estVerifie2) {
        this.estVerifie2 = estVerifie2;
    }

    public LocalDateTime getDateVerification2() {
        return dateVerification2;
    }

    public void setDateVerification2(LocalDateTime dateVerification2) {
        this.dateVerification2 = dateVerification2;
    }

    public String getUserLoginVerificateur2() {
        return userLoginVerificateur2;
    }

    public void setUserLoginVerificateur2(String userLoginVerificateur2) {
        this.userLoginVerificateur2 = userLoginVerificateur2;
    }
}
