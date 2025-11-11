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
public class PointInvestissementRequest {
    private Long idOpcvm;
    private Boolean echue;
    private Boolean traiter;
    private Boolean detache;
    private String typeOp;
    private String type;
    private LocalDateTime dateDeb;
    private LocalDateTime dateFin;
    private LocalDateTime dateEstimation;
    private DatatableParameters datatableParameters;
    private String Descrip;
    private String denominationOpcvm;
    private LocalDateTime dateOuverture;
}
