package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Blob;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MailSenderDto {
    private String[] recipientEmailMany;
//    private String recipientEmailOne;
    private String subject;
    private String content;
    private String[] fileName;
    private String[] url;
    private byte[] fToByte[];
    private String[] fToBlob;
    private Long idMail;

    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    public String[] getfToBlob() {
        return fToBlob;
    }

    public void setfToBlob(String[] fToBlob) {
        this.fToBlob = fToBlob;
    }

    public byte[][] getfToByte() {
        return fToByte;
    }

    public void setfToByte(byte[][] fToByte) {
        this.fToByte = fToByte;
    }

    public String[] getFileName() {
        return fileName;
    }

    public void setFileName(String[] fileName) {
        this.fileName = fileName;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    public String[] getRecipientEmailMany() {
        return recipientEmailMany;
    }

    public void setRecipientEmailMany(String[] recipientEmailMany) {
        this.recipientEmailMany = recipientEmailMany;
    }

//    public String getRecipientEmailOne() {
//        return recipientEmailOne;
//    }
//
//    public void setRecipientEmailOne(String recipientEmailOne) {
//        this.recipientEmailOne = recipientEmailOne;
//    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
