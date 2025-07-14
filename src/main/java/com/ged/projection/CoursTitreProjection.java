package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CoursTitreProjection {
    Long getIdTitre();
    String getSymbolTitre ();
    String getDesignationTitre();
    String getCodePlace();
    String getLibellePlace();
    LocalDateTime getDateCours ();
    BigDecimal getCoursVeille ();
    BigDecimal getOuverture ();
    BigDecimal getHaut ();
    BigDecimal getBas ();
    BigDecimal getCoursSeance ();
}
