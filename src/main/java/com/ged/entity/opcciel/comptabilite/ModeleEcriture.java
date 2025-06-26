package com.ged.entity.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.entity.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "T_ModeleEcriture", schema = "Comptabilite")
public class ModeleEcriture extends Base {
    @Id
    private String codeModeleEcriture;
	private String libelleModeleEcriture;
	private String userLogin;
    @OneToMany(mappedBy = "modeleEcriture")
    private Set<ModeleEcritureFormule> modeleEcritureFormules;
//    @OneToMany(mappedBy = "modeleEcriture")
//    private Set<ModeleEcritureNatureOperation> modeleEcritureNatureOperations;

    public ModeleEcriture() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getCodeModeleEcriture() {
        return codeModeleEcriture;
    }

    public void setCodeModeleEcriture(String codeModeleEcriture) {
        this.codeModeleEcriture = codeModeleEcriture;
    }

    public String getLibelleModeleEcriture() {
        return libelleModeleEcriture;
    }

    public void setLibelleModeleEcriture(String libelleModeleEcriture) {
        this.libelleModeleEcriture = libelleModeleEcriture;
    }

    public Set<ModeleEcritureFormule> getModeleEcritureFormules() {
        return modeleEcritureFormules;
    }

    public void setModeleEcritureFormules(Set<ModeleEcritureFormule> modeleEcritureFormules) {
        this.modeleEcritureFormules = modeleEcritureFormules;
    }

}
