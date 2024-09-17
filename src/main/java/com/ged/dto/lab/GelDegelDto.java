package com.ged.dto.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonneDto;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GelDegelDto {

    private Long idGelDegel;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean estGele;
    private PersonneDto personneDto;

    public GelDegelDto() {
    }

    public GelDegelDto(Long idGelDegel, LocalDateTime dateDebut, boolean estGele, PersonneDto personneDto) {
        this.idGelDegel = idGelDegel;
        this.dateDebut = dateDebut;
        this.estGele = estGele;
        this.personneDto = personneDto;
    }

    public Long getIdGelDegel() {
        return idGelDegel;
    }

    public void setIdGelDegel(Long idGelDegel) {
        this.idGelDegel = idGelDegel;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isEstGele() {
        return estGele;
    }

    public void setEstGele(boolean estGele) {
        this.estGele = estGele;
    }

    public PersonneDto getPersonneDto() {
        return personneDto;
    }

    public void setPersonneDto(PersonneDto personneDto) {
        this.personneDto = personneDto;
    }

    @Override
    public String toString() {
        return "GelDegel [" +
                "idGelDegel=" + idGelDegel +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", estGele=" + estGele +
                ", personne=" + personneDto +
                ']';
    }
}
