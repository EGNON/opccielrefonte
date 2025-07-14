package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.SoldeCompteFormuleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DifferenceEstimationRequest {
    private Long idSeance;
    private Long idOpcvm;
    private Long idOperation;
    private Long idTitre;
    private Long niveau;
    private BigDecimal actifBrut;
    private Long nbreJour;
    private Long usance;
    private LocalDateTime dateSeance;
    private LocalDateTime dateOperation;
    private Boolean estVerifie1;
    private Boolean estVerifie2;
    private String userLogin1;
    private String userLogin2;
    private String userLogin;
    private DatatableParameters datatableParameters;
    private SoldeCompteFormuleDto soldeCompteFormuleDto;
}
