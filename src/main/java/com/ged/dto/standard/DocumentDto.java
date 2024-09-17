package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.CompteRenduDto;
import com.ged.dto.crm.RDVDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDto {
    private Long idDoc;
    private LocalDateTime dateValidite;
    private LocalDateTime dateRattachement;
    private String chemin;
    private String nomDoc;
    private String numeroPiece;
    @Transient
    private byte[] fToByte;
    private String extensionDoc;
    private TypeDocumentDto typeDocument;
//    @JsonIgnore
    private CompteRenduDto compteRendu;
    private PersonneDto personne;
    private RDVDto rdv;
    private Set<DocumentMailDto> documentMails = new HashSet<>();

    public DocumentDto() {
    }

    public DocumentDto(String chemin, String nomDoc, String extensionDoc, TypeDocumentDto typeDocumentDto) {
        this.chemin = chemin;
        this.nomDoc = nomDoc;
        this.extensionDoc = extensionDoc;
        this.typeDocument = typeDocumentDto;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public byte[] getfToByte() {
        return fToByte;
    }

    public void setfToByte(byte[] fToByte) {
        this.fToByte = fToByte;
    }

    public RDVDto getRdv() {
        return rdv;
    }

    public void setRdv(RDVDto rdvDto) {
        this.rdv = rdvDto;
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
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

    public TypeDocumentDto getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocumentDto typeDocumentDto) {
        this.typeDocument = typeDocumentDto;
    }

    public CompteRenduDto getCompteRendu() {
        return compteRendu;
    }

    public void setCompteRendu(CompteRenduDto compteRendu) {
        this.compteRendu = compteRendu;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    public Set<DocumentMailDto> getDocumentMails() {
        return documentMails;
    }

    public void setDocumentMails(Set<DocumentMailDto> documentMails) {
        this.documentMails = documentMails;
    }

    @Override
    public String toString() {
        return "DocumentDto{" +
                "idDoc=" + idDoc +
                ", dateValidite=" + dateValidite +
                ", dateRattachement=" + dateRattachement +
                ", chemin='" + chemin + '\'' +
                ", nomDoc='" + nomDoc + '\'' +
                ", fToByte=" + Arrays.toString(fToByte) +
                ", extensionDoc='" + extensionDoc + '\'' +
                ", typeDocument=" + typeDocument +
                ", compteRendu=" + compteRendu +
                ", personne=" + personne +
                ", rdv=" + rdv +
                ", documentMails=" + documentMails +
                '}';
    }
}
