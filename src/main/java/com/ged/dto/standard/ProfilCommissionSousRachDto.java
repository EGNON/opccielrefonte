package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfilCommissionSousRachDto extends Base {
    private CleProfilCommissionSousRachDto cleProfilCommissionSousRach;
    private String codeProfil;
    private OpcvmDto opcvm;
    private String libelleProfil;
    private String typeCommission;
    private boolean standard;

    public ProfilCommissionSousRachDto() {
    }

    public CleProfilCommissionSousRachDto getCleProfilCommissionSousRach() {
        return cleProfilCommissionSousRach;
    }

    public void setCleProfilCommissionSousRach(CleProfilCommissionSousRachDto cleProfilCommissionSousRach) {
        this.cleProfilCommissionSousRach = cleProfilCommissionSousRach;
    }

    public String getCodeProfil() {
        return codeProfil;
    }

    public void setCodeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
    }

    public String getLibelleProfil() {
        return libelleProfil;
    }

    public void setLibelleProfil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
    }

    public String getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(String typeCommission) {
        this.typeCommission = typeCommission;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }
}
