package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
//@DiscriminatorValue("EM")
@Table(name = "T_Emetteur", schema = "Titre")
public class Emetteur extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmetteur;

    public Emetteur() {
    }

    public Long getIdEmetteur() {
        return idEmetteur;
    }

    public void setIdEmetteur(Long idEmetteur) {
        this.idEmetteur = idEmetteur;
    }
}
