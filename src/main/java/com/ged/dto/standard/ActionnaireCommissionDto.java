package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.entity.Base;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionnaireCommissionDto extends Base {
    private CleActionnaireCommissionDto cleActionnaireCommission;

    private String codeProfil;
    private String libelleProfil;
    private OpcvmDto opcvm;
    private PersonneDto personne;
    private String typeCommission;
    private LocalDateTime date;
    public ActionnaireCommissionDto() {
    }

    public CleActionnaireCommissionDto getCleActionnaireCommission() {
        return cleActionnaireCommission;
    }

    public void setCleActionnaireCommission(CleActionnaireCommissionDto cleActionnaireCommission) {
        this.cleActionnaireCommission = cleActionnaireCommission;
    }

    public String getLibelleProfil() {
        return libelleProfil;
    }

    public void setLibelleProfil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
    }

    public String getCodeProfil() {
        return codeProfil;
    }

    public void setCodeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
    }

    public String getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(String typeCommission) {
        this.typeCommission = typeCommission;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
