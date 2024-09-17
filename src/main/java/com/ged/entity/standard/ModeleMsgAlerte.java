package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ged.entity.Base;
import com.ged.entity.crm.RDV;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_ModeleMsgAlerte", schema = "Notification")
public class ModeleMsgAlerte extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModele;
    @Basic
    private String objet;
    @Basic
    private String contenu;
    @ManyToOne
    @JoinColumn(name = "idTypeModele")
    private TypeModele typeModele;
    private boolean defaut;
    private LocalDateTime dateModeleMsgAlerte;
    @OneToMany(mappedBy = "modeleMsgAlerte")
    private Set<RDV> rdvs=new HashSet<>();
    @OneToMany(mappedBy = "modeleMsgAlerte")
    //@JsonManagedReference
    private Set<DiffusionAlerte> diffusionAlertes = new HashSet<>();

    public ModeleMsgAlerte() {
    }

    public ModeleMsgAlerte(String objet, String contenu) {
        this.objet = objet;
        this.contenu = contenu;
    }

    public Set<RDV> getRdvs() {
        return rdvs;
    }

    public void setRdvs(Set<RDV> rdvs) {
        this.rdvs = rdvs;
    }

    public Long getIdModele() {
        return idModele;
    }

    public void setIdModele(Long idModele) {
        this.idModele = idModele;
    }

    public LocalDateTime getDateModeleMsgAlerte() {
        return dateModeleMsgAlerte;
    }

    public void setDateModeleMsgAlerte(LocalDateTime dateModeleMsgAlerte) {
        this.dateModeleMsgAlerte = dateModeleMsgAlerte;
    }

    public TypeModele getTypeModele() {
        return typeModele;
    }

    public void setTypeModele(TypeModele typeModele) {
        this.typeModele = typeModele;
    }

    public boolean isDefaut() {
        return defaut;
    }

    public void setDefaut(boolean defaut) {
        this.defaut = defaut;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Set<DiffusionAlerte> getDiffusionAlertes() {
        return diffusionAlertes;
    }

    public void setDiffusionAlertes(Set<DiffusionAlerte> diffusionAlertes) {
        this.diffusionAlertes = diffusionAlertes;
    }

    @Override
    public String toString() {
        return "ModeleMsgAlerte [" +
                "idModele=" + idModele +
                ", objet='" + objet + '\'' +
                ']';
    }
}
