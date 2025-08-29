package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatsSuiviActionnaireProjection {
    Long getIdOpcvm();
    String getDenominationOpcvm();
    BigDecimal getNbreInitial();
    BigDecimal getPartInitial();
    BigDecimal getNbreEntrantAnc();
    BigDecimal getPartEntrant();
    BigDecimal getNbreSortantAnc();
    BigDecimal getPartSortant();
    BigDecimal getNbreEntrantNew();
    BigDecimal getNbreSortantNew();
    BigDecimal getNbreFinal();
    BigDecimal getPartFinal();
}
