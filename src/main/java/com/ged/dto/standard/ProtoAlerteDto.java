package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ged.entity.standard.CleProtoAlerte;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProtoAlerteDto {
    private CleProtoAlerte idProtoAlerte;
    private String contenu;
    private AlerteDto alerte;
    private ModeleMsgAlerteDto modeleMsgAlerte;
    private Set<DiffusionAlerteDto> diffusionAlertes = new HashSet<>();
    private Set<PersonnelDto> personnels = new HashSet<>();

    public ProtoAlerteDto() {
    }

    public ProtoAlerteDto(CleProtoAlerte idProtoAlerte, String contenu, AlerteDto alerte, ModeleMsgAlerteDto modeleMsgAlerte) {
        this.idProtoAlerte = idProtoAlerte;
        this.contenu = contenu;
        this.alerte = alerte;
        this.modeleMsgAlerte = modeleMsgAlerte;
    }

    public Set<PersonnelDto> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Set<PersonnelDto> personnels) {
        this.personnels = personnels;
    }

    public Set<DiffusionAlerteDto> getDiffusionAlertes() {
        return diffusionAlertes;
    }

    public void setDiffusionAlertes(Set<DiffusionAlerteDto> diffusionAlertes) {
        this.diffusionAlertes = diffusionAlertes;
    }

    public CleProtoAlerte getIdProtoAlerte() {
        return idProtoAlerte;
    }

    public void setIdProtoAlerte(CleProtoAlerte idProtoAlerte) {
        this.idProtoAlerte = idProtoAlerte;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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

    public void setModeleMsgAlerte(ModeleMsgAlerteDto modeleMsgAlerte) {
        this.modeleMsgAlerte = modeleMsgAlerte;
    }

    @Override
    public String toString() {
        return "ProtoAlerteDto{" +
                "idProtoAlerte=" + idProtoAlerte +
                ", contenu='" + contenu + '\'' +
                ", alerte=" + alerte +
                ", modeleMsgAlerte=" + modeleMsgAlerte +
                ", diffusionAlertes=" + diffusionAlertes +
                ", personnels=" + personnels +
                '}';
    }
}
