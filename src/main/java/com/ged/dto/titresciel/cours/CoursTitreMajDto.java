package com.ged.dto.titresciel.cours;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoursTitreMajDto {
    private Long idTitre;
    private String symbolTitre;
    private String designationTitre;
    private String codePlace;
    private String libellePlace;
    private LocalDateTime dateCours;
    private BigDecimal coursVeille;
    private BigDecimal ouverture;
    private BigDecimal haut;
    private BigDecimal bas;
    private BigDecimal coursSeance;
    private BigDecimal variation;
    private Integer nbreTrans;
    private Integer volTransiger;
    private BigDecimal valTransiger;
    private Integer resteOffre;
    private Integer resteDemande;
    private BigDecimal coursReference;
    private Boolean estValider;

    public CoursTitreMajDto(
            Long idTitre,
            String symbolTitre,
            String designationTitre,
            String codePlace,
            String libellePlace,
            LocalDateTime dateCours,
            BigDecimal coursVeille,
            BigDecimal ouverture,
            BigDecimal haut,
            BigDecimal bas,
            BigDecimal coursSeance,
            BigDecimal variation,
            Integer nbreTrans,
            Integer volTransiger,
            BigDecimal valTransiger,
            Integer resteOffre,
            Integer resteDemande,
            BigDecimal coursReference,
            Boolean estValider) {
        this.idTitre = idTitre;
        this.symbolTitre = symbolTitre;
        this.designationTitre = designationTitre;
        this.codePlace = codePlace;
        this.libellePlace = libellePlace;
        this.dateCours = dateCours;
        this.coursVeille = coursVeille;
        this.ouverture = ouverture;
        this.haut = haut;
        this.bas = bas;
        this.coursSeance = coursSeance;
        this.variation = variation;
        this.nbreTrans = nbreTrans;
        this.volTransiger = volTransiger;
        this.valTransiger = valTransiger;
        this.resteOffre = resteOffre;
        this.resteDemande = resteDemande;
        this.coursReference = coursReference;
        this.estValider = estValider;
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

    public String getCodePlace() {
        return codePlace;
    }

    public void setCodePlace(String codePlace) {
        this.codePlace = codePlace;
    }

    public String getLibellePlace() {
        return libellePlace;
    }

    public void setLibellePlace(String libellePlace) {
        this.libellePlace = libellePlace;
    }

    public LocalDateTime getDateCours() {
        return dateCours;
    }

    public void setDateCours(LocalDateTime dateCours) {
        this.dateCours = dateCours;
    }

    public BigDecimal getCoursVeille() {
        return coursVeille;
    }

    public void setCoursVeille(BigDecimal coursVeille) {
        this.coursVeille = coursVeille;
    }

    public BigDecimal getOuverture() {
        return ouverture;
    }

    public void setOuverture(BigDecimal ouverture) {
        this.ouverture = ouverture;
    }

    public BigDecimal getHaut() {
        return haut;
    }

    public void setHaut(BigDecimal haut) {
        this.haut = haut;
    }

    public BigDecimal getBas() {
        return bas;
    }

    public void setBas(BigDecimal bas) {
        this.bas = bas;
    }

    public BigDecimal getCoursSeance() {
        return coursSeance;
    }

    public void setCoursSeance(BigDecimal coursSeance) {
        this.coursSeance = coursSeance;
    }

    public BigDecimal getVariation() {
        return variation;
    }

    public void setVariation(BigDecimal variation) {
        this.variation = variation;
    }

    public Integer getNbreTrans() {
        return nbreTrans;
    }

    public void setNbreTrans(Integer nbreTrans) {
        this.nbreTrans = nbreTrans;
    }

    public Integer getVolTransiger() {
        return volTransiger;
    }

    public void setVolTransiger(Integer volTransiger) {
        this.volTransiger = volTransiger;
    }

    public BigDecimal getValTransiger() {
        return valTransiger;
    }

    public void setValTransiger(BigDecimal valTransiger) {
        this.valTransiger = valTransiger;
    }

    public Integer getResteOffre() {
        return resteOffre;
    }

    public void setResteOffre(Integer resteOffre) {
        this.resteOffre = resteOffre;
    }

    public Integer getResteDemande() {
        return resteDemande;
    }

    public void setResteDemande(Integer resteDemande) {
        this.resteDemande = resteDemande;
    }

    public BigDecimal getCoursReference() {
        return coursReference;
    }

    public void setCoursReference(BigDecimal coursReference) {
        this.coursReference = coursReference;
    }

    public Boolean getEstValider() {
        return estValider;
    }

    public void setEstValider(Boolean estValider) {
        this.estValider = estValider;
    }
}
