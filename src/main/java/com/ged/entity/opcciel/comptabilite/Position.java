package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Position", schema = "Comptabilite")
public class Position extends Base {
	@Id
	@Column(length = 16)
	private String codePosition;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codeTypePosition")
	private TypePosition typePosition;

	public Position() {
	}

	public String getCodePosition() {
		return codePosition;
	}

	public void setCodePosition(String codePosition) {
		this.codePosition = codePosition;
	}

	public TypePosition getTypePosition() {
		return typePosition;
	}

	public void setTypePosition(TypePosition typePosition) {
		this.typePosition = typePosition;
	}
}
