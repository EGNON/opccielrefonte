package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Compte", schema = "Tarification")
public class Compte extends Base {
    @EmbeddedId
    private CleCompte idCompte;
    @ManyToOne
    @JoinColumn(name = "idRegistraireNew")
//    @MapsId("idRegistraireNew")
    private Registraire registraire;
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

    public Registraire getRegistraire() {
        return registraire;
    }

    public void setRegistraire(Registraire registraire) {
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
