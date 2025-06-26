package com.ged.projection;

import java.math.BigDecimal;
import java.util.Date;

public interface SoldeCompteProjection {
    Date getDate();
    BigDecimal getSoldeCredit();
    BigDecimal getSoldeDebit();
    BigDecimal getSolde();
    String getSensSolde();
}
