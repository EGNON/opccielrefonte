package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.comptabilite.OperationDto;

@JsonIgnoreProperties(ignoreUnknown = true)

public class MiseEnPlaceDATDto extends OperationDto {
	private String codeBanqueD;
	private String numCompteD;
	private String codeBanqueA;
	private String numCompteA;
	private String typeDAT;
	private Double soldeDispoCompteD;

	public MiseEnPlaceDATDto() {
	}

	public String getCodeBanqueD() {
		return codeBanqueD;
	}

	public void setCodeBanqueD(String codeBanqueD) {
		this.codeBanqueD = codeBanqueD;
	}

	public String getNumCompteD() {
		return numCompteD;
	}

	public void setNumCompteD(String numCompteD) {
		this.numCompteD = numCompteD;
	}

	public String getCodeBanqueA() {
		return codeBanqueA;
	}

	public void setCodeBanqueA(String codeBanqueA) {
		this.codeBanqueA = codeBanqueA;
	}

	public String getNumCompteA() {
		return numCompteA;
	}

	public void setNumCompteA(String numCompteA) {
		this.numCompteA = numCompteA;
	}

	public String getTypeDAT() {
		return typeDAT;
	}

	public void setTypeDAT(String typeDAT) {
		this.typeDAT = typeDAT;
	}

	public Double getSoldeDispoCompteD() {
		return soldeDispoCompteD;
	}

	public void setSoldeDispoCompteD(Double soldeDispoCompteD) {
		this.soldeDispoCompteD = soldeDispoCompteD;
	}
}
