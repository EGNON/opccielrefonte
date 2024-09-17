package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.titresciel.TypeAmortissementDto;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargeDto extends Base {
    private Long idCharge;
    private String codeCharge;
    private OpcvmDto opcvm;
	private String designation;
    private Double montant;
    private int periodicite;
    private String unitePeriodicite;
    private boolean estActif;
    private int nbreAmortissement;
    private String typeCharge;
    private NatureOperationDto natureOperation;
    private String typeCommission;
    private TypeAmortissementDto typeAmortissement;
    private boolean appliquerSurActifNet;
    private String codeFiscalite;
    private boolean reference;
    private boolean adeduire;
    private int numOrdre;

    public ChargeDto() {
    }

    public String getCodeCharge() {
        return codeCharge;
    }

    public void setCodeCharge(String codeCharge) {
        this.codeCharge = codeCharge;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
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

    public Long getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(Long idCharge) {
        this.idCharge = idCharge;
    }

    public NatureOperationDto getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperationDto natureOperation) {
        this.natureOperation = natureOperation;
    }

    public String getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(String typeCommission) {
        this.typeCommission = typeCommission;
    }

    public TypeAmortissementDto getTypeAmortissement() {
        return typeAmortissement;
    }

    public void setTypeAmortissement(TypeAmortissementDto typeAmortissement) {
        this.typeAmortissement = typeAmortissement;
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
