package com.ged.projection;

import java.sql.Date;
import java.time.LocalDateTime;

public interface ObjectifAffecteProjection {
    LocalDateTime getDateAffectation();
    String getDenomination();
    String getLibelleCategorie();
    String getLibelleIndicateur();
    String getLibellePeriodicite();
    double getValeurAffecte();
    double getValeurReelle();

}
