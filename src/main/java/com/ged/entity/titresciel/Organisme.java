package com.ged.entity.titresciel;

import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("OR")
@Table(name = "T_Organisme", schema = "Titre")
public class Organisme extends PersonneMorale {
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
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;

    public Organisme() {
    }

    @Override
    public void setDenomination(String denomination) {
        super.setDenomination(denomination);
    }


//    public LocalDateTime getDateCreationServeur() {
//        return dateCreationServeur;
//    }
//
//    public void setDateCreationServeur(LocalDateTime dateCreationServeur) {
//        this.dateCreationServeur = dateCreationServeur;
//    }
//
//    public LocalDateTime getDateDernModifServeur() {
//        return dateDernModifServeur;
//    }
//
//    public void setDateDernModifServeur(LocalDateTime dateDernModifServeur) {
//        this.dateDernModifServeur = dateDernModifServeur;
//    }
//
//    public LocalDateTime getDateDernModifClient() {
//        return dateDernModifClient;
//    }
//
//    public void setDateDernModifClient(LocalDateTime dateDernModifClient) {
//        this.dateDernModifClient = dateDernModifClient;
//    }
//
//    public long getNumLigne() {
//        return numLigne;
//    }
//
//    public void setNumLigne(long numLigne) {
//        this.numLigne = numLigne;
//    }
//
//    public boolean isSupprimer() {
//        return supprimer;
//    }
//
//    public void setSupprimer(boolean supprimer) {
//        this.supprimer = supprimer;
//    }
//
//    public LocalDateTime getRowvers() {
//        return rowvers;
//    }
//
//    public void setRowvers(LocalDateTime rowvers) {
//        this.rowvers = rowvers;
//    }
//
//    public String getUserLogin() {
//        return userLogin;
//    }
//
//    public void setUserLogin(String userLogin) {
//        this.userLogin = userLogin;
//    }

    //OPCCIEL1

    //FIN
}
