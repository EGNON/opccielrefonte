package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.dto.titresciel.OrganismeDto;
import com.ged.dto.titresciel.PlaceDto;
import com.ged.dto.titresciel.TypeEmetteurDto;
import com.ged.dto.titresciel.TypeGarantDto;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonneMoraleDto extends PersonneDto{
    //OPCCIEL 1
    private String numeroAgrementPersonneMorale;
    private LocalDateTime dateAgrement;
    private String NumeroINSAE;
    private String numRegistre;
    private String codeSecteur;
    private long CapitalSocial;
    private String SiglePersonneMorale;
    private String RaisonSociale;
    private FormeJuridiqueDto formeJuridique;
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
    private long tauxRetroCourSous=0;
    private long tauxRetroCourRach=0;
    private String numCompteSgi;
    private String libelleSousCategorie;
    private LocalDateTime dateFermetureCompte;
    private String motifFermetureCompte;
    private String pieceFermetureCompte;
    private String statutCompte;
    private VilleDto ville;
    //FIN
    private Long idPersonne;
    private String sigle;
//    private String raisonSociale;
    private String siteWeb;

    private PlaceDto place;
    private TypeEmetteurDto typeEmetteur;
    private TypeGarantDto typeGarant;
    private OrganismeDto organisme;

    @Override
    public Long getIdPersonne() {
        return idPersonne;
    }

    @Override
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNumeroAgrementPersonneMorale() {
        return numeroAgrementPersonneMorale;
    }

    public void setNumeroAgrementPersonneMorale(String numeroAgrementPersonneMorale) {
        this.numeroAgrementPersonneMorale = numeroAgrementPersonneMorale;
    }

    public LocalDateTime getDateAgrement() {
        return dateAgrement;
    }

    public void setDateAgrement(LocalDateTime dateAgrement) {
        this.dateAgrement = dateAgrement;
    }

    public String getNumeroINSAE() {
        return NumeroINSAE;
    }

    public void setNumeroINSAE(String numeroINSAE) {
        NumeroINSAE = numeroINSAE;
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

    public long getCapitalSocial() {
        return CapitalSocial;
    }

    public void setCapitalSocial(long capitalSocial) {
        CapitalSocial = capitalSocial;
    }

    public String getSiglePersonneMorale() {
        return SiglePersonneMorale;
    }

    public void setSiglePersonneMorale(String siglePersonneMorale) {
        SiglePersonneMorale = siglePersonneMorale;
    }

    public FormeJuridiqueDto getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(FormeJuridiqueDto formeJuridique) {
        this.formeJuridique = formeJuridique;
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

    public long getTauxRetroCourSous() {
        return tauxRetroCourSous;
    }

    public void setTauxRetroCourSous(long tauxRetroCourSous) {
        this.tauxRetroCourSous = tauxRetroCourSous;
    }

    public long getTauxRetroCourRach() {
        return tauxRetroCourRach;
    }

    public void setTauxRetroCourRach(long tauxRetroCourRach) {
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

    public VilleDto getVille() {
        return ville;
    }

    public void setVille(VilleDto ville) {
        this.ville = ville;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getRaisonSociale() {
        return RaisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.RaisonSociale = raisonSociale;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    public TypeEmetteurDto getTypeEmetteur() {
        return typeEmetteur;
    }

    public void setTypeEmetteur(TypeEmetteurDto typeEmetteur) {
        this.typeEmetteur = typeEmetteur;
    }

    public TypeGarantDto getTypeGarant() {
        return typeGarant;
    }

    public void setTypeGarant(TypeGarantDto typeGarant) {
        this.typeGarant = typeGarant;
    }

    public OrganismeDto getOrganisme() {
        return organisme;
    }

    public void setOrganisme(OrganismeDto organisme) {
        this.organisme = organisme;
    }
}
