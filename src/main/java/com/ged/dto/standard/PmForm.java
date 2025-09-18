package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.titresciel.TitreDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PmForm {
    private Long idSeance;
    private OpcvmDto opcvm;
    private Long quantite;
    private String modeVL;
    private String type;
    private String numeroCpteDeposit;
    private LocalDateTime dateOperation;
    private NatureOperationDto natureOperation;
    private String valeurCodeAnalytique;
    private String valeurFormule;
    private Boolean estGenere;
    private Boolean estVerifier;
    private String nomVerificateur;
    private LocalDateTime dateVerification;
    private BigDecimal montantSouscrit;
    private TitreDto titre;
    private BigDecimal qte;
    private  BigDecimal cours;
    private BigDecimal commission;
    private BigDecimal interetCouru;
    private BigDecimal intererPrecompte;
        //Champs communs
    private Long id;
    private Long idPersonne;
    private SecteurDto secteur;
    private String mobile1;
    private String mobile2;
    private String fixe1;
    private String fixe2;
    private String ifu;
    private String typePiece;
    private String numeroPiece;
    private LocalDateTime dateExpirationPiece;
    private String emailPerso;
    private String emailPro;
    private PersonneDto distributeur;
        //Champs PersonnePhysique
    private String sigle;
    private String raisonSociale;

    public String getNumeroCpteDeposit() {
        return numeroCpteDeposit;
    }

    public void setNumeroCpteDeposit(String numeroCpteDeposit) {
        this.numeroCpteDeposit = numeroCpteDeposit;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public String getModeVL() {
        return modeVL;
    }

    public void setModeVL(String modeVL) {
        this.modeVL = modeVL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public NatureOperationDto getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperationDto natureOperation) {
        this.natureOperation = natureOperation;
    }

    public String getValeurCodeAnalytique() {
        return valeurCodeAnalytique;
    }

    public void setValeurCodeAnalytique(String valeurCodeAnalytique) {
        this.valeurCodeAnalytique = valeurCodeAnalytique;
    }

    public String getValeurFormule() {
        return valeurFormule;
    }

    public void setValeurFormule(String valeurFormule) {
        this.valeurFormule = valeurFormule;
    }

    public Boolean getEstGenere() {
        return estGenere;
    }

    public void setEstGenere(Boolean estGenere) {
        this.estGenere = estGenere;
    }

    public Boolean getEstVerifier() {
        return estVerifier;
    }

    public void setEstVerifier(Boolean estVerifier) {
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

    public BigDecimal getMontantSouscrit() {
        return montantSouscrit;
    }

    public void setMontantSouscrit(BigDecimal montantSouscrit) {
        this.montantSouscrit = montantSouscrit;
    }

    public TitreDto getTitre() {
        return titre;
    }

    public void setTitre(TitreDto titre) {
        this.titre = titre;
    }

    public BigDecimal getQte() {
        return qte;
    }

    public void setQte(BigDecimal qte) {
        this.qte = qte;
    }

    public BigDecimal getCours() {
        return cours;
    }

    public void setCours(BigDecimal cours) {
        this.cours = cours;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getInteretCouru() {
        return interetCouru;
    }

    public void setInteretCouru(BigDecimal interetCouru) {
        this.interetCouru = interetCouru;
    }

    public BigDecimal getIntererPrecompte() {
        return intererPrecompte;
    }

    public void setIntererPrecompte(BigDecimal intererPrecompte) {
        this.intererPrecompte = intererPrecompte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public SecteurDto getSecteur() {
        return secteur;
    }

    public void setSecteur(SecteurDto secteur) {
        this.secteur = secteur;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getFixe1() {
        return fixe1;
    }

    public void setFixe1(String fixe1) {
        this.fixe1 = fixe1;
    }

    public String getFixe2() {
        return fixe2;
    }

    public void setFixe2(String fixe2) {
        this.fixe2 = fixe2;
    }

    public String getIfu() {
        return ifu;
    }

    public void setIfu(String ifu) {
        this.ifu = ifu;
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public LocalDateTime getDateExpirationPiece() {
        return dateExpirationPiece;
    }

    public void setDateExpirationPiece(LocalDateTime dateExpirationPiece) {
        this.dateExpirationPiece = dateExpirationPiece;
    }

    public String getEmailPerso() {
        return emailPerso;
    }

    public void setEmailPerso(String emailPerso) {
        this.emailPerso = emailPerso;
    }

    public String getEmailPro() {
        return emailPro;
    }

    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }

    public PersonneDto getDistributeur() {
        return distributeur;
    }

    public void setDistributeur(PersonneDto distributeur) {
        this.distributeur = distributeur;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
}
