package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "T_Operation", schema = "Comptabilite")
public class Operation extends Base {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOperation;
    private Long idOcc;
    @ManyToOne
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;
    private long idActionnaire;
    @ManyToOne
    @JoinColumn(name = "idActionnaireNew",referencedColumnName = "idPersonne")
    private Personne personneActionnaire;

    private long idTitre;
    @ManyToOne
    @JoinColumn(name = "idTitreNew",referencedColumnName = "idTitre")
    private Titre titre;
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
    private Double montant;
    private String ecriture;
    private boolean estOD;
    private String  type;
    @Column(length = 4000)
    private String valeurFormule;
    private String valeurCodeAnalytique;
    private boolean estExtournee;
    private boolean estOpExtournee;
    private long idOpExtournee;
    @ManyToOne
    @JoinColumn(name ="idOpExtourneeNew" ,referencedColumnName = "idOperation")
    private Operation operation;
    private boolean estVerifie1;
    private LocalDateTime dateVerification1;
    private String userLoginVerificateur1;
    private boolean estVerifie2;
    private LocalDateTime dateVerification2;
    private String userLoginVerificateur2;

    public Operation() {
    }

    public long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Personne getPersonneActionnaire() {
        return personneActionnaire;
    }

    public void setPersonneActionnaire(Personne personneActionnaire) {
        this.personneActionnaire = personneActionnaire;
    }

    public long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(long idTitre) {
        this.idTitre = idTitre;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public long getIdOpExtournee() {
        return idOpExtournee;
    }

    public void setIdOpExtournee(long idOpExtournee) {
        this.idOpExtournee = idOpExtournee;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
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

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    /*public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }*/

    /*public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }*/

    /*public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }*/

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

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getEcriture() {
        return ecriture;
    }

    public void setEcriture(String ecriture) {
        this.ecriture = ecriture;
    }

    public boolean isEstOD() {
        return estOD;
    }

    public void setEstOD(boolean estOD) {
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

    public boolean isEstExtournee() {
        return estExtournee;
    }

    public void setEstExtournee(boolean estExtournee) {
        this.estExtournee = estExtournee;
    }

    public boolean isEstOpExtournee() {
        return estOpExtournee;
    }

    public void setEstOpExtournee(boolean estOpExtournee) {
        this.estOpExtournee = estOpExtournee;
    }

/*    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }*/

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
}
