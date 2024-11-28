package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SoldeCompteFormuleProjection {
    String getNumCpte();
    String sensCpte();
    String getMvt();
    LocalDateTime getDate();
    BigDecimal getSoldeCredit();
    BigDecimal getSoldeDebit();
    BigDecimal getSolde();
    BigDecimal getSoldeReel();
    String getSensSolde();
    String getSortie();
}
