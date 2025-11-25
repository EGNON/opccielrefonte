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
public class DocumentSeanceListeVerificationEcritureChargeRequest {
    private Long idOpcvm;
    private String codeTypeOperation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Boolean estVerifie1;
    private Boolean estVerifie2;
    private String idOperation;
    private DatatableParameters datatableParameters;
    private String letterDate;
}
