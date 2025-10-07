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
public class PointSouscriptionGlobalRequest {
    private Long idOpcvm;
    private Long idSeance;
    private Long idPersonne;
    private String type;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private DatatableParameters datatableParameters;
    private String Descrip;
}
