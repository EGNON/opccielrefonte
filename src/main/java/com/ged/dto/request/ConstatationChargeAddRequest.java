package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.ChargeDto;
import com.ged.dto.opcciel.OpcvmDto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
public class ConstatationChargeAddRequest {
    private Long idOperation;
    private Long idSeance;
    @NotNull(message = "Veuillez définir l'opcvm concerné")
    private OpcvmDto opcvm;
    @NotNull(message = "Le choix de la charge est obligatoire")
    private ChargeDto charge;
    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin("1.00")
    private BigDecimal montant;
    @NotNull(message = "Le montant réel est obligatoire")
    @DecimalMin("1.00")
    private BigDecimal montantCharge;
    @NotNull(message = "La date est obligatoire")
    private LocalDateTime dateOperation;
    private LocalDateTime dateSolde;
    private LocalDateTime datePiece;
    private LocalDateTime dateValeur;
    private LocalDateTime dateSaisie;
}
