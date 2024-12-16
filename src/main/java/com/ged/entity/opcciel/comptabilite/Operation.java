package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeOp", discriminatorType = DiscriminatorType.STRING)
@Table(name = "T_Operation", schema = "Comptabilite")
public class Operation extends Base implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOperation;
    private Long idOcc;
    @ManyToOne
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;
    private Long idActionnaire;
    @Column(insertable = false,updatable = false)
    private String typeOp;
    @ManyToOne
    @JoinColumn(name = "idActionnaireNew",referencedColumnName = "idPersonne")
    private Personne actionnaire;
    private Long idTitre;
    @ManyToOne
    @JoinColumn(name = "idTitreNew",referencedColumnName = "idTitre")
    private Titre titre;
    private Long idTransaction;
    @ManyToOne
    @JoinColumn(name = "idTransactionNew",referencedColumnName = "idTransaction")
    private Transaction transaction;
    private Long idSeance;
    @ManyToOne
    @JoinColumn(name = "codeNatureOperation")
    private NatureOperation natureOperation;
    private LocalDateTime dateOperation;
    private String libelleOperation;
    private LocalDateTime dateSaisie;
    private LocalDateTime datePiece;
    private LocalDateTime dateValeur;
    private String referencePiece;
    private BigDecimal montant;
    private String ecriture;
    private Boolean estOD;
    private String  type;
    @Column(length = 4000)
    private String valeurFormule;
    private String valeurCodeAnalytique;
    private Boolean estExtournee;
    private Boolean estOpExtournee;
    private Long idOpExtournee;
    @ManyToOne
    @JoinColumn(name ="idOpExtourneeNew" ,referencedColumnName = "idOperation")
    private Operation operation;
    private Boolean estVerifie1;
    private LocalDateTime dateVerification1;
    private String userLoginVerificateur1;
    private Boolean estVerifie2;
    private LocalDateTime dateVerification2;
    private String userLoginVerificateur2;

    public Operation() {
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public void setTypeOp(String typeOp) {
        this.typeOp = typeOp;
    }

    @Override
    public Long getIdOcc() {
        return idOcc;
    }

    @Override
    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Personne getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(Personne actionnaire) {
        this.actionnaire = actionnaire;
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

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getLibelleOperation() {
        return libelleOperation;
    }

    public void setLibelleOperation(String libelleOperation) {
        this.libelleOperation = libelleOperation;
    }

    public LocalDateTime getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(LocalDateTime dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public LocalDateTime getDatePiece() {
        return datePiece;
    }

    public void setDatePiece(LocalDateTime datePiece) {
        this.datePiece = datePiece;
    }

    public LocalDateTime getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(LocalDateTime dateValeur) {
        this.dateValeur = dateValeur;
    }

    public String getReferencePiece() {
        return referencePiece;
    }

    public void setReferencePiece(String referencePiece) {
        this.referencePiece = referencePiece;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getEcriture() {
        return ecriture;
    }

    public void setEcriture(String ecriture) {
        this.ecriture = ecriture;
    }

    public Boolean getEstOD() {
        return estOD;
    }

    public void setEstOD(Boolean estOD) {
        this.estOD = estOD;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValeurFormule() {
        return valeurFormule;
    }

    public void setValeurFormule(String valeurFormule) {
        this.valeurFormule = valeurFormule;
    }

    public String getValeurCodeAnalytique() {
        return valeurCodeAnalytique;
    }

    public void setValeurCodeAnalytique(String valeurCodeAnalytique) {
        this.valeurCodeAnalytique = valeurCodeAnalytique;
    }

    public Boolean getEstExtournee() {
        return estExtournee;
    }

    public void setEstExtournee(Boolean estExtournee) {
        this.estExtournee = estExtournee;
    }

    public Boolean getEstOpExtournee() {
        return estOpExtournee;
    }

    public void setEstOpExtournee(Boolean estOpExtournee) {
        this.estOpExtournee = estOpExtournee;
    }

    public Long getIdOpExtournee() {
        return idOpExtournee;
    }

    public void setIdOpExtournee(Long idOpExtournee) {
        this.idOpExtournee = idOpExtournee;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Boolean getEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(Boolean estVerifie1) {
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

    public Boolean getEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(Boolean estVerifie2) {
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

    @Override
    public Long getId() {
        return idOperation;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
