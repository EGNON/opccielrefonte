package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_IndiceBrvm", schema = "Titre")
public class IndiceBrvm {
    @EmbeddedId
    private CleIndiceBrvm idIndice;
    private BigDecimal valeur;
    private BigDecimal variationNet;
    private BigDecimal percentVariation;
    @Column(columnDefinition = "BIT", nullable = false)
    private Boolean estParDefaut;

    public CleIndiceBrvm getIdIndice() {
        return idIndice;
    }

    public void setIdIndice(CleIndiceBrvm idIndice) {
        this.idIndice = idIndice;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public BigDecimal getVariationNet() {
        return variationNet;
    }

    public void setVariationNet(BigDecimal variationNet) {
        this.variationNet = variationNet;
    }

    public BigDecimal getPercentVariation() {
        return percentVariation;
    }

    public void setPercentVariation(BigDecimal percentVariation) {
        this.percentVariation = percentVariation;
    }

    public Boolean getEstParDefaut() {
        return estParDefaut;
    }

    public void setEstParDefaut(Boolean estParDefaut) {
        this.estParDefaut = estParDefaut;
    }
}
