package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Ib", schema = "Comptabilite")
public class Ib extends Base {
	@Id
	@Column(name = "codeIb", length = 20)
    private String codeIB;
	private String libelleIb;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codeTypeIb")
	private TypeIb typeIb;
	private boolean estIbSysteme;

	public Ib() {
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

	public TypeIb getTypeIb() {
		return typeIb;
	}

	public void setTypeIb(TypeIb typeIb) {
		this.typeIb = typeIb;
	}

	public boolean isEstIbSysteme() {
		return estIbSysteme;
	}

	public void setEstIbSysteme(boolean estIbSysteme) {
		this.estIbSysteme = estIbSysteme;
	}
}
