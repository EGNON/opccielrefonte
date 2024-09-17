package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMail", scope = Mail.class)
@Table(name = "T_Mail", schema = "Notification")
public class Mail extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMail;
    @Basic
    @Column(length = 2500)
    private String msg;
    @Basic
    private String objet;
    private Date dateEnvoi;
    private Time heureEnvoi;
    @OneToOne(mappedBy = "mail",cascade = CascadeType.ALL)
    private DiffusionAlerte diffusionAlerte;
    @OneToMany(mappedBy = "mail")
    //@JsonManagedReference
    private Set<DocumentMail> documentMails = new HashSet<>();
    @OneToMany(mappedBy = "mail")
    //@JsonManagedReference
    private Set<EnvoiMail> envoiMails = new HashSet<>();
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idRdv")
//    //@JsonBackReference
//    private RDV rdv;
    @ManyToOne()
    @JoinColumn(name = "idModeleMsgAlerte")
    private ModeleMsgAlerte modeleMsgAlerte;

    public Mail() {
    }

    public Mail(String msg, String objet, DiffusionAlerte diffusionAlerte) {
        this.msg = msg;
        this.objet = objet;
        this.diffusionAlerte = diffusionAlerte;
    }

    public ModeleMsgAlerte getModeleMsgAlerte() {
        return modeleMsgAlerte;
    }

    public void setModeleMsgAlerte(ModeleMsgAlerte modeleMsgAlerte) {
        this.modeleMsgAlerte = modeleMsgAlerte;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    public Time getHeureEnvoi() {
        return heureEnvoi;
    }

    public void setHeureEnvoi(Time heureEnvoi) {
        this.heureEnvoi = heureEnvoi;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public DiffusionAlerte getDiffusionAlerte() {
        return diffusionAlerte;
    }

    public void setDiffusionAlerte(DiffusionAlerte diffusionAlerte) {
        this.diffusionAlerte = diffusionAlerte;
    }

    public Set<DocumentMail> getDocumentMails() {
        return documentMails;
    }

    public void setDocumentMails(Set<DocumentMail> documentMails) {
        this.documentMails = documentMails;
    }

    public Set<EnvoiMail> getEnvoiMails() {
        return envoiMails;
    }

    public void setEnvoiMails(Set<EnvoiMail> envoiMails) {
        this.envoiMails = envoiMails;
    }

    @Override
    public String toString() {
        return "Mail [" +
                "idMail=" + idMail +
                ", objet='" + objet + '\'' +
                ", diffusionAlerte=" + diffusionAlerte +
                ']';
    }
}
