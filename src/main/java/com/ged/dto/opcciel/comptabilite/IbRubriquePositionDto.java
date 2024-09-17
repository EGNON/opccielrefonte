package com.ged.dto.opcciel.comptabilite;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IbRubriquePositionDto extends Base {
	private IbDto ib;
	private String codeRubrique;
	private String codePosition;
	private String libellePosition;
	private String typeValeur;
	private Double valeur;
	private Double totalBlocage;
	private boolean estModele;

	public IbRubriquePositionDto() {
	}

	public String getCodeRubrique() {
		return codeRubrique;
	}

	public void setCodeRubrique(String codeRubrique) {
		this.codeRubrique = codeRubrique;
	}

	public String getCodePosition() {
		return codePosition;
	}

	public void setCodePosition(String codePosition) {
		this.codePosition = codePosition;
	}

	public IbDto getIb() {
		return ib;
	}

	public void setIb(IbDto ib) {
		this.ib = ib;
	}

	public String getLibellePosition() {
		return libellePosition;
	}

	public void setLibellePosition(String libellePosition) {
		this.libellePosition = libellePosition;
	}

	public String getTypeValeur() {
		return typeValeur;
	}

	public void setTypeValeur(String typeValeur) {
		this.typeValeur = typeValeur;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public Double getTotalBlocage() {
		return totalBlocage;
	}

	public void setTotalBlocage(Double totalBlocage) {
		this.totalBlocage = totalBlocage;
	}

	public boolean isEstModele() {
		return estModele;
	}

	public void setEstModele(boolean estModele) {
		this.estModele = estModele;
	}
}
