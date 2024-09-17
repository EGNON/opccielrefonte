package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_NatureEvenement", schema = "Titre")
public class NatureEvenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNatureEvenement;
    private String libelleNatureEvenement;
//    @Basic
//    private LocalDateTime dateCreationServeur;
//    @Basic
//    private LocalDateTime dateDernModifServeur;
//    @Basic
//    private LocalDateTime dateDernModifClient;
//    @Basic
//    private long numLigne;
//    @Basic
//    private boolean supprimer;
//    @CreationTimestamp
//    @Column(name="rowvers", nullable = false, updatable = false, insertable = false)
//    private Timestamp rowvers;
//    @Basic
//    private String userLogin;

    public NatureEvenement() {
    }

    public NatureEvenement(String libelleNatureEvenement) {
        this.libelleNatureEvenement = libelleNatureEvenement;
    }

    public Long getIdNatureEvenement() {
        return idNatureEvenement;
    }

    public void setIdNatureEvenement(Long idNatureEvenement) {
        this.idNatureEvenement = idNatureEvenement;
    }

    public String getLibelleNatureEvenement() {
        return libelleNatureEvenement;
    }

    public void setLibelleNatureEvenement(String libelleNatureEvenement) {
        this.libelleNatureEvenement = libelleNatureEvenement;
    }
}
