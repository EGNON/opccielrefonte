package com.ged.dto.lab.reportings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SqlResultSetMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*@MappedSuperclass*/
@JsonIgnoreProperties(ignoreUnknown = true)
/*@SqlResultSetMapping(name = "RecensementOpClientOcasionnelMapping", classes = {
        @ConstructorResult(targetClass = RecensementOpClientOcasionnelDto.class, columns = {
                @ColumnResult(name = "idOperation", type = BigDecimal.class),
                @ColumnResult(name = "idActionnaire", type = BigDecimal.class),
                @ColumnResult(name = "dateOperation"),
                @ColumnResult(name = "actionnaire"),
                @ColumnResult(name = "souscription", type = BigDecimal.class),
                @ColumnResult(name = "rachat", type = BigDecimal.class),
                @ColumnResult(name = "transfere", type = BigDecimal.class)
        })
})*/
public class RecensementOpClientOcasionnelDto {
    private BigDecimal idOperation;
    private BigDecimal idActionnaire;
    private LocalDateTime dateOperation;
    private String actionnaire;
    private BigDecimal souscription;
    private BigDecimal rachat;
    private BigDecimal transfere;

    public BigDecimal getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(BigDecimal idOperation) {
        this.idOperation = idOperation;
    }

    public BigDecimal getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(BigDecimal idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getActionnaire() {
        return actionnaire;
    }

    public void setActionnaire(String actionnaire) {
        this.actionnaire = actionnaire;
    }

    public BigDecimal getSouscription() {
        return souscription;
    }

    public void setSouscription(BigDecimal souscription) {
        this.souscription = souscription;
    }

    public BigDecimal getRachat() {
        return rachat;
    }

    public void setRachat(BigDecimal rachat) {
        this.rachat = rachat;
    }

    public BigDecimal getTransfere() {
        return transfere;
    }

    public void setTransfere(BigDecimal transfere) {
        this.transfere = transfere;
    }
}
