package com.ged.entity.titresciel;

import com.ged.entity.standard.PersonneMorale;

import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("RG")
@Table(name = "T_Registraire", schema = "Titre")
public class Registraire extends PersonneMorale {
    //OPCCIEL 1

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
//
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;

    public Registraire() {
    }

    @Override
    public void setDenomination(String denomination) {
        super.setDenomination(denomination);
    }

    //OPCCIEL1

    //FIN
}
