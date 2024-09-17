package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_FormeJuridique", schema = "Titre")
public class FormeJuridique extends Base {
    @Id
    private String codeFormeJuridique;

    private String libelleFormeJuridique;

    public FormeJuridique() {
    }

    public FormeJuridique(String codeFormeJuridique, String libelleFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
        this.libelleFormeJuridique = libelleFormeJuridique;
    }

    public String getCodeFormeJuridique() {
        return codeFormeJuridique;
    }

    public void setCodeFormeJuridique(String codeFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
    }

    public String getLibelleFormeJuridique() {
        return libelleFormeJuridique;
    }

    public void setLibelleFormeJuridique(String libelleFormeJuridique) {
        this.libelleFormeJuridique = libelleFormeJuridique;
    }
}
