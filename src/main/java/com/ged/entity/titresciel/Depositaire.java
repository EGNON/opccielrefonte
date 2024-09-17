package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("DP")
@Table(name = "T_Depositaire", schema = "Titre")
public class Depositaire extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepositaire;
    //OPCCIEL 1
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlace")
    private Place place;

    public Depositaire() {
    }

    public Long getIdDepositaire() {
        return idDepositaire;
    }

    public void setIdDepositaire(Long idDepositaire) {
        this.idDepositaire = idDepositaire;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
