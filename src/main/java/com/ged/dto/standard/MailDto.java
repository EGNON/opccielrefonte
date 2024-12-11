package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.RDVDto;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MailDto {
    private Long idMail;
    private String msg;
    private String objet;
    private Date dateEnvoi;
    private Time heureEnvoi;

    private DiffusionAlerteDto diffusionAlerteDto;
    private Set<DocumentMailDto> documentMailDtos = new HashSet<>();
    private Set<EnvoiMailDto> envoiMailDtos = new HashSet<>();
    private ModeleMsgAlerteDto modeleMsgAlerteDto;
    public MailDto() {
    }

    public MailDto(String msg, String objet, DiffusionAlerteDto diffusionAlerte) {
        this.msg = msg;
        this.objet = objet;
        this.diffusionAlerteDto = diffusionAlerte;
    }

    public ModeleMsgAlerteDto getModeleMsgAlerteDto() {
        return modeleMsgAlerteDto;
    }

    public void setModeleMsgAlerteDto(ModeleMsgAlerteDto modeleMsgAlerteDto) {
        this.modeleMsgAlerteDto = modeleMsgAlerteDto;
    }

    public Time getHeureEnvoi() {
        return heureEnvoi;
    }

    public void setHeureEnvoi(Time heureEnvoi) {
        this.heureEnvoi = heureEnvoi;
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

    public DiffusionAlerteDto getDiffusionAlerteDto() {
        return diffusionAlerteDto;
    }

    public void setDiffusionAlerteDto(DiffusionAlerteDto diffusionAlerte) {
        this.diffusionAlerteDto = diffusionAlerte;
    }

    public Set<DocumentMailDto> getDocumentMailDtos() {
        return documentMailDtos;
    }

    public void setDocumentMailDtos(Set<DocumentMailDto> documentMails) {
        this.documentMailDtos = documentMails;
    }

    public Set<EnvoiMailDto> getEnvoiMailDtos() {
        return envoiMailDtos;
    }

    public void setEnvoiMailDtos(Set<EnvoiMailDto> envoiMails) {
        this.envoiMailDtos = envoiMails;
    }

    @Override
    public String toString() {
        return "Mail [" +
                "idMail=" + idMail +
                ", objet='" + objet + '\'' +
                ", diffusionAlerte=" + diffusionAlerteDto +
                ']';
    }
}
