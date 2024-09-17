package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeIb", schema = "Comptabilite")
public class TypeIb extends Base {
    @Id
    @Column(length = 250)
    private String codeTypeIb;
	private String libelleTypeIB;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeClasseIb")
    private ClasseIb classeIb;
	private boolean referencerIBSysteme;

    public TypeIb() {
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

    public ClasseIb getClasseIb() {
        return classeIb;
    }

    public void setClasseIb(ClasseIb classeIb) {
        this.classeIb = classeIb;
    }

    public boolean isReferencerIBSysteme() {
        return referencerIBSysteme;
    }

    public void setReferencerIBSysteme(boolean referencerIBSysteme) {
        this.referencerIBSysteme = referencerIBSysteme;
    }
}
