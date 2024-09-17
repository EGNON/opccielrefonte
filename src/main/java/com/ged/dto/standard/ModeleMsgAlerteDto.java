package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.crm.RDVDto;
import com.ged.entity.crm.RDV;
import com.ged.entity.standard.TypeModele;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeleMsgAlerteDto {
    private Long idModele;
    private String objet;
    private String contenu;
    private Set<DiffusionAlerteDto> diffusionAlerteDtos = new HashSet<>();
    private TypeModeleDto typeModele;
    private boolean defaut;
    private LocalDateTime dateModeleMsgAlerte;
    private Set<RDVDto> rdvs=new HashSet<>();
    public ModeleMsgAlerteDto() {
    }

    public ModeleMsgAlerteDto(String objet, String contenu) {
        this.objet = objet;
        this.contenu = contenu;
    }

    public Set<RDVDto> getRdvs() {
        return rdvs;
    }

    public void setRdvs(Set<RDVDto> rdvs) {
        this.rdvs = rdvs;
    }

    public Long getIdModele() {
        return idModele;
    }

    public void setIdModele(Long idModele) {
        this.idModele = idModele;
    }

    public LocalDateTime getDateModeleMsgAlerte() {
        return dateModeleMsgAlerte;
    }

    public void setDateModeleMsgAlerte(LocalDateTime dateModeleMsgAlerte) {
        this.dateModeleMsgAlerte = dateModeleMsgAlerte;
    }

    public TypeModeleDto getTypeModele() {
        return typeModele;
    }

    public void setTypeModele(TypeModeleDto typeModele) {
        this.typeModele = typeModele;
    }

    public boolean isDefaut() {
        return defaut;
    }

    public void setDefaut(boolean defaut) {
        this.defaut = defaut;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Set<DiffusionAlerteDto> getDiffusionAlerteDtos() {
        return diffusionAlerteDtos;
    }

    public void setDiffusionAlerteDtos(Set<DiffusionAlerteDto> diffusionAlerteDtos) {
        this.diffusionAlerteDtos = diffusionAlerteDtos;
    }

    @Override
    public String toString() {
        return "ModeleMsgAlerte [" +
                "idModele=" + idModele +
                ", objet='" + objet + '\'' +
                ']';
    }
}
