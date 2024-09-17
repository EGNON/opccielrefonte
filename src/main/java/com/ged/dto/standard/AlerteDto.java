package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.CompteRenduDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlerteDto {
    private Long idAlerte;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String destinataire;
    private int frequence;
    @CreationTimestamp
    private LocalDateTime dateCreationServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifClient;
    private long numLigne;
    private boolean supprimer;
    private LocalDateTime rowvers;
    private String userLogin;
    private CompteRenduDto compteRendu;
    private PeriodiciteDto periodicite;
    private TypePlanificationDto typePlanification;
    private Set<ProtoAlerteDto> protoAlertes = new HashSet<>();
    private Set<TempsAlerteDto> tempsAlertes = new HashSet<>();
    private Set<NbreJoursAlerteDto> nbreJoursAlertes = new HashSet<>();
    private Set<JoursAlerteDto> joursAlertes = new HashSet<>();
    private String typeAlerte = "Desktop";

    public AlerteDto() {
    }

    public AlerteDto(Long idAlerte, LocalDateTime dateDebut, LocalDateTime dateFin, String userLogin, CompteRenduDto compteRendu) {
        this.idAlerte = idAlerte;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.userLogin = userLogin;
        this.compteRendu = compteRendu;
    }

    public String getTypeAlerte() {
        return typeAlerte;
    }

    public void setTypeAlerte(String typeAlerte) {
        this.typeAlerte = typeAlerte;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public Set<JoursAlerteDto> getJoursAlertes() {
        return joursAlertes;
    }

    public void setJoursAlertes(Set<JoursAlerteDto> joursAlertes) {
        this.joursAlertes = joursAlertes;
    }

    public Set<NbreJoursAlerteDto> getNbreJoursAlertes() {
        return nbreJoursAlertes;
    }

    public void setNbreJoursAlertes(Set<NbreJoursAlerteDto> nbreJoursAlertes) {
        this.nbreJoursAlertes = nbreJoursAlertes;
    }

    public Set<TempsAlerteDto> getTempsAlertes() {
        return tempsAlertes;
    }

    public void setTempsAlertes(Set<TempsAlerteDto> tempsAlertes) {
        this.tempsAlertes = tempsAlertes;
    }

    public Set<ProtoAlerteDto> getProtoAlertes() {
        return protoAlertes;
    }

    public void setProtoAlertes(Set<ProtoAlerteDto> protoAlertes) {
        this.protoAlertes = protoAlertes;
    }

    public CompteRenduDto getCompteRendu() {
        return compteRendu;
    }

    public void setCompteRendu(CompteRenduDto compteRendu) {
        this.compteRendu = compteRendu;
    }

    public PeriodiciteDto getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(PeriodiciteDto periodicite) {
        this.periodicite = periodicite;
    }

    public TypePlanificationDto getTypePlanification() {
        return typePlanification;
    }

    public void setTypePlanification(TypePlanificationDto typePlanification) {
        this.typePlanification = typePlanification;
    }

    public Long getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public LocalDateTime getDateCreationServeur() {
        return dateCreationServeur;
    }

    public void setDateCreationServeur(LocalDateTime dateCreationServeur) {
        this.dateCreationServeur = dateCreationServeur;
    }

    public LocalDateTime getDateDernModifServeur() {
        return dateDernModifServeur;
    }

    public void setDateDernModifServeur(LocalDateTime dateDernModifServeur) {
        this.dateDernModifServeur = dateDernModifServeur;
    }

    public LocalDateTime getDateDernModifClient() {
        return dateDernModifClient;
    }

    public void setDateDernModifClient(LocalDateTime dateDernModifClient) {
        this.dateDernModifClient = dateDernModifClient;
    }

    public long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(long numLigne) {
        this.numLigne = numLigne;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public LocalDateTime getRowvers() {
        return rowvers;
    }

    public void setRowvers(LocalDateTime rowvers) {
        this.rowvers = rowvers;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public CompteRenduDto getCompteRenduDto() {
        return compteRendu;
    }

    public void setCompteRenduDto(CompteRenduDto compteRendu) {
        this.compteRendu = compteRendu;
    }

    @Override
    public String toString() {
        return "AlerteDto{" +
                "idAlerte=" + idAlerte +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", compteRendu=" + compteRendu +
                ", periodicite=" + periodicite +
                ", typePlanification=" + typePlanification +
                ", protoAlertes=" + protoAlertes +
                '}';
    }
}
