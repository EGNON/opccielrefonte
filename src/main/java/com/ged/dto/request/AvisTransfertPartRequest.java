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
public class AvisTransfertPartRequest {
    private Long idOpcvm;
    private Long idOperation;
    private String type;
    private LocalDateTime dateDeb;
    private LocalDateTime dateFin;
    private DatatableParameters datatableParameters;
    private String Descrip;
}
