package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_Compartiment", schema = "Titre")
public class Compartiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompartiment;
    private String libelleCompartiment;
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

    public Compartiment() {
    }

    public Compartiment(String libelleCompartiment) {
        this.libelleCompartiment = libelleCompartiment;
    }

    public Long getIdCompartiment() {
        return idCompartiment;
    }

    public void setIdCompartiment(Long idCompartiment) {
        this.idCompartiment = idCompartiment;
    }

    public String getLibelleCompartiment() {
        return libelleCompartiment;
    }

    public void setLibelleCompartiment(String libelleCompartiment) {
        this.libelleCompartiment = libelleCompartiment;
    }
}
