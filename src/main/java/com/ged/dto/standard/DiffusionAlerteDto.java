package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.entity.standard.CleDiffusionAlerte;
import com.ged.dto.lab.TypeCritereDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiffusionAlerteDto {
    private CleDiffusionAlerte idDiffusionAlerte;
    private AlerteDto alerte;
    private ModeleMsgAlerteDto modeleMsgAlerte;
    private PersonneDto personne;
    private String statut;
    private String objet;
    private String Contenu;
    private MailDto mailDto;
    private TypeCritereDto typeCritere;
    private ProtoAlerteDto protoAlerte;
    private Boolean isShown = false;
    private Boolean isRead = false;
    private Integer compteur = 0;

    public DiffusionAlerteDto() {
    }

    public DiffusionAlerteDto(AlerteDto alerte, PersonneDto personneDto, String statut, MailDto mailDto) {
        this.alerte = alerte;
//        this.modeleMsgAlerte = modeleMsgAlerteDto;
        this.personne = personneDto;
        this.statut = statut;
        this.mailDto = mailDto;
    }

    public TypeCritereDto getTypeCritere() {
        return typeCritere;
    }

    public void setTypeCritere(TypeCritereDto typeCritere) {
        this.typeCritere = typeCritere;
    }

    public ProtoAlerteDto getProtoAlerte() {
        return protoAlerte;
    }

    public void setProtoAlerte(ProtoAlerteDto protoAlerte) {
        this.protoAlerte = protoAlerte;
    }

    public CleDiffusionAlerte getIdDiffusionAlerte() {
        return idDiffusionAlerte;
    }

    public void setIdDiffusionAlerte(CleDiffusionAlerte idDiffusionAlerte) {
        this.idDiffusionAlerte = idDiffusionAlerte;
    }

    public TypeCritereDto getTypeCritereDto() {
        return typeCritere;
    }

    public void setTypeCritereDto(TypeCritereDto typeCritereDto) {
        this.typeCritere = typeCritereDto;
    }

    public AlerteDto getAlerte() {
        return alerte;
    }

    public void setAlerte(AlerteDto alerte) {
        this.alerte = alerte;
    }

    public ModeleMsgAlerteDto getModeleMsgAlerte() {
        return modeleMsgAlerte;
    }

    public void setModeleMsgAlerte(ModeleMsgAlerteDto modeleMsgAlerteDto) {
        this.modeleMsgAlerte = modeleMsgAlerteDto;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personneDto) {
        this.personne = personneDto;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Boolean getIsShown() {
        return isShown;
    }

    public void setIsShown(Boolean shown) {
        isShown = shown;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }

    public MailDto getMailDto() {
        return mailDto;
    }

    public void setMailDto(MailDto mailDto) {
        this.mailDto = mailDto;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String contenu) {
        Contenu = contenu;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    @Override
    public String toString() {
        return "DiffusionAlerteDto{" +
                "idDiffusionAlerte=" + idDiffusionAlerte +
                ", alerte=" + alerte +
                ", modeleMsgAlerte=" + modeleMsgAlerte +
                ", destinaire=" + personne +
                ", statut='" + statut + '\'' +
                ", mailDto=" + mailDto +
                ", typeCritere=" + typeCritere +
                ", protoAlerte=" + protoAlerte +
                ", isShown=" + isShown +
                ", isRead=" + isRead +
                '}';
    }
}
