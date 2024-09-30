package com.ged.entity;

import com.ged.entity.security.Utilisateur;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Types;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Base implements Serializable {
    //Champs communs
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
    @ManyToOne
    @JoinColumn(name = "idCreateur")
    private Utilisateur createur;
    private Long idOcc;

    public Utilisateur getCreateur() {
        return createur;
    }

    public void setCreateur(Utilisateur createur) {
        this.createur = createur;
    }

    //Champ Commun Getters and Setters

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

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

    public Boolean getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    /*public byte[] getRowvers() {
        return rowvers;
    }

    public void setRowvers(byte[] rowvers) {
        this.rowvers = rowvers;
    }*/
}
