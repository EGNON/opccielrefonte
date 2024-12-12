package com.ged.dto.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.opcciel.CleSeanceOpcvm;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeanceOpcvmDto {
	private CleSeanceOpcvm idSeanceOpcvm;
	private OpcvmDto opcvm;
	private Long idSeance;
	private LocalDateTime dateOuverture;
	private LocalDateTime dateFermeture;
	private Boolean genere;
	private String typeSeance;
	private BigDecimal valeurLiquidative;
	private Boolean estEnCours;
	private Long niveau;
	private Boolean estEnCloture;
	private BigDecimal navBenchmark;
	private BigDecimal tauxEquiMarche;

	public SeanceOpcvmDto() {
	}

	public CleSeanceOpcvm getIdSeanceOpcvm() {
		return idSeanceOpcvm;
	}

	public void setIdSeanceOpcvm(CleSeanceOpcvm idSeanceOpcvm) {
		this.idSeanceOpcvm = idSeanceOpcvm;
	}

	public OpcvmDto getOpcvm() {
		return opcvm;
	}

	public void setOpcvm(OpcvmDto opcvm) {
		this.opcvm = opcvm;
	}

	public Long getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(Long idSeance) {
		this.idSeance = idSeance;
	}

	public LocalDateTime getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(LocalDateTime dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public LocalDateTime getDateFermeture() {
		return dateFermeture;
	}

	public void setDateFermeture(LocalDateTime dateFermeture) {
		this.dateFermeture = dateFermeture;
	}

	public Boolean getGenere() {
		return genere;
	}

	public void setGenere(Boolean genere) {
		this.genere = genere;
	}

	public String getTypeSeance() {
		return typeSeance;
	}

	public void setTypeSeance(String typeSeance) {
		this.typeSeance = typeSeance;
	}

	public BigDecimal getValeurLiquidative() {
		return valeurLiquidative;
	}

	public void setValeurLiquidative(BigDecimal valeurLiquidative) {
		this.valeurLiquidative = valeurLiquidative;
	}

	public Boolean getEstEnCours() {
		return estEnCours;
	}

	public void setEstEnCours(Boolean estEnCours) {
		this.estEnCours = estEnCours;
	}

	public Long getNiveau() {
		return niveau;
	}

	public void setNiveau(Long niveau) {
		this.niveau = niveau;
	}

	public Boolean getEstEnCloture() {
		return estEnCloture;
	}

	public void setEstEnCloture(Boolean estEnCloture) {
		this.estEnCloture = estEnCloture;
	}

	public BigDecimal getNavBenchmark() {
		return navBenchmark;
	}

	public void setNavBenchmark(BigDecimal navBenchmark) {
		this.navBenchmark = navBenchmark;
	}

	public BigDecimal getTauxEquiMarche() {
		return tauxEquiMarche;
	}

	public void setTauxEquiMarche(BigDecimal tauxEquiMarche) {
		this.tauxEquiMarche = tauxEquiMarche;
	}
}
