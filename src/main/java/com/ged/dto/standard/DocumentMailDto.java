package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.entity.standard.CleDocumentMail;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentMailDto {
    private CleDocumentMail idDocumentMail;
    private MailDto mailDto;

    private DocumentDto documentDto;

    public DocumentMailDto() {
    }

    public DocumentMailDto(MailDto mailDto, DocumentDto documentDto) {
        this.mailDto = mailDto;
        this.documentDto = documentDto;
    }

    public CleDocumentMail getIdDocumentMail() {
        return idDocumentMail;
    }

    public void setIdDocumentMail(CleDocumentMail idDocumentMail) {
        this.idDocumentMail = idDocumentMail;
    }

    public MailDto getMailDto() {
        return mailDto;
    }

    public void setMailDto(MailDto mailDto) {
        this.mailDto = mailDto;
    }

    public DocumentDto getDocumentDto() {
        return documentDto;
    }

    public void setDocumentDto(DocumentDto documentDto) {
        this.documentDto = documentDto;
    }
}
