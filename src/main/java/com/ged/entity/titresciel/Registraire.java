package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("RG")
@Table(name = "T_Registraire", schema = "Titre")
//public class Registraire extends PersonneMorale {
public class Registraire extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistraire;
    private String siglePersonneMorale;
    private String raisonSociale;
    private String codePays;
    private String codeFormeJuridique;
    private Long idPersonne;

    public Registraire() {
    }

    public Long getIdRegistraire() {
        return idRegistraire;
    }

    public void setIdRegistraire(Long idRegistraire) {
        this.idRegistraire = idRegistraire;
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

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getCodeFormeJuridique() {
        return codeFormeJuridique;
    }

    public void setCodeFormeJuridique(String codeFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }
}
