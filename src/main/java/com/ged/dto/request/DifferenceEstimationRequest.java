package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.SoldeCompteFormuleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DifferenceEstimationRequest {
    private Long idSeance;
    private Long idOpcvm;
    private Long idOperation;
    private Long idTitre;
    private Long niveau;
    private BigDecimal actifBrut;
    private Long nbreJour;
    private Long usance;
    private LocalDateTime dateSeance;
    private LocalDateTime dateOperation;
    private Boolean estVerifie1;
    private Boolean estVerifie2;
    private String userLogin1;
    private String userLogin2;
    private String userLogin;
    private DatatableParameters datatableParameters;
    private SoldeCompteFormuleDto soldeCompteFormuleDto;


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

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public Long getNiveau() {
        return niveau;
    }

    public void setNiveau(Long niveau) {
        this.niveau = niveau;
    }

    public BigDecimal getActifBrut() {
        return actifBrut;
    }

    public void setActifBrut(BigDecimal actifBrut) {
        this.actifBrut = actifBrut;
    }

    public Long getNbreJour() {
        return nbreJour;
    }

    public void setNbreJour(Long nbreJour) {
        this.nbreJour = nbreJour;
    }

    public Long getUsance() {
        return usance;
    }

    public void setUsance(Long usance) {
        this.usance = usance;
    }

    public LocalDateTime getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(LocalDateTime dateSeance) {
        this.dateSeance = dateSeance;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
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

    public String getUserLogin2() {
        return userLogin2;
    }

    public void setUserLogin2(String userLogin2) {
        this.userLogin2 = userLogin2;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }

    public SoldeCompteFormuleDto getSoldeCompteFormuleDto() {
        return soldeCompteFormuleDto;
    }

    public void setSoldeCompteFormuleDto(SoldeCompteFormuleDto soldeCompteFormuleDto) {
        this.soldeCompteFormuleDto = soldeCompteFormuleDto;
    }
}
