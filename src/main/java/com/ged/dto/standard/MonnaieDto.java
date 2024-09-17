package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonnaieDto {
    private String codeMonnaie;
    private String nom;
    private Set<PaysDto> paysDtos = new HashSet<>();

    public MonnaieDto() {

    }

    public MonnaieDto(String codeMonnaie, String nom) {
        this.codeMonnaie = codeMonnaie;
        this.nom = nom;
    }

    public String getCodeMonnaie() {
        return codeMonnaie;
    }

    public void setCodeMonnaie(String codeMonnaie) {
        this.codeMonnaie = codeMonnaie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<PaysDto> getPaysDtos() {
        return paysDtos;
    }

    public void setPaysDtos(Set<PaysDto> paysDtos) {
        this.paysDtos = paysDtos;
    }

    @Override
    public String toString() {
        return "Monnaie codeMonnaie='" + codeMonnaie + "'";
    }
}
