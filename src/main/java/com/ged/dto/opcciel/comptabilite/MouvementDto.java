package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.PlanDto;
import com.ged.dto.standard.PersonneDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MouvementDto {

    private Long idMvt;
    private Long idOcc;
	private OperationDto operation;
	private OpcvmDto opcvm;
	private PersonneDto personneActionnaire;
	private PlanDto plan;
	private int numeroOdreModele;
	private String codeModeleEcriture;
	private int numeroOdreLigneMvt;
	private String numCompteComptable;
	private String sensMvt;
	private Double valeur;
	private String typeValeur;
	private IbDto ib;
	private String  rubrique;
	private String codePosition;

	public MouvementDto() {
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

	public OperationDto getOperation() {
		return operation;
	}

	public void setOperation(OperationDto operationDto) {
		this.operation = operationDto;
	}

	public OpcvmDto getOpcvm() {
		return opcvm;
	}

	public void setOpcvm(OpcvmDto opcvm) {
		this.opcvm = opcvm;
	}

	public PersonneDto getPersonneActionnaire() {
		return personneActionnaire;
	}

	public void setPersonneActionnaire(PersonneDto personneActionnaire) {
		this.personneActionnaire = personneActionnaire;
	}

	public PlanDto getPlan() {
		return plan;
	}

	public void setPlan(PlanDto plan) {
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

	public IbDto getIb() {
		return ib;
	}

	public void setIb(IbDto ib) {
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
