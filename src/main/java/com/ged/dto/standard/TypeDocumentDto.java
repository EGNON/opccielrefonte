package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeDocumentDto {
    private Long idTypeDoc;
    private String libelleTypeDoc;
//    private Date dateCreationServeur;
//    private Date dateDernModifServeur;
//    private Date dateDernModifClient;
//    private long numLigne;
//    private boolean supprimer;
//    private LocalDateTime rowvers;
//    private String userLogin;
    @JsonIgnore
    private Set<DocumentDto> documentDtos = new HashSet<>();

    public TypeDocumentDto() {
    }

    public TypeDocumentDto(Long idTypeDoc, String libelleTypeDoc, String userLogin) {
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

    public Set<DocumentDto> getDocumentDtos() {
        return documentDtos;
    }

    public void setDocuments(Set<DocumentDto> documents) {
        this.documentDtos = documents;
    }

    public String toString() {
        return "TypeDocument{" +
                "idTypeDoc=" + idTypeDoc +
                ", libelleTypeDoc='" + libelleTypeDoc + '\'' +
                '}';
    }
}
