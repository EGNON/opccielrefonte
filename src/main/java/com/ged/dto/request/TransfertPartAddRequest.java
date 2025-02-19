package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.standard.PersonneDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransfertPartAddRequest {
    private OpcvmDto opcvm;
    private PersonneDto demandeur;
    private PersonneDto beneficiaire;
    private BigDecimal cumpEntre;
    private BigDecimal qteAvtD;
    private BigDecimal qteApresD;
    private BigDecimal qteAvtB;
    private BigDecimal qteApresB;
    private BigDecimal qteTrans;
    private LocalDateTime dateOperation;
    private Long idSeance;
}
