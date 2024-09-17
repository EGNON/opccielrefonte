package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeIbDto extends Base {

    private String codeTypeIb;
	private String libelleTypeIB;
    private ClasseIbDto classeIb;
	private boolean referencerIBSysteme;

    public TypeIbDto() {
    }

    public String getCodeTypeIb() {
        return codeTypeIb;
    }

    public void setCodeTypeIb(String codeTypeIb) {
        this.codeTypeIb = codeTypeIb;
    }

    public String getLibelleTypeIB() {
        return libelleTypeIB;
    }

    public void setLibelleTypeIB(String libelleTypeIB) {
        this.libelleTypeIB = libelleTypeIB;
    }

    public ClasseIbDto getClasseIb() {
        return classeIb;
    }

    public void setClasseIb(ClasseIbDto classeIb) {
        this.classeIb = classeIb;
    }

    public boolean isReferencerIBSysteme() {
        return referencerIBSysteme;
    }

    public void setReferencerIBSysteme(boolean referencerIBSysteme) {
        this.referencerIBSysteme = referencerIBSysteme;
    }
}
