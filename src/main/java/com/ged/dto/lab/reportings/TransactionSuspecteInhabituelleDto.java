package com.ged.dto.lab.reportings;

import java.math.BigDecimal;

public class TransactionSuspecteInhabituelleDto {
    String typePersonne;
    String opMontantTransac;
    BigDecimal montantTransac;
    String opQtePart;
    BigDecimal qtePart;

    public String getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(String typePersonne) {
        this.typePersonne = typePersonne;
    }

    public String getOpMontantTransac() {
        return opMontantTransac;
    }

    public void setOpMontantTransac(String opMontantTransac) {
        this.opMontantTransac = opMontantTransac;
    }

    public BigDecimal getMontantTransac() {
        return montantTransac;
    }

    public void setMontantTransac(BigDecimal montantTransac) {
        this.montantTransac = montantTransac;
    }

    public String getOpQtePart() {
        return opQtePart;
    }

    public void setOpQtePart(String opQtePart) {
        this.opQtePart = opQtePart;
    }

    public BigDecimal getQtePart() {
        return qtePart;
    }

    public void setQtePart(BigDecimal qtePart) {
        this.qtePart = qtePart;
    }
}
