package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.ChargeDto;
import com.ged.dto.opcciel.OpcvmDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstatationChargeEditRequest {
    private Long idOperation;
    private Long idSeance;
    private OpcvmDto opcvm;
    private ChargeDto charge;
    private BigDecimal montant;
    private BigDecimal montantCharge;
    private LocalDateTime dateOperation;
    private LocalDateTime dateSolde;
    private LocalDateTime datePiece;
    private LocalDateTime dateValeur;
    private LocalDateTime dateSaisie;
}
