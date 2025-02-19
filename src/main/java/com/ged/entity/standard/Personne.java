package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.crm.Degre;
import com.ged.entity.lab.GelDegel;
import com.ged.entity.standard.revuecompte.CategorieClient;
import com.ged.entity.standard.revuecompte.SousTypeClient;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.*;

@SuperBuilder
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "typePersonne")
@Table(name = "T_Personne", schema = "Parametre")
/*@Indexed({
        @Index(columnList = "category_id"),
        @Index(columnList = "name, price")
})*/
public class Personne extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq")*/
    private Long idPersonne;
    private Long idOcc;
    @Column(insertable = false,updatable = false)
    private String typePersonne;
    private String denomination;
    private String statutCompte;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModeEtablissement")
    private ModeEtablissement modeEtablissement2;
    private String nomContact;
    private String prenomContact;
    private String telContact;
    private String emailContact;
    private String titreContact;
    private String numeroCpteDeposit;
    //OPCCIEL1
   private String libelleTypePersonne;
    /*private String CodePays;
   private String CodeLangue;
   @Column(columnDefinition="BIT")
   private Boolean EstActifPersonne = false;
   private String codeCategorieClient;
   private String codeSousTypeClient;*/
    @Column(nullable =true)
    private Long numOrdre=0L;
    //FIN OPCCIEL1
    @Column(columnDefinition="BIT")
    private Boolean estsgi = false;
    @Column(columnDefinition="BIT")
    private Boolean estGele = false;
    @Column(columnDefinition="BIT")
    private Boolean ppe1 = false;
    @Column(columnDefinition="BIT")
    private Boolean ppe2 = false;
    @Column(columnDefinition="BIT")
    private Boolean ppe3 = false;
    @Column(columnDefinition="BIT")
    private Boolean ppe4 = false;
    @Column(columnDefinition="BIT")
    private Boolean estConvertie;
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
            /*cascade = {CascadeType.PERSIST, CascadeType.MERGE},*/
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
    private Boolean estExpose=false;
    @Column(columnDefinition="BIT")
    private Boolean estJuge=false;
    @Column(columnDefinition="BIT")
    private Boolean estSanctionNationale = false;
    @Column(columnDefinition="BIT")
    private Boolean estSanctionOnusienne = false;

    public Personne() {
    }

    public Personne(String ifu, String mobile1, String mobile2, String fixe1, String fixe2, String bp, String emailPerso, String emailPro, String domicile, String numeroPiece, String typePiece, LocalDateTime dateExpirationPiece, String modeEtablissement) {
        this.ifu = ifu;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.fixe1 = fixe1;
        this.fixe2 = fixe2;
        this.bp = bp;
        this.emailPerso = emailPerso;
        this.emailPro = emailPro;
        this.domicile = domicile;
        this.numeroPiece = numeroPiece;
        this.typePiece = typePiece;
        this.dateExpirationPiece = dateExpirationPiece;
        this.modeEtablissement = modeEtablissement;
    }

    public void ajouterDocument(Document document)
    {
        this.documents.add(document);
        document.setPersonne(this);
    }

    public String getStatutCompte() {
        return statutCompte;
    }

    public void setStatutCompte(String statutCompte) {
        this.statutCompte = statutCompte;
    }

    public CategorieClient getCategorieClient() {
        return categorieClient;
    }

    public void setCategorieClient(CategorieClient categorieClient) {
        this.categorieClient = categorieClient;
    }

    public SousTypeClient getSousTypeClient() {
        return sousTypeClient;
    }

    public void setSousTypeClient(SousTypeClient sousTypeClient) {
        this.sousTypeClient = sousTypeClient;
    }

    public ModeEtablissement getModeEtablissement2() {
        return modeEtablissement2;
    }

    public void setModeEtablissement2(ModeEtablissement modeEtablissement2) {
        this.modeEtablissement2 = modeEtablissement2;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public String getLibelleTypePersonne() {
        return libelleTypePersonne;
    }

    public void setLibelleTypePersonne(String libelleTypePersonne) {
        this.libelleTypePersonne = libelleTypePersonne;
    }

    public void setEstExpose(Boolean estExpose) {
        this.estExpose = estExpose;
    }

    public void setEstJuge(Boolean estJuge) {
        this.estJuge = estJuge;
    }

    public Personne getDistributeur() {
        return distributeur;
    }

    public void setDistributeur(Personne personne) {
        this.distributeur = personne;
    }

    public Pays getPaysResidence() {
        return paysResidence;
    }

    public void setPaysResidence(Pays paysResidence) {
        this.paysResidence = paysResidence;
    }

    public Quartier getQuartier() {
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public Set<StatutPersonne> getStatutPersonnes() {
        return statutPersonnes;
    }

    public void setStatutPersonnes(Set<StatutPersonne> statutPersonnes) {
        this.statutPersonnes = statutPersonnes;
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

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
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

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Degre getDegre() {
        return degre;
    }

    public void setDegre(Degre degre) {
        this.degre = degre;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<EnvoiMail> getEnvoiMails() {
        return envoiMails;
    }

    public void setEnvoiMails(Set<EnvoiMail> envoiMails) {
        this.envoiMails = envoiMails;
    }

    public Set<GelDegel> getGelDegels() {
        return gelDegels;
    }

    public void setGelDegels(Set<GelDegel> gelDegels) {
        this.gelDegels = gelDegels;
    }

    public String getNumeroCpteDeposit() {
        return numeroCpteDeposit;
    }

    public void setNumeroCpteDeposit(String numeroCpteDeposit) {
        this.numeroCpteDeposit = numeroCpteDeposit;
    }

    public Set<MessageBox> getMessageBox() {
        return messageBox;
    }

    public void setMessageBox(Set<MessageBox> messageBox) {
        this.messageBox = messageBox;
    }

    public Set<DiffusionAlerte> getDiffusionAlertes() {
        return diffusionAlertes;
    }

    public void setDiffusionAlertes(Set<DiffusionAlerte> diffusionAlertes) {
        this.diffusionAlertes = diffusionAlertes;
    }

    public Boolean isEstConvertie() {
        return estConvertie;
    }

    public void setEstConvertie(Boolean estConvertie) {
        this.estConvertie = estConvertie;
    }

    public Boolean isEstSanctionNationale() {
        return estSanctionNationale;
    }

    public void setEstSanctionNationale(Boolean estSanctionNationale) {
        this.estSanctionNationale = estSanctionNationale;
    }

    public Boolean isEstSanctionOnusienne() {
        return estSanctionOnusienne;
    }

    public Boolean getEstsgi() {
        return estsgi;
    }

    public void setEstsgi(Boolean estsgi) {
        this.estsgi = estsgi;
    }

    public Boolean getEstGele() {
        return estGele;
    }

    public void setEstGele(Boolean estGele) {
        this.estGele = estGele;
    }

    public Long getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(Long numOrdre) {
        this.numOrdre = numOrdre;
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

    public Boolean getEstConvertie() {
        return estConvertie;
    }

    public Boolean getEstExpose() {
        return estExpose;
    }

    public Boolean getEstJuge() {
        return estJuge;
    }

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

    public Boolean getEstSanctionNationale() {
        return estSanctionNationale;
    }

    public Boolean getEstSanctionOnusienne() {
        return estSanctionOnusienne;
    }

    public void setEstSanctionOnusienne(Boolean estSanctionOnusienne) {
        this.estSanctionOnusienne = estSanctionOnusienne;
    }

    public String getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(String typePersonne) {
        this.typePersonne = typePersonne;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "idPersonne=" + idPersonne +
                ", ifu='" + ifu + '\'' +
                '}';
    }
}
