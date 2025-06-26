package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtourneVDERequest {
    private Long idSeance;
    private Long idOpcvm;
    private Long idTitre;
    private Boolean estVerifie;
    private Boolean estVerifie1;
    private Boolean estVerifie2;
    private String userLogin1;

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public Boolean getEstVerifie() {
        return estVerifie;
    }

    public void setEstVerifie(Boolean estVerifie) {
        this.estVerifie = estVerifie;
    }

    public Boolean getEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(Boolean estVerifie1) {
        this.estVerifie1 = estVerifie1;
    }

    public Boolean getEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(Boolean estVerifie2) {
        this.estVerifie2 = estVerifie2;
    }

    public String getUserLogin1() {
        return userLogin1;
    }

    public void setUserLogin1(String userLogin1) {
        this.userLogin1 = userLogin1;
    }
}
