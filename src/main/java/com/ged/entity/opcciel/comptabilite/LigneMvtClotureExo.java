package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.opcciel.*;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_LigneMvtClotureExo", schema = "Comptabilite")
public class LigneMvtClotureExo extends Base {
    @EmbeddedId
    private CleLigneMvtClotureExo idLigneMvtClotureExo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeNatureOperation")
    private NatureOperation natureOperation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeModeleEcriture")
    private ModeleEcriture modeleEcriture;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlan")
    private Plan plan;
    private String Sens;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFormule")
    private Formule formule;
    private Double valeur;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeTypeFormule")
    private TypeFormule typeFormule;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeIB")
    private Ib ib;
    private String codeRubrique;
    private String codePosition;

    public LigneMvtClotureExo() {
    }

    public CleLigneMvtClotureExo getIdLigneMvtClotureExo() {
        return idLigneMvtClotureExo;
    }

    public void setIdLigneMvtClotureExo(CleLigneMvtClotureExo idLigneMvtClotureExo) {
        this.idLigneMvtClotureExo = idLigneMvtClotureExo;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public NatureOperation getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperation natureOperation) {
        this.natureOperation = natureOperation;
    }

    public ModeleEcriture getModeleEcriture() {
        return modeleEcriture;
    }

    public void setModeleEcriture(ModeleEcriture modeleEcriture) {
        this.modeleEcriture = modeleEcriture;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getSens() {
        return Sens;
    }

    public void setSens(String sens) {
        Sens = sens;
    }

    public Formule getFormule() {
        return formule;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public TypeFormule getTypeFormule() {
        return typeFormule;
    }

    public void setTypeFormule(TypeFormule typeFormule) {
        this.typeFormule = typeFormule;
    }

    public Ib getIb() {
        return ib;
    }

    public void setIb(Ib ib) {
        this.ib = ib;
    }

    public String getCodeRubrique() {
        return codeRubrique;
    }

    public void setCodeRubrique(String codeRubrique) {
        this.codeRubrique = codeRubrique;
    }

    public String getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(String codePosition) {
        this.codePosition = codePosition;
    }
}
