package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpcDto extends TitreDto {
    private BigDecimal vlOrigine;
    private Integer periodiciteVlNbre;
    private String periodiciteVlUnite;
    private ClassificationOPCDto classificationOPC;
    private TypeAffectationTitreDto typeAffectationTitre;
    private FormeJuridiqueOpcDto formeJuridiqueOpc;

    public BigDecimal getVlOrigine() {
        return vlOrigine;
    }

    public void setVlOrigine(BigDecimal vlOrigine) {
        this.vlOrigine = vlOrigine;
    }

    public Integer getPeriodiciteVlNbre() {
        return periodiciteVlNbre;
    }

    public void setPeriodiciteVlNbre(Integer periodiciteVlNbre) {
        this.periodiciteVlNbre = periodiciteVlNbre;
    }

    public String getPeriodiciteVlUnite() {
        return periodiciteVlUnite;
    }

    public void setPeriodiciteVlUnite(String periodiciteVlUnite) {
        this.periodiciteVlUnite = periodiciteVlUnite;
    }

    public ClassificationOPCDto getClassificationOPC() {
        return classificationOPC;
    }

    public void setClassificationOPC(ClassificationOPCDto classificationOPC) {
        this.classificationOPC = classificationOPC;
    }

    public TypeAffectationTitreDto getTypeAffectationTitre() {
        return typeAffectationTitre;
    }

    public void setTypeAffectationTitre(TypeAffectationTitreDto typeAffectationTitre) {
        this.typeAffectationTitre = typeAffectationTitre;
    }

    public FormeJuridiqueOpcDto getFormeJuridiqueOpc() {
        return formeJuridiqueOpc;
    }

    public void setFormeJuridiqueOpc(FormeJuridiqueOpcDto formeJuridiqueOpc) {
        this.formeJuridiqueOpc = formeJuridiqueOpc;
    }
}
