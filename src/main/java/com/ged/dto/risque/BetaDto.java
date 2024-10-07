package com.ged.dto.risque;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BetaDto {
    private LocalDateTime dateFermeture;
    private double VL;
    private double nav;
    private double performancePortefeuille;
    private double performanceBenchMark;
    private double volatiliteAnnualiseeOpcvm;
    private double volatiliteAnnualiseeBenchMark;
    private double beta;

    public BetaDto() {
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

    public double getNav() {
        return nav;
    }

    public void setNav(double nav) {
        this.nav = nav;
    }

    public double getPerformancePortefeuille() {
        return performancePortefeuille;
    }

    public void setPerformancePortefeuille(double performancePortefeuille) {
        this.performancePortefeuille = performancePortefeuille;
    }

    public double getPerformanceBenchMark() {
        return performanceBenchMark;
    }

    public void setPerformanceBenchMark(double performanceBenchMark) {
        this.performanceBenchMark = performanceBenchMark;
    }

    public double getVolatiliteAnnualiseeOpcvm() {
        return volatiliteAnnualiseeOpcvm;
    }

    public void setVolatiliteAnnualiseeOpcvm(double volatiliteAnnualiseeOpcvm) {
        this.volatiliteAnnualiseeOpcvm = volatiliteAnnualiseeOpcvm;
    }

    public double getVolatiliteAnnualiseeBenchMark() {
        return volatiliteAnnualiseeBenchMark;
    }

    public void setVolatiliteAnnualiseeBenchMark(double volatiliteAnnualiseeBenchMark) {
        this.volatiliteAnnualiseeBenchMark = volatiliteAnnualiseeBenchMark;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }
}
