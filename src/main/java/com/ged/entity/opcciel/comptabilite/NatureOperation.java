package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_NatureOperation", schema = "Comptabilite")
public class NatureOperation extends Base {
    @Id
    private String codeNatureOperation;
    private String libelleNatureOperation;
    private String userLogin;
    @ManyToOne()
    @JoinColumn(name = "codeTypeOperation")
    private TypeOperation typeOperation;
    @ManyToOne()
    @JoinColumn(name = "codeJournal")
    private Journal journal;

    public NatureOperation() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
    }

    public String getLibelleNatureOperation() {
        return libelleNatureOperation;
    }

    public void setLibelleNatureOperation(String libelleNatureOperation) {
        this.libelleNatureOperation = libelleNatureOperation;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
