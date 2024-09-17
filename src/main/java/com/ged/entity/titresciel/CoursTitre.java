package com.ged.entity.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TJ_CoursTitre", schema = "Titre")
public class CoursTitre {
    @EmbeddedId
    private CleCoursTitre idCoursTitre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTitre")
    @MapsId("idTitre")
    @JsonIgnore
    private Titre titre;
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
    private LocalDateTime dateCreationServeur;
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    private String userLogin;
    private Long numLigne;
    private Boolean supprimer;
    private byte[] rowvers;
    private Boolean estVerifie1;
    private LocalDateTime dateVerification1;
    private String userLoginVerificateur1;
    private Boolean estVerifie2;
    private LocalDateTime dateVerification2;
    private String userLoginVerificateur2;

    public CleCoursTitre getIdCoursTitre() {
        return idCoursTitre;
    }

    public void setIdCoursTitre(CleCoursTitre idCoursTitre) {
        this.idCoursTitre = idCoursTitre;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

    public byte[] getRowvers() {
        return rowvers;
    }

    public void setRowvers(byte[] rowvers) {
        this.rowvers = rowvers;
    }

    public Boolean getEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(Boolean estVerifie1) {
        this.estVerifie1 = estVerifie1;
    }

    public LocalDateTime getDateVerification1() {
        return dateVerification1;
    }

    public void setDateVerification1(LocalDateTime dateVerification1) {
        this.dateVerification1 = dateVerification1;
    }

    public String getUserLoginVerificateur1() {
        return userLoginVerificateur1;
    }

    public void setUserLoginVerificateur1(String userLoginVerificateur1) {
        this.userLoginVerificateur1 = userLoginVerificateur1;
    }

    public Boolean getEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(Boolean estVerifie2) {
        this.estVerifie2 = estVerifie2;
    }

    public LocalDateTime getDateVerification2() {
        return dateVerification2;
    }

    public void setDateVerification2(LocalDateTime dateVerification2) {
        this.dateVerification2 = dateVerification2;
    }

    public String getUserLoginVerificateur2() {
        return userLoginVerificateur2;
    }

    public void setUserLoginVerificateur2(String userLoginVerificateur2) {
        this.userLoginVerificateur2 = userLoginVerificateur2;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }
}
