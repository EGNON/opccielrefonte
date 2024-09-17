package com.ged.entity.crm;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Indicateur", schema = "Parametre")
public class Indicateur extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIndicateur;
    @Basic
    @Column(unique = true)
    private String code;
    @Basic
    private String libelle;
    @OneToMany(mappedBy = "indicateur")
    //@JsonManagedReference
    private Set<DetailObjectif> detailObjectifs = new HashSet<>();
    @OneToMany(mappedBy = "indicateur")
    //@JsonManagedReference
    private Set<ObjectifAffecte> objectifAffectes;

    public Indicateur() {
    }

    public Indicateur(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdIndicateur() {
        return idIndicateur;
    }

    public void setIdIndicateur(Long idIndicateur) {
        this.idIndicateur = idIndicateur;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<DetailObjectif> getDetailObjectifs() {
        return detailObjectifs;
    }

    public void setDetailObjectifs(Set<DetailObjectif> detailObjectifs) {
        this.detailObjectifs = detailObjectifs;
    }

    public Set<ObjectifAffecte> getObjectifAffectes() {
        return objectifAffectes;
    }

    public void setObjectifAffectes(Set<ObjectifAffecte> objectifAffectes) {
        this.objectifAffectes = objectifAffectes;
    }

    @Override
    public String toString() {
        return "Indicateur [" +
                "idIndicateur=" + idIndicateur +
                ", libelle='" + libelle + '\'' +
                ']';
    }
}
