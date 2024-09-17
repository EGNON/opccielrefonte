package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonSerialize(using = LocalDateTimeSerializer.class)
//@JsonDeserialize(using = LocalDateTimeDeserializer.class)
public class PersonnePhysiqueDtoEJ extends PersonneDtoEJ{
    private Long idPersonne;
    private String nom;
    private String prenom;
    private String sexe;
    private LocalDateTime dateNaissance;
    private String civilite;
    private String lieuTravail;
    private PaysDto paysNationalite;
    private Set<PersonnePhysiquePaysDto> personnePhysiquePaysDtos = new HashSet<>();
    private String lieuNaissance;
    public PersonnePhysiqueDtoEJ() {
    }

//    public Long getId() {
//        return idPersonne;
//    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Set<PersonnePhysiquePaysDto> getPersonnePhysiquePaysDtos() {
        return personnePhysiquePaysDtos;
    }

    public void setPersonnePhysiquePaysDtos(Set<PersonnePhysiquePaysDto> personnePhysiquePaysDtos) {
        this.personnePhysiquePaysDtos = personnePhysiquePaysDtos;
    }


    public PersonnePhysiqueDtoEJ(String nom, String prenom, String sexe, LocalDateTime dateNaissance, String civilite, String lieuTravail, double autresRevenus, String periodicite, String statutMatrimonial, int nbrEnfant, int nbrPersonneACharge, String nomEmployeur, String adressePostaleEmp, String adresseGeoEmp, String telEmp, String emailEmp, String nomPere, String prenomsPere, LocalDateTime dateNaissancePere, String nomMere, String prenomsMere, LocalDateTime dateNaissanceMere, String nomConjoint, String prenomConjoint, LocalDateTime dateNaissanceConjoint, String origineFonds, String transactionEnvisagee, String immobilier, String autresBiens, double surfaceTotale, double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.civilite = civilite;
        this.lieuTravail = lieuTravail;
    }

    public PaysDto getPaysNationalite() {
        return paysNationalite;
    }

    public void setPaysNationalite(PaysDto paysNationalite) {
        this.paysNationalite = paysNationalite;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDateTime getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDateTime dateNaissance) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
//        LocalDateTime newDate = LocalDateTime.parse(dateNaissance, formatter);
        this.dateNaissance = dateNaissance;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getLieuTravail() {
        return lieuTravail;
    }

    public void setLieuTravail(String lieuTravail) {
        this.lieuTravail = lieuTravail;
    }


    @Override
    public String toString() {
        return "PersonnePhysiqueDto{" +
                "idPersonne=" + idPersonne +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", civilite='" + civilite + '\'' +
                ", lieuTravail='" + lieuTravail + '\'' +
                '}';
    }
}
