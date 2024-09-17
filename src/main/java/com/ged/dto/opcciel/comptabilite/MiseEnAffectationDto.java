package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiseEnAffectationDto {

    private Long idMiseEnAffectation;
    private Long idOcc;
    private OpcvmDto opcvm;
	private Long idSeance;
	private LocalDateTime dateMiseEnAffectation;
    private String codeExercice;
    private Double resultat;
    private Double regBeneInstAffectation;
    private Double BeneInstAffectation;
    private Double nbrePartEnCirculation;
    private Double coupDivUnitaire;

    public MiseEnAffectationDto() {
    }

    public Long getIdMiseEnAffectation() {
        return idMiseEnAffectation;
    }

    public void setIdMiseEnAffectation(Long idMiseEnAffectation) {
        this.idMiseEnAffectation = idMiseEnAffectation;
    }

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public LocalDateTime getDateMiseEnAffectation() {
        return dateMiseEnAffectation;
    }

    public void setDateMiseEnAffectation(LocalDateTime dateMiseEnAffectation) {
        this.dateMiseEnAffectation = dateMiseEnAffectation;
    }

    public String getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(String codeExercice) {
        this.codeExercice = codeExercice;
    }

    public Double getResultat() {
        return resultat;
    }

    public void setResultat(Double resultat) {
        this.resultat = resultat;
    }

    public Double getRegBeneInstAffectation() {
        return regBeneInstAffectation;
    }

    public void setRegBeneInstAffectation(Double regBeneInstAffectation) {
        this.regBeneInstAffectation = regBeneInstAffectation;
    }

    public Double getBeneInstAffectation() {
        return BeneInstAffectation;
    }

    public void setBeneInstAffectation(Double beneInstAffectation) {
        BeneInstAffectation = beneInstAffectation;
    }

    public Double getNbrePartEnCirculation() {
        return nbrePartEnCirculation;
    }

    public void setNbrePartEnCirculation(Double nbrePartEnCirculation) {
        this.nbrePartEnCirculation = nbrePartEnCirculation;
    }

    public Double getCoupDivUnitaire() {
        return coupDivUnitaire;
    }

    public void setCoupDivUnitaire(Double coupDivUnitaire) {
        this.coupDivUnitaire = coupDivUnitaire;
    }
}
