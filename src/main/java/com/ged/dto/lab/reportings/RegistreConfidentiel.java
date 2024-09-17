package com.ged.dto.lab.reportings;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SqlResultSetMapping(
        name = "RegistreMapping",
        classes = @ConstructorResult(
                targetClass = RegistreConfidentiel.class,
                columns = {
                        @ColumnResult(name = "idOpcvm", type = BigDecimal.class),
                        @ColumnResult(name = "denominationOpcvm"),
                        @ColumnResult(name = "idActionnaire", type = BigDecimal.class),
                        @ColumnResult(name = "personne"),
                        @ColumnResult(name = "dateOperation", type = java.time.LocalDateTime.class),
                        @ColumnResult(name = "montantOp", type = BigDecimal.class),
                        @ColumnResult(name = "partOp", type = BigDecimal.class),
                        @ColumnResult(name = "valeurLiquidative", type = BigDecimal.class),
                        @ColumnResult(name = "prixSouscription", type = BigDecimal.class),
                        @ColumnResult(name = "titre"),
                        @ColumnResult(name = "totalParFCP", type = BigDecimal.class),
                }))
public class RegistreConfidentiel {
    private BigDecimal idOpcvm;
    private String denominationOpcvm;
    private BigDecimal idActionnaire;
    private String personne;
    private LocalDateTime dateOperation;
    private BigDecimal montantOp;
    private BigDecimal partOp;
    private BigDecimal valeurLiquidative;
    private BigDecimal prixSouscription;
    private String titre;
    private BigDecimal totalParFCP;

    public BigDecimal getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(BigDecimal idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public String getDenominationOpcvm() {
        return denominationOpcvm;
    }

    public void setDenominationOpcvm(String denominationOpcvm) {
        this.denominationOpcvm = denominationOpcvm;
    }

    public BigDecimal getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(BigDecimal idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public String getPersonne() {
        return personne;
    }

    public void setPersonne(String personne) {
        this.personne = personne;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public BigDecimal getMontantOp() {
        return montantOp;
    }

    public void setMontantOp(BigDecimal montantOp) {
        this.montantOp = montantOp;
    }

    public BigDecimal getPartOp() {
        return partOp;
    }

    public void setPartOp(BigDecimal partOp) {
        this.partOp = partOp;
    }

    public BigDecimal getValeurLiquidative() {
        return valeurLiquidative;
    }

    public void setValeurLiquidative(BigDecimal valeurLiquidative) {
        this.valeurLiquidative = valeurLiquidative;
    }

    public BigDecimal getPrixSouscription() {
        return prixSouscription;
    }

    public void setPrixSouscription(BigDecimal prixSouscription) {
        this.prixSouscription = prixSouscription;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public BigDecimal getTotalParFCP() {
        return totalParFCP;
    }

    public void setTotalParFCP(BigDecimal totalParFCP) {
        this.totalParFCP = totalParFCP;
    }
}
