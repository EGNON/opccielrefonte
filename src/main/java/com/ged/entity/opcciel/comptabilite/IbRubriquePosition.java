package com.ged.entity.opcciel.comptabilite;


import com.ged.entity.Base;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TJ_IbRubriquePosition", schema = "Comptabilite")
public class IbRubriquePosition extends Base {
	@EmbeddedId
	private CleIbRubriquePosition idIbRubriquePosition;
	@ManyToOne()
	@JoinColumn(name = "codeIB")
	@MapsId("codeIB")
	private Ib ib;
	@Column(insertable = false,updatable = false)
	private String codeRubrique;
	@Column(insertable = false,updatable = false)
	private String codePosition;
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "codeRubrique")
//	@MapsId("codeRubrique")
//	private Rubrique rubrique;
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "codePosition")
//	@MapsId("codePosition")
//	private Position position;
	private String libellePosition;
	private String typeValeur;
	private Double valeur;
	@Column(precision = 18, scale = 6)
	private BigDecimal totalBlocage;
	private boolean estModele;

	public IbRubriquePosition() {
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

	public Ib getIb() {
		return ib;
	}

	public void setIb(Ib ib) {
		this.ib = ib;
	}


	public CleIbRubriquePosition getIdIbRubriquePosition() {
		return idIbRubriquePosition;
	}

	public void setIdIbRubriquePosition(CleIbRubriquePosition idIbRubriquePosition) {
		this.idIbRubriquePosition = idIbRubriquePosition;
	}
//
//	public Position getPosition() {
//		return position;
//	}
//
//	public void setPosition(Position position) {
//		this.position = position;
//	}

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

	public BigDecimal getTotalBlocage() {
		return totalBlocage;
	}

	public void setTotalBlocage(BigDecimal totalBlocage) {
		this.totalBlocage = totalBlocage;
	}

	public boolean isEstModele() {
		return estModele;
	}

	public void setEstModele(boolean estModele) {
		this.estModele = estModele;
	}
}
