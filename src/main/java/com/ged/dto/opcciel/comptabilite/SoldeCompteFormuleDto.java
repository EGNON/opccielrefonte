package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SoldeCompteFormuleDto {
	Long idOpcvm;
	String numCompte;
	String codeplan;
	Long idTitre;
	Date date;

	public Long getIdOpcvm() {
		return idOpcvm;
	}

	public void setIdOpcvm(Long idOpcvm) {
		this.idOpcvm = idOpcvm;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public String getCodeplan() {
		return codeplan;
	}

	public void setCodeplan(String codeplan) {
		this.codeplan = codeplan;
	}

	public Long getIdTitre() {
		return idTitre;
	}

	public void setIdTitre(Long idTitre) {
		this.idTitre = idTitre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
