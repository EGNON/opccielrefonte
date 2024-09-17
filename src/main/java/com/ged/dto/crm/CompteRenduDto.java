package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.standard.AlerteDto;
import com.ged.dto.standard.BaseDto;
import com.ged.dto.standard.DocumentDto;
import com.ged.dto.standard.ProduitDto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompteRenduDto extends BaseDto {
    private Long idCR;
    private LocalDateTime dateCR;
    private LocalTime heureDebCR;
    private LocalTime heureFinCR;
    private String objetCR;
    private String appreciation;
    private String description;
    private LocalDateTime dateProchainRDV;
    private double montantPromesse;
    private double montantRealisation;
    private String promesse;
    private String realisation;
    private LocalDateTime dateEffectivePromesse;
    private ProduitDto produitASouscrire;
    private ProduitDto produitSouscrit;
    private OpcvmDto opcvmASouscrire;
    private OpcvmDto opcvmSouscrit;
    private AlerteDto alerte;
    private RDVDto rdv;
    private Boolean estValide = false;
    private Boolean estRappel = false;
    private String elementRappel;
    private Set<DocumentDto> documents = new HashSet<>();

    public CompteRenduDto() {
    }

    public CompteRenduDto(LocalDateTime dateCR, LocalTime heureDebCR, String objetCR, String appreciation, String description, AlerteDto alerte) {
        this.dateCR = dateCR;
        this.heureDebCR = heureDebCR;
        this.objetCR = objetCR;
        this.appreciation = appreciation;
        this.description = description;
        this.alerte = alerte;
    }

    public OpcvmDto getOpcvmASouscrire() {
        return opcvmASouscrire;
    }

    public void setOpcvmASouscrire(OpcvmDto opcvmASouscrire) {
        this.opcvmASouscrire = opcvmASouscrire;
    }

    public OpcvmDto getOpcvmSouscrit() {
        return opcvmSouscrit;
    }

    public void setOpcvmSouscrit(OpcvmDto opcvmSouscrit) {
        this.opcvmSouscrit = opcvmSouscrit;
    }

    public Long getId()
    {
        return idCR;
    }

    public Long getIdCR() {
        return idCR;
    }

    public void setIdCR(Long idCR) {
        this.idCR = idCR;
    }

    public LocalDateTime getDateCR() {
        return dateCR;
    }

    public void setDateCR(LocalDateTime dateCR) {
        this.dateCR = dateCR;
    }

    public LocalTime getHeureDebCR() {
        return heureDebCR;
    }

    public void setHeureDebCR(LocalTime heureDebCR) {
        this.heureDebCR = heureDebCR;
    }

    public LocalTime getHeureFinCR() {
        return heureFinCR;
    }

    public void setHeureFinCR(LocalTime heureFinCR) {
        this.heureFinCR = heureFinCR;
    }

    public String getObjetCR() {
        return objetCR;
    }

    public void setObjetCR(String objetCR) {
        this.objetCR = objetCR;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateProchainRDV() {
        return dateProchainRDV;
    }

    public void setDateProchainRDV(LocalDateTime dateProchainRDV) {
        this.dateProchainRDV = dateProchainRDV;
    }

    public double getMontantPromesse() {
        return montantPromesse;
    }

    public void setMontantPromesse(double montantPromesse) {
        this.montantPromesse = montantPromesse;
    }

    public double getMontantRealisation() {
        return montantRealisation;
    }

    public void setMontantRealisation(double montantRealisation) {
        this.montantRealisation = montantRealisation;
    }

    public String getPromesse() {
        return promesse;
    }

    public void setPromesse(String promesse) {
        this.promesse = promesse;
    }

    public String getRealisation() {
        return realisation;
    }

    public void setRealisation(String realisation) {
        this.realisation = realisation;
    }

    public LocalDateTime getDateEffectivePromesse() {
        return dateEffectivePromesse;
    }

    public void setDateEffectivePromesse(LocalDateTime dateEffectivePromesse) {
        this.dateEffectivePromesse = dateEffectivePromesse;
    }

    public ProduitDto getProduitASouscrire() {
        return produitASouscrire;
    }

    public void setProduitASouscrire(ProduitDto produitDtoASouscrire) {
        this.produitASouscrire = produitDtoASouscrire;
    }

    public ProduitDto getProduitSouscrit() {
        return produitSouscrit;
    }

    public void setProduitSouscrit(ProduitDto produitDtoSouscrit) {
        this.produitSouscrit = produitDtoSouscrit;
    }

    public AlerteDto getAlerte() {
        return alerte;
    }

    public void setAlerte(AlerteDto alerte) {
        this.alerte = alerte;
    }

    public Set<DocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentDto> documents) {
        this.documents = documents;
    }

    public Boolean getEstValide() {
        return estValide;
    }

    public void setEstValide(Boolean estValide) {
        this.estValide = estValide;
    }

    public Boolean getEstRappel() {
        return estRappel;
    }

    public void setEstRappel(Boolean estRappel) {
        this.estRappel = estRappel;
    }

    public String getElementRappel() {
        return elementRappel;
    }

    public void setElementRappel(String elementRappel) {
        this.elementRappel = elementRappel;
    }

    public RDVDto getRdv() {
        return rdv;
    }

    public void setRdv(RDVDto rdv) {
        this.rdv = rdv;
    }

    @Override
    public String toString() {
        return "CompteRenduDto{" +
                "idCR=" + idCR +
                ", dateCR=" + dateCR +
                ", heureDebCR=" + heureDebCR +
                ", heureFinCR=" + heureFinCR +
                ", objetCR='" + objetCR + '\'' +
                ", appreciation='" + appreciation + '\'' +
                ", description='" + description + '\'' +
                ", dateProchainRDV=" + dateProchainRDV +
                ", montantPromesse=" + montantPromesse +
                ", montantRealisation=" + montantRealisation +
                ", promesse='" + promesse + '\'' +
                ", realisation='" + realisation + '\'' +
                ", dateEffectivePromesse=" + dateEffectivePromesse +
                ", produitASouscrire=" + produitASouscrire +
                ", produitSouscrit=" + produitSouscrit +
                ", alerte=" + alerte +
                ", rdv=" + rdv +
                ", documents=" + documents +
                '}';
    }
}
