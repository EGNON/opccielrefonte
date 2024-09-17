package com.ged.entity.opcciel;

import com.ged.entity.standard.PersonneMorale;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
@DiscriminatorValue("SG")
@Table(name = "T_SocieteDeGestion", schema = "Parametre")
public class SocieteDeGestion extends PersonneMorale {
    private String codeSkype;
    private LocalDateTime dateAgrement;
    private String codeAgence;
    private Long typeTeneurCompte;
    private String numeroOrdreTeneur;
    private BigDecimal tauxEpargne ;

    public SocieteDeGestion() {
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

    public String getCodeSkype() {
        return codeSkype;
    }

    public void setCodeSkype(String codeSkype) {
        this.codeSkype = codeSkype;
    }

    public LocalDateTime getDateAgrement() {
        return dateAgrement;
    }

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
}
