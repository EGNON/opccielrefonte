package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommissionAddRequest {
    private OpcvmDto opcvm;
    private Long idSeance;
    private NatureOperationDto natureOperation;
    private BigDecimal montantCommission;
    private BigDecimal montant;
    private String typeCommission;
    private String userLogin;
    @NotNull(message = "La date est obligatoire")
    private LocalDateTime dateOperation;
    private LocalDateTime dateSolde;
    private LocalDateTime datePiece;
    private LocalDateTime dateValeur;
    private LocalDateTime dateSaisie;
}
