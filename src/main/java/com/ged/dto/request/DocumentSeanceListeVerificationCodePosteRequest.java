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
public class DocumentSeanceListeVerificationCodePosteRequest {
    private Long idSeance;
    private Long idOpcvm;
    private Boolean estVerifie1;
    private Boolean estVerifie2;
    private DatatableParameters datatableParameters;
    private String letterDate;
    private String denominationOpcvm;
    private LocalDateTime dateOuverture;
    private LocalDateTime dateFermeture;
}
