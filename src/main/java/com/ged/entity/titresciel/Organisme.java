package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
//@DiscriminatorValue("OR")
@Table(name = "T_Organisme", schema = "Titre")
public class Organisme extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrganisme;
    private String siglePersonneMorale;
    private String raisonSociale;
    private String codePays;
    private String codeFormeJuridique;

    public Organisme() {
    }

    public Long getIdOrganisme() {
        return idOrganisme;
    }

    public void setIdOrganisme(Long idOrganisme) {
        this.idOrganisme = idOrganisme;
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
}
