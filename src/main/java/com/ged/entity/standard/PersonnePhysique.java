package com.ged.entity.standard;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
/*@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "type",
        discriminatorType = DiscriminatorType.STRING,
        columnDefinition = "varchar default 'PH'" ,
        length = 20)*/
@DiscriminatorValue("PH")
@PrimaryKeyJoinColumn(name = "idPersonne")
@Table(name = "T_PersonnePhysique", schema = "Parametre")
public class PersonnePhysique extends Personne{
    //OPCCIEL 1
    @Basic
    private String NomPersonnePhysique;
    @Basic
    private String PrenomPersonnePhysique;
    private String CodePaysNaissance;
    @Basic
    private String Sexe;
    @Basic
    private LocalDateTime DateNaissance;
    @Basic
    private String Civilite;
    @Basic
    private String LieuNaissance;
//    @Column(nullable =true)
    @Column(columnDefinition="BIT")
    private boolean EstMineur=false;
    private String nomDeJeuneFille;
    private String PhotoPersonnePhysique;
    private String SignaturePersonnePhysique;
    private String LibelleSecteurActivite;
    private String LibelleProfession;
    private String numCompteDepositaire;
    @Column(length = 20)
    private String telephoneFixe;
    @Column(length = 20)
    private String telephoneMobile1;
    @Column(length = 20)
    private String telephoneMobile2;
    @Column(length = 128)
    private String email;
    @Column(length = 128)
    private String boitePostale;
    @Column(length = 250)
    private String adresseComplete;
    private String typePieceIdentite;
    private String nomPrenomMere;
    @Column(length = 16)
    private String numCompteSgi;
    @Column(nullable =true)
    private BigDecimal tauxRetroCourSous;
    @Column(nullable =true)
    private BigDecimal tauxRetroCourRach;
    private LocalDateTime dateFermetureCompte;
    @Column(name = "motifFermetureCompte")
    private String motifFermetureCompte;
    private String pieceFermetureCompte;
    private String statutCompte;
    private String teint;
    private String exposeMotif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLangueParle")
    private Langue langue;
    //FIN OPCCIEL 1
    @Basic
    private String nom;
    @Basic
    private String prenom;
    @Basic
    private String lieuTravail;
    @Column(name = "autresRevenus")
    private Double autresRevenus;
    @Basic
    private String periodicite;
    @Basic
    private String statutMatrimonial;
    @Basic
    @Column(nullable = true)
    private Integer nbrEnfant=0;
    @Basic
    @Column(nullable = true)
    private Integer nbrPersonneACharge=0;
    @Basic
    private String nomEmployeur;
    @Basic
    private String adressePostaleEmp;
    @Basic
    private String adresseGeoEmp;
    @Basic
    private String telEmp;
    @Basic
    private String emailEmp;
    @Basic
    private String nomPere;
    @Basic
    private String prenomsPere;
    @Basic
    private LocalDateTime dateNaissancePere;
    @Basic
    private String nomMere;
    @Basic
    private String prenomsMere;
    @Basic
    private LocalDateTime dateNaissanceMere;
    @Basic
    private String nomConjoint;
    @Basic
    private String prenomConjoint;
    @Basic
    private LocalDateTime dateNaissanceConjoint;
    @Basic
    private String origineFonds;
    @Basic
    private String transactionEnvisagee;
    @Basic
    private String immobilier;
    @Basic
    private String autresBiens;
    @Basic
    @Column(nullable = true)
    private Double surfaceTotale=0D;
    @Basic
    @Column(nullable = true)
    private Double salaire=0D;
//    @Basic
//    private LocalDateTime dateCreationServeur;
//    @Basic
//    private LocalDateTime dateDernModifServeur;
//    @Basic
//    private LocalDateTime dateDernModifClient;
//    @Basic
//    private long numLigne;
//    @Basic
//    @Column(columnDefinition = "BIT", length = 1)
//    private boolean supprimer;
//    @Basic
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProf", referencedColumnName = "idProf")
    //@JsonBackReference
    private Profession profession;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaysPere", referencedColumnName = "idPays")
    //@JsonBackReference
    private Pays paysPere;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaysMere", referencedColumnName = "idPays")
    //@JsonBackReference
    private Pays paysMere;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaysConjoint", referencedColumnName = "idPays")
    //@JsonBackReference
    private Pays paysConjoint;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaysNationalite", referencedColumnName = "idPays")
    //@JsonBackReference
    private Pays paysNationalite;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSecteurEmp", referencedColumnName = "idSecteur")
    //@JsonBackReference
    private Secteur secteurEmp;
    @OneToMany(mappedBy = "personnePhysique",cascade = CascadeType.ALL)
    //@JsonManagedReference
    private Set<PersonnePhysiquePays> personnePhysiquePays;

    public PersonnePhysique() {
        super();
    }

    public PersonnePhysique(
            String nom, String prenom, String sexe, String civilite, Profession profession,
            String ifu, String mobile1, String mobile2, String fixe1, String fixe2, String bp, String emailPerso, String emailPro, String domicile, String numeroPiece, String typePiece, LocalDateTime dateExpirationPiece, String modeEtablissement, long numLigne, boolean supprimer, String userLogin) {
        super(ifu, mobile1, mobile2, fixe1, fixe2, bp, emailPerso, emailPro, domicile, numeroPiece, typePiece, dateExpirationPiece, modeEtablissement);
        this.nom = nom;
        this.prenom = prenom;
        this.Sexe = sexe;
        this.Civilite = civilite;
        this.profession = profession;
    }

    @Override
    public void setDenomination(String denomination) {
        super.setDenomination(denomination);
    }

    public Secteur getSecteurEmp() {
        return secteurEmp;
    }

    public void setSecteurEmp(Secteur secteurEmp) {
        this.secteurEmp = secteurEmp;
    }

    public Profession getProfession() {
        return profession;
    }
    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

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
        return LieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.LieuNaissance = lieuNaissance;
    }

    public Set<PersonnePhysiquePays> getPersonnePhysiquePays() {
        return personnePhysiquePays;
    }

    public void setPersonnePhysiquePays(Set<PersonnePhysiquePays> personnePhysiquePays) {
        this.personnePhysiquePays = personnePhysiquePays;
    }
    public void ajouterPersonnePhysiquePays(PersonnePhysiquePays personnePhysiquePays)
    {
        this.personnePhysiquePays.add(personnePhysiquePays);
        personnePhysiquePays.setPersonnePhysique(this);
    }
    public Pays getPaysPere() {
        return paysPere;
    }

    public void setPaysPere(Pays paysPere) {
        this.paysPere = paysPere;
    }

    public Pays getPaysMere() {
        return paysMere;
    }

    public void setPaysMere(Pays paysMere) {
        this.paysMere = paysMere;
    }

    public Pays getPaysConjoint() {
        return paysConjoint;
    }

    public void setPaysConjoint(Pays paysConjoint) {
        this.paysConjoint = paysConjoint;
    }

    public Pays getPaysNationalite() {
        return paysNationalite;
    }

    public void setPaysNationalite(Pays paysNationalite) {
        this.paysNationalite = paysNationalite;
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
        return Sexe;
    }

    public void setSexe(String sexe) {
        this.Sexe = sexe;
    }

    public LocalDateTime getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(LocalDateTime dateNaissance) {
        this.DateNaissance = dateNaissance;
    }

    public String getCivilite() {
        return Civilite;
    }

    public void setCivilite(String civilite) {
        this.Civilite = civilite;
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

    //OPCCIEL1

    public String getNomPersonnePhysique() {
        return NomPersonnePhysique;
    }

    public void setNomPersonnePhysique(String nomPersonnePhysique) {
        NomPersonnePhysique = nomPersonnePhysique;
    }

    public String getPrenomPersonnePhysique() {
        return PrenomPersonnePhysique;
    }

    public void setPrenomPersonnePhysique(String prenomPersonnePhysique) {
        PrenomPersonnePhysique = prenomPersonnePhysique;
    }

    public String getCodePaysNaissance() {
        return CodePaysNaissance;
    }

    public void setCodePaysNaissance(String codePaysNaissance) {
        CodePaysNaissance = codePaysNaissance;
    }

    public boolean isEstMineur() {
        return EstMineur;
    }

    public void setEstMineur(boolean estMineur) {
        EstMineur = estMineur;
    }

    public String getNomDeJeuneFille() {
        return nomDeJeuneFille;
    }

    public void setNomDeJeuneFille(String nomDeJeuneFille) {
        this.nomDeJeuneFille = nomDeJeuneFille;
    }

    public String getPhotoPersonnePhysique() {
        return PhotoPersonnePhysique;
    }

    public void setPhotoPersonnePhysique(String photoPersonnePhysique) {
        PhotoPersonnePhysique = photoPersonnePhysique;
    }

    public String getSignaturePersonnePhysique() {
        return SignaturePersonnePhysique;
    }

    public void setSignaturePersonnePhysique(String signaturePersonnePhysique) {
        SignaturePersonnePhysique = signaturePersonnePhysique;
    }

    public String getLibelleSecteurActivite() {
        return LibelleSecteurActivite;
    }

    public void setLibelleSecteurActivite(String libelleSecteurActivite) {
        LibelleSecteurActivite = libelleSecteurActivite;
    }

    public String getLibelleProfession() {
        return LibelleProfession;
    }

    public void setLibelleProfession(String libelleProfession) {
        LibelleProfession = libelleProfession;
    }

    public String getNumCompteDepositaire() {
        return numCompteDepositaire;
    }

    public void setNumCompteDepositaire(String numCompteDepositaire) {
        this.numCompteDepositaire = numCompteDepositaire;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    public String getTelephoneMobile1() {
        return telephoneMobile1;
    }

    public void setTelephoneMobile1(String telephoneMobile1) {
        this.telephoneMobile1 = telephoneMobile1;
    }

    public String getTelephoneMobile2() {
        return telephoneMobile2;
    }

    public void setTelephoneMobile2(String telephoneMobile2) {
        this.telephoneMobile2 = telephoneMobile2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBoitePostale() {
        return boitePostale;
    }

    public void setBoitePostale(String boitePostale) {
        this.boitePostale = boitePostale;
    }

    public String getAdresseComplete() {
        return adresseComplete;
    }

    public void setAdresseComplete(String adresseComplete) {
        this.adresseComplete = adresseComplete;
    }

    public String getTypePieceIdentite() {
        return typePieceIdentite;
    }

    public void setTypePieceIdentite(String typePieceIdentite) {
        this.typePieceIdentite = typePieceIdentite;
    }

    public String getNomPrenomMere() {
        return nomPrenomMere;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getNumCompteSgi() {
        return numCompteSgi;
    }

    public void setNumCompteSgi(String numCompteSgi) {
        this.numCompteSgi = numCompteSgi;
    }

    public BigDecimal getTauxRetroCourSous() {
        return tauxRetroCourSous;
    }

    public void setTauxRetroCourSous(BigDecimal tauxRetroCourSous) {
        this.tauxRetroCourSous = tauxRetroCourSous;
    }

    public BigDecimal getTauxRetroCourRach() {
        return tauxRetroCourRach;
    }

    public void setTauxRetroCourRach(BigDecimal tauxRetroCourRach) {
        this.tauxRetroCourRach = tauxRetroCourRach;
    }

    public LocalDateTime getDateFermetureCompte() {
        return dateFermetureCompte;
    }

    public void setDateFermetureCompte(LocalDateTime dateFermetureCompte) {
        this.dateFermetureCompte = dateFermetureCompte;
    }

    public String getMotifFermetureCompte() {
        return motifFermetureCompte;
    }

    public void setMotifFermetureCompte(String motifFermetureCompte) {
        this.motifFermetureCompte = motifFermetureCompte;
    }

    public String getPieceFermetureCompte() {
        return pieceFermetureCompte;
    }

    public void setPieceFermetureCompte(String pieceFermetureCompte) {
        this.pieceFermetureCompte = pieceFermetureCompte;
    }

    public String getStatutCompte() {
        return statutCompte;
    }

    public void setStatutCompte(String statutCompte) {
        this.statutCompte = statutCompte;
    }


    @Override
    public String toString() {
        return "PersonnePhysique [" +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + Sexe + '\'' +
                ", civilite='" + Civilite + '\'' +
                ", lieuTravail='" + lieuTravail + '\'' +
                ", statutMatrimonial='" + statutMatrimonial + '\'' +
                ", nbrEnfant=" + nbrEnfant +
                ']';
    }
}
