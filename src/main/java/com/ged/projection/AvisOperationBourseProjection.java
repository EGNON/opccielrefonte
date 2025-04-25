package com.ged.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.entity.Base;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AvisOperationBourseProjection {
	Long getIdAvis();
	Long getiIdTransaction();
	Long getIdSeance();
	String getCodeNatureOperation();
	LocalDateTime getDateOperation();
	LocalDateTime getDateSaisie();
	LocalDateTime getDateValeur();
	LocalDateTime getDatePiece();
	String getReferencePiece() ;
	BigDecimal getMontant();
	String getEcriture() ;
	String getLibelleOperation();
	Boolean getEstOD();
	String getType();
	String getReferenceAvis() ;

	OrdreDto getOrdre() ;

	LocalDateTime getDateReceptionLivraisonPrevu() ;
	BigDecimal getQuantiteLimite() ;
	BigDecimal getCoursLimite() ;
	BigDecimal getCommissionPlace();
	BigDecimal getCommissionDepositaire() ;
	BigDecimal getCommissionSGI() ;
	BigDecimal gettAF();
	BigDecimal getiRVM();
	BigDecimal getInteret() ;
	BigDecimal getPlusOuMoinsValue();
	BigDecimal getMontantBrut();

	Long getIdOperationRL();
	Long getNumLigne();
	LocalDateTime getDateDernModifClient();
}
