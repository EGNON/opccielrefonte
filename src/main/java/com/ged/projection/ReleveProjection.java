package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ReleveProjection {
    Long getIdOpcvm();
    String getSigle();
    String getRaisonSociale();
    Long getIdOperation ();
    LocalDateTime getDateOperation();
    LocalDateTime getDateValeur();
    BigDecimal getDebit();
    BigDecimal getCredit();
    String getLibelleOperation();
    BigDecimal getSoldeDebut();
    BigDecimal getSoldeFin();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();
    BigDecimal getTotDebit();
    BigDecimal getTotCredit();
    BigDecimal getNumligneMVT();
    BigDecimal getSoldeParLigne();
    int getNum();
}
