package com.ged.entity.standard;

import com.ged.entity.titresciel.Organisme;
import com.ged.entity.titresciel.Place;
import com.ged.entity.titresciel.TypeEmetteur;
import com.ged.entity.titresciel.TypeGarant;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("PM")
@Table(name = "T_PersonneMorale", schema = "Parametre")
public class PersonneMorale extends Personne{
    //OPCCIEL 1
    private String numeroAgrementPersonneMorale;
    private String numeroINSAE;
    private String numRegistre;
    private String codeSecteur;
    @Column(nullable =true)
    private BigDecimal CapitalSocial;
    private String siglePersonneMorale;
    private String raisonSociale;
    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeFormeJuridique")
    private FormeJuridique  formeJuridique;*/
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlace")
    private Place place;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeTypeEmetteur")
    private TypeEmetteur typeEmetteur;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeGarant")
    private TypeGarant typeGarant;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOrganisme",referencedColumnName = "idPersonne")
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
    @Column(nullable =true)
    private BigDecimal tauxRetroCourSous;
    @Column(nullable =true)
    private BigDecimal tauxRetroCourRach;
    private String numCompteSgi;
    private String libelleSousCategorie;
    private LocalDateTime dateFermetureCompte;
    private String motifFermetureCompte;
    private String pieceFermetureCompte;
    private String statutCompte;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idVille")
    private Ville ville;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSousCategorie")
    private SousCategorie sousCategorie;
    //FIN

    @Basic
    private String sigle;
    @Basic
    private String siteWeb;
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

    public PersonneMorale() {
    }

    public PersonneMorale(String sigle, String raisonSociale, String siteWeb) {
        this.sigle = sigle;
        this.raisonSociale = raisonSociale;
        this.siteWeb = siteWeb;
    }


    @Override
    public void setDenomination(String denomination) {
        super.setDenomination(denomination);

    }

    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
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

    /*public FormeJuridique getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(FormeJuridique formeJuridique) {
        this.formeJuridique = formeJuridique;
    }*/

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

//    public LocalDateTime getDateAgrement() {
//        return dateAgrement;
//    }
//
//    public void setDateAgrement(LocalDateTime dateAgrement) {
//        this.dateAgrement = dateAgrement;
//    }

    public String getNumRegistre() {
        return numRegistre;
    }

    public void setNumRegistre(String numRegistre) {
        this.numRegistre = numRegistre;
    }

    public String getIndexFixe1() {
        return indexFixe1;
    }

    public void setIndexFixe1(String indexFixe1) {
        this.indexFixe1 = indexFixe1;
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

    public String getIndexMobile2() {
        return indexMobile2;
    }

    public void setIndexMobile2(String indexMobile2) {
        this.indexMobile2 = indexMobile2;
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

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public String getTelephoneFixe1() {
        return telephoneFixe1;
    }

    public void setTelephoneFixe1(String telephoneFixe1) {
        this.telephoneFixe1 = telephoneFixe1;
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
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

    //FIN

    @Override
    public String toString() {
        return "PersonneMorale{" +
                "numeroAgrementPersonneMorale='" + numeroAgrementPersonneMorale + '\'' +
                ", numeroINSAE='" + numeroINSAE + '\'' +
                ", numRegistre='" + numRegistre + '\'' +
                ", codeSecteur='" + codeSecteur + '\'' +
                ", CapitalSocial=" + CapitalSocial +
                ", siglePersonneMorale='" + siglePersonneMorale + '\'' +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", dateCreationPM=" + dateCreationPM +
                ", numCompteDepositaire='" + numCompteDepositaire + '\'' +
                ", indexFixe1='" + indexFixe1 + '\'' +
                ", telephoneFixe1='" + telephoneFixe1 + '\'' +
                ", indexFixe2='" + indexFixe2 + '\'' +
                ", telephoneFixe2='" + telephoneFixe2 + '\'' +
                ", indexMobile1='" + indexMobile1 + '\'' +
                ", telephoneMobile1='" + telephoneMobile1 + '\'' +
                ", indexMobile2='" + indexMobile2 + '\'' +
                ", telephoneMobile2='" + telephoneMobile2 + '\'' +
                ", indexFax='" + indexFax + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", boitePostale='" + boitePostale + '\'' +
                ", adresseComplete='" + adresseComplete + '\'' +
                ", tauxRetroCourSous=" + tauxRetroCourSous +
                ", tauxRetroCourRach=" + tauxRetroCourRach +
                ", numCompteSgi='" + numCompteSgi + '\'' +
                ", libelleSousCategorie='" + libelleSousCategorie + '\'' +
                ", dateFermetureCompte=" + dateFermetureCompte +
                ", motifFermetureCompte='" + motifFermetureCompte + '\'' +
                ", pieceFermetureCompte='" + pieceFermetureCompte + '\'' +
                ", statutCompte='" + statutCompte + '\'' +
                ", sigle='" + sigle + '\'' +
                ", siteWeb='" + siteWeb + '\'' +
                '}';
    }
}
