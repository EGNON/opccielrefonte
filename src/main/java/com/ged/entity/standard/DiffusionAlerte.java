package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ged.entity.Base;
import com.ged.entity.lab.TypeCritere;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TJ_DiffusionAlerte", schema = "Notification")
public class DiffusionAlerte extends Base {
    @EmbeddedId
    private CleDiffusionAlerte idDiffusion;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAlerte")
    @JoinColumn(name = "idAlerte")
    //@JsonBackReference
    private Alerte alerte;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idModele")
    @JoinColumn(name = "idModele")
    //@JsonBackReference
    private ModeleMsgAlerte modeleMsgAlerte;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPersonne")
    @JoinColumn(name = "idPersonne")
    //@JsonBackReference
    private Personne personne;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTypeCritere")
    private TypeCritere typeCritere;
    @Basic
    private String statut;
    private String objet;
    private String contenu;
    private Integer compteur = 0;
    @Column(columnDefinition = "BIT", length = 1)
    private Boolean isShown = false;
    @Column(columnDefinition = "BIT", length = 1)
    private Boolean isRead = false;
    @OneToOne(targetEntity = Mail.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "idMail")
    private Mail mail;

//    @MapsId("CleProtoAlerte")
//    @JoinColumns({
//            @JoinColumn(name="idAlerte", referencedColumnName="idAlerte"),
//            @JoinColumn(name="idModele", referencedColumnName="idModele")
//    })
//    @ManyToOne
//    private ProtoAlerte protoAlerte;

    public DiffusionAlerte() {
    }

    public DiffusionAlerte(
            CleDiffusionAlerte idDiffusion,
            Alerte alerte,
            ModeleMsgAlerte modeleMsgAlerte,
            Personne personne,
            String statut,
            Mail mail) {
        this.idDiffusion = idDiffusion;
        this.alerte = alerte;
        this.modeleMsgAlerte = modeleMsgAlerte;
        this.personne = personne;
        this.statut = statut;
        this.mail = mail;
    }

//    public ProtoAlerte getProtoAlerte() {
//        return protoAlerte;
//    }
//
//    public void setProtoAlerte(ProtoAlerte protoAlerte) {
//        this.protoAlerte = protoAlerte;
//    }

    public CleDiffusionAlerte getIdDiffusion() {
        return idDiffusion;
    }

    public void setIdDiffusion(CleDiffusionAlerte idDiffusion) {
        this.idDiffusion = idDiffusion;
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

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Boolean getIsShown() {
        return isShown;
    }

    public void setIsShown(Boolean shown) {
        isShown = shown;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public TypeCritere getTypeCritere() {
        return typeCritere;
    }

    public void setTypeCritere(TypeCritere typeCritere) {
        this.typeCritere = typeCritere;
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

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    @Override
    public String toString() {
        return "DiffusionAlerte{" +
                "idDiffusion=" + idDiffusion +
                ", alerte=" + alerte +
                ", modeleMsgAlerte=" + modeleMsgAlerte +
                ", destinaire=" + personne +
                ", typeCritere=" + typeCritere +
                ", statut='" + statut + '\'' +
                ", mail=" + mail +
                '}';
    }
}
