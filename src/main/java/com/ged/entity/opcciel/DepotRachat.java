package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_DepotRachat", schema = "Parametre")
//public class DepotRachat extends Operation {
public class DepotRachat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOperation;
    private Long idOcc;
    private Double quantite;
    private String modeVL;
    private boolean estGenere;
    private boolean estVerifier;
    private String nomVerificateur;
    private LocalDateTime dateVerification;
    private Double montantSouscrit;
    @ManyToOne
    @JoinColumn(name = "idPersonneNew",referencedColumnName = "idPersonne")
    private Personne personne;
   /* @ManyToOne
    @JoinColumn(name = "idTitre",referencedColumnName = "idTitre")
    private Titre titre;*/
//    private long idTitre;
    private Double qte;
    private Double cours;
    private Double commission;
    private Double interetCouru;
    private Double interetPrecompte;

    /***********************************************************************/
    @ManyToOne
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;
    @ManyToOne
    @JoinColumn(name = "idActionnaireNew",referencedColumnName = "idPersonne")
    private Personne actionnaire;
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
    private Boolean estOD;
    private String  type;
    @Column(length = 4000)
    private String valeurFormule;
    private String valeurCodeAnalytique;
    private Boolean estVerifie1;
    private LocalDateTime dateVerification1;
    private String userLoginVerificateur1;
    private Boolean estVerifie2;
    private LocalDateTime dateVerification2;
    private String userLoginVerificateur2;
    private Long idSeance;
    @ManyToOne
    @JoinColumn(name = "idTitreNew",referencedColumnName = "idTitre")
    private Titre titre;

    public DepotRachat() {
    }

   /* @Override
    public Titre getTitre() {
        return titre;
    }

    @Override
    public void setTitre(Titre titre) {
        this.titre = titre;
    }*/

    /*@Override
    public long getIdTitre() {
        return idTitre;
    }

    @Override
    public void setIdTitre(long idTitre) {
        this.idTitre = idTitre;
    }*/

    public Personne getPersonne() {
        return personne;
    }


    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Long getIdOcc() {
        return idOcc;
    }

    public void setIdOcc(Long idOcc) {
        this.idOcc = idOcc;
    }

//    public Transaction getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(Transaction transaction) {
//        this.transaction = transaction;
//    }
//
//    public NatureOperation getNatureOperation() {
//        return natureOperation;
//    }
//
//    public void setNatureOperation(NatureOperation natureOperation) {
//        this.natureOperation = natureOperation;
//    }
//
//    public LocalDateTime getDateOperation() {
//        return dateOperation;
//    }
//
//    public void setDateOperation(LocalDateTime dateOperation) {
//        this.dateOperation = dateOperation;
//    }
//
//    public String getLibelleOperation() {
//        return libelleOperation;
//    }
//
//    public void setLibelleOperation(String libelleOperation) {
//        this.libelleOperation = libelleOperation;
//    }
//
//    public LocalDateTime getDateSaisie() {
//        return dateSaisie;
//    }
//
//    public void setDateSaisie(LocalDateTime dateSaisie) {
//        this.dateSaisie = dateSaisie;
//    }
//
//    public LocalDateTime getDatePiece() {
//        return datePiece;
//    }
//
//    public void setDatePiece(LocalDateTime datePiece) {
//        this.datePiece = datePiece;
//    }
//
//    public LocalDateTime getDateValeur() {
//        return dateValeur;
//    }
//
//    public void setDateValeur(LocalDateTime dateValeur) {
//        this.dateValeur = dateValeur;
//    }
//
//    public String getReferencePiece() {
//        return referencePiece;
//    }
//
//    public void setReferencePiece(String referencePiece) {
//        this.referencePiece = referencePiece;
//    }
//
//    public Double getMontant() {
//        return montant;
//    }
//
//    public void setMontant(Double montant) {
//        this.montant = montant;
//    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

//    public String getEcriture() {
//        return ecriture;
//    }
//
//    public void setEcriture(String ecriture) {
//        this.ecriture = ecriture;
//    }
//
//    public boolean isEstOD() {
//        return estOD;
//    }
//
//    public void setEstOD(boolean estOD) {
//        this.estOD = estOD;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Personne getPersonneActionnaire() {
//        return personneActionnaire;
//    }
//
//    public void setPersonneActionnaire(Personne personneActionnaire) {
//        this.personneActionnaire = personneActionnaire;
//    }
//
//    public long getIdSeance() {
//        return IdSeance;
//    }
//
//    public void setIdSeance(long idSeance) {
//        IdSeance = idSeance;
//    }
//
//    public Personne getPersonne() {
//        return personne;
//    }
//
//    public void setPersonne(Personne personne) {
//        this.personne = personne;
//    }
//
//    public Opcvm getOpcvm() {
//        return opcvm;
//    }
//
//    public void setOpcvm(Opcvm opcvm) {
//        this.opcvm = opcvm;
//    }

    public String getModeVL() {
        return modeVL;
    }

    public void setModeVL(String modeVL) {
        this.modeVL = modeVL;
    }

//    public boolean isEstVerifie1() {
//        return estVerifie1;
//    }
//
//    public void setEstVerifie1(boolean estVerifie1) {
//        this.estVerifie1 = estVerifie1;
//    }
//
//    public LocalDateTime getDateVerification1() {
//        return dateVerification1;
//    }
//
//    public void setDateVerification1(LocalDateTime dateVerification1) {
//        this.dateVerification1 = dateVerification1;
//    }
//
//    public String getUserLoginVerificateur1() {
//        return userLoginVerificateur1;
//    }
//
//    public void setUserLoginVerificateur1(String userLoginVerificateur1) {
//        this.userLoginVerificateur1 = userLoginVerificateur1;
//    }
//
//    public boolean isEstVerifie2() {
//        return estVerifie2;
//    }
//
//    public void setEstVerifie2(boolean estVerifie2) {
//        this.estVerifie2 = estVerifie2;
//    }
//
//    public LocalDateTime getDateVerification2() {
//        return dateVerification2;
//    }
//
//    public void setDateVerification2(LocalDateTime dateVerification2) {
//        this.dateVerification2 = dateVerification2;
//    }
//
//    public String getUserLoginVerificateur2() {
//        return userLoginVerificateur2;
//    }
//
//    public void setUserLoginVerificateur2(String userLoginVerificateur2) {
//        this.userLoginVerificateur2 = userLoginVerificateur2;
//    }

    public boolean isEstGenere() {
        return estGenere;
    }

    public void setEstGenere(boolean estGenere) {
        this.estGenere = estGenere;
    }

    public boolean isEstVerifier() {
        return estVerifier;
    }

    public void setEstVerifier(boolean estVerifier) {
        this.estVerifier = estVerifier;
    }

    public String getNomVerificateur() {
        return nomVerificateur;
    }

    public void setNomVerificateur(String nomVerificateur) {
        this.nomVerificateur = nomVerificateur;
    }

    public LocalDateTime getDateVerification() {
        return dateVerification;
    }

    public void setDateVerification(LocalDateTime dateVerification) {
        this.dateVerification = dateVerification;
    }

    public Double getMontantSouscrit() {
        return montantSouscrit;
    }

    public void setMontantSouscrit(Double montantSouscrit) {
        this.montantSouscrit = montantSouscrit;
    }

    /*public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }*/

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getCours() {
        return cours;
    }

    public void setCours(Double cours) {
        this.cours = cours;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getInteretCouru() {
        return interetCouru;
    }

    public void setInteretCouru(Double interetCouru) {
        this.interetCouru = interetCouru;
    }

    public Double getInteretPrecompte() {
        return interetPrecompte;
    }

    public void setInteretPrecompte(Double interetPrecompte) {
        this.interetPrecompte = interetPrecompte;
    }
    /************************************************************************************/
    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public Personne getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(Personne actionnaire) {
        this.actionnaire = actionnaire;
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

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    /*****************************************************************************/

    @Override
    public String toString() {
        return "DepotRachat{" +
                "idOperation=" + idOperation +
                ", quantite=" + quantite +
                ", montantSouscrit=" + montantSouscrit +
                ", montant=" + montant +
                '}';
    }
}
