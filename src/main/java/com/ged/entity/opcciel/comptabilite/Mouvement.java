package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import jakarta.persistence.*;

@Entity
@Table(name = "T_Mouvement", schema = "Comptabilite")
public class Mouvement extends Base {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMvt;
    private Long idOcc;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idOperation")
	private Operation operation;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idOpcvm")
	private Opcvm opcvm;
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idActionnaire",referencedColumnName = "idPersonne", nullable = true)
	private Personne personneActionnaire;*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codePlan")
	private Plan plan;
	private int numeroOdreModele;
	@Column(length = 16)
	private String codeModeleEcriture;
	private int numeroOdreLigneMvt;
	private String numCompteComptable;
	private String sensMvt;
	private Double valeur;
	private String typeValeur;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codeIB")
	private Ib ib;
	@Column(length = 16)
	private String  rubrique;
	@Column(length = 16)
	private String codePosition;

	public Mouvement() {
	}

	public Long getIdMvt() {
		return idMvt;
	}

	public void setIdMvt(Long idMvt) {
		this.idMvt = idMvt;
	}

	public Long getIdOcc() {
		return idOcc;
	}

	public void setIdOcc(Long idOcc) {
		this.idOcc = idOcc;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Opcvm getOpcvm() {
		return opcvm;
	}

	public void setOpcvm(Opcvm opcvm) {
		this.opcvm = opcvm;
	}

	/*public Personne getPersonneActionnaire() {
		return personneActionnaire;
	}

	public void setPersonneActionnaire(Personne personneActionnaire) {
		this.personneActionnaire = personneActionnaire;
	}*/

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public int getNumeroOdreModele() {
		return numeroOdreModele;
	}

	public void setNumeroOdreModele(int numeroOdreModele) {
		this.numeroOdreModele = numeroOdreModele;
	}

	public String getCodeModeleEcriture() {
		return codeModeleEcriture;
	}

	public void setCodeModeleEcriture(String codeModeleEcriture) {
		this.codeModeleEcriture = codeModeleEcriture;
	}

	public int getNumeroOdreLigneMvt() {
		return numeroOdreLigneMvt;
	}

	public void setNumeroOdreLigneMvt(int numeroOdreLigneMvt) {
		this.numeroOdreLigneMvt = numeroOdreLigneMvt;
	}

	public String getNumCompteComptable() {
		return numCompteComptable;
	}

	public void setNumCompteComptable(String numCompteComptable) {
		this.numCompteComptable = numCompteComptable;
	}

	public String getSensMvt() {
		return sensMvt;
	}

	public void setSensMvt(String sensMvt) {
		this.sensMvt = sensMvt;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public String getTypeValeur() {
		return typeValeur;
	}

	public void setTypeValeur(String typeValeur) {
		this.typeValeur = typeValeur;
	}

	public Ib getIb() {
		return ib;
	}

	public void setIb(Ib ib) {
		this.ib = ib;
	}

	public String getRubrique() {
		return rubrique;
	}

	public void setRubrique(String rubrique) {
		this.rubrique = rubrique;
	}

	public String getCodePosition() {
		return codePosition;
	}

	public void setCodePosition(String codePosition) {
		this.codePosition = codePosition;
	}
}
