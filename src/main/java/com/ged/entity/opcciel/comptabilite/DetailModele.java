package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_DetailModele", schema = "Comptabilite")
public class DetailModele extends Base {
    @EmbeddedId
    private CleDetailModele idDetailModele;
    @ManyToOne()
    @JoinColumn(name="coodeModeleEcriture")
    @MapsId("coodeModeleEcriture")
    private ModeleEcriture modeleEcriture;
	private String sensMvt;
	private String userLogin;
    @ManyToOne()
    @JoinColumn(name="idFormule")
    private Formule formule;
    @Column(insertable = false,updatable = false, length = 16)
    private String numCompteComptable;
    @Column(insertable = false,updatable = false)
    private int numeroOrdre;
    @Column(insertable = false,updatable = false)
    private Boolean actionnaire;
    @Column(insertable = false,updatable = false)
    private Boolean banque;

    public DetailModele() {
    }

    public Boolean getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(Boolean actionnaire) {
        this.actionnaire = actionnaire;
    }

    public Boolean getBanque() {
        return banque;
    }

    public void setBanque(Boolean banque) {
        this.banque = banque;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(int numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public CleDetailModele getIdDetailModele() {
        return idDetailModele;
    }

    public void setIdDetailModele(CleDetailModele idDetailModele) {
        this.idDetailModele = idDetailModele;
    }

    public ModeleEcriture getModeleEcriture() {
        return modeleEcriture;
    }

    public void setModeleEcriture(ModeleEcriture modeleEcriture) {
        this.modeleEcriture = modeleEcriture;
    }

    public String getSensMvt() {
        return sensMvt;
    }

    public void setSensMvt(String sensMvt) {
        this.sensMvt = sensMvt;
    }

    public Formule getFormule() {
        return formule;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public String getNumCompteComptable() {
        return numCompteComptable;
    }

    public void setNumCompteComptable(String numCompteComptable) {
        this.numCompteComptable = numCompteComptable;
    }
}
