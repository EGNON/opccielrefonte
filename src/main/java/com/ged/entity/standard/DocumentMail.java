package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_DocumentMail", schema = "Notification")
public class DocumentMail extends Base {
    @EmbeddedId
    private CleDocumentMail id;
    @ManyToOne
    @MapsId("idMail")
    @JoinColumn(name = "idMail")
    //@JsonBackReference
    private Mail mail;
    @ManyToOne
    @MapsId("idDoc")
    @JoinColumn(name = "idDoc")
    //@JsonBackReference
    private Document document;

    public DocumentMail() {
    }

    public DocumentMail(CleDocumentMail id, Mail mail, Document document) {
        this.id = id;
        this.mail = mail;
        this.document = document;
    }

    public CleDocumentMail getId() {
        return id;
    }

    public void setId(CleDocumentMail id) {
        this.id = id;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
