package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ReleveActionnaireProjection {
    Long getIdActionnaire();
    String getNumCompteSgi();
    String getIntitule();
    String getTelephoneFixe();
    String getTelephoneMobile1();
    String getTelephoneMobile2();
    String getEmail();
    String getBoitePostale();
    String getAdresseComplete();
    Long getIdOperation();
    LocalDateTime getDateOperation();
    LocalDateTime getDateValeur();
    BigDecimal getDebit();
    BigDecimal getCredit();
    String getLibelleOperation();
    BigDecimal getSoldeDebut();
    BigDecimal getSoldeFin();
    String getDateDebut();
    String getDateFin();
    BigDecimal getTotDebit();
    BigDecimal getTotCredit();
    BigDecimal getNumligneMVT();
    BigDecimal getSoldeParLigne();
}
