package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PlaceCoursTitreProjection {
    String getCodePlace ();
    String getLibellePlace();
    LocalDateTime getDateCours ();
}
