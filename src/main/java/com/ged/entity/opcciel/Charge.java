package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.Base;
import com.ged.entity.titresciel.TypeAmortissement;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_Charge", schema = "Parametre")
public class Charge extends Base {
    @EmbeddedId
    private CleCharge idCharge;
    @ManyToOne()
    @JoinColumn(name = "idOpcvm", referencedColumnName = "idOpcvm")
    @MapsId("idOpcvm")
    private Opcvm opcvm;
	private String designation;
    @Column(precision = 18, scale = 6)
    private BigDecimal montant;
    private int periodicite;
    private String unitePeriodicite;
    private boolean estActif;
    private int nbreAmortissement;
    private String typeCharge;
    @ManyToOne()
    @JoinColumn(name = "codeModele")
    private NatureOperation natureOperation;
    private String typeCommission;
    private String typeAmortissement;
    @ManyToOne()
    @JoinColumn(name = "idTypeAmortissement",referencedColumnName = "idTypeAmortissement")
    private TypeAmortissement typeAmortissementNew;
    private boolean appliquerSurActifNet;
    private String codeFiscalite;
    private boolean reference;
    private boolean adeduire;
    private int numOrdre;

    public Charge() {
    }

    public CleCharge getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(CleCharge idCharge) {
        this.idCharge = idCharge;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }

    public String getUnitePeriodicite() {
        return unitePeriodicite;
    }

    public void setUnitePeriodicite(String unitePeriodicite) {
        this.unitePeriodicite = unitePeriodicite;
    }

    public boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    public int getNbreAmortissement() {
        return nbreAmortissement;
    }

    public void setNbreAmortissement(int nbreAmortissement) {
        this.nbreAmortissement = nbreAmortissement;
    }

    public String getTypeCharge() {
        return typeCharge;
    }

    public void setTypeCharge(String typeCharge) {
        this.typeCharge = typeCharge;
    }

    public NatureOperation getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperation natureOperation) {
        this.natureOperation = natureOperation;
    }

    public String getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(String typeCommission) {
        this.typeCommission = typeCommission;
    }

    public String getTypeAmortissement() {
        return typeAmortissement;
    }

    public void setTypeAmortissement(String typeAmortissement) {
        this.typeAmortissement = typeAmortissement;
    }

    public TypeAmortissement getTypeAmortissementNew() {
        return typeAmortissementNew;
    }

    public void setTypeAmortissementNew(TypeAmortissement typeAmortissementNew) {
        this.typeAmortissementNew = typeAmortissementNew;
    }

    public boolean isAppliquerSurActifNet() {
        return appliquerSurActifNet;
    }

    public void setAppliquerSurActifNet(boolean appliquerSurActifNet) {
        this.appliquerSurActifNet = appliquerSurActifNet;
    }

    public String getCodeFiscalite() {
        return codeFiscalite;
    }

    public void setCodeFiscalite(String codeFiscalite) {
        this.codeFiscalite = codeFiscalite;
    }

    public boolean isReference() {
        return reference;
    }

    public void setReference(boolean reference) {
        this.reference = reference;
    }

    public boolean isAdeduire() {
        return adeduire;
    }

    public void setAdeduire(boolean adeduire) {
        this.adeduire = adeduire;
    }

    public int getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(int numOrdre) {
        this.numOrdre = numOrdre;
    }
}
