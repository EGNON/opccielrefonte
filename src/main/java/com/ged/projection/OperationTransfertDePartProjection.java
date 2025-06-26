package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OperationTransfertDePartProjection {
    Long getIdDemandeur();
    String getDemandeur();
    BigDecimal getQteInitialeD();
    Long getIdBeneficiaire();
    String getBeneficiaire();
    BigDecimal getQteInitialeB();
    BigDecimal getCumpEntre();
    BigDecimal getQteTransfert();
    LocalDateTime getDateOperation();
    Long getIdOpcvm();
    String getIdOperation();
}
