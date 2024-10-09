package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_ClassificationOPC", schema = "Titre")
public class ClassificationOPC {
    @Id
    @Column(name = "codeClassificationOPC")
    private String codeClassification;
    @Column(name = "libelleClassificationOPC")
    private String libelleClassification;
    /*@OneToMany(mappedBy = "classification", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<Opcvm> opcvms = new HashSet<>();*/

    public ClassificationOPC() {
    }

    public ClassificationOPC(String codeClassification, String libelleClassification) {
        this.codeClassification = codeClassification;
        this.libelleClassification = libelleClassification;
    }

    public String getCodeClassification() {
        return codeClassification;
    }

    public void setCodeClassification(String codeClassification) {
        this.codeClassification = codeClassification;
    }

    public String getLibelleClassification() {
        return libelleClassification;
    }

    public void setLibelleClassification(String libelleClassification) {
        this.libelleClassification = libelleClassification;
    }

    /*public Set<Opcvm> getOpcvms() {
        return opcvms;
    }

    public void setOpcvms(Set<Opcvm> opcvms) {
        this.opcvms = opcvms;
    }*/
}
