package com.ged.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.TypeOrdreDto;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.standard.PersonneDto;
import com.ged.dto.titresciel.TitreDto;
import com.ged.entity.Base;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrdreProjection  {
    Long getIdOrdre();
    String getSigleOpcvm();
    String getDenominationOpcvm();
    Long getIdOpcvm();
    Long getIdTitre();
    String getSymbolTitre();
    String getLibelleCotation();
    String getRole();
    LocalDateTime getDateOrdre();
    String getStatut();
    BigDecimal getQuantiteLimite();
    LocalDateTime getDateEnvoi();
    LocalDateTime getDateLimite();
    BigDecimal getCoursLimite();
    Boolean getAccepterPerte();
    Boolean getEstEnvoye();
    BigDecimal getCommissionPlace();
    BigDecimal getCommissionSGI();
    BigDecimal getCommissionDepositaire();
    BigDecimal gettAF();
    BigDecimal getiRVM();
    Double getInteret();
    BigDecimal getPlusOuMoinsValue();
    BigDecimal getQuantiteExecute();
    BigDecimal getMontantNet();
    BigDecimal getMontantBrut();
    String getCommentaires();
    String getLibelleOperation();
    String getLibelleTypeOrdre();
    String getSignataire1();
    String getSignataire2();
    String getSignataire3();
    String getDepositaire();
}
