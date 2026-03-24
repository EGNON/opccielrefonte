package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.ClasseTitre;
import com.ged.entity.titresciel.Place;
import com.ged.entity.titresciel.Registraire;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TableauAmortissementProjection {
    Long getNumEcheance ();
    LocalDateTime getDateEcheance ();
    BigDecimal getNbreTitre ();
    BigDecimal getCapital ();
    BigDecimal getInteret();
    BigDecimal getNbreTitreAmorti();
    BigDecimal getMontantRembourse();
    BigDecimal getAnnuiteTotale();
    BigDecimal getMontantFin();
    String getSymbolTitre();
    String getDesignationTitre();
    String getIsin();
    BigDecimal getNominal();
    Long getIdTitre();
}
