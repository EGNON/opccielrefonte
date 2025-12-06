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
public class CompositionDetailleActifRequest {
    private Long idOpcvm;
    private LocalDateTime dateEstimation;
    private LocalDateTime dateOperation;
    private DatatableParameters datatableParameters;
    private String letterDate;
    private String frequence;
    private String sigleOpcvm;
    private String denominationOpcvm;
}
