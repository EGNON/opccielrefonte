package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.PlanDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LigneMvtClotureExoDto {
    private String codeExercice;
    private int etape;
    private int numeroOrdeEtape;
    private String numeroCompteComptable;
    private OpcvmDto opcvm;
    private NatureOperationDto natureOperation;
    private ModeleEcritureDto modeleEcriture;
    private PlanDto plan;
    private String Sens;
    private FormuleDto formule;
    private Double valeur;
    private TypeFormuleDto typeFormule;
    private IbDto ib;
    private String codeRubrique;
    private String codePosition;

    public LigneMvtClotureExoDto() {
    }

    public String getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(String codeExercice) {
        this.codeExercice = codeExercice;
    }

    public int getEtape() {
        return etape;
    }

    public void setEtape(int etape) {
        this.etape = etape;
    }

    public int getNumeroOrdeEtape() {
        return numeroOrdeEtape;
    }

    public void setNumeroOrdeEtape(int numeroOrdeEtape) {
        this.numeroOrdeEtape = numeroOrdeEtape;
    }

    public String getNumeroCompteComptable() {
        return numeroCompteComptable;
    }

    public void setNumeroCompteComptable(String numeroCompteComptable) {
        this.numeroCompteComptable = numeroCompteComptable;
    }

    public OpcvmDto getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(OpcvmDto opcvm) {
        this.opcvm = opcvm;
    }

    public NatureOperationDto getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperationDto natureOperation) {
        this.natureOperation = natureOperation;
    }

    public ModeleEcritureDto getModeleEcriture() {
        return modeleEcriture;
    }

    public void setModeleEcriture(ModeleEcritureDto modeleEcriture) {
        this.modeleEcriture = modeleEcriture;
    }

    public PlanDto getPlan() {
        return plan;
    }

    public void setPlan(PlanDto plan) {
        this.plan = plan;
    }

    public String getSens() {
        return Sens;
    }

    public void setSens(String sens) {
        Sens = sens;
    }

    public FormuleDto getFormule() {
        return formule;
    }

    public void setFormule(FormuleDto formule) {
        this.formule = formule;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public TypeFormuleDto getTypeFormule() {
        return typeFormule;
    }

    public void setTypeFormule(TypeFormuleDto typeFormule) {
        this.typeFormule = typeFormule;
    }

    public IbDto getIb() {
        return ib;
    }

    public void setIb(IbDto ib) {
        this.ib = ib;
    }

    public String getCodeRubrique() {
        return codeRubrique;
    }

    public void setCodeRubrique(String codeRubrique) {
        this.codeRubrique = codeRubrique;
    }

    public String getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(String codePosition) {
        this.codePosition = codePosition;
    }
}
