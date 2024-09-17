package com.ged.entity.titresciel;

import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("DP")
@Table(name = "T_Depositaire", schema = "Titre")
public class Depositaire extends PersonneMorale {
    //OPCCIEL 1
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlace")
    private Place place;
    //FIN

//    @Basic
//    private LocalDateTime dateCreationServeur;
//    @Basic
//    private LocalDateTime dateDernModifServeur;
//    @Basic
//    private LocalDateTime dateDernModifClient;
//    @Basic
//    private long numLigne;
//    @Basic
//    @Column(columnDefinition = "BIT", length = 1)
//    private boolean supprimer;
//    @Basic
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;

    public Depositaire() {
    }

    @Override
    public void setDenomination(String denomination) {
        super.setDenomination(denomination);
    }
    //OPCCIEL1

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }


    //FIN
}
