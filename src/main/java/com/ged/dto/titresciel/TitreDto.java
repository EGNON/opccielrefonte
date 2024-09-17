package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.*;
import com.ged.dto.standard.BaseDto;
import com.ged.dto.standard.PaysDto;
import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.dto.standard.SecteurDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TitreDto extends BaseDto {
    private Long idTitre;
    private String typeVM;
    private Long idOcc;
    private String symbolTitre;
    private String designationTitre;
    private PlaceDto place;
    private PersonneMoraleDto depositaire;
    private TypeEmissionDto typeEmission;
    private TypeTitreDto typeTitre;
    private SecteurDto secteur;
    private CompartimentDto compartiment;
    private NormalAssimileDto normalAssimile;
    private PaysDto pays;
    private PersonneMoraleDto registraire;
    private PersonneMoraleDto emetteur;
    private Integer lotMinimum;
    private BigDecimal nominal;
    private String codeIsin;
    private LocalDateTime dateOuverture;
    private LocalDateTime dateEmission;
    private LocalDateTime dateCloture;
    private Boolean estActif;
    private boolean appliqueFiscaliteLocale;
    private Double tauxFiscaliteLocale;
    private Double tauxFiscalitePays;
    private Boolean estReglementaire;
    private Double borneInferieurFluctuation;
    private Double borneSuperieurFluctuation;
    private Double irvm;
    private String libelleCotation;
//    private Set<CoursTitreDto> cours = new HashSet<>();
    private Set<TableauAmortissementDto> tabAmortissements = new HashSet<>();
    private Set<OpcvmDto> opcvms = new HashSet<>();

    public TitreDto() {
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public String getTypeVM() {
        return typeVM;
    }

    public void setTypeVM(String typeVM) {
        this.typeVM = typeVM;
    }

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
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

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    public PersonneMoraleDto getDepositaire() {
        return depositaire;
    }

    public void setDepositaire(PersonneMoraleDto depositaire) {
        this.depositaire = depositaire;
    }

    public TypeEmissionDto getTypeEmission() {
        return typeEmission;
    }

    public void setTypeEmission(TypeEmissionDto typeEmission) {
        this.typeEmission = typeEmission;
    }

    public TypeTitreDto getTypeTitre() {
        return typeTitre;
    }

    public void setTypeTitre(TypeTitreDto typeTitre) {
        this.typeTitre = typeTitre;
    }

    public SecteurDto getSecteur() {
        return secteur;
    }

    public void setSecteur(SecteurDto secteur) {
        this.secteur = secteur;
    }

    public CompartimentDto getCompartiment() {
        return compartiment;
    }

    public void setCompartiment(CompartimentDto compartiment) {
        this.compartiment = compartiment;
    }

    public NormalAssimileDto getNormalAssimile() {
        return normalAssimile;
    }

    public void setNormalAssimile(NormalAssimileDto normalAssimile) {
        this.normalAssimile = normalAssimile;
    }

    public PaysDto getPays() {
        return pays;
    }

    public void setPays(PaysDto pays) {
        this.pays = pays;
    }

    public PersonneMoraleDto getRegistraire() {
        return registraire;
    }

    public void setRegistraire(PersonneMoraleDto registraire) {
        this.registraire = registraire;
    }

    public PersonneMoraleDto getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(PersonneMoraleDto emetteur) {
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

    public Boolean getEstActif() {
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

    public Boolean getEstReglementaire() {
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

    /*public Set<CoursTitreDto> getCours() {
        return cours;
    }

    public void setCours(Set<CoursTitreDto> cours) {
        this.cours = cours;
    }*/

    public Set<TableauAmortissementDto> getTabAmortissements() {
        return tabAmortissements;
    }

    public void setTabAmortissements(Set<TableauAmortissementDto> tabAmortissements) {
        this.tabAmortissements = tabAmortissements;
    }

    public Set<OpcvmDto> getOpcvms() {
        return opcvms;
    }

    public void setOpcvms(Set<OpcvmDto> opcvms) {
        this.opcvms = opcvms;
    }
}
