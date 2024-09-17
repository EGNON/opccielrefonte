package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_CritereAlerte",schema = "Notification")
public class CritereAlerte extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCritereAlerte;
    private LocalDateTime dateAlerte;
    private String description;
    private String etat;
    @Column(length = 5000)
    private String expression;
    @Column(length = 5000)
    private String sql;
    @Column(length = 8000)
    private String route;

    public CritereAlerte() {
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
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
