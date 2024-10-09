package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name="idTitre")
@DiscriminatorValue("OPC")
@Table(name = "T_OPC", schema = "Titre")
public class Opc extends Titre {
    private BigDecimal vlOrigine;
    private Integer periodiciteVlNbre;
    private String periodiciteVlUnite;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeClassification", referencedColumnName = "codeClassificationOPC")
    private ClassificationOPC classificationOPC;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeAffectation", referencedColumnName = "idTypeAffectation")
    private TypeAffectationVL typeAffectationTitre;
    private String codeClassificationOPC;
    private String codeFormeJuridiqueOPC;
    private String libelleTypeAffectationVL;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFormeJuridiqueOpc", referencedColumnName = "idFormeJuridiqueOpc")
    private FormeJuridiqueOpc formeJuridiqueOpc;

    public BigDecimal getVlOrigine() {
        return vlOrigine;
    }

    public void setVlOrigine(BigDecimal vlOrigine) {
        this.vlOrigine = vlOrigine;
    }

    public Integer getPeriodiciteVlNbre() {
        return periodiciteVlNbre;
    }

    public void setPeriodiciteVlNbre(Integer periodiciteVlNbre) {
        this.periodiciteVlNbre = periodiciteVlNbre;
    }

    public String getPeriodiciteVlUnite() {
        return periodiciteVlUnite;
    }

    public void setPeriodiciteVlUnite(String periodiciteVlUnite) {
        this.periodiciteVlUnite = periodiciteVlUnite;
    }

    public String getCodeClassificationOPC() {
        return codeClassificationOPC;
    }

    public void setCodeClassificationOPC(String codeClassificationOPC) {
        this.codeClassificationOPC = codeClassificationOPC;
    }

    public String getCodeFormeJuridiqueOPC() {
        return codeFormeJuridiqueOPC;
    }

    public void setCodeFormeJuridiqueOPC(String codeFormeJuridiqueOPC) {
        this.codeFormeJuridiqueOPC = codeFormeJuridiqueOPC;
    }

    public String getLibelleTypeAffectationVL() {
        return libelleTypeAffectationVL;
    }

    public void setLibelleTypeAffectationVL(String libelleTypeAffectationVL) {
        this.libelleTypeAffectationVL = libelleTypeAffectationVL;
    }

    public ClassificationOPC getClassificationOPC() {
        return classificationOPC;
    }

    public void setClassificationOPC(ClassificationOPC classificationOPC) {
        this.classificationOPC = classificationOPC;
    }

    public TypeAffectationVL getTypeAffectationTitre() {
        return typeAffectationTitre;
    }

    public void setTypeAffectationTitre(TypeAffectationVL typeAffectationTitre) {
        this.typeAffectationTitre = typeAffectationTitre;
    }

    public FormeJuridiqueOpc getFormeJuridiqueOpc() {
        return formeJuridiqueOpc;
    }

    public void setFormeJuridiqueOpc(FormeJuridiqueOpc formeJuridiqueOpc) {
        this.formeJuridiqueOpc = formeJuridiqueOpc;
    }
}
