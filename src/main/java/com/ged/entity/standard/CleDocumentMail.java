package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleDocumentMail implements Serializable {
    private Long idMail;
    private Long idDoc;

    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleDocumentMail that = (CleDocumentMail) o;
        return Objects.equals(idMail, that.idMail) && Objects.equals(idDoc, that.idDoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMail, idDoc);
    }
}
