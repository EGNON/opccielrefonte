package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PersonneMoraleDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SocieteDeGestionDto extends PersonneMoraleDto {
    //OPCCIEL 1
    private String codeSkype;
    private LocalDateTime dateAgrement;
    //FIN
    private String codeAgence;
    private Long typeTeneurCompte;
    private String numeroOrdreTeneur;
    private BigDecimal tauxEpargne ;

    public SocieteDeGestionDto() {
    }

    public BigDecimal getTauxEpargne() {
        return tauxEpargne;
    }

    public void setTauxEpargne(BigDecimal tauxEpargne) {
        this.tauxEpargne = tauxEpargne;
    }

    @Override
    public void setDenomination(String denomination) {
        super.setDenomination(denomination);
    }

    @Override
    public LocalDateTime getDateAgrement() {
        return dateAgrement;
    }

    @Override
    public void setDateAgrement(LocalDateTime dateAgrement) {
        this.dateAgrement = dateAgrement;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public Long getTypeTeneurCompte() {
        return typeTeneurCompte;
    }

    public void setTypeTeneurCompte(Long typeTeneurCompte) {
        this.typeTeneurCompte = typeTeneurCompte;
    }

    public String getNumeroOrdreTeneur() {
        return numeroOrdreTeneur;
    }

    public void setNumeroOrdreTeneur(String numeroOrdreTeneur) {
        this.numeroOrdreTeneur = numeroOrdreTeneur;
    }

    //OPCCIEL1

    public String getCodeSkype() {
        return codeSkype;
    }

    public void setCodeSkype(String codeSkype) {
        this.codeSkype = codeSkype;
    }

    //FIN
}
