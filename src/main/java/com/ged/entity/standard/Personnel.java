package com.ged.entity.standard;

import  com.ged.entity.crm.Affectation;
import com.ged.entity.crm.AgentConcerne;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Personnel", schema = "Parametre")
@DiscriminatorValue("PL")
@PrimaryKeyJoinColumn(name = "idPersonne")
public class Personnel extends PersonnePhysique{
    private String matricule;
    private Boolean estCommercial = false;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<AgentConcerne> agentConcernes = new HashSet<>();
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "personnel")
    //@JsonManagedReference
    private Set<Affectation> affectations = new HashSet<>();
    @OneToMany(mappedBy = "personnel")
    //@JsonManagedReference
    private Set<StatutPersonne> statutPersonnes;

    public Personnel() {
        super();
    }

    public Personnel(String matricule) {
        //super(nom, prenom, sexe, civilite, userLogin, profession);
        this.matricule = matricule;
        this.estCommercial = false;
    }

    @Override
    public void setDenomination(String denomination) {
        super.setDenomination(denomination);
    }

    @Override
    public Set<StatutPersonne> getStatutPersonnes() {
        return statutPersonnes;
    }

    @Override
    public void setStatutPersonnes(Set<StatutPersonne> statutPersonnes) {
        this.statutPersonnes = statutPersonnes;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

//    public boolean isEstCommercial() {
//        return estCommercial;
//    }
//
//    public void setEstCommercial(boolean estCommercial) {
//        this.estCommercial = estCommercial;
//    }

    public Set<AgentConcerne> getAgentConcernes() {
        return agentConcernes;
    }

    public void setAgentConcernes(Set<AgentConcerne> agentConcernes) {
        this.agentConcernes = agentConcernes;
    }

    public Set<Affectation> getAffectations() {
        return affectations;
    }

    public void setAffectations(Set<Affectation> affectations) {
        this.affectations = affectations;
    }

    public Boolean getEstCommercial() {
        return estCommercial;
    }

    public void setEstCommercial(Boolean estCommercial) {
        this.estCommercial = estCommercial;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "matricule='" + matricule + '\'' +
                '}';
    }
}
