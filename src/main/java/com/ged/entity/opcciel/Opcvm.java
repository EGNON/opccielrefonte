package com.ged.entity.opcciel;

import com.ged.entity.Base;
import com.ged.entity.standard.*;
import com.ged.entity.titresciel.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@Table(name = "T_Opcvm", schema = "Parametre")
public class Opcvm extends Base {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idOpcvm;
    private Long idOcc;
    //OPCCIEL 1
    private String ibOpcvm;
    private String agrement;
    private LocalDateTime dateAgrement;
    private String registreDeCommerce;
    private Boolean estCompteSysteme;
    @ManyToOne
    @JoinColumn(name = "idPersonneGestionnaire",referencedColumnName = "idPersonne")
    private PersonnePhysique personneGestionnaire;
    private String codeFormeJuridique;
    @ManyToOne
    @JoinColumn(name = "idFormeJuridiqueOpc", referencedColumnName = "idFormeJuridiqueOpc")
    private FormeJuridiqueOpc formeJuridiqueOpc;
    private long idPersonneIntervenant;
    @ManyToOne
    @JoinColumn(name = "idPersonneIntervenantNew",referencedColumnName = "idPersonne")
    private PersonneMorale personneIntervenant;
    private String codeTypeAffectation;
    @ManyToOne
    @JoinColumn(name = "idTypeAffectation")
    private TypeAffectationVL typeAffectationTitre;
    private long idPersonneEmetteur;
    @ManyToOne
    @JoinColumn(name = "idPersonneEmetteurNew",referencedColumnName = "idPersonne")
    private PersonneMorale personneEmetteur;
    @ManyToOne
    @JoinColumn(name = "codeClassification",referencedColumnName = "codeClassificationOPC")
    private ClassificationOPC classification;
    private LocalDateTime dateCreationOpcvm;
    private String sigleOpcvm;
    private String denominationOpcvm;
    private BigDecimal nbrePartInitial;
    private BigDecimal nbrePartDebutExercice;
    private BigDecimal nbrePartActuelle;
    private BigDecimal valeurLiquidativeOrigine;
    private BigDecimal valeurLiquidativeActuelle;
    private BigDecimal capitalInitialOpcvm;
    private String coursInitial;
    private String coursActuel;
    private Integer dureeExerciceOpcvm;
    private LocalDateTime debutExerciceActuelOpcvm;
    private String uniteDureeExerciceOpcvm;
    private Short periodiciteAffectationOpcvm;
    private String unitePeriodiciteAffectationOpcvm;
    private LocalDateTime derniereDateAffectationOpcvm;
    private Short periodiciteCalculValeurLiquidativeOpcvm;
    private String unitePeriodiciteCalculValeurLiquidative;
    private BigDecimal valeurMinimalPlacement;
    private Short dureeMinimalPlacement;
    private String uniteDureeMinimalPlacement;
    private LocalDateTime dateProchainCalculVL;
    private Boolean appliqueeTVA;
    private Boolean appliqueeTAF;
    private Integer nombreDecimaux;
    private Boolean estArrondiSupInf;
    private Integer nombreDecimauxCompta;
    private Boolean estArrondiSupInfCompta;
    private Integer nombreDecimauxPart;
    private BigDecimal tauxCommissionSouscription;
    private BigDecimal tauxCommissionRachat;
    private BigDecimal tauxTAF;
    private BigDecimal tauxRetrocessionSouscription;
    private BigDecimal tauxRetrocessionRachat;
    private BigDecimal tauxFraisGestion;
    private Boolean AppliquerSurActifNet;
    private Short delaiLivraisonOpcvm;
    private String uniteDelaiLivraisonOpcvm;
    private String nomContact;
    private String prenomContact;
    private String adresseContact;
    private BigDecimal borneInferieureSensibilite;
    private BigDecimal borneSuperieureSensibilite;
    private Boolean inclusBorneInferieureSensibilite;
    private Boolean inclusBorneSuperieureSensibilite;
    @Column(length = 150)
    private String visaNoteInformation;
    private Boolean verifier;
    private Boolean verifierNiveau1;
    @Column(nullable = true)
    private Boolean verifierNiveau2;
    @Column(nullable = true)
    private String verificateur1;
    @Column(nullable = true)
    private String verificateur2;
    @Column(nullable = true)
    private String dateVerifNiveau1;
    @Column(nullable = true)
    private String dateVerifNiveau2;
    @Column(nullable = true)
    private String cheminArchive;
    @Column(nullable = true)
    private String adresseComplete;
    @Column(nullable = true)
    private String telephoneFixe;
    @Column(nullable = true)
    private String telephoneMobile;
    @Column(nullable = true)
    private String codeSkype;
    @Column(nullable = true)
    private String siteweb;
    @Column(nullable = true)
    private String fax;
    @Column(nullable = true)
    private String email;
    @Column(nullable = true)
    private String boitePostale;
    private String codePays;
    @ManyToOne()
    @JoinColumn(name = "idPays")
    private Pays pays;
    private String codeVille;
    @ManyToOne()
    @JoinColumn(name = "idVille")
    private Ville ville;
    @Column(precision = 18, scale = 6)
    private BigDecimal tauxRendement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTitre", referencedColumnName = "idTitre")
    private Titre titre;
    //FIN
    private Boolean estObligataire;
    private BigDecimal proportionIndice;
    private BigDecimal proportionTaux ;

    public Opcvm() {
    }

    @Override
    public Long getIdOcc() {
        return idOcc;
    }

    @Override
    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

    public long getIdPersonneIntervenant() {
        return idPersonneIntervenant;
    }

    public void setIdPersonneIntervenant(long idPersonneIntervenant) {
        this.idPersonneIntervenant = idPersonneIntervenant;
    }

    public PersonneMorale getPersonneIntervenant() {
        return personneIntervenant;
    }

    public void setPersonneIntervenant(PersonneMorale personneIntervenant) {
        this.personneIntervenant = null;
    }

    public String getCodeTypeAffectation() {
        return codeTypeAffectation;
    }

    public void setCodeTypeAffectation(String codeTypeAffectation) {
        this.codeTypeAffectation = codeTypeAffectation;
    }

    public long getIdPersonneEmetteur() {
        return idPersonneEmetteur;
    }

    public void setIdPersonneEmetteur(long idPersonneEmetteur) {
        this.idPersonneEmetteur = idPersonneEmetteur;
    }

    public PersonneMorale getPersonneEmetteur() {
        return personneEmetteur;
    }

    public void setPersonneEmetteur(PersonneMorale personneEmetteur) {
        this.personneEmetteur = personneEmetteur;
    }

    public ClassificationOPC getClassification() {
        return classification;
    }

    public void setClassification(ClassificationOPC classification) {
        this.classification = classification;
    }

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(String codeVille) {
        this.codeVille = codeVille;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    public Boolean getEstObligataire() {
        return estObligataire;
    }

    public void setEstObligataire(Boolean estObligataire) {
        this.estObligataire = estObligataire;
    }

    public BigDecimal getProportionIndice() {
        return proportionIndice;
    }

    public void setProportionIndice(BigDecimal proportionIndice) {
        this.proportionIndice = proportionIndice;
    }

    public BigDecimal getProportionTaux() {
        return proportionTaux;
    }

    public void setProportionTaux(BigDecimal proportionTaux) {
        this.proportionTaux = proportionTaux;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getIbOpcvm() {
        return ibOpcvm;
    }

    public void setIbOpcvm(String ibOpcvm) {
        this.ibOpcvm = ibOpcvm;
    }

    public BigDecimal getTauxRendement() {
        return tauxRendement;
    }

    public void setTauxRendement(BigDecimal tauxRendement) {
        this.tauxRendement = tauxRendement;
    }

    public String getAgrement() {
        return agrement;
    }

    public void setAgrement(String agrement) {
        this.agrement = agrement;
    }

    public LocalDateTime getDateAgrement() {
        return dateAgrement;
    }

    public void setDateAgrement(LocalDateTime dateAgrement) {
        this.dateAgrement = dateAgrement;
    }

    public String getRegistreDeCommerce() {
        return registreDeCommerce;
    }

    public void setRegistreDeCommerce(String registreDeCommerce) {
        this.registreDeCommerce = registreDeCommerce;
    }

    public Boolean getEstCompteSysteme() {
        return estCompteSysteme;
    }

    public void setEstCompteSysteme(Boolean estCompteSysteme) {
        this.estCompteSysteme = estCompteSysteme;
    }

    public PersonnePhysique getPersonneGestionnaire() {
        return personneGestionnaire;
    }

    public void setPersonneGestionnaire(PersonnePhysique personneGestionnaire) {
        this.personneGestionnaire = personneGestionnaire;
    }

    public String getCodeFormeJuridique() {
        return codeFormeJuridique;
    }

    public void setCodeFormeJuridique(String codeFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
    }

    public FormeJuridiqueOpc getFormeJuridiqueOpc() {
        return formeJuridiqueOpc;
    }

    public void setFormeJuridiqueOpc(FormeJuridiqueOpc formeJuridiqueOpc) {
        this.formeJuridiqueOpc = formeJuridiqueOpc;
    }

    /*public PersonneMorale getPersonneIntervenant() {
        return personneIntervenant;
    }

    public void setPersonneIntervenant(PersonneMorale personneIntervenant) {
        this.personneIntervenant = personneIntervenant;
    }*/

    public TypeAffectationVL getTypeAffectationTitre() {
        return typeAffectationTitre;
    }

    public void setTypeAffectationTitre(TypeAffectationVL typeAffectationTitre) {
        this.typeAffectationTitre = typeAffectationTitre;
    }

    /*public PersonneMorale getPersonneEmetteur() {
        return personneEmetteur;
    }

    public void setPersonneEmetteur(PersonneMorale personneEmetteur) {
        this.personneEmetteur = personneEmetteur;
    }*/

    /*public ClassificationOPC getClassification() {
        return classification;
    }

    public void setClassification(ClassificationOPC classification) {
        this.classification = classification;
    }*/

    public LocalDateTime getDateCreationOpcvm() {
        return dateCreationOpcvm;
    }

    public void setDateCreationOpcvm(LocalDateTime dateCreationOpcvm) {
        this.dateCreationOpcvm = dateCreationOpcvm;
    }

    public String getSigleOpcvm() {
        return sigleOpcvm;
    }

    public void setSigleOpcvm(String sigleOpcvm) {
        this.sigleOpcvm = sigleOpcvm;
    }

    public String getDenominationOpcvm() {
        return denominationOpcvm;
    }

    public void setDenominationOpcvm(String denominationOpcvm) {
        this.denominationOpcvm = denominationOpcvm;
    }

    public BigDecimal getNbrePartInitial() {
        return nbrePartInitial;
    }

    public void setNbrePartInitial(BigDecimal nbrePartInitial) {
        this.nbrePartInitial = nbrePartInitial;
    }

    public BigDecimal getNbrePartDebutExercice() {
        return nbrePartDebutExercice;
    }

    public void setNbrePartDebutExercice(BigDecimal nbrePartDebutExercice) {
        this.nbrePartDebutExercice = nbrePartDebutExercice;
    }

    public BigDecimal getNbrePartActuelle() {
        return nbrePartActuelle;
    }

    public void setNbrePartActuelle(BigDecimal nbrePartActuelle) {
        this.nbrePartActuelle = nbrePartActuelle;
    }

    public BigDecimal getValeurLiquidativeOrigine() {
        return valeurLiquidativeOrigine;
    }

    public void setValeurLiquidativeOrigine(BigDecimal valeurLiquidativeOrigine) {
        this.valeurLiquidativeOrigine = valeurLiquidativeOrigine;
    }

    public BigDecimal getValeurLiquidativeActuelle() {
        return valeurLiquidativeActuelle;
    }

    public void setValeurLiquidativeActuelle(BigDecimal valeurLiquidativeActuelle) {
        this.valeurLiquidativeActuelle = valeurLiquidativeActuelle;
    }

    public BigDecimal getCapitalInitialOpcvm() {
        return capitalInitialOpcvm;
    }

    public void setCapitalInitialOpcvm(BigDecimal capitalInitialOpcvm) {
        this.capitalInitialOpcvm = capitalInitialOpcvm;
    }

    public String getCoursInitial() {
        return coursInitial.trim();
    }

    public void setCoursInitial(String coursInitial) {
        this.coursInitial = coursInitial;
    }

    public String getCoursActuel() {
        return coursActuel.trim();
    }

    public void setCoursActuel(String coursActuel) {
        this.coursActuel = coursActuel;
    }

    public Integer getDureeExerciceOpcvm() {
        return dureeExerciceOpcvm;
    }

    public void setDureeExerciceOpcvm(Integer dureeExerciceOpcvm) {
        this.dureeExerciceOpcvm = dureeExerciceOpcvm;
    }

    public LocalDateTime getDebutExerciceActuelOpcvm() {
        return debutExerciceActuelOpcvm;
    }

    public void setDebutExerciceActuelOpcvm(LocalDateTime debutExerciceActuelOpcvm) {
        this.debutExerciceActuelOpcvm = debutExerciceActuelOpcvm;
    }

    public String getUniteDureeExerciceOpcvm() {
        return uniteDureeExerciceOpcvm;
    }

    public void setUniteDureeExerciceOpcvm(String uniteDureeExerciceOpcvm) {
        this.uniteDureeExerciceOpcvm = uniteDureeExerciceOpcvm;
    }

    public Short getPeriodiciteAffectationOpcvm() {
        return periodiciteAffectationOpcvm;
    }

    public void setPeriodiciteAffectationOpcvm(Short periodiciteAffectationOpcvm) {
        this.periodiciteAffectationOpcvm = periodiciteAffectationOpcvm;
    }

    public String getUnitePeriodiciteAffectationOpcvm() {
        return unitePeriodiciteAffectationOpcvm;
    }

    public void setUnitePeriodiciteAffectationOpcvm(String unitePeriodiciteAffectationOpcvm) {
        this.unitePeriodiciteAffectationOpcvm = unitePeriodiciteAffectationOpcvm;
    }

    public LocalDateTime getDerniereDateAffectationOpcvm() {
        return derniereDateAffectationOpcvm;
    }

    public void setDerniereDateAffectationOpcvm(LocalDateTime derniereDateAffectationOpcvm) {
        this.derniereDateAffectationOpcvm = derniereDateAffectationOpcvm;
    }

    public Short getPeriodiciteCalculValeurLiquidativeOpcvm() {
        return periodiciteCalculValeurLiquidativeOpcvm;
    }

    public void setPeriodiciteCalculValeurLiquidativeOpcvm(Short periodiciteCalculValeurLiquidativeOpcvm) {
        this.periodiciteCalculValeurLiquidativeOpcvm = periodiciteCalculValeurLiquidativeOpcvm;
    }

    public String getUnitePeriodiciteCalculValeurLiquidative() {
        return unitePeriodiciteCalculValeurLiquidative;
    }

    public void setUnitePeriodiciteCalculValeurLiquidative(String unitePeriodiciteCalculValeurLiquidative) {
        this.unitePeriodiciteCalculValeurLiquidative = unitePeriodiciteCalculValeurLiquidative;
    }

    public BigDecimal getValeurMinimalPlacement() {
        return valeurMinimalPlacement;
    }

    public void setValeurMinimalPlacement(BigDecimal valeurMinimalPlacement) {
        this.valeurMinimalPlacement = valeurMinimalPlacement;
    }

    public Short getDureeMinimalPlacement() {
        return dureeMinimalPlacement;
    }

    public void setDureeMinimalPlacement(Short dureeMinimalPlacement) {
        this.dureeMinimalPlacement = dureeMinimalPlacement;
    }

    public String getUniteDureeMinimalPlacement() {
        return uniteDureeMinimalPlacement;
    }

    public void setUniteDureeMinimalPlacement(String uniteDureeMinimalPlacement) {
        this.uniteDureeMinimalPlacement = uniteDureeMinimalPlacement;
    }

    public LocalDateTime getDateProchainCalculVL() {
        return dateProchainCalculVL;
    }

    public void setDateProchainCalculVL(LocalDateTime dateProchainCalculVL) {
        this.dateProchainCalculVL = dateProchainCalculVL;
    }

    public Boolean getAppliqueeTVA() {
        return appliqueeTVA;
    }

    public void setAppliqueeTVA(Boolean appliqueeTVA) {
        this.appliqueeTVA = appliqueeTVA;
    }

    public Boolean getAppliqueeTAF() {
        return appliqueeTAF;
    }

    public void setAppliqueeTAF(Boolean appliqueeTAF) {
        this.appliqueeTAF = appliqueeTAF;
    }

    public Integer getNombreDecimaux() {
        return nombreDecimaux;
    }

    public void setNombreDecimaux(Integer nombreDecimaux) {
        this.nombreDecimaux = nombreDecimaux;
    }

    public Boolean getEstArrondiSupInf() {
        return estArrondiSupInf;
    }

    public void setEstArrondiSupInf(Boolean estArrondiSupInf) {
        this.estArrondiSupInf = estArrondiSupInf;
    }

    public Integer getNombreDecimauxCompta() {
        return nombreDecimauxCompta;
    }

    public void setNombreDecimauxCompta(Integer nombreDecimauxCompta) {
        this.nombreDecimauxCompta = nombreDecimauxCompta;
    }

    public Boolean getEstArrondiSupInfCompta() {
        return estArrondiSupInfCompta;
    }

    public void setEstArrondiSupInfCompta(Boolean estArrondiSupInfCompta) {
        this.estArrondiSupInfCompta = estArrondiSupInfCompta;
    }

    public Integer getNombreDecimauxPart() {
        return nombreDecimauxPart;
    }

    public void setNombreDecimauxPart(Integer nombreDecimauxPart) {
        this.nombreDecimauxPart = nombreDecimauxPart;
    }

    public BigDecimal getTauxCommissionSouscription() {
        return tauxCommissionSouscription;
    }

    public void setTauxCommissionSouscription(BigDecimal tauxCommissionSouscription) {
        this.tauxCommissionSouscription = tauxCommissionSouscription;
    }

    public BigDecimal getTauxCommissionRachat() {
        return tauxCommissionRachat;
    }

    public void setTauxCommissionRachat(BigDecimal tauxCommissionRachat) {
        this.tauxCommissionRachat = tauxCommissionRachat;
    }

    public BigDecimal getTauxTAF() {
        return tauxTAF;
    }

    public void setTauxTAF(BigDecimal tauxTAF) {
        this.tauxTAF = tauxTAF;
    }

    public BigDecimal getTauxRetrocessionSouscription() {
        return tauxRetrocessionSouscription;
    }

    public void setTauxRetrocessionSouscription(BigDecimal tauxRetrocessionSouscription) {
        this.tauxRetrocessionSouscription = tauxRetrocessionSouscription;
    }

    public BigDecimal getTauxRetrocessionRachat() {
        return tauxRetrocessionRachat;
    }

    public void setTauxRetrocessionRachat(BigDecimal tauxRetrocessionRachat) {
        this.tauxRetrocessionRachat = tauxRetrocessionRachat;
    }

    public BigDecimal getTauxFraisGestion() {
        return tauxFraisGestion;
    }

    public void setTauxFraisGestion(BigDecimal tauxFraisGestion) {
        this.tauxFraisGestion = tauxFraisGestion;
    }

    public Boolean getAppliquerSurActifNet() {
        return AppliquerSurActifNet;
    }

    public void setAppliquerSurActifNet(Boolean appliquerSurActifNet) {
        AppliquerSurActifNet = appliquerSurActifNet;
    }

    public Short getDelaiLivraisonOpcvm() {
        return delaiLivraisonOpcvm;
    }

    public void setDelaiLivraisonOpcvm(Short delaiLivraisonOpcvm) {
        this.delaiLivraisonOpcvm = delaiLivraisonOpcvm;
    }

    public String getUniteDelaiLivraisonOpcvm() {
        return uniteDelaiLivraisonOpcvm;
    }

    public void setUniteDelaiLivraisonOpcvm(String uniteDelaiLivraisonOpcvm) {
        this.uniteDelaiLivraisonOpcvm = uniteDelaiLivraisonOpcvm;
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

    public String getAdresseContact() {
        return adresseContact;
    }

    public void setAdresseContact(String adresseContact) {
        this.adresseContact = adresseContact;
    }

    public BigDecimal getBorneInferieureSensibilite() {
        return borneInferieureSensibilite;
    }

    public void setBorneInferieureSensibilite(BigDecimal borneInferieureSensibilite) {
        this.borneInferieureSensibilite = borneInferieureSensibilite;
    }

    public BigDecimal getBorneSuperieureSensibilite() {
        return borneSuperieureSensibilite;
    }

    public void setBorneSuperieureSensibilite(BigDecimal borneSuperieureSensibilite) {
        this.borneSuperieureSensibilite = borneSuperieureSensibilite;
    }

    public Boolean getInclusBorneInferieureSensibilite() {
        return inclusBorneInferieureSensibilite;
    }

    public void setInclusBorneInferieureSensibilite(Boolean inclusBorneInferieureSensibilite) {
        this.inclusBorneInferieureSensibilite = inclusBorneInferieureSensibilite;
    }

    public Boolean getInclusBorneSuperieureSensibilite() {
        return inclusBorneSuperieureSensibilite;
    }

    public void setInclusBorneSuperieureSensibilite(Boolean inclusBorneSuperieureSensibilite) {
        this.inclusBorneSuperieureSensibilite = inclusBorneSuperieureSensibilite;
    }

    public String getVisaNoteInformation() {
        return visaNoteInformation;
    }

    public void setVisaNoteInformation(String visaNoteInformation) {
        this.visaNoteInformation = visaNoteInformation;
    }

    public Boolean getVerifier() {
        return verifier;
    }

    public void setVerifier(Boolean verifier) {
        this.verifier = verifier;
    }

    public Boolean getVerifierNiveau1() {
        return verifierNiveau1;
    }

    public void setVerifierNiveau1(Boolean verifierNiveau1) {
        this.verifierNiveau1 = verifierNiveau1;
    }

    public Boolean getVerifierNiveau2() {
        return verifierNiveau2;
    }

    public void setVerifierNiveau2(Boolean verifierNiveau2) {
        this.verifierNiveau2 = verifierNiveau2;
    }

    public String getVerificateur1() {
        return verificateur1;
    }

    public void setVerificateur1(String verificateur1) {
        this.verificateur1 = verificateur1;
    }

    public String getVerificateur2() {
        return verificateur2;
    }

    public void setVerificateur2(String verificateur2) {
        this.verificateur2 = verificateur2;
    }

    public String getDateVerifNiveau1() {
        return dateVerifNiveau1;
    }

    public void setDateVerifNiveau1(String dateVerifNiveau1) {
        this.dateVerifNiveau1 = dateVerifNiveau1;
    }

    public String getDateVerifNiveau2() {
        return dateVerifNiveau2;
    }

    public void setDateVerifNiveau2(String dateVerifNiveau2) {
        this.dateVerifNiveau2 = dateVerifNiveau2;
    }

    public String getCheminArchive() {
        return cheminArchive;
    }

    public void setCheminArchive(String cheminArchive) {
        this.cheminArchive = cheminArchive;
    }

    public String getAdresseComplete() {
        return adresseComplete;
    }

    public void setAdresseComplete(String adresseComplete) {
        this.adresseComplete = adresseComplete;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }

    public void setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
    }

    public String getCodeSkype() {
        return codeSkype;
    }

    public void setCodeSkype(String codeSkype) {
        this.codeSkype = codeSkype;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
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

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    /*public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }*/
}
