package com.ged.entity.opcciel;

import com.ged.entity.Base;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_OperationExtourneVDE", schema = "Operation")
public class OperationExtourneVDE extends Base {
    @EmbeddedId
    private CleOperationExtourneVDE idOperationExtourneVDE;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTitre")
    @MapsId("idTitre")
    private Titre titre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;
    @Column(precision = 18, scale = 6)
	private BigDecimal qteDetenue;
    @Column(precision = 18, scale = 6)
    private BigDecimal cours;
    @Column(precision = 18, scale = 6)
    private BigDecimal cumpT;
    @Column(precision = 18, scale = 6)
    private BigDecimal cumpReel;
    @Column(precision = 18, scale = 6)
    private BigDecimal plusOuMoinsValue;
    @Column(precision = 18, scale = 6)
    private BigDecimal nbreJourCourus;
    @Column(precision = 18, scale = 6)
    private BigDecimal interetCourus;
    @Column(precision = 18, scale = 6)
    private BigDecimal valeurVDECours;
    @Column(precision = 18, scale = 6)
    private BigDecimal valeurVDEInteret;
    @Column(precision = 18, scale = 6)
    private BigDecimal idOpCours;
    @Column(precision = 18, scale = 6)
    private BigDecimal idOpInteret;
    @Column(precision = 18, scale = 6)
    private BigDecimal irvm;
    private Boolean estVerifie;
    private LocalDateTime dateVerification;
    private String userLoginVerificateur;
    private Boolean estVerifie1;
    private LocalDateTime dateVerification1;
    private String userLoginVerificateur1;
    private Boolean estVerifie2;
    private LocalDateTime dateVerification2;
    private String userLoginVerificateur2;

    public OperationExtourneVDE() {
    }

    public CleOperationExtourneVDE getIdOperationExtourneVDE() {
        return idOperationExtourneVDE;
    }

    public void setIdOperationExtourneVDE(CleOperationExtourneVDE idOperationExtourneVDE) {
        this.idOperationExtourneVDE = idOperationExtourneVDE;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public BigDecimal getQteDetenue() {
        return qteDetenue;
    }

    public void setQteDetenue(BigDecimal qteDetenue) {
        this.qteDetenue = qteDetenue;
    }

    public BigDecimal getCours() {
        return cours;
    }

    public void setCours(BigDecimal cours) {
        this.cours = cours;
    }

    public BigDecimal getCumpT() {
        return cumpT;
    }

    public void setCumpT(BigDecimal cumpT) {
        this.cumpT = cumpT;
    }

    public BigDecimal getCumpReel() {
        return cumpReel;
    }

    public void setCumpReel(BigDecimal cumpReel) {
        this.cumpReel = cumpReel;
    }

    public BigDecimal getPlusOuMoinsValue() {
        return plusOuMoinsValue;
    }

    public void setPlusOuMoinsValue(BigDecimal plusOuMoinsValue) {
        this.plusOuMoinsValue = plusOuMoinsValue;
    }

    public BigDecimal getNbreJourCourus() {
        return nbreJourCourus;
    }

    public void setNbreJourCourus(BigDecimal nbreJourCourus) {
        this.nbreJourCourus = nbreJourCourus;
    }

    public BigDecimal getInteretCourus() {
        return interetCourus;
    }

    public void setInteretCourus(BigDecimal interetCourus) {
        this.interetCourus = interetCourus;
    }

    public BigDecimal getValeurVDECours() {
        return valeurVDECours;
    }

    public void setValeurVDECours(BigDecimal valeurVDECours) {
        this.valeurVDECours = valeurVDECours;
    }

    public BigDecimal getValeurVDEInteret() {
        return valeurVDEInteret;
    }

    public void setValeurVDEInteret(BigDecimal valeurVDEInteret) {
        this.valeurVDEInteret = valeurVDEInteret;
    }

    public BigDecimal getIdOpCours() {
        return idOpCours;
    }

    public void setIdOpCours(BigDecimal idOpCours) {
        this.idOpCours = idOpCours;
    }

    public BigDecimal getIdOpInteret() {
        return idOpInteret;
    }

    public void setIdOpInteret(BigDecimal idOpInteret) {
        this.idOpInteret = idOpInteret;
    }

    public BigDecimal getIrvm() {
        return irvm;
    }

    public void setIrvm(BigDecimal irvm) {
        this.irvm = irvm;
    }

    public Boolean getEstVerifie() {
        return estVerifie;
    }

    public void setEstVerifie(Boolean estVerifie) {
        this.estVerifie = estVerifie;
    }

    public Boolean getEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(Boolean estVerifie1) {
        this.estVerifie1 = estVerifie1;
    }

    public Boolean getEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(Boolean estVerifie2) {
        this.estVerifie2 = estVerifie2;
    }

    public LocalDateTime getDateVerification() {
        return dateVerification;
    }

    public void setDateVerification(LocalDateTime dateVerification) {
        this.dateVerification = dateVerification;
    }

    public String getUserLoginVerificateur() {
        return userLoginVerificateur;
    }

    public void setUserLoginVerificateur(String userLoginVerificateur) {
        this.userLoginVerificateur = userLoginVerificateur;
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
