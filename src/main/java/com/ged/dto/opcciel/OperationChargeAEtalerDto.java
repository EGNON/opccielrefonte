package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationChargeAEtalerDto extends OperationDto {
    private boolean estGenere;
    private Double actifBrut;
    private String codeCharge;
    private int nbreJour;
    private int usance;
    private String codeModele;

    public OperationChargeAEtalerDto() {
    }

    public boolean isEstGenere() {
        return estGenere;
    }

    public void setEstGenere(boolean estGenere) {
        this.estGenere = estGenere;
    }

    public Double getActifBrut() {
        return actifBrut;
    }

    public void setActifBrut(Double actifBrut) {
        this.actifBrut = actifBrut;
    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }

    public int getNbreJour() {
        return nbreJour;
    }

    public void setNbreJour(int nbreJour) {
        this.nbreJour = nbreJour;
    }

    public int getUsance() {
        return usance;
    }

    public void setUsance(int usance) {
        this.usance = usance;
    }

    public String getCodeModele() {
        return codeModele;
    }

    public void setCodeModele(String codeModele) {
        this.codeModele = codeModele;
    }
}
