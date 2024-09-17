package com.ged.entity.opcciel.comptabilite;


import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_IbRubrique", schema = "Comptabilite")
public class IbRubrique extends Base {
	@EmbeddedId
	private CleIbRubrique idIbRubrique;
	@ManyToOne()
	@JoinColumn(name = "codeIB")
	@MapsId("codeIB")
	private Ib ib;
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codeRubrique")
	@MapsId("codeRubrique")
	private Rubrique rubrique;*/
	@Column(insertable = false,updatable = false)
	private String codeRubrique;
	private String libelleRubrique;
	@ManyToOne()
	@JoinColumn(name = "codeTypeRubrique")
	private TypeRubrique typeRubrique;

	public IbRubrique() {
	}

	public CleIbRubrique getIdIbRubrique() {
		return idIbRubrique;
	}

	public void setIdIbRubrique(CleIbRubrique idIbRubrique) {
		this.idIbRubrique = idIbRubrique;
	}

	public String getCodeRubrique() {
		return codeRubrique;
	}

	public void setCodeRubrique(String codeRubrique) {
		this.codeRubrique = codeRubrique;
	}

	public Ib getIb() {
		return ib;
	}

	public void setIb(Ib ib) {
		this.ib = ib;
	}

	/*public Rubrique getRubrique() {
		return rubrique;
	}

	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}*/

	public String getLibelleRubrique() {
		return libelleRubrique;
	}

	public void setLibelleRubrique(String libelleRubrique) {
		this.libelleRubrique = libelleRubrique;
	}

	public TypeRubrique getTypeRubrique() {
		return typeRubrique;
	}

	public void setTypeRubrique(TypeRubrique typeRubrique) {
		this.typeRubrique = typeRubrique;
	}
}
