package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_EnvoiMail", schema = "Notification")
public class EnvoiMail extends Base {
    @EmbeddedId
    private CleEnvoiMail idEnvoi;
    @ManyToOne
    @MapsId("idPersonne")
    @JoinColumn(name = "idPersonne")
    //@JsonBackReference
    private Personne personne;
    @ManyToOne
    @MapsId("idMail")
    @JoinColumn(name = "idMail")
    //@JsonBackReference
    private Mail mail;

    public EnvoiMail() {
    }

    public EnvoiMail(CleEnvoiMail idEnvoi, Personne personne, Mail mail) {
        this.idEnvoi = idEnvoi;
        this.personne = personne;
        this.mail = mail;
    }

    public CleEnvoiMail getIdEnvoi() {
        return idEnvoi;
    }

    public void setIdEnvoi(CleEnvoiMail idEnvoi) {
        this.idEnvoi = idEnvoi;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
