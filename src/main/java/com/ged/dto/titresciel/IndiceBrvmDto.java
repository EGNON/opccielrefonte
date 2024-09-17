package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndiceBrvmDto {
    private CleIndiceBrvmDto idIndice;
    private BigDecimal valeur;
    private BigDecimal variationNet;
    private BigDecimal percentVariation;
    private Boolean estParDefaut;

    public CleIndiceBrvmDto getIdIndice() {
        return idIndice;
    }

    public void setIdIndice(CleIndiceBrvmDto idIndice) {
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

    @Override
    public String toString() {
        return "IndiceBrvmDto{" +
                "idIndice=" + idIndice +
                ", valeur=" + valeur +
                ", variationNet=" + variationNet +
                ", percentVariation=" + percentVariation +
                ", estParDefaut=" + estParDefaut +
                '}';
    }
}
