package com.ged.dto.crm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeAffectationDto {
    private Long idTypeAffectation;
    private String libelleTypeAffectation;
    @CreationTimestamp
    private LocalDateTime dateCreationServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifServeur;
    @UpdateTimestamp
    private LocalDateTime dateDernModifClient;
    private long numLigne;
    private boolean supprimer;
    private LocalDateTime rowvers;
    private String userLogin;

    public TypeAffectationDto() {
    }

    public TypeAffectationDto(String libelleTypeAffectation) {
        this.libelleTypeAffectation = libelleTypeAffectation;
    }

    public Long getIdTypeAffectation() {
        return idTypeAffectation;
    }

    public void setIdTypeAffectation(Long idTypeAffectation) {
        this.idTypeAffectation = idTypeAffectation;
    }

    public String getLibelleTypeAffectation() {
        return libelleTypeAffectation;
    }

    public void setLibelleTypeAffectation(String libelleTypeAffectation) {
        this.libelleTypeAffectation = libelleTypeAffectation;
    }


    public long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(long numLigne) {
        this.numLigne = numLigne;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public LocalDateTime getRowvers() {
        return rowvers;
    }

    public void setRowvers(LocalDateTime rowvers) {
        this.rowvers = rowvers;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

}
