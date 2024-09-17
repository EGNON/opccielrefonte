package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "T_MiseEnPlaceDAT", schema = "Operation")
public class MiseEnPlaceDAT extends Operation {
	private String codeBanqueD;
	private String numCompteD;
	private String codeBanqueA;
	private String numCompteA;
	private String typeDAT;
	@Column(precision = 18, scale = 6)
	private BigDecimal soldeDispoCompteD;

	public MiseEnPlaceDAT() {
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

	public BigDecimal getSoldeDispoCompteD() {
		return soldeDispoCompteD;
	}

	public void setSoldeDispoCompteD(BigDecimal soldeDispoCompteD) {
		this.soldeDispoCompteD = soldeDispoCompteD;
	}
}
