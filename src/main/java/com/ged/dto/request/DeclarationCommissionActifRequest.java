package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeclarationCommissionActifRequest {
    private Long idOpcvm;
    private String trimestre;
    private Long anneeExo;
    private BigDecimal taux;
    private String type;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private DatatableParameters datatableParameters;
    private String Descrip;
}
