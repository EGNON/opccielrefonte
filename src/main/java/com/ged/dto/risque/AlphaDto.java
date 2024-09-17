package com.ged.dto.risque;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlphaDto {
    private LocalDateTime dateFermeture;
    private double VL;
    private double dividendeDistribue;
    private double performanceAnnuelle;
    private double indiceReference;
    private double alpha;

    public AlphaDto() {
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

    public double getIndiceReference() {
        return indiceReference;
    }

    public void setIndiceReference(double indiceReference) {
        this.indiceReference = indiceReference;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
}
