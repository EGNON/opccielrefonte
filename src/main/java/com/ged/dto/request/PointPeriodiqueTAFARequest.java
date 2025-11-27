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
public class PointPeriodiqueTAFARequest {
    private Long idOpcvm;
    private LocalDateTime dateOuverture;
    private LocalDateTime dateFermeture;
    private DatatableParameters datatableParameters;
    private String letterDate;
    private String denominationOpcvm;
}
