package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_Place", schema = "Titre")
public class Place {
    @Id
    private String codePlace;

    private String libellePlace;
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

    public Place() {
    }

    public Place(String codePlace, String libellePlace) {
        this.codePlace = codePlace;
        this.libellePlace = libellePlace;
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
}
