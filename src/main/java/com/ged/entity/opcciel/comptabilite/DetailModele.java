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
    @MapsId("codeModeleEcriture")
    private ModeleEcriture modeleEcriture;
	private String sensMvt;
    @ManyToOne()
    @JoinColumn(name="idFormule")
    private Formule formule;
    @Column(insertable = false,updatable = false, length = 16)
    private String numCompteComptable;
    @Column(insertable = false,updatable = false)
    private int numeroOrdre;

    public DetailModele() {
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
