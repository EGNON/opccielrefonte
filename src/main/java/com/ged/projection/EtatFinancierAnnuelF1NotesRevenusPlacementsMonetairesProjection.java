package com.ged.projection;

import java.math.BigDecimal;

public interface EtatFinancierAnnuelF1NotesRevenusPlacementsMonetairesProjection {

    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    String getPays();

    Integer getAnneeN();
    BigDecimal getRevenusBonTresoN();
    BigDecimal getRevenusBilletsTresoN();
    BigDecimal getRevenusCertifcatDepoN();
    BigDecimal getRevenusDatN();
    BigDecimal getTotalN();



    Integer getAnneeA();
    BigDecimal getRevenusBonTresoA();
    BigDecimal getRevenusBilletsTresoA();
    BigDecimal getRevenusCertifcatDepoA();
    BigDecimal getRevenusDatA();
    BigDecimal getTotalA();
}
