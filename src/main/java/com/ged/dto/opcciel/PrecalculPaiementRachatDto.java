package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrecalculPaiementRachatDto  {
    private Long idOpcvm;
    private Long idSeance;
    private Long idActionnaire;
    private String nomSigle;
    private String prenomRaisonSociale;
    private BigDecimal montant;

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public String getNomSigle() {
        return nomSigle;
    }

    public void setNomSigle(String nomSigle) {
        this.nomSigle = nomSigle;
    }

    public String getPrenomRaisonSociale() {
        return prenomRaisonSociale;
    }

    public void setPrenomRaisonSociale(String prenomRaisonSociale) {
        this.prenomRaisonSociale = prenomRaisonSociale;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
}
