package com.ged.dto.risque;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatioSharpDto {
    private LocalDateTime dateFermeture;
    private double VL;
    private double dividendeDistribue;
    private double performanceAnnuelle;
    private double volatiliteAnnualisee;
    private double sharp;

    public RatioSharpDto() {
    }

    public double getVL() {
        return VL;
    }

    public void setVL(double VL) {
        this.VL = VL;
    }

    public LocalDateTime getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(LocalDateTime dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public double getDividendeDistribue() {
        return dividendeDistribue;
    }

    public void setDividendeDistribue(double dividendeDistribue) {
        this.dividendeDistribue = dividendeDistribue;
    }

    public double getPerformanceAnnuelle() {
        return performanceAnnuelle;
    }

    public void setPerformanceAnnuelle(double performanceAnnuelle) {
        this.performanceAnnuelle = performanceAnnuelle;
    }

    public double getVolatiliteAnnualisee() {
        return volatiliteAnnualisee;
    }

    public void setVolatiliteAnnualisee(double volatiliteAnnualisee) {
        this.volatiliteAnnualisee = volatiliteAnnualisee;
    }

    public double getSharp() {
        return sharp;
    }

    public void setSharp(double sharp) {
        this.sharp = sharp;
    }
}
