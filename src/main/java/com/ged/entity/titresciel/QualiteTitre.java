package com.ged.entity.titresciel;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_QualiteTitre",schema = "Titre")
public class QualiteTitre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQualite;
    private String libelleQualite;
    private String classname;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codeClasseTitre")
    private ClasseTitre classeTitre;

    @OneToMany(
            mappedBy = "qualiteTitre",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private Set<StatutTitre> statutTitres = new HashSet<>();

    public QualiteTitre(){

    }

    public QualiteTitre(String libelleQualite) {
        this.libelleQualite = libelleQualite;
    }

    public Set<StatutTitre> getStatutTitres() {
        return statutTitres;
    }

    public void setStatutTitres(Set<StatutTitre> statutTitres) {
        this.statutTitres = statutTitres;
    }

    public Long getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(Long idQualite) {
        this.idQualite = idQualite;
    }

    public String getLibelleQualite() {
        return libelleQualite;
    }

    public void setLibelleQualite(String libelleQualite) {
        this.libelleQualite = libelleQualite;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public ClasseTitre getClasseTitre() {
        return classeTitre;
    }

    public void setClasseTitre(ClasseTitre classeTitre) {
        this.classeTitre = classeTitre;
    }

    @Override
    public String toString() {
        return "Qualite{" +
                "idQualite=" + idQualite +
                ", libelleQualite='" + libelleQualite + '\'' +
                '}';
    }
}
