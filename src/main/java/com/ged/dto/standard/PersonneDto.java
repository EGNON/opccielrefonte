package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.DegreDto;
import com.ged.dto.standard.revuecompte.CategorieClientDto;
import com.ged.dto.standard.revuecompte.SousTypeClientDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonneDto {
    private Long idPersonne;
    private String ifu;
    private String mobile1;
    private String mobile2;
    private String fixe1;
    private String fixe2;
    private String statutCompte;
    private String bp;
    private String emailPerso;
    private String emailPro;
    private String domicile;
    private String numeroPiece;
    private String typePiece;
    private LocalDateTime dateExpirationPiece;
    private String modeEtablissement;
    private ModeEtablissementDto modeEtablissementDto;
    private Boolean estsgi;
    private Boolean ppe1;
    private Boolean ppe2;
    private Boolean ppe3;
    private Boolean ppe4;
    private SecteurDto secteur;
    private DegreDto degre;
    private PersonneDto distributeur;
    private PaysDto paysResidence;
    private String denomination;
    private String nomContact;
    private String prenomContact;
    private String telContact;
    private String emailContact;
    private String titreContact;
    private String numeroCpteDeposit;
    private Boolean estConvertie;
    private CommuneDto commune;
    private Set<DocumentDto> documents = new HashSet<>();
    private Set<StatutPersonneDto> statutPersonnes = new HashSet<>();
    private Boolean estExpose;
    private Boolean estJuge;
    private Boolean estGele;
    private String typePersonne;
    private CategorieClientDto categorieClient;
    private SousTypeClientDto sousTypeClient;

    public String getStatutCompte() {
        return statutCompte;
    }

    public void setStatutCompte(String statutCompte) {
        this.statutCompte = statutCompte;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
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

    public ModeEtablissementDto getModeEtablissementDto() {
        return modeEtablissementDto;
    }

    public void setModeEtablissementDto(ModeEtablissementDto modeEtablissementDto) {
        this.modeEtablissementDto = modeEtablissementDto;
    }

    public Boolean getEstsgi() {
        return estsgi;
    }

    public void setEstsgi(Boolean estsgi) {
        this.estsgi = estsgi;
    }

    public Boolean getPpe1() {
        return ppe1;
    }

    public void setPpe1(Boolean ppe1) {
        this.ppe1 = ppe1;
    }

    public Boolean getPpe2() {
        return ppe2;
    }

    public void setPpe2(Boolean ppe2) {
        this.ppe2 = ppe2;
    }

    public Boolean getPpe3() {
        return ppe3;
    }

    public void setPpe3(Boolean ppe3) {
        this.ppe3 = ppe3;
    }

    public Boolean getPpe4() {
        return ppe4;
    }

    public void setPpe4(Boolean ppe4) {
        this.ppe4 = ppe4;
    }

    public SecteurDto getSecteur() {
        return secteur;
    }

    public Boolean getEstExpose() {
        return estExpose;
    }

    public void setEstExpose(Boolean estExpose) {
        this.estExpose = estExpose;
    }

    public Boolean getEstJuge() {
        return estJuge;
    }

    public void setEstJuge(Boolean estJuge) {
        this.estJuge = estJuge;
    }

    public Boolean getEstGele() {
        return estGele;
    }

    public void setEstGele(Boolean estGele) {
        this.estGele = estGele;
    }

    public void setSecteur(SecteurDto secteur) {
        this.secteur = secteur;
    }

    public DegreDto getDegre() {
        return degre;
    }

    public void setDegre(DegreDto degre) {
        this.degre = degre;
    }

    public PersonneDto getDistributeur() {
        return distributeur;
    }

    public void setDistributeur(PersonneDto distributeur) {
        this.distributeur = distributeur;
    }

    public PaysDto getPaysResidence() {
        return paysResidence;
    }

    public void setPaysResidence(PaysDto paysResidence) {
        this.paysResidence = paysResidence;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
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

    public Boolean getEstConvertie() {
        return estConvertie;
    }

    public void setEstConvertie(Boolean estConvertie) {
        this.estConvertie = estConvertie;
    }

    public CommuneDto getCommune() {
        return commune;
    }

    public void setCommune(CommuneDto commune) {
        this.commune = commune;
    }

    public Set<DocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDto> documents) {
        this.documents = documents;
    }

    public Set<StatutPersonneDto> getStatutPersonnes() {
        return statutPersonnes;
    }

    public void setStatutPersonnes(Set<StatutPersonneDto> statutPersonnes) {
        this.statutPersonnes = statutPersonnes;
    }

    public String getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(String typePersonne) {
        this.typePersonne = typePersonne;
    }

    public CategorieClientDto getCategorieClient() {
        return categorieClient;
    }

    public void setCategorieClient(CategorieClientDto categorieClient) {
        this.categorieClient = categorieClient;
    }

    public SousTypeClientDto getSousTypeClient() {
        return sousTypeClient;
    }

    public void setSousTypeClient(SousTypeClientDto sousTypeClient) {
        this.sousTypeClient = sousTypeClient;
    }
}
