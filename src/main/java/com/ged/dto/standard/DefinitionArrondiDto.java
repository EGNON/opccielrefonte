package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefinitionArrondiDto extends Base {
    private String codeDefinitionArrondi;
    private String typeArrondi;
    private Boolean estParDefaut;
    private Long nbreDecimaux;
    private Boolean estParValSup;
    private String obserVationModeleArrondis;
    private String libelleDefinitionArrondi;
    private Long numLigne;
    private String userLogin;
    public DefinitionArrondiDto() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getCodeDefinitionArrondi() {
        return codeDefinitionArrondi;
    }

    public void setCodeDefinitionArrondi(String codeDefinitionArrondi) {
        this.codeDefinitionArrondi = codeDefinitionArrondi;
    }

    public String getTypeArrondi() {
        return typeArrondi;
    }

    public void setTypeArrondi(String typeArrondi) {
        this.typeArrondi = typeArrondi;
    }

    public Boolean getEstParDefaut() {
        return estParDefaut;
    }

    public void setEstParDefaut(Boolean estParDefaut) {
        this.estParDefaut = estParDefaut;
    }

    public Long getNbreDecimaux() {
        return nbreDecimaux;
    }

    public void setNbreDecimaux(Long nbreDecimaux) {
        this.nbreDecimaux = nbreDecimaux;
    }

    public Boolean getEstParValSup() {
        return estParValSup;
    }

    public void setEstParValSup(Boolean estParValSup) {
        this.estParValSup = estParValSup;
    }

    public String getObserVationModeleArrondis() {
        return obserVationModeleArrondis;
    }

    public void setObserVationModeleArrondis(String obserVationModeleArrondis) {
        this.obserVationModeleArrondis = obserVationModeleArrondis;
    }

    public String getLibelleDefinitionArrondi() {
        return libelleDefinitionArrondi;
    }

    public void setLibelleDefinitionArrondi(String libelleDefinitionArrondi) {
        this.libelleDefinitionArrondi = libelleDefinitionArrondi;
    }

    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    //FIN
}
