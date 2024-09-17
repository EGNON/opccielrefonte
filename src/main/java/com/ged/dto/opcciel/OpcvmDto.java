package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PaysDto;
import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.dto.standard.PersonnePhysiqueDto;
import com.ged.dto.standard.VilleDto;
import com.ged.dto.titresciel.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpcvmDto {
    private Long idOpcvm;
    private String ibOpcvm;
    private String agrement;
    private LocalDateTime dateAgrement;
    private String registreDeCommerce;
    private Boolean estCompteSysteme;
    private PersonnePhysiqueDto personneGestionnaire;
    private String codeFormeJuridique;
    private FormeJuridiqueOpcDto formeJuridiqueOpc;
    private PersonneMoraleDto personneIntervenant;
    private TypeAffectationTitreDto typeAffectationTitre;
    private PersonneMoraleDto personneEmetteur;
    private ClassificationOPCDto classification;
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
    private Short dureeExerciceOpcvm;
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
    private String visaNoteInformation;
    private Boolean verifier;
    private Boolean verifierNiveau1;
    private Boolean verifierNiveau2;
    private String verificateur1;
    private String verificateur2;
    private String dateVerifNiveau1;
    private String dateVerifNiveau2;
    private String cheminArchive;
    private String adresseComplete;
    private String telephoneFixe;
    private String telephoneMobile;
    private String codeSkype;
    private String siteweb;
    private String fax;
    private String email;
    private String boitePostale;
    private PaysDto pays;
    private VilleDto ville;
    private Long tauxRendement;
    private TitreDto titre;
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private Long numLigne;
    private Boolean supprimer;
    private Boolean estObligataire;
    private BigDecimal proportionIndice;
    private BigDecimal proportionTaux ;
    //    @Basic
//
//    private LocalDateTime rowvers;
    private String userLogin;

    public OpcvmDto() {
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

    public PersonnePhysiqueDto getPersonneGestionnaire() {
        return personneGestionnaire;
    }

    public void setPersonneGestionnaire(PersonnePhysiqueDto personneGestionnaire) {
        this.personneGestionnaire = personneGestionnaire;
    }

    public String getCodeFormeJuridique() {
        return codeFormeJuridique;
    }

    public void setCodeFormeJuridique(String codeFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
    }

    public FormeJuridiqueOpcDto getFormeJuridiqueOpc() {
        return formeJuridiqueOpc;
    }

    public void setFormeJuridiqueOpc(FormeJuridiqueOpcDto formeJuridiqueOpc) {
        this.formeJuridiqueOpc = formeJuridiqueOpc;
    }

    public PersonneMoraleDto getPersonneIntervenant() {
        return personneIntervenant;
    }

    public void setPersonneIntervenant(PersonneMoraleDto personneIntervenant) {
        this.personneIntervenant = personneIntervenant;
    }

    public TypeAffectationTitreDto getTypeAffectationTitre() {
        return typeAffectationTitre;
    }

    public void setTypeAffectationTitre(TypeAffectationTitreDto typeAffectationTitre) {
        this.typeAffectationTitre = typeAffectationTitre;
    }

    public PersonneMoraleDto getPersonneEmetteur() {
        return personneEmetteur;
    }

    public void setPersonneEmetteur(PersonneMoraleDto personneEmetteur) {
        this.personneEmetteur = personneEmetteur;
    }

    public ClassificationOPCDto getClassification() {
        return classification;
    }

    public void setClassification(ClassificationOPCDto classification) {
        this.classification = classification;
    }

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
        return coursInitial;
    }

    public void setCoursInitial(String coursInitial) {
        this.coursInitial = coursInitial;
    }

    public String getCoursActuel() {
        return coursActuel;
    }

    public void setCoursActuel(String coursActuel) {
        this.coursActuel = coursActuel;
    }

    public Short getDureeExerciceOpcvm() {
        return dureeExerciceOpcvm;
    }

    public void setDureeExerciceOpcvm(Short dureeExerciceOpcvm) {
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

    public PaysDto getPays() {
        return pays;
    }

    public void setPays(PaysDto pays) {
        this.pays = pays;
    }

    public VilleDto getVille() {
        return ville;
    }

    public void setVille(VilleDto ville) {
        this.ville = ville;
    }

    public Long getTauxRendement() {
        return tauxRendement;
    }

    public void setTauxRendement(Long tauxRendement) {
        this.tauxRendement = tauxRendement;
    }

    public TitreDto getTitre() {
        return titre;
    }

    public void setTitre(TitreDto titre) {
        this.titre = titre;
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

    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    public Boolean getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
