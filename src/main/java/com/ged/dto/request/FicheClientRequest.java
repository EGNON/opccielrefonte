package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FicheClientRequest {
    private String idActionnaire;
    private Boolean estPH;

    public String getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(String idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public Boolean getEstPH() {
        return estPH;
    }

    public void setEstPH(Boolean estPH) {
        this.estPH = estPH;
    }
}
