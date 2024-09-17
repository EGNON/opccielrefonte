package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.dto.crm.AffectationDto;
import com.ged.dto.crm.AgentConcerneDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonnelDto extends PersonnePhysiqueDto {
    private String matricule;
    private Boolean estCommercial = false;
    private Set<AgentConcerneDto> agentConcernes = new HashSet<>();
    private Set<AffectationDto> affectations = new HashSet<>();
    private Set<StatutPersonneDto> statutPersonnes;

    public PersonnelDto() {
    }

    public PersonnelDto(String nom, String prenom, String sexe, LocalDateTime dateNaissance, String civilite, String lieuTravail, double autresRevenus, String periodicite, String statutMatrimonial, int nbrEnfant, int nbrPersonneACharge, String nomEmployeur, String adressePostaleEmp, String adresseGeoEmp, String telEmp, String emailEmp, String nomPere, String prenomsPere, LocalDateTime dateNaissancePere, String nomMere, String prenomsMere, LocalDateTime dateNaissanceMere, String nomConjoint, String prenomConjoint, LocalDateTime dateNaissanceConjoint, String origineFonds, String transactionEnvisagee, String immobilier, String autresBiens, double surfaceTotale, double salaire, String matricule) {
        super(nom, prenom, sexe, dateNaissance, civilite, lieuTravail, autresRevenus, periodicite, statutMatrimonial, nbrEnfant, nbrPersonneACharge, nomEmployeur, adressePostaleEmp, adresseGeoEmp, telEmp, emailEmp, nomPere, prenomsPere, dateNaissancePere, nomMere, prenomsMere, dateNaissanceMere, nomConjoint, prenomConjoint, dateNaissanceConjoint, origineFonds, transactionEnvisagee, immobilier, autresBiens, surfaceTotale, salaire);
        this.matricule = matricule;
        this.estCommercial = false;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Boolean getEstCommercial() {
        return estCommercial;
    }

    public void setEstCommercial(Boolean estCommercial) {
        this.estCommercial = estCommercial;
    }

    public Set<AgentConcerneDto> getAgentConcernes() {
        return agentConcernes;
    }

    public void setAgentConcernes(Set<AgentConcerneDto> agentConcernes) {
        this.agentConcernes = agentConcernes;
    }

    public Set<AffectationDto> getAffectations() {
        return affectations;
    }

    public void setAffectations(Set<AffectationDto> affectations) {
        this.affectations = affectations;
    }

    @Override
    public Set<StatutPersonneDto> getStatutPersonnes() {
        return statutPersonnes;
    }

    @Override
    public void setStatutPersonnes(Set<StatutPersonneDto> statutPersonnes) {
        this.statutPersonnes = statutPersonnes;
    }

    @Override
    public String toString() {
        return "PersonnelDto{" +
                "matricule='" + matricule + '\'' +
                '}';
    }
}
