package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.dto.standard.PersonnePhysiqueDto;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportationDepotDto {
    private OpcvmDto opcvm;
    private List<PersonnePhysiqueDto> phList;
    private List<PersonneMoraleDto> pmList;

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public List<PersonnePhysiqueDto> getPhList() {
        return phList;
    }

    public void setPhList(List<PersonnePhysiqueDto> phList) {
        this.phList = phList;
    }

    public List<PersonneMoraleDto> getPmList() {
        return pmList;
    }

    public void setPmList(List<PersonneMoraleDto> pmList) {
        this.pmList = pmList;
    }
}
