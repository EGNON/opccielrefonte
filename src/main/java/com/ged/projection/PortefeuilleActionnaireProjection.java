package com.ged.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PortefeuilleActionnaireProjection {
    Long getIdOpcvm();
    String getSigleOpcvm();
    String getDenominationOpcvm ();
    Long getIdActionnaire ();
    String getNomSigle();
    String getPrenomRaison();
    String getInttule();
    String getNumCompteSgi();
    String getTelephoneFixe();
    String getTelephoneMobile1();
    String getTelephoneMobile2();
    String getEmail();
    String getBoitePostale();
    String getAdresseComplete();
    LocalDateTime getDateDebutEstimation();
    LocalDateTime getDateEstimation();
    BigDecimal getNombrePartDebut();
    BigDecimal getNombrePartActuel ();
    BigDecimal getNombrePartNanti();
    BigDecimal getValeurLiquidativeActuelle();
    BigDecimal getValorisation ();
    BigDecimal getDividendeReçu ();
    BigDecimal getSoldeEspece();
    BigDecimal getCump();
    BigDecimal getValoInitial();
    BigDecimal getTotalSouscription();
    BigDecimal getTotalRachat();
    BigDecimal getCouponPaye ();
    BigDecimal getPlusValue();
}
