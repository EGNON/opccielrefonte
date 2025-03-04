package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationConstatationChargeDto extends OperationDto {
   private LocalDateTime dateSolde;
   private BigDecimal montantCharge;
   private ChargeDto charge;
   private String codeCharge;
   private Boolean estPayee;
   private Long idSeancePaiement;
}
