package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("RG")
@Table(name = "T_Registraire", schema = "Titre")
public class Registraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistraire;

    public Registraire() {
    }

    public Long getIdRegistraire() {
        return idRegistraire;
    }

    public void setIdRegistraire(Long idRegistraire) {
        this.idRegistraire = idRegistraire;
    }
}
