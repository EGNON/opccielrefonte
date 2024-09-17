package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_PosteComptable", schema = "Comptabilite")
public class PosteComptable extends Base {
	@EmbeddedId
	private ClePosteComptable idPosteComptable;
    @ManyToOne()
	@JoinColumn(name = "codePlan")
	@MapsId("codePlan")
	private Plan plan;
	@Column(insertable = false,updatable = false, length = 16)
	private String codePosteComptable;
	private String libellePosteComptable;
	private String type;
	private String formule;

	public PosteComptable() {
	}

	public ClePosteComptable getIdPosteComptable() {
		return idPosteComptable;
	}

	public void setIdPosteComptable(ClePosteComptable idPosteComptable) {
		this.idPosteComptable = idPosteComptable;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getLibellePosteComptable() {
		return libellePosteComptable;
	}

	public void setLibellePosteComptable(String libellePosteComptable) {
		this.libellePosteComptable = libellePosteComptable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormule() {
		return formule;
	}

	public void setFormule(String formule) {
		this.formule = formule;
	}

	public String getCodePosteComptable() {
		return codePosteComptable;
	}

	public void setCodePosteComptable(String codePosteComptable) {
		this.codePosteComptable = codePosteComptable;
	}
}
