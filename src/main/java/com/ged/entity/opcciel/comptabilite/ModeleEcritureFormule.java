package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;


@Entity
@Table(name = "TJ_ModeleEcritureFormule", schema = "Comptabilite")
public class ModeleEcritureFormule extends Base {
    @EmbeddedId
    private CleModeleEcritureFormule idModeleEcritureFormule;
    @ManyToOne()
    @JoinColumn(name = "codeModeleEcriture")
    @MapsId("codeModeleEcriture")
    private ModeleEcriture modeleEcriture;
    @ManyToOne()
    @JoinColumn(name = "idFormule")
    @MapsId("idFormule")
    private Formule formule;
    private String userLogin;
    public ModeleEcritureFormule() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public CleModeleEcritureFormule getIdModeleEcritureFormule() {
        return idModeleEcritureFormule;
    }

    public void setIdModeleEcritureFormule(CleModeleEcritureFormule idModeleEcritureFormule) {
        this.idModeleEcritureFormule = idModeleEcritureFormule;
    }

    public ModeleEcriture getModeleEcriture() {
        return modeleEcriture;
    }

    public void setModeleEcriture(ModeleEcriture modeleEcriture) {
        this.modeleEcriture = modeleEcriture;
    }

    public Formule getFormule() {
        return formule;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }
}
