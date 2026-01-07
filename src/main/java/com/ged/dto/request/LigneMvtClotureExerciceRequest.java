package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LigneMvtClotureExerciceRequest {
    private Long idOpcvm;
    private LocalDateTime dateCloture;
    private BigDecimal ran;
    private Long etape;
    private String userLogin;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public LocalDateTime getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDateTime dateCloture) {
        this.dateCloture = dateCloture;
    }

    public BigDecimal getRan() {
        return ran;
    }

    public void setRan(BigDecimal ran) {
        this.ran = ran;
    }

    public Long getEtape() {
        return etape;
    }

    public void setEtape(Long etape) {
        this.etape = etape;
    }
}
