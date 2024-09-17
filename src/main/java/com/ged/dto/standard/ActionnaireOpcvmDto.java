package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionnaireOpcvmDto extends Base {
    private CleActionnaireOpcvmDto cleActionnaireOpcvm;
    private PersonneDto personne;
    private OpcvmDto opcvm;

    public ActionnaireOpcvmDto() {
    }

    public CleActionnaireOpcvmDto getCleActionnaireOpcvm() {
        return cleActionnaireOpcvm;
    }

    public void setCleActionnaireOpcvm(CleActionnaireOpcvmDto cleActionnaireOpcvm) {
        this.cleActionnaireOpcvm = cleActionnaireOpcvm;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }
}
