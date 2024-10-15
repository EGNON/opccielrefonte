package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleTarificationOrdinaire implements Serializable {
    private String codeRole;
    private String codeClasseTitre;
    private String codePlace;
    private long idDepositaireNew;
    private long idOpcvm;
    private long idRegistraireNew;

    public CleTarificationOrdinaire() {
    }

    public String getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    public String getCodeClasseTitre() {
        return codeClasseTitre;
    }

    public void setCodeClasseTitre(String codeClasseTitre) {
        this.codeClasseTitre = codeClasseTitre;
    }

    public String getCodePlace() {
        return codePlace;
    }

    public void setCodePlace(String codePlace) {
        this.codePlace = codePlace;
    }

    public long getIdDepositaireNew() {
        return idDepositaireNew;
    }

    public void setIdDepositaireNew(long idDepositaireNew) {
        this.idDepositaireNew = idDepositaireNew;
    }

    public long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public long getIdRegistraireNew() {
        return idRegistraireNew;
    }

    public void setIdRegistraireNew(long idRegistraireNew) {
        this.idRegistraireNew = idRegistraireNew;
    }
}
