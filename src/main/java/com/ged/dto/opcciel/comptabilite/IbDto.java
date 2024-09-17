package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IbDto extends Base {

    private String codeIB;
	private String libelleIb;
	private TypeIbDto typeIb;
	private boolean estIbSysteme;

	public IbDto() {
	}

	public String getCodeIB() {
		return codeIB;
	}

	public void setCodeIB(String codeIB) {
		this.codeIB = codeIB;
	}

	public String getLibelleIb() {
		return libelleIb;
	}

	public void setLibelleIb(String libelleIb) {
		this.libelleIb = libelleIb;
	}

	public TypeIbDto getTypeIb() {
		return typeIb;
	}

	public void setTypeIb(TypeIbDto typeIb) {
		this.typeIb = typeIb;
	}

	public boolean isEstIbSysteme() {
		return estIbSysteme;
	}

	public void setEstIbSysteme(boolean estIbSysteme) {
		this.estIbSysteme = estIbSysteme;
	}
}
