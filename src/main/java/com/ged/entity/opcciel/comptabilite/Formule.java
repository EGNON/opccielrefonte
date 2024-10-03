package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "T_Formule", schema = "Comptabilite")
public class Formule extends Base {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormule;
	private Long idOcc;
	private String libelleFormule;
	@ManyToOne()
	@JoinColumn(name = "codeTypeFormule")
	private TypeFormule typeFormule;
	@OneToMany(mappedBy = "formule")
	private Set<ModeleEcritureFormule> modeleEcritureFormules;
	private boolean estSysteme;
	public Formule() {
	}

	public Set<ModeleEcritureFormule> getModeleEcritureFormules() {
		return modeleEcritureFormules;
	}

	public void setModeleEcritureFormules(Set<ModeleEcritureFormule> modeleEcritureFormules) {
		this.modeleEcritureFormules = modeleEcritureFormules;
	}

	public Long getIdOcc() {
		return idOcc;
	}

	public void setIdOcc(Long idOcc) {
		this.idOcc = idOcc;
	}

	public Long getIdFormule() {
		return idFormule;
	}

	public void setIdFormule(Long idFormule) {
		this.idFormule = idFormule;
	}

	public String getLibelleFormule() {
		return libelleFormule;
	}

	public void setLibelleFormule(String libelleFormule) {
		this.libelleFormule = libelleFormule;
	}

	public TypeFormule getTypeFormule() {
		return typeFormule;
	}

	public void setTypeFormule(TypeFormule typeFormule) {
		this.typeFormule = typeFormule;
	}

	public boolean isEstSysteme() {
		return estSysteme;
	}

	public void setEstSysteme(boolean estSysteme) {
		this.estSysteme = estSysteme;
	}

	/*public Set<ModeleEcritureFormule> getModeleEcritureFormules() {
		return modeleEcritureFormules;
	}

	public void setModeleEcritureFormules(Set<ModeleEcritureFormule> modeleEcritureFormules) {
		this.modeleEcritureFormules = modeleEcritureFormules;
	}*/
}
