package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDetachementDroitDto extends OperationDto {
    private BigDecimal qteAction;
    private BigDecimal qteDroit;
    private String codeNatureOperation;
    private String userLogin;
    private Long idOpcvm;
    private Long idAvis;
    public OperationDetachementDroitDto() {
    }

    public Long getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(Long idAvis) {
        this.idAvis = idAvis;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public BigDecimal getQteAction() {
        return qteAction;
    }

    public void setQteAction(BigDecimal qteAction) {
        this.qteAction = qteAction;
    }

    public BigDecimal getQteDroit() {
        return qteDroit;
    }

    public void setQteDroit(BigDecimal qteDroit) {
        this.qteDroit = qteDroit;
    }
}
