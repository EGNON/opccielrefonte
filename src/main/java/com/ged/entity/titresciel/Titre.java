package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeVM")
@Table(name = "T_Titre", schema = "Titre")
public class Titre extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq")*/
    private Long idTitre;
    @Column(insertable = false,updatable = false)
    private String typeVM;
    @Column
    private Long idOcc;
    @Column(name = "symbolTitre")
    private String symbolTitre;
    @Column(name = "designationTitre")
    private String designationTitre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlace")
    private Place place;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDepositaireNew", referencedColumnName = "idPersonne")
    private PersonneMorale depositaire;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeEmission")
    private TypeEmission typeEmission;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeTypeTitre")
    private TypeTitre typeTitre;
    //
    private String libelleSecteurBoursier;
    private String libelleCompartiment;
//    private String codeNormalAssimile;
    private String codePays;
    private Long idEmetteur;
    private Long idRegistraire;
    //
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSecteur")
    private Secteur secteur;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCompartiment")
    private Compartiment compartiment;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeNormalAssimile")
    private NormalAssimile normalAssimile;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPaysNew")
    private Pays pays;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRegistraireNew",referencedColumnName = "idPersonne")
    private PersonneMorale registraire;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmetteurNew",referencedColumnName = "idPersonne")
    private PersonneMorale emetteur;
    @Column(name = "lotMinimum")
    private Integer lotMinimum;
    @Column(name = "nominal")
    private BigDecimal nominal;
    @Column(name = "codeIsin")
    private String codeIsin;
    @Column(name = "dateOuverture")
    private LocalDateTime dateOuverture;
    @Column(name = "dateEmission")
    private LocalDateTime dateEmission;
    @Column(name = "dateCloture")
    private LocalDateTime dateCloture;
    @Column(name = "estActif")
    private Boolean estActif;
    @Column(name = "appliqueFiscaliteLocale")
    private boolean appliqueFiscaliteLocale;
    @Column(name = "tauxFiscaliteLocale")
    private Double tauxFiscaliteLocale;
    @Column(name = "tauxFiscalitePays")
    private Double tauxFiscalitePays;
    @Column(name = "estReglementaire")
    private Boolean estReglementaire;
    @Column(name = "borneInferieurFluctuation")
    private Double borneInferieurFluctuation;
    @Column(name = "borneSuperieurFluctuation")
    private Double borneSuperieurFluctuation;
    @Column(name = "irvm")
    private Double irvm;
    @Column(name = "libelleCotation")
    private String libelleCotation;
    @OneToMany(
            mappedBy = "titre",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CoursTitre> cours = new HashSet<>();

    @OrderBy("numeroEcheance ASC")
    @OneToMany(
            mappedBy = "titre",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TableauAmortissement> tabAmortissements = new HashSet<>();
//    @CreationTimestamp
//    private LocalDateTime dateCreationServeur;
//    @UpdateTimestamp
//    private LocalDateTime dateDernModifServeur;
//    @UpdateTimestamp
//    private LocalDateTime dateDernModifClient;
//    private long numLigne;
//    @Column(columnDefinition = "BIT", length = 1)
//    private boolean supprimer;
//
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;
    /*@OneToMany(mappedBy = "titre", fetch = FetchType.LAZY)
    private Set<Opcvm> opcvms = new HashSet<>();*/

    public Titre() {
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public String getSymbolTitre() {
        return symbolTitre;
    }

    public void setSymbolTitre(String symbolTitre) {
        this.symbolTitre = symbolTitre;
    }

    public String getDesignationTitre() {
        return designationTitre;
    }

    public void setDesignationTitre(String designationTitre) {
        this.designationTitre = designationTitre;
    }

    //

    public String getLibelleSecteurBoursier() {
        return libelleSecteurBoursier;
    }

    public void setLibelleSecteurBoursier(String libelleSecteurBoursier) {
        this.libelleSecteurBoursier = libelleSecteurBoursier;
    }

    public String getLibelleCompartiment() {
        return libelleCompartiment;
    }

    public void setLibelleCompartiment(String libelleCompartiment) {
        this.libelleCompartiment = libelleCompartiment;
    }

    /*public String getCodeNormalAssimile() {
        return codeNormalAssimile;
    }

    public void setCodeNormalAssimile(String codeNormalAssimile) {
        this.codeNormalAssimile = codeNormalAssimile;
    }*/

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public Long getIdEmetteur() {
        return idEmetteur;
    }

    public void setIdEmetteur(Long idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public Long getIdRegistraire() {
        return idRegistraire;
    }

    public void setIdRegistraire(Long idRegistraire) {
        this.idRegistraire = idRegistraire;
    }

    //

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public PersonneMorale getDepositaire() {
        return depositaire;
    }

    public void setDepositaire(PersonneMorale depositaire) {
        this.depositaire = depositaire;
    }

    public TypeEmission getTypeEmission() {
        return typeEmission;
    }

    public void setTypeEmission(TypeEmission typeEmission) {
        this.typeEmission = typeEmission;
    }

    public TypeTitre getTypeTitre() {
        return typeTitre;
    }

    public void setTypeTitre(TypeTitre typeTitre) {
        this.typeTitre = typeTitre;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Compartiment getCompartiment() {
        return compartiment;
    }

    public void setCompartiment(Compartiment compartiment) {
        this.compartiment = compartiment;
    }

    public NormalAssimile getNormalAssimile() {
        return normalAssimile;
    }

    public void setNormalAssimile(NormalAssimile normalAssimile) {
        this.normalAssimile = normalAssimile;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public PersonneMorale getRegistraire() {
        return registraire;
    }

    public void setRegistraire(PersonneMorale registraire) {
        this.registraire = registraire;
    }

    public PersonneMorale getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(PersonneMorale emetteur) {
        this.emetteur = emetteur;
    }

    public Integer getLotMinimum() {
        return lotMinimum;
    }

    public void setLotMinimum(Integer lotMinimum) {
        this.lotMinimum = lotMinimum;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public String getCodeIsin() {
        return codeIsin;
    }

    public void setCodeIsin(String codeIsin) {
        this.codeIsin = codeIsin;
    }

    public LocalDateTime getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(LocalDateTime dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public LocalDateTime getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDateTime dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDateTime getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDateTime dateCloture) {
        this.dateCloture = dateCloture;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public boolean isAppliqueFiscaliteLocale() {
        return appliqueFiscaliteLocale;
    }

    public void setAppliqueFiscaliteLocale(boolean appliqueFiscaliteLocale) {
        this.appliqueFiscaliteLocale = appliqueFiscaliteLocale;
    }

    public Double getTauxFiscaliteLocale() {
        return tauxFiscaliteLocale;
    }

    public void setTauxFiscaliteLocale(Double tauxFiscaliteLocale) {
        this.tauxFiscaliteLocale = tauxFiscaliteLocale;
    }

    public Double getTauxFiscalitePays() {
        return tauxFiscalitePays;
    }

    public void setTauxFiscalitePays(Double tauxFiscalitePays) {
        this.tauxFiscalitePays = tauxFiscalitePays;
    }

    public Boolean isEstReglementaire() {
        return estReglementaire;
    }

    public void setEstReglementaire(Boolean estReglementaire) {
        this.estReglementaire = estReglementaire;
    }

    public Double getBorneInferieurFluctuation() {
        return borneInferieurFluctuation;
    }

    public void setBorneInferieurFluctuation(Double borneInferieurFluctuation) {
        this.borneInferieurFluctuation = borneInferieurFluctuation;
    }

    public Double getBorneSuperieurFluctuation() {
        return borneSuperieurFluctuation;
    }

    public void setBorneSuperieurFluctuation(Double borneSuperieurFluctuation) {
        this.borneSuperieurFluctuation = borneSuperieurFluctuation;
    }

    public Double getIrvm() {
        return irvm;
    }

    public void setIrvm(Double irvm) {
        this.irvm = irvm;
    }

    public String getLibelleCotation() {
        return libelleCotation;
    }

    public void setLibelleCotation(String libelleCotation) {
        this.libelleCotation = libelleCotation;
    }

    /*public Set<Opcvm> getOpcvms() {
        return opcvms;
    }

    public void setOpcvms(Set<Opcvm> opcvms) {
        this.opcvms = opcvms;
    }*/

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

    public Boolean getEstActif() {
        return estActif;
    }

    public Boolean getEstReglementaire() {
        return estReglementaire;
    }

    public String getTypeVM() {
        return typeVM;
    }

    public void setTypeVM(String typeVM) {
        this.typeVM = typeVM;
    }

    public Set<CoursTitre> getCours() {
        return cours;
    }

    public void setCours(Set<CoursTitre> cours) {
        this.cours = cours;
    }

    public Set<TableauAmortissement> getTabAmortissements() {
        return tabAmortissements;
    }

    public void setTabAmortissements(Set<TableauAmortissement> tabAmortissements) {
        this.tabAmortissements = tabAmortissements;
    }
}
