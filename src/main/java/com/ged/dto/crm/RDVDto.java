package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RDVDto {
    private Long idRdv;
    private Date dateDebRdv;
    private Date dateFinRdv;
    private Time heureFinRdv;
    private Time heureDebutRdv;
    private String objetRdv;
    private String denomination;
    private Date dateDebReelle;
    private Time heureDebReelle;
    private Date dateFinReelle;
    private Time heureFinReelle;
    private PaysDto paysDto;
    private PersonnePhysiqueMoraleDto personnePhysiqueMoraleDto;
    private PersonneDto personne;
    private QuartierDto quartierDto;
    private CommuneDto communeDto;

    private Set<AgentConcerneDto> agentConcerneDtos = new HashSet<>();
    private Set<DocumentDto> documents = new HashSet<>();
    private CompteRenduDto compteRenduDto;
    private ModeleMsgAlerteDto modeleMsgAlerteDto;
    public RDVDto() {

    }

    public RDVDto(Date dateDebRdv, String objetRdv, PaysDto paysDto, PersonnePhysiqueMoraleDto personneDto, CompteRenduDto compteRenduDto) {
        this.dateDebRdv = dateDebRdv;
        this.objetRdv = objetRdv;
        this.paysDto = paysDto;
        this.personnePhysiqueMoraleDto = personneDto;
        this.compteRenduDto = compteRenduDto;
    }

    public ModeleMsgAlerteDto getModeleMsgAlerteDto() {
        return modeleMsgAlerteDto;
    }

    public void setModeleMsgAlerteDto(ModeleMsgAlerteDto modeleMsgAlerteDto) {
        this.modeleMsgAlerteDto = modeleMsgAlerteDto;
    }

    public Time getHeureDebutRdv() {
        return heureDebutRdv;
    }

    public void setHeureDebutRdv(Time heureDebutRdv) {
        this.heureDebutRdv = heureDebutRdv;
    }

    public Set<DocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDto> documents) {
        this.documents = documents;
    }

    public CommuneDto getCommuneDto() {
        return communeDto;
    }

    public void setCommuneDto(CommuneDto communeDto) {
        this.communeDto = communeDto;
    }

    public Date getDateDebReelle() {
        return dateDebReelle;
    }

    public void setDateDebReelle(Date dateDebReelle) {
        this.dateDebReelle = dateDebReelle;
    }

    public Time getHeureDebReelle() {
        return heureDebReelle;
    }

    public void setHeureDebReelle(Time heureDebReelle) {
        this.heureDebReelle = heureDebReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public Time getHeureFinReelle() {
        return heureFinReelle;
    }

    public void setHeureFinReelle(Time heureFinReelle) {
        this.heureFinReelle = heureFinReelle;
    }

    public QuartierDto getQuartierDto() {
        return quartierDto;
    }

    public void setQuartierDto(QuartierDto quartierDto) {
        this.quartierDto = quartierDto;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Long getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(Long idRdv) {
        this.idRdv = idRdv;
    }

    public Date getDateDebRdv() {
        return dateDebRdv;
    }

    public void setDateDebRdv(Date dateDebRdv) {
        this.dateDebRdv = dateDebRdv;
    }

    public Date getDateFinRdv() {
        return dateFinRdv;
    }

    public void setDateFinRdv(Date dateFinRdv) {
        this.dateFinRdv = dateFinRdv;
    }

    public Time getHeureFinRdv() {
        return heureFinRdv;
    }

    public void setHeureFinRdv(Time heureFinRdv) {
        this.heureFinRdv = heureFinRdv;
    }

    public String getObjetRdv() {
        return objetRdv;
    }

    public void setObjetRdv(String objetRdv) {
        this.objetRdv = objetRdv;
    }

    public PaysDto getPaysDto() {
        return paysDto;
    }

    public void setPaysDto(PaysDto paysDto) {
        this.paysDto = paysDto;
    }

    public PersonnePhysiqueMoraleDto getPersonnePhysiqueMoraleDto() {
        return personnePhysiqueMoraleDto;
    }

    public void setPersonnePhysiqueMoraleDto(PersonnePhysiqueMoraleDto personnePhysiqueMoraleDto) {
        this.personnePhysiqueMoraleDto = personnePhysiqueMoraleDto;
    }

    public Set<AgentConcerneDto> getAgentConcerneDtos() {
        return agentConcerneDtos;
    }

    public void setAgentConcernes(Set<AgentConcerneDto> agentConcernes) {
        this.agentConcerneDtos = agentConcernes;
    }

    public CompteRenduDto getCompteRenduDto() {
        return compteRenduDto;
    }

    public void setCompteRenduDto(CompteRenduDto compteRenduDto) {
        this.compteRenduDto = compteRenduDto;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "RDVDto{" +
                "idRdv=" + idRdv +
                ", dateDebRdv=" + dateDebRdv +
                ", dateFinRdv=" + dateFinRdv +
                ", heureFinRdv=" + heureFinRdv +
                ", objetRdv='" + objetRdv + '\'' +
                ", denomination='" + denomination + '\'' +
                ", dateDebReelle=" + dateDebReelle +
                ", heureDebReelle=" + heureDebReelle +
                ", dateFinReelle=" + dateFinReelle +
                ", heureFinReelle=" + heureFinReelle +
                '}';
    }
}
