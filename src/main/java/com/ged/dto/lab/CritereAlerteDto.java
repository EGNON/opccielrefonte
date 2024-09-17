package com.ged.dto.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CritereAlerteDto {
    private long idCritereAlerte;
    private LocalDateTime dateAlerte;
    private String description;
    private String etat;
    private String expression;
    private String sql;
    private String route;
    public CritereAlerteDto() {
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public long getIdCritereAlerte() {
        return idCritereAlerte;
    }

    public void setIdCritereAlerte(long idCritereAlerte) {
        this.idCritereAlerte = idCritereAlerte;
    }

    public LocalDateTime getDateAlerte() {
        return dateAlerte;
    }

    public void setDateAlerte(LocalDateTime dateAlerte) {
        this.dateAlerte = dateAlerte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
