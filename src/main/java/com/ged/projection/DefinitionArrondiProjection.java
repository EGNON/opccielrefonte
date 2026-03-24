package com.ged.projection;

import java.time.LocalDateTime;

public interface DefinitionArrondiProjection {
    String getCodeDefinitionArrondi();
    String getTypeArrondi();
    Boolean getEstParDefaut();
    Long getNbreDecimaux();
    Boolean getEstParValSup();
    String getObserVationModeleArrondis();
    String getLibelleDefinitionArrondi();
    Long getNumLigne();
}
