package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.TransactionDto;
import com.ged.dto.standard.PersonneDto;
import com.ged.dto.titresciel.TitreDto;
import com.ged.entity.Base;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDto extends Base {
    private Long idOperation;
    private Long idActionnaire;
    private Long idTitre;
    private Long idOcc;
    private OpcvmDto opcvm;
    private PersonneDto actionnaire;
    private TitreDto titre;
    private Long idTransaction;
    private TransactionDto transaction;
    private Long idSeance;
    private NatureOperationDto natureOperation;
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
    private String valeurFormule;
    private String valeurCodeAnalytique;
    private Boolean estExtournee;
    private Boolean estOpExtournee;
    private OperationDto operationDto;
    private Boolean estVerifie1;
    private LocalDateTime dateVerification1;
    private String userLoginVerificateur1;
    private Boolean estVerifie2;
    private LocalDateTime dateVerification2;
    private String userLoginVerificateur2;

    public OperationDto() {
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
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

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public PersonneDto getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(PersonneDto actionnaire) {
        this.actionnaire = actionnaire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Boolean getEstOD() {
        return estOD;
    }

    public void setEstOD(Boolean estOD) {
        this.estOD = estOD;
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

    public Boolean getEstVerifie1() {
        return estVerifie1;
    }

    public void setEstVerifie1(Boolean estVerifie1) {
        this.estVerifie1 = estVerifie1;
    }

    public Boolean getEstVerifie2() {
        return estVerifie2;
    }

    public void setEstVerifie2(Boolean estVerifie2) {
        this.estVerifie2 = estVerifie2;
    }

    public TitreDto getTitre() {
        return titre;
    }

    public void setTitre(TitreDto titre) {
        this.titre = titre;
    }

    public TransactionDto getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDto transaction) {
        this.transaction = transaction;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public NatureOperationDto getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperationDto natureOperation) {
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

    public String getEcriture() {
        return ecriture;
    }

    public void setEcriture(String ecriture) {
        this.ecriture = ecriture;
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

    public OperationDto getOperationDto() {
        return operationDto;
    }

    public void setOperationDto(OperationDto operationDto) {
        this.operationDto = operationDto;
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
