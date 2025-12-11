package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistreActionPDFRequest {
    private Long idOpcvm;
    private Long idActionnaire;
    private LocalDateTime dateEstimation;

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

    public LocalDateTime getDateEstimation() {
        return dateEstimation;
    }

    public void setDateEstimation(LocalDateTime dateEstimation) {
        this.dateEstimation = dateEstimation;
    }
}
