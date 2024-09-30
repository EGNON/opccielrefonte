package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.standard.Langue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonSerialize(using = LocalDateTimeSerializer.class)
//@JsonDeserialize(using = LocalDateTimeDeserializer.class)
public class PersonnePhysiqueDto extends PersonneDto{
    private Long idPersonne;
    private String nom;
    private String prenom;
    private String sexe;
    private LocalDateTime dateNaissance;
    private String civilite;
    private String lieuTravail;
    private Double autresRevenus;
    private String periodicite;
    private String statutMatrimonial;
    private Integer nbrEnfant;
    private Integer nbrPersonneACharge;
    private String nomEmployeur;
    private String adressePostaleEmp;
    private String adresseGeoEmp;
    private String telEmp;
    private String emailEmp;
    private String nomPere;
    private String prenomsPere;
    private LocalDateTime dateNaissancePere;
    private String nomMere;
    private String prenomsMere;
    private LocalDateTime dateNaissanceMere;
    private String nomConjoint;
    private String prenomConjoint;
    private LocalDateTime dateNaissanceConjoint;
    private String origineFonds;
    private String transactionEnvisagee;
    private String immobilier;
    private String autresBiens;
    private Double surfaceTotale;
    private Double salaire;
    private ProfessionDto profession;
    private PaysDto paysPere;
    private PaysDto paysMere;
    private PaysDto paysConjoint;
    private PaysDto paysNationalite;
    private SecteurDto secteurEmp;
    private Set<PersonnePhysiquePaysDto> personnePhysiquePaysDtos = new HashSet<>();
    private String lieuNaissance;
    private String teint;
    private String exposeMotif;
    private LangueDto langue;

    public PersonnePhysiqueDto() {
    }

//    public Long getId() {
//        return idPersonne;
//    }

    public String getTeint() {
        return teint;
    }

    public void setTeint(String teint) {
        this.teint = teint;
    }

    public String getExposeMotif() {
        return exposeMotif;
    }

    public void setExposeMotif(String exposeMotif) {
        this.exposeMotif = exposeMotif;
    }

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

    public LangueDto getLangue() {
        return langue;
    }

    public void setLangue(LangueDto langue) {
        this.langue = langue;
    }

    public PaysDto getPaysPere() {
        return paysPere;
    }

    public void setPaysPere(PaysDto paysPere) {
        this.paysPere = paysPere;
    }

    public PaysDto getPaysMere() {
        return paysMere;
    }

    public void setPaysMere(PaysDto paysMere) {
        this.paysMere = paysMere;
    }

    public PaysDto getPaysConjoint() {
        return paysConjoint;
    }

    public void setPaysConjoint(PaysDto paysConjoint) {
        this.paysConjoint = paysConjoint;
    }

    public PaysDto getPaysNationalite() {
        return paysNationalite;
    }

    public void setPaysNationalite(PaysDto paysNationalite) {
        this.paysNationalite = paysNationalite;
    }
    public SecteurDto getSecteurEmp() {
        return secteurEmp;
    }

    public void setSecteurEmp(SecteurDto secteur) {
        this.secteurEmp = secteur;
    }

    public PersonnePhysiqueDto(String nom, String prenom, String sexe, LocalDateTime dateNaissance, String civilite, String lieuTravail, Double autresRevenus, String periodicite, String statutMatrimonial, Integer nbrEnfant, Integer nbrPersonneACharge, String nomEmployeur, String adressePostaleEmp, String adresseGeoEmp, String telEmp, String emailEmp, String nomPere, String prenomsPere, LocalDateTime dateNaissancePere, String nomMere, String prenomsMere, LocalDateTime dateNaissanceMere, String nomConjoint, String prenomConjoint, LocalDateTime dateNaissanceConjoint, String origineFonds, String transactionEnvisagee, String immobilier, String autresBiens, Double surfaceTotale, Double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.civilite = civilite;
        this.lieuTravail = lieuTravail;
        this.autresRevenus = autresRevenus;
        this.periodicite = periodicite;
        this.statutMatrimonial = statutMatrimonial;
        this.nbrEnfant = nbrEnfant;
        this.nbrPersonneACharge = nbrPersonneACharge;
        this.nomEmployeur = nomEmployeur;
        this.adressePostaleEmp = adressePostaleEmp;
        this.adresseGeoEmp = adresseGeoEmp;
        this.telEmp = telEmp;
        this.emailEmp = emailEmp;
        this.nomPere = nomPere;
        this.prenomsPere = prenomsPere;
        this.dateNaissancePere = dateNaissancePere;
        this.nomMere = nomMere;
        this.prenomsMere = prenomsMere;
        this.dateNaissanceMere = dateNaissanceMere;
        this.nomConjoint = nomConjoint;
        this.prenomConjoint = prenomConjoint;
        this.dateNaissanceConjoint = dateNaissanceConjoint;
        this.origineFonds = origineFonds;
        this.transactionEnvisagee = transactionEnvisagee;
        this.immobilier = immobilier;
        this.autresBiens = autresBiens;
        this.surfaceTotale = surfaceTotale;
        this.salaire = salaire;
    }

    public ProfessionDto getProfession() {
        return profession;
    }

    public void setProfession(ProfessionDto professionDto) {
        this.profession = professionDto;
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

    public Double getAutresRevenus() {
        return autresRevenus;
    }

    public void setAutresRevenus(Double autresRevenus) {
        this.autresRevenus = autresRevenus;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getStatutMatrimonial() {
        return statutMatrimonial;
    }

    public void setStatutMatrimonial(String statutMatrimonial) {
        this.statutMatrimonial = statutMatrimonial;
    }

    public Integer getNbrEnfant() {
        return nbrEnfant;
    }

    public void setNbrEnfant(Integer nbrEnfant) {
        this.nbrEnfant = nbrEnfant;
    }

    public Integer getNbrPersonneACharge() {
        return nbrPersonneACharge;
    }

    public void setNbrPersonneACharge(Integer nbrPersonneACharge) {
        this.nbrPersonneACharge = nbrPersonneACharge;
    }

    public String getNomEmployeur() {
        return nomEmployeur;
    }

    public void setNomEmployeur(String nomEmployeur) {
        this.nomEmployeur = nomEmployeur;
    }

    public String getAdressePostaleEmp() {
        return adressePostaleEmp;
    }

    public void setAdressePostaleEmp(String adressePostaleEmp) {
        this.adressePostaleEmp = adressePostaleEmp;
    }

    public String getAdresseGeoEmp() {
        return adresseGeoEmp;
    }

    public void setAdresseGeoEmp(String adresseGeoEmp) {
        this.adresseGeoEmp = adresseGeoEmp;
    }

    public String getTelEmp() {
        return telEmp;
    }

    public void setTelEmp(String telEmp) {
        this.telEmp = telEmp;
    }

    public String getEmailEmp() {
        return emailEmp;
    }

    public void setEmailEmp(String emailEmp) {
        this.emailEmp = emailEmp;
    }

    public String getNomPere() {
        return nomPere;
    }

    public void setNomPere(String nomPere) {
        this.nomPere = nomPere;
    }

    public String getPrenomsPere() {
        return prenomsPere;
    }

    public void setPrenomsPere(String prenomsPere) {
        this.prenomsPere = prenomsPere;
    }

    public LocalDateTime getDateNaissancePere() {
        return dateNaissancePere;
    }

    public void setDateNaissancePere(LocalDateTime dateNaissancePere) {
        this.dateNaissancePere = dateNaissancePere;
    }

    public String getNomMere() {
        return nomMere;
    }

    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    public String getPrenomsMere() {
        return prenomsMere;
    }

    public void setPrenomsMere(String prenomsMere) {
        this.prenomsMere = prenomsMere;
    }

    public LocalDateTime getDateNaissanceMere() {
        return dateNaissanceMere;
    }

    public void setDateNaissanceMere(LocalDateTime dateNaissanceMere) {
        this.dateNaissanceMere = dateNaissanceMere;
    }

    public String getNomConjoint() {
        return nomConjoint;
    }

    public void setNomConjoint(String nomConjoint) {
        this.nomConjoint = nomConjoint;
    }

    public String getPrenomConjoint() {
        return prenomConjoint;
    }

    public void setPrenomConjoint(String prenomConjoint) {
        this.prenomConjoint = prenomConjoint;
    }

    public LocalDateTime getDateNaissanceConjoint() {
        return dateNaissanceConjoint;
    }

    public void setDateNaissanceConjoint(LocalDateTime dateNaissanceConjoint) {
        this.dateNaissanceConjoint = dateNaissanceConjoint;
    }

    public String getOrigineFonds() {
        return origineFonds;
    }

    public void setOrigineFonds(String origineFonds) {
        this.origineFonds = origineFonds;
    }

    public String getTransactionEnvisagee() {
        return transactionEnvisagee;
    }

    public void setTransactionEnvisagee(String transactionEnvisagee) {
        this.transactionEnvisagee = transactionEnvisagee;
    }

    public String getImmobilier() {
        return immobilier;
    }

    public void setImmobilier(String immobilier) {
        this.immobilier = immobilier;
    }

    public String getAutresBiens() {
        return autresBiens;
    }

    public void setAutresBiens(String autresBiens) {
        this.autresBiens = autresBiens;
    }

    public Double getSurfaceTotale() {
        return surfaceTotale;
    }

    public void setSurfaceTotale(Double surfaceTotale) {
        this.surfaceTotale = surfaceTotale;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
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
                ", autresRevenus=" + autresRevenus +
                ", periodicite='" + periodicite + '\'' +
                ", statutMatrimonial='" + statutMatrimonial + '\'' +
                ", nbrEnfant=" + nbrEnfant +
                ", nbrPersonneACharge=" + nbrPersonneACharge +
                ", nomEmployeur='" + nomEmployeur + '\'' +
                ", adressePostaleEmp='" + adressePostaleEmp + '\'' +
                ", adresseGeoEmp='" + adresseGeoEmp + '\'' +
                ", telEmp='" + telEmp + '\'' +
                ", emailEmp='" + emailEmp + '\'' +
                ", nomPere='" + nomPere + '\'' +
                ", prenomsPere='" + prenomsPere + '\'' +
                ", dateNaissancePere=" + dateNaissancePere +
                ", nomMere='" + nomMere + '\'' +
                ", prenomsMere='" + prenomsMere + '\'' +
                ", dateNaissanceMere=" + dateNaissanceMere +
                ", nomConjoint='" + nomConjoint + '\'' +
                ", prenomConjoint='" + prenomConjoint + '\'' +
                ", dateNaissanceConjoint=" + dateNaissanceConjoint +
                ", origineFonds='" + origineFonds + '\'' +
                ", transactionEnvisagee='" + transactionEnvisagee + '\'' +
                ", immobilier='" + immobilier + '\'' +
                ", autresBiens='" + autresBiens + '\'' +
                ", surfaceTotale=" + surfaceTotale +
                ", salaire=" + salaire +
                '}';
    }
}
