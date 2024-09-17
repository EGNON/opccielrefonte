package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TJ_ActionnaireCommission", schema = "Tarification")
public class ActionnaireCommission extends Base {
    @EmbeddedId
    private CleActionnaireCommission cleActionnaireCommission;

    private String codeProfil;
    private String libelleProfil;
    @ManyToOne()
    @JoinColumn(name = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;
    @ManyToOne()
    @JoinColumn(name = "idPersonne")
    @MapsId("idPersonne")
    private Personne personne;
    @Column(insertable = false,updatable = false)
    private String typeCommission;
    private LocalDateTime date;
    public ActionnaireCommission() {
    }

    public CleActionnaireCommission getCleActionnaireCommission() {
        return cleActionnaireCommission;
    }

    public void setCleActionnaireCommission(CleActionnaireCommission cleActionnaireCommission) {
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

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
