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
public class EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest {
    private Long idOpcvm;
    private String type;
    private DatatableParameters datatableParameters;
    private String libellePays;
    private String denominationOpcvm;
    private String periodicite;
    private LocalDateTime dateEstimation;
}
