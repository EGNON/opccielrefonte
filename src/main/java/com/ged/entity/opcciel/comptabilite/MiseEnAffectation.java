package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_MiseEnAffectation", schema = "Comptabilite")
public class MiseEnAffectation extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMiseEnAffectation;
    private Long idOcc;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;
	private Long idSeance;
	private LocalDateTime dateMiseEnAffectation;
    private String codeExercice;
    private Double resultat;
    private Double regBeneInstAffectation;
    private Double BeneInstAffectation;
    private Double nbrePartEnCirculation;
    private Double coupDivUnitaire;

    public MiseEnAffectation() {
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

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
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
