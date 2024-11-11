package com.ged.entity.opcciel;

import com.ged.entity.Base;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_Ordre", schema = "OrdreDeBourse")
public class Ordre extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdre;
    @ManyToOne()
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;
    private Long idTitre;
    @ManyToOne()
    @JoinColumn(name = "idTitreNew",referencedColumnName = "idTitre")
    private Titre titre;
    private String role;
    private LocalDateTime dateOrdre;
    private String statut;
    @ManyToOne()
    @JoinColumn(name = "idTypeOrdre")
    private TypeOrdre typeOrdre;
    private BigDecimal quantiteLimite;
    @ManyToOne()
    @JoinColumn(name = "idIntervenantNew",referencedColumnName = "idPersonne")
    private Personne personne;
    private LocalDateTime dateEnvoi;
    private LocalDateTime dateLimite;
    private BigDecimal coursLimite;
    private Boolean accepterPerte;
    private Boolean estEnvoye;
    private BigDecimal commissionPlace;
    private BigDecimal commissionSGI;
    private BigDecimal commissionDepositaire;
    private BigDecimal TAF;
    private BigDecimal IRVM;
    private Double interet;
    private BigDecimal plusOuMoinsValue;
    private BigDecimal quantiteExecute;
    private BigDecimal montantNet;
    private BigDecimal montantBrut;
    @Column(length = 8000)
    private String commentaires;
    private Long idOperation;
    private Long idSeance;
    @ManyToOne()
    @JoinColumn(name = "codeNatureOperation")
    private NatureOperation natureOperation;
    private String libelleOperation;

    public Ordre() {
    }

    public Long getIdOrdre() {
        return idOrdre;
    }

    public void setIdOrdre(Long idOrdre) {
        this.idOrdre = idOrdre;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getDateOrdre() {
        return dateOrdre;
    }

    public void setDateOrdre(LocalDateTime dateOrdre) {
        this.dateOrdre = dateOrdre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public TypeOrdre getTypeOrdre() {
        return typeOrdre;
    }

    public void setTypeOrdre(TypeOrdre typeOrdre) {
        this.typeOrdre = typeOrdre;
    }

    public BigDecimal getQuantiteLimite() {
        return quantiteLimite;
    }

    public void setQuantiteLimite(BigDecimal quantiteLimite) {
        this.quantiteLimite = quantiteLimite;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public LocalDateTime getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDateTime dateLimite) {
        this.dateLimite = dateLimite;
    }

    public BigDecimal getCoursLimite() {
        return coursLimite;
    }

    public void setCoursLimite(BigDecimal coursLimite) {
        this.coursLimite = coursLimite;
    }

    public Boolean getAccepterPerte() {
        return accepterPerte;
    }

    public void setAccepterPerte(Boolean accepterPerte) {
        this.accepterPerte = accepterPerte;
    }

    public Boolean getEstEnvoye() {
        return estEnvoye;
    }

    public void setEstEnvoye(Boolean estEnvoye) {
        this.estEnvoye = estEnvoye;
    }

    public BigDecimal getCommissionPlace() {
        return commissionPlace;
    }

    public void setCommissionPlace(BigDecimal commissionPlace) {
        this.commissionPlace = commissionPlace;
    }

    public BigDecimal getCommissionSGI() {
        return commissionSGI;
    }

    public void setCommissionSGI(BigDecimal commissionSGI) {
        this.commissionSGI = commissionSGI;
    }

    public BigDecimal getCommissionDepositaire() {
        return commissionDepositaire;
    }

    public void setCommissionDepositaire(BigDecimal commissionDepositaire) {
        this.commissionDepositaire = commissionDepositaire;
    }

    public BigDecimal getTAF() {
        return TAF;
    }

    public void setTAF(BigDecimal TAF) {
        this.TAF = TAF;
    }

    public BigDecimal getIRVM() {
        return IRVM;
    }

    public void setIRVM(BigDecimal IRVM) {
        this.IRVM = IRVM;
    }

    public Double getInteret() {
        return interet;
    }

    public void setInteret(Double interet) {
        this.interet = interet;
    }

    public BigDecimal getPlusOuMoinsValue() {
        return plusOuMoinsValue;
    }

    public void setPlusOuMoinsValue(BigDecimal plusOuMoinsValue) {
        this.plusOuMoinsValue = plusOuMoinsValue;
    }

    public BigDecimal getQuantiteExecute() {
        return quantiteExecute;
    }

    public void setQuantiteExecute(BigDecimal quantiteExecute) {
        this.quantiteExecute = quantiteExecute;
    }

    public BigDecimal getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(BigDecimal montantNet) {
        this.montantNet = montantNet;
    }

    public BigDecimal getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(BigDecimal montantBrut) {
        this.montantBrut = montantBrut;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public NatureOperation getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperation natureOperation) {
        this.natureOperation = natureOperation;
    }

    public String getLibelleOperation() {
        return libelleOperation;
    }

    public void setLibelleOperation(String libelleOperation) {
        this.libelleOperation = libelleOperation;
    }
}
