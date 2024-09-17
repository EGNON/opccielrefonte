package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("OR")
@Table(name = "T_Organisme", schema = "Titre")
public class Organisme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrganisme;

    public Organisme() {
    }

    public Long getIdOrganisme() {
        return idOrganisme;
    }

    public void setIdOrganisme(Long idOrganisme) {
        this.idOrganisme = idOrganisme;
    }
}
