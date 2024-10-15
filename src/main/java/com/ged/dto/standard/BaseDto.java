package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.security.Utilisateur2Dto;
import jakarta.persistence.Column;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDto implements Serializable {
    @CreationTimestamp
    @Column(name = "dateCreationServeur", updatable = false)
    private LocalDateTime dateCreationServeur;
    @UpdateTimestamp
    @Column(name = "dateDernModifServeur")
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    @ColumnDefault("0")
    private Boolean supprimer = false;
    /*@Column(insertable = false, updatable = false)
    @JdbcTypeCode(Types.TIMESTAMP)
    private byte[] rowvers;*/
    private String creerPar;
    private String modifierPar;
    /* @ManyToOne
     @JoinColumn(name = "idCreateur")
     private Utilisateur createur;*/
    private Long idCreateur;
    private Long idOcc;

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

    public Boolean getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public String getCreerPar() {
        return creerPar;
    }

    public void setCreerPar(String creerPar) {
        this.creerPar = creerPar;
    }

    public String getModifierPar() {
        return modifierPar;
    }

    public void setModifierPar(String modifierPar) {
        this.modifierPar = modifierPar;
    }

    public Long getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(Long idCreateur) {
        this.idCreateur = idCreateur;
    }

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }
}
