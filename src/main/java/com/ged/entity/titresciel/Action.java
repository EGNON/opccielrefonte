package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name="idTitre")
@DiscriminatorValue("ACT")
@Table(name = "T_Action", schema = "Titre")
public class Action extends Titre{
    private BigDecimal per;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeAction", referencedColumnName = "idTypeAction")
    private TypeAction typeAction;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSousTypeAction", referencedColumnName = "idSousTypeAction")
    private SousTypeAction sousTypeAction;
    private BigDecimal nominalNonVerse;

    public BigDecimal getPer() {
        return per;
    }

    public void setPer(BigDecimal per) {
        this.per = per;
    }

    public TypeAction getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(TypeAction typeAction) {
        this.typeAction = typeAction;
    }

    public SousTypeAction getSousTypeAction() {
        return sousTypeAction;
    }

    public void setSousTypeAction(SousTypeAction sousTypeAction) {
        this.sousTypeAction = sousTypeAction;
    }

    public BigDecimal getNominalNonVerse() {
        return nominalNonVerse;
    }

    public void setNominalNonVerse(BigDecimal nominalNonVerse) {
        this.nominalNonVerse = nominalNonVerse;
    }
}
