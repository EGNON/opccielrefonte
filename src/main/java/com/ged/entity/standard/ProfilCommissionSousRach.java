package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

@Entity
@Table(name = "T_ProfilCommissionSousRach", schema = "Tarification")
public class ProfilCommissionSousRach extends Base {
    @EmbeddedId
    private CleProfilCommissionSousRach cleProfilCommissionSousRach;
    @Column(insertable = false,updatable = false)
    private String codeProfil;
    @ManyToOne()
    @JoinColumn(name = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;
    private String libelleProfil;
    private String typeCommission;
    private boolean standard;

    public ProfilCommissionSousRach() {
    }

    public CleProfilCommissionSousRach getCleProfilCommissionSousRach() {
        return cleProfilCommissionSousRach;
    }

    public void setCleProfilCommissionSousRach(CleProfilCommissionSousRach cleProfilCommissionSousRach) {
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

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }
}
