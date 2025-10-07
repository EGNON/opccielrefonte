package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoldeDesComptesComptablesRequest {
    private String codePlan;
    private Long idOpcvm;
    private String numCompteComptable;
    private String type;
    private LocalDateTime dateEstimation;
    private DatatableParameters datatableParameters;
    private String Descrip;
}
