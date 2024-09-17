package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_PersonnePhysiquePays",schema = "Parametre")
public class PersonnePhysiquePays extends Base {
    @EmbeddedId
    private ClePersonnePhysiquePays idPersonnePhysiquePays;
    @ManyToOne
    @JoinColumn(name = "idPersonne")
    @MapsId("idPersonne")
    private PersonnePhysique personnePhysique;
    @ManyToOne
    @JoinColumn(name = "idPays")
    @MapsId("idPays")
    //@JsonBackReference
    private Pays pays;

    public PersonnePhysiquePays() {
    }

    public PersonnePhysiquePays(ClePersonnePhysiquePays clePersonnePhysiquePays) {
        this.idPersonnePhysiquePays = clePersonnePhysiquePays;
    }

    public ClePersonnePhysiquePays getIdPersonnePhysiquePays() {
        return idPersonnePhysiquePays;
    }

    public void setIdPersonnePhysiquePays(ClePersonnePhysiquePays idPersonnePhysiquePays) {
        this.idPersonnePhysiquePays = idPersonnePhysiquePays;
    }

    public PersonnePhysique getPersonnePhysique() {
        return personnePhysique;
    }

    public void setPersonnePhysique(PersonnePhysique personnePhysique) {
        this.personnePhysique = personnePhysique;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "PersonnePhysiquePays{" +
                "idPersonnePhysiquePays=" + idPersonnePhysiquePays +
                ", personnePhysique=" + personnePhysique +
                ", pays=" + pays +
                '}';
    }
}
