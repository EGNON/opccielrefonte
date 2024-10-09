package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.FormeJuridique;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
/*@DiscriminatorValue("RG")*/
@Table(name = "T_Garant", schema = "Titre")
public class Garant extends Base {
    //OPCCIEL 1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq")*/
    @Column(name = "idGarant")
    private Long idPersonne;
    private Long idOcc;
    @Column(insertable = false, updatable = false)
    private String typePersonne;
    private String denomination;
    private String ifu;
    private String mobile1;
    private String mobile2;
    private String fixe1;
    private String fixe2;
    private String bp;
    private String emailPerso;
    private String emailPro;
    private String domicile;
    private String numeroPiece;
    private String typePiece;
    private LocalDateTime dateExpirationPiece;
    private String modeEtablissement;
    /*    @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "idModeEtablissement")
        private ModeEtablissement modeEtablissement2;*/
    private String nomContact;
    private String prenomContact;
    private String telContact;
    private String emailContact;
    private String titreContact;
    private String numeroCpteDeposit;
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
//
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;
    //OPCCIEL1
    private String libelleTypePersonne;
    private String CodePays;
    private String CodeLangue;
    @Column(columnDefinition = "BIT")
    private boolean EstActifPersonne = false;
    private String codeCategorieClient;
    private String codeSousTypeClient;
    @Column(nullable = true)
    private long numOrdre = 0;
    //FIN OPCCIEL1
    /*@Column(columnDefinition="BIT")
    private boolean estsgi = false;
    @Column(columnDefinition="BIT")
    private boolean estGele = false;
    @Column(columnDefinition="BIT")
    private boolean ppe1 = false;
    @Column(columnDefinition="BIT")
    private boolean ppe2 = false;
    @Column(columnDefinition="BIT")
    private boolean ppe3 = false;
    @Column(columnDefinition="BIT")
    private boolean ppe4 = false;
    @Column(columnDefinition="BIT")
    private boolean estConvertie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSecteur", referencedColumnName = "idSecteur")
    //@JsonBackReference
    private Secteur secteur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDegre", referencedColumnName = "idDegre")
    //@JsonBackReference
    private Degre degre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDistributeur", referencedColumnName = "idPersonne")
    private Personne distributeur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaysResidence", referencedColumnName = "idPays")
    //@JsonBackReference
    private Pays paysResidence;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personne")
    //@JsonManagedReference
    private Set<Document> documents = new HashSet<>();
    @OneToMany(mappedBy = "personne")
    //@JsonManagedReference
    private Set<EnvoiMail> envoiMails = new HashSet<>();
    @OneToMany(mappedBy = "personne")
    private Set<GelDegel> gelDegels = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuartier", referencedColumnName = "idQuartier")
    //@JsonBackReference
    private Quartier quartier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCommune", referencedColumnName = "idCommune")
    private Commune commune;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategorieClient")
    private CategorieClient categorieClient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSousTypeClient")
    private SousTypeClient sousTypeClient;

    @OneToMany(
            mappedBy = "personne",
            *//*cascade = {CascadeType.PERSIST, CascadeType.MERGE},*//*
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    //@JsonManagedReference
    private Set<StatutPersonne> statutPersonnes = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinataire")
    //@JsonManagedReference
    private Set<MessageBox> messageBox = new HashSet<>();
    @OneToMany(mappedBy = "personne")
    //@JsonManagedReference
    private Set<DiffusionAlerte> diffusionAlertes = new HashSet<>();
    @Column(columnDefinition="BIT")
    private boolean estExpose=false;
    @Column(columnDefinition="BIT")
    private boolean estJuge=false;
    @Column(columnDefinition="BIT")
    private Boolean estSanctionNationale = false;
    @Column(columnDefinition="BIT")
    private Boolean estSanctionOnusienne = false;*/
    private String numeroAgrementPersonneMorale;
    private String numeroINSAE;
    private String numRegistre;
    private String codeSecteur;
    @Column(nullable = true)
    private BigDecimal CapitalSocial;
    private String siglePersonneMorale;
    private String raisonSociale;
    private String codeFormeJuridique;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeFormeJuridiqueNew")
    private FormeJuridique formeJuridique;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlace")
    private Place place;
    private String codeTypeGrant;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeTypeEmetteur")
    private TypeEmetteur typeEmetteur;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeGarant")
    private TypeGarant typeGarant;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOrganisme", referencedColumnName = "idOrganisme")
    private Organisme organisme;

    private LocalDateTime dateCreationPM;
    private String numCompteDepositaire;
    private String indexFixe1;
    private String telephoneFixe1;
    private String indexFixe2;
    private String telephoneFixe2;
    private String indexMobile1;
    private String telephoneMobile1;
    private String indexMobile2;
    private String telephoneMobile2;
    private String indexFax;
    private String fax;
    private String email;
    private String boitePostale;
    private String adresseComplete;
    @Column(nullable = true)
    private BigDecimal tauxRetroCourSous;
    @Column(nullable = true)
    private BigDecimal tauxRetroCourRach;
    private String numCompteSgi;
    private String libelleSousCategorie;
    private LocalDateTime dateFermetureCompte;
    private String motifFermetureCompte;
    private String pieceFermetureCompte;
    private String statutCompte;
    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idVille")
    private Ville ville;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSousCategorie")
    private SousCategorie sousCategorie;*/
    //FIN

    @Basic
    private String sigle;
    @Basic
    private String siteWeb;
    //FIN

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
//
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;

    public Garant() {
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

    public String getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(String typePersonne) {
        this.typePersonne = typePersonne;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getIfu() {
        return ifu;
    }

    public void setIfu(String ifu) {
        this.ifu = ifu;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getFixe1() {
        return fixe1;
    }

    public void setFixe1(String fixe1) {
        this.fixe1 = fixe1;
    }

    public String getFixe2() {
        return fixe2;
    }

    public void setFixe2(String fixe2) {
        this.fixe2 = fixe2;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getEmailPerso() {
        return emailPerso;
    }

    public void setEmailPerso(String emailPerso) {
        this.emailPerso = emailPerso;
    }

    public String getEmailPro() {
        return emailPro;
    }

    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public LocalDateTime getDateExpirationPiece() {
        return dateExpirationPiece;
    }

    public void setDateExpirationPiece(LocalDateTime dateExpirationPiece) {
        this.dateExpirationPiece = dateExpirationPiece;
    }

    public String getModeEtablissement() {
        return modeEtablissement;
    }

    public void setModeEtablissement(String modeEtablissement) {
        this.modeEtablissement = modeEtablissement;
    }

    public String getNomContact() {
        return nomContact;
    }

    public void setNomContact(String nomContact) {
        this.nomContact = nomContact;
    }

    public String getPrenomContact() {
        return prenomContact;
    }

    public void setPrenomContact(String prenomContact) {
        this.prenomContact = prenomContact;
    }

    public String getTelContact() {
        return telContact;
    }

    public void setTelContact(String telContact) {
        this.telContact = telContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public String getTitreContact() {
        return titreContact;
    }

    public void setTitreContact(String titreContact) {
        this.titreContact = titreContact;
    }

    public String getNumeroCpteDeposit() {
        return numeroCpteDeposit;
    }

    public void setNumeroCpteDeposit(String numeroCpteDeposit) {
        this.numeroCpteDeposit = numeroCpteDeposit;
    }

    public String getLibelleTypePersonne() {
        return libelleTypePersonne;
    }

    public void setLibelleTypePersonne(String libelleTypePersonne) {
        this.libelleTypePersonne = libelleTypePersonne;
    }

    public String getCodePays() {
        return CodePays;
    }

    public void setCodePays(String codePays) {
        CodePays = codePays;
    }

    public String getCodeLangue() {
        return CodeLangue;
    }

    public void setCodeLangue(String codeLangue) {
        CodeLangue = codeLangue;
    }

    public boolean isEstActifPersonne() {
        return EstActifPersonne;
    }

    public void setEstActifPersonne(boolean estActifPersonne) {
        EstActifPersonne = estActifPersonne;
    }

    public String getCodeCategorieClient() {
        return codeCategorieClient;
    }

    public void setCodeCategorieClient(String codeCategorieClient) {
        this.codeCategorieClient = codeCategorieClient;
    }

    public String getCodeSousTypeClient() {
        return codeSousTypeClient;
    }

    public void setCodeSousTypeClient(String codeSousTypeClient) {
        this.codeSousTypeClient = codeSousTypeClient;
    }

    public long getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(long numOrdre) {
        this.numOrdre = numOrdre;
    }

    public String getNumeroAgrementPersonneMorale() {
        return numeroAgrementPersonneMorale;
    }

    public void setNumeroAgrementPersonneMorale(String numeroAgrementPersonneMorale) {
        this.numeroAgrementPersonneMorale = numeroAgrementPersonneMorale;
    }

    public String getNumeroINSAE() {
        return numeroINSAE;
    }

    public void setNumeroINSAE(String numeroINSAE) {
        this.numeroINSAE = numeroINSAE;
    }

    public String getNumRegistre() {
        return numRegistre;
    }

    public void setNumRegistre(String numRegistre) {
        this.numRegistre = numRegistre;
    }

    public String getCodeSecteur() {
        return codeSecteur;
    }

    public void setCodeSecteur(String codeSecteur) {
        this.codeSecteur = codeSecteur;
    }

    public BigDecimal getCapitalSocial() {
        return CapitalSocial;
    }

    public void setCapitalSocial(BigDecimal capitalSocial) {
        CapitalSocial = capitalSocial;
    }

    public String getSiglePersonneMorale() {
        return siglePersonneMorale;
    }

    public void setSiglePersonneMorale(String siglePersonneMorale) {
        this.siglePersonneMorale = siglePersonneMorale;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getCodeFormeJuridique() {
        return codeFormeJuridique;
    }

    public void setCodeFormeJuridique(String codeFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
    }

    public FormeJuridique getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(FormeJuridique formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public String getCodeTypeGrant() {
        return codeTypeGrant;
    }

    public void setCodeTypeGrant(String codeTypeGrant) {
        this.codeTypeGrant = codeTypeGrant;
    }

    public TypeEmetteur getTypeEmetteur() {
        return typeEmetteur;
    }

    public void setTypeEmetteur(TypeEmetteur typeEmetteur) {
        this.typeEmetteur = typeEmetteur;
    }

    public TypeGarant getTypeGarant() {
        return typeGarant;
    }

    public void setTypeGarant(TypeGarant typeGarant) {
        this.typeGarant = typeGarant;
    }

    public Organisme getOrganisme() {
        return organisme;
    }

    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }

    public LocalDateTime getDateCreationPM() {
        return dateCreationPM;
    }

    public void setDateCreationPM(LocalDateTime dateCreationPM) {
        this.dateCreationPM = dateCreationPM;
    }

    public String getNumCompteDepositaire() {
        return numCompteDepositaire;
    }

    public void setNumCompteDepositaire(String numCompteDepositaire) {
        this.numCompteDepositaire = numCompteDepositaire;
    }

    public String getIndexFixe1() {
        return indexFixe1;
    }

    public void setIndexFixe1(String indexFixe1) {
        this.indexFixe1 = indexFixe1;
    }

    public String getTelephoneFixe1() {
        return telephoneFixe1;
    }

    public void setTelephoneFixe1(String telephoneFixe1) {
        this.telephoneFixe1 = telephoneFixe1;
    }

    public String getIndexFixe2() {
        return indexFixe2;
    }

    public void setIndexFixe2(String indexFixe2) {
        this.indexFixe2 = indexFixe2;
    }

    public String getTelephoneFixe2() {
        return telephoneFixe2;
    }

    public void setTelephoneFixe2(String telephoneFixe2) {
        this.telephoneFixe2 = telephoneFixe2;
    }

    public String getIndexMobile1() {
        return indexMobile1;
    }

    public void setIndexMobile1(String indexMobile1) {
        this.indexMobile1 = indexMobile1;
    }

    public String getTelephoneMobile1() {
        return telephoneMobile1;
    }

    public void setTelephoneMobile1(String telephoneMobile1) {
        this.telephoneMobile1 = telephoneMobile1;
    }

    public String getIndexMobile2() {
        return indexMobile2;
    }

    public void setIndexMobile2(String indexMobile2) {
        this.indexMobile2 = indexMobile2;
    }

    public String getTelephoneMobile2() {
        return telephoneMobile2;
    }

    public void setTelephoneMobile2(String telephoneMobile2) {
        this.telephoneMobile2 = telephoneMobile2;
    }

    public String getIndexFax() {
        return indexFax;
    }

    public void setIndexFax(String indexFax) {
        this.indexFax = indexFax;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getNumCompteSgi() {
        return numCompteSgi;
    }

    public void setNumCompteSgi(String numCompteSgi) {
        this.numCompteSgi = numCompteSgi;
    }

    public String getLibelleSousCategorie() {
        return libelleSousCategorie;
    }

    public void setLibelleSousCategorie(String libelleSousCategorie) {
        this.libelleSousCategorie = libelleSousCategorie;
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

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

//OPCCIEL1

    //FIN
}
