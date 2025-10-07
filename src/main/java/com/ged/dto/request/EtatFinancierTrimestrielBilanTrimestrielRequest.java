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
public class EtatFinancierTrimestrielBilanTrimestrielRequest {
    private Long idOpcvm;
    private Integer annee;
    private String type;
    private LocalDateTime dateEstimation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private DatatableParameters datatableParameters;
    private String raisonSocial;
    private String format;
    private String libellePays;
    private String periodicite;
    private String denominationOpcvm;
}
