package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "T_TypeDocument", schema = "Parametre")
public class TypeDocument extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeDoc;
    @Basic
    private String libelleTypeDoc;
    @OneToMany(mappedBy = "typeDocument")
    //@JsonManagedReference
    private Set<Document> documents = new HashSet<>();

    public TypeDocument() {
    }

    public TypeDocument(Long idTypeDoc, String libelleTypeDoc) {
        this.idTypeDoc = idTypeDoc;
        this.libelleTypeDoc = libelleTypeDoc;
    }

    public Long getIdTypeDoc() {
        return idTypeDoc;
    }

    public void setIdTypeDoc(Long idTypeDoc) {
        this.idTypeDoc = idTypeDoc;
    }

    public String getLibelleTypeDoc() {
        return libelleTypeDoc;
    }

    public void setLibelleTypeDoc(String libelleTypeDoc) {
        this.libelleTypeDoc = libelleTypeDoc;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "TypeDocument{" +
                "idTypeDoc=" + idTypeDoc +
                ", libelleTypeDoc='" + libelleTypeDoc + '\'' +
                '}';
    }
}
