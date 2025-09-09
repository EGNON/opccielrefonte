package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoursTitreRequest {
    private String codePlace;
    private String userLogin;
    private Long niveau;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Boolean estVerifie1;
    private Boolean estVerifie2;
    private DatatableParameters datatableParameters;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getNiveau() {
        return niveau;
    }

    public void setNiveau(Long niveau) {
        this.niveau = niveau;
    }

    public String getCodePlace() {
        return codePlace;
    }

    public void setCodePlace(String codePlace) {
        this.codePlace = codePlace;
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

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }
}
