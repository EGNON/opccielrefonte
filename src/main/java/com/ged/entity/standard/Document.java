package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.crm.CompteRendu;
import com.ged.entity.crm.RDV;
import com.itextpdf.commons.utils.Base64;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Document", schema = "Parametre")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDoc", scope = Document.class)
public class Document extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoc;
    @Basic
    private LocalDateTime dateValidite;
    @Basic
    private LocalDateTime dateRattachement;
    @Basic
    private String chemin;
    @Basic
//    @ColumnDefault("")
    private String nomDoc;
    @Basic
    private String numeroPiece;
    @Basic
    private String extensionDoc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTypeDoc")
    //@JsonBackReference
    private TypeDocument typeDocument;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCR")
    //@JsonBackReference
    private CompteRendu compteRendu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonne")
    //@JsonBackReference
    private Personne personne;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRdv")
    //@JsonBackReference
    private RDV rdv;
    @OneToMany(mappedBy = "document")
    //@JsonManagedReference
    private Set<DocumentMail> documentMails = new HashSet<>();
    @Transient
    private byte[] fToByte;
    @Transient
    private String fToblob;
    public Document() {
    }

    public Document(String chemin, String nomDoc, String extensionDoc, TypeDocument typeDocument) {
        this.chemin = chemin;
        this.nomDoc = nomDoc;
        this.extensionDoc = extensionDoc;
        this.typeDocument = typeDocument;
    }

    public String getfToblob() {
        return fToblob;
    }

    public void setfToblob(String fToblob) {
        this.fToblob = fToblob;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public RDV getRdv() {
        return rdv;
    }

    public void setRdv(RDV rdv) {
        this.rdv = rdv;
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
    }

    public byte[] getfToByte() {
        return fToByte;
    }

    public void setfToByte(byte[] fToByte) {
        this.fToByte = fToByte;
    }

    public LocalDateTime getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(LocalDateTime dateValidite) {
        this.dateValidite = dateValidite;
    }

    public LocalDateTime getDateRattachement() {
        return dateRattachement;
    }

    public void setDateRattachement(LocalDateTime dateRattachement) {
        this.dateRattachement = dateRattachement;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public String getExtensionDoc() {
        return extensionDoc;
    }

    public void setExtensionDoc(String extensionDoc) {
        this.extensionDoc = extensionDoc;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public CompteRendu getCompteRendu() {
        return compteRendu;
    }

    public void setCompteRendu(CompteRendu compteRendu) {
        this.compteRendu = compteRendu;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Set<DocumentMail> getDocumentMails() {
        return documentMails;
    }

    public void setDocumentMails(Set<DocumentMail> documentMails) {
        this.documentMails = documentMails;
    }

    @Override
    public String toString() {
        return "Document [" +
                "idDoc=" + idDoc +
                ", chemin='" + chemin + '\'' +
                ", nomDoc='" + nomDoc + '\'' +
                ']';
    }
}
