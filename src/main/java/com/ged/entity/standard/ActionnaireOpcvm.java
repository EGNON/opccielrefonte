package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_OpcvmActionnaire", schema = "Tarification")
public class ActionnaireOpcvm extends Base {
    @EmbeddedId
    private CleActionnaireOpcvm cleActionnaireOpcvm;
    @ManyToOne()
    @JoinColumn(name = "idPersonne")
    @MapsId("idPersonne")
    private Personne personne;
    @ManyToOne()
    @JoinColumn(name = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;

    public ActionnaireOpcvm() {
    }

    public CleActionnaireOpcvm getCleActionnaireOpcvm() {
        return cleActionnaireOpcvm;
    }

    public void setCleActionnaireOpcvm(CleActionnaireOpcvm cleActionnaireOpcvm) {
        this.cleActionnaireOpcvm = cleActionnaireOpcvm;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }
}
