package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Compte", schema = "Tarification")
public class Compte extends Base {
    @EmbeddedId
    private CleCompte idCompte;
    @ManyToOne
    @JoinColumn(name = "idRegistraire")
    @MapsId("idRegistraire")
    private PersonneMorale registraire;
	private String intitule;
    private String typeGestion;

    public Compte() {
    }

    public CleCompte getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(CleCompte idCompte) {
        this.idCompte = idCompte;
    }

    public PersonneMorale getRegistraire() {
        return registraire;
    }

    public void setRegistraire(PersonneMorale registraire) {
        this.registraire = registraire;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getTypeGestion() {
        return typeGestion;
    }

    public void setTypeGestion(String typeGestion) {
        this.typeGestion = typeGestion;
    }
}
