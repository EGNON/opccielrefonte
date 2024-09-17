package com.ged.entity.crm;

import com.ged.entity.Base;
import com.ged.entity.standard.Personnel;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_AgentConcerne", schema = "RDV")
public class AgentConcerne extends Base {
    @EmbeddedId
    private CleAgentConcerne id;
    @ManyToOne
    @MapsId("idPersonne")
    @JoinColumn(name = "idPersonne")
    //@JsonBackReference
    private Personnel personnel;
    @ManyToOne
    @MapsId("idRdv")
    @JoinColumn(name = "idRdv")
    //@JsonBackReference
    private RDV rdv;
    private boolean etat;

    public AgentConcerne() {
    }

    public AgentConcerne(CleAgentConcerne id, Personnel personnel, RDV rdv) {
        this.id = id;
        this.personnel = personnel;
        this.rdv = rdv;
    }

    public CleAgentConcerne getId() {
        return id;
    }

    public void setId(CleAgentConcerne id) {
        this.id = id;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public RDV getRdv() {
        return rdv;
    }

    public void setRdv(RDV rdv) {
        this.rdv = rdv;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
