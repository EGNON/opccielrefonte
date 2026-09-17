package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("EM")
@Table(name = "T_Emetteur", schema = "Titre")
//public class Emetteur extends PersonneMorale {
public class Emetteur extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmetteur;
    private Long idPersonne;
    private String siglePersonneMorale;
    private String raisonSociale;
    public Emetteur() {
    }

    public String getSiglePersonneMorale() {
        return siglePersonneMorale;
    }

    public void setSiglePersonneMorale(String siglePersonneMorale) {
        this.siglePersonneMorale = siglePersonneMorale;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Long getIdEmetteur() {
        return idEmetteur;
    }

    public void setIdEmetteur(Long idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }
}
