package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_ClasseTitre", schema = "Titre")
public class ClasseTitre extends Base {
    @Id
    private String codeClasseTitre;
    private String libelleClasseTitre;
    @OneToMany(mappedBy = "classeTitre")
    private Set<QualiteTitre> qualiteTitres = new HashSet<>();

    public ClasseTitre() {
    }

    public ClasseTitre(String codeClasseTitre, String libelleClasseTitre) {
        this.codeClasseTitre = codeClasseTitre;
        this.libelleClasseTitre = libelleClasseTitre;
    }

    public String getCodeClasseTitre() {
        return codeClasseTitre;
    }

    public void setCodeClasseTitre(String codeClasseTitre) {
        this.codeClasseTitre = codeClasseTitre;
    }

    public String getLibelleClasseTitre() {
        return libelleClasseTitre;
    }

    public void setLibelleClasseTitre(String libelleClasseTitre) {
        this.libelleClasseTitre = libelleClasseTitre;
    }

    public Set<QualiteTitre> getQualiteTitres() {
        return qualiteTitres;
    }

    public void setQualiteTitres(Set<QualiteTitre> qualiteTitres) {
        this.qualiteTitres = qualiteTitres;
    }
}
