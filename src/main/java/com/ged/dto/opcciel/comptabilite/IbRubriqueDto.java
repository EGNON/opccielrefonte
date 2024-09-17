package com.ged.dto.opcciel.comptabilite;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;
@JsonIgnoreProperties(ignoreUnknown = true)
public class IbRubriqueDto extends Base {
	private IbDto ib;
	private String libelleRubrique;
	private String codeRubrique;
	private TypeRubriqueDto typeRubrique;

	public IbRubriqueDto() {
	}

	public String getCodeRubrique() {
		return codeRubrique;
	}

	public void setCodeRubrique(String codeRubrique) {
		this.codeRubrique = codeRubrique;
	}

	public IbDto getIb() {
		return ib;
	}

	public void setIb(IbDto ib) {
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

	public TypeRubriqueDto getTypeRubrique() {
		return typeRubrique;
	}

	public void setTypeRubrique(TypeRubriqueDto typeRubrique) {
		this.typeRubrique = typeRubrique;
	}
}
