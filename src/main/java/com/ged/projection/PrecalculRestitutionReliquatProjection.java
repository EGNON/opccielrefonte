package com.ged.projection;

import java.math.BigDecimal;

public interface PrecalculRestitutionReliquatProjection {
    Long getIdOpcvm();
    Long getIdSeance();
    Long getIdActionnaire();
    String getNomSigle();
    String getPrenomRaisonSociale();
    BigDecimal getMontant();
}
