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
public class DocumentSeanceListeVerificationRachatsRequest {
    private Long idSeance;
    private Long idPersonne;
    private Long idOpcvm;
    private String codeNatureOperation;
    private Boolean niveau1;
    private Boolean niveau2;
    private DatatableParameters datatableParameters;
    private String letterDate;
}
