package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TJ_ProtoAlerte", schema = "Notification")
public class ProtoAlerte extends Base {
    @EmbeddedId
    private CleProtoAlerte idProtoAlerte;
    private String contenu;
    @ManyToOne
    @MapsId("idAlerte")
    @JoinColumn(name = "idAlerte")
    //@JsonBackReference
    private Alerte alerte;
    @ManyToOne
    @MapsId("idModele")
    @JoinColumn(name = "idModele")
    private ModeleMsgAlerte modeleMsgAlerte;
    @Transient
    private Set<Personnel> personnels = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protoAlerte")
//    private Set<DiffusionAlerte> diffusionAlertes = new HashSet<>();

    public ProtoAlerte() {
    }

    public ProtoAlerte(CleProtoAlerte idProtoAlerte, String contenu, Alerte alerte, ModeleMsgAlerte modeleMsgAlerte) {
        this.idProtoAlerte = idProtoAlerte;
        this.contenu = contenu;
        this.alerte = alerte;
        this.modeleMsgAlerte = modeleMsgAlerte;
    }

    public Set<Personnel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Set<Personnel> personnels) {
        this.personnels = personnels;
    }

//    public void ajouterDiffusionAlerte(DiffusionAlerte diffusionAlerte)
//    {
//        this.diffusionAlertes.add(diffusionAlerte);
//        diffusionAlerte.setProtoAlerte(this);
//    }
//
//    public Set<DiffusionAlerte> getDiffusionAlertes() {
//        return diffusionAlertes;
//    }

//    public void setDiffusionAlertes(Set<DiffusionAlerte> diffusionAlertes) {
//        this.diffusionAlertes = diffusionAlertes;
//    }

    public CleProtoAlerte getIdProtoAlerte() {
        return idProtoAlerte;
    }

    public void setIdProtoAlerte(CleProtoAlerte idProtoAlerte) {
        this.idProtoAlerte = idProtoAlerte;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Alerte getAlerte() {
        return alerte;
    }

    public void setAlerte(Alerte alerte) {
        this.alerte = alerte;
    }

    public ModeleMsgAlerte getModeleMsgAlerte() {
        return modeleMsgAlerte;
    }

    public void setModeleMsgAlerte(ModeleMsgAlerte modeleMsgAlerte) {
        this.modeleMsgAlerte = modeleMsgAlerte;
    }

    @Override
    public String toString() {
        return "ProtoAlerte{" +
                "idProtoAlerte=" + idProtoAlerte +
                ", contenu='" + contenu + '\'' +
                ", alerte=" + alerte +
                ", modeleMsgAlerte=" + modeleMsgAlerte +
                ", personnels=" + personnels +
                '}';
    }
}
