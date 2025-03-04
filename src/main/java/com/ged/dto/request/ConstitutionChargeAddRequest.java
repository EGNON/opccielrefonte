package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.ChargeDto;
import com.ged.dto.opcciel.OpcvmDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class ConstitutionChargeAddRequest {
    private Long idOperation;
    private Long idSeance;
    private OpcvmDto opcvm;
    private ChargeDto charge;
    private BigDecimal montant;
    /*@NotBlank(message = "Le montant réel est obligatoire")
    @NotEmpty(message = "Le montant réel doit être > 0")*/
    private BigDecimal montantCharge;
    private LocalDateTime dateOperation;
    private LocalDateTime dateSolde;
    private LocalDateTime datePiece;
    private LocalDateTime dateValeur;
    private LocalDateTime dateSaisie;
}
