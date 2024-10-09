package com.ged.entity.opcciel;

import com.ged.entity.Base;
import com.ged.entity.standard.Personne;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_Nantissement", schema = "Operation")
public class Nantissement extends Base {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOperation;
    private Long idOcc;
    private LocalDateTime dateOperation;
    private Double montant;
    private Double quantite;
    private long idActionnaire;
    @ManyToOne()
    @JoinColumn(name = "idActionnaireNew",referencedColumnName = "idPersonne")
    private Personne personneActionnaire;
    @ManyToOne()
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;
    private Long idSeance;
    private Double coursVL;
    private Double montantFrais;
    private LocalDateTime dateFinPrevue;
    private boolean estLevee;
    private LocalDateTime dateLevee;
    private boolean estVerifie1;
    private LocalDateTime dateVerification1;
    private String userLoginVerificateur1;
    private boolean estVerifie2;
    private LocalDateTime dateVerification2;
    private String userLoginVerificateur2;
    private String observation;
    private String observationNantis;
    private boolean estVerifie1L;
    private LocalDateTime dateVerification1L;
    private String userLoginVerificateur1L;
    private boolean estVerifie2L;
    private LocalDateTime dateVerification2L;
    private String userLoginVerificateur2L;
    private Double montantPret;
    private Long durree;
    private String uniteDuree;
    private String banque;

    public Nantissement() {
    }

    public long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Personne getPersonneActionnaire() {
        return personneActionnaire;
    }

    public void setPersonneActionnaire(Personne personneActionnaire) {
        this.personneActionnaire = personneActionnaire;
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

    public Double getCoursVL() {
        return coursVL;
    }

    public void setCoursVL(Double coursVL) {
        this.coursVL = coursVL;
    }

    public Double getMontantFrais() {
        return montantFrais;
    }

    public void setMontantFrais(Double montantFrais) {
        this.montantFrais = montantFrais;
    }

    public LocalDateTime getDateFinPrevue() {
        return dateFinPrevue;
    }

    public void setDateFinPrevue(LocalDateTime dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }

    public boolean isEstLevee() {
        return estLevee;
    }

    public void setEstLevee(boolean estLevee) {
        this.estLevee = estLevee;
    }

    public LocalDateTime getDateLevee() {
        return dateLevee;
    }

    public void setDateLevee(LocalDateTime dateLevee) {
        this.dateLevee = dateLevee;
    }

    public boolean isEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(boolean estVerifie1) {
        this.estVerifie1 = estVerifie1;
    }

    public LocalDateTime getDateVerification1() {
        return dateVerification1;
    }

    public void setDateVerification1(LocalDateTime dateVerification1) {
        this.dateVerification1 = dateVerification1;
    }

    public String getUserLoginVerificateur1() {
        return userLoginVerificateur1;
    }

    public void setUserLoginVerificateur1(String userLoginVerificateur1) {
        this.userLoginVerificateur1 = userLoginVerificateur1;
    }

    public boolean isEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(boolean estVerifie2) {
        this.estVerifie2 = estVerifie2;
    }

    public LocalDateTime getDateVerification2() {
        return dateVerification2;
    }

    public void setDateVerification2(LocalDateTime dateVerification2) {
        this.dateVerification2 = dateVerification2;
    }

    public String getUserLoginVerificateur2() {
        return userLoginVerificateur2;
    }

    public void setUserLoginVerificateur2(String userLoginVerificateur2) {
        this.userLoginVerificateur2 = userLoginVerificateur2;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getObservationNantis() {
        return observationNantis;
    }

    public void setObservationNantis(String observationNantis) {
        this.observationNantis = observationNantis;
    }

    public boolean isEstVerifie1L() {
        return estVerifie1L;
    }

    public void setEstVerifie1L(boolean estVerifie1L) {
        this.estVerifie1L = estVerifie1L;
    }

    public LocalDateTime getDateVerification1L() {
        return dateVerification1L;
    }

    public void setDateVerification1L(LocalDateTime dateVerification1L) {
        this.dateVerification1L = dateVerification1L;
    }

    public String getUserLoginVerificateur1L() {
        return userLoginVerificateur1L;
    }

    public void setUserLoginVerificateur1L(String userLoginVerificateur1L) {
        this.userLoginVerificateur1L = userLoginVerificateur1L;
    }

    public boolean isEstVerifie2L() {
        return estVerifie2L;
    }

    public void setEstVerifie2L(boolean estVerifie2L) {
        this.estVerifie2L = estVerifie2L;
    }

    public LocalDateTime getDateVerification2L() {
        return dateVerification2L;
    }

    public void setDateVerification2L(LocalDateTime dateVerification2L) {
        this.dateVerification2L = dateVerification2L;
    }

    public String getUserLoginVerificateur2L() {
        return userLoginVerificateur2L;
    }

    public void setUserLoginVerificateur2L(String userLoginVerificateur2L) {
        this.userLoginVerificateur2L = userLoginVerificateur2L;
    }

    public Double getMontantPret() {
        return montantPret;
    }

    public void setMontantPret(Double montantPret) {
        this.montantPret = montantPret;
    }

    public Long getDurree() {
        return durree;
    }

    public void setDurree(Long durree) {
        this.durree = durree;
    }

    public String getUniteDuree() {
        return uniteDuree;
    }

    public void setUniteDuree(String uniteDuree) {
        this.uniteDuree = uniteDuree;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }
}
