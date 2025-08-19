package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.BaseDto;
import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfosCirculaire8Dto extends BaseDto {
    private Long numLigne;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String evenementMarquant;
    private String analysePortefeuille1;
    private String analysePortefeuille2;
    private String userLogin;
    private OpcvmDto opcvm;

    public InfosCirculaire8Dto() {
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

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }
}
