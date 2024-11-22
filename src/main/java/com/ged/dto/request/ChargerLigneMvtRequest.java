package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.opcciel.comptabilite.Operation;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargerLigneMvtRequest {
    private String codeNatureOperation;
    private String valeurCodeAnalytique;
    private String valeurFormule;
    private Long idOpcvm;
    private Long idActionnaire;
    private Long idTitre;
    private Operation operation;

    public ChargerLigneMvtRequest() {
    }

    public ChargerLigneMvtRequest(String codeNatureOperation, String valeurCodeAnalytique, String valeurFormule, Long idOpcvm, Long idActionnaire, Long idTitre) {
        this.codeNatureOperation = codeNatureOperation;
        this.valeurCodeAnalytique = valeurCodeAnalytique;
        this.valeurFormule = valeurFormule;
        this.idOpcvm = idOpcvm;
        this.idActionnaire = idActionnaire;
        this.idTitre = idTitre;
    }

    public String getCodeNatureOperation() {
        return codeNatureOperation;
    }

    public void setCodeNatureOperation(String codeNatureOperation) {
        this.codeNatureOperation = codeNatureOperation;
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

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
