package com.ged.entity.opcciel;

import com.ged.entity.Base;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.titresciel.TypeAmortissement;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_InfosCirculaire8", schema = "Impressions")
public class InfosCirculaire8 extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numLigne;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String evenementMarquant;
    private String analysePortefeuille1;
    private String analysePortefeuille2;
    private String userLogin;
    @ManyToOne
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;

    public InfosCirculaire8() {
    }
    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getEvenementMarquant() {
        return evenementMarquant;
    }

    public void setEvenementMarquant(String evenementMarquant) {
        this.evenementMarquant = evenementMarquant;
    }

    public String getAnalysePortefeuille1() {
        return analysePortefeuille1;
    }

    public void setAnalysePortefeuille1(String analysePortefeuille1) {
        this.analysePortefeuille1 = analysePortefeuille1;
    }

    public String getAnalysePortefeuille2() {
        return analysePortefeuille2;
    }

    public void setAnalysePortefeuille2(String analysePortefeuille2) {
        this.analysePortefeuille2 = analysePortefeuille2;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }
}
