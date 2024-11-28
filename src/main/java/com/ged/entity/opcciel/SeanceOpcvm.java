package com.ged.entity.opcciel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.entity.Base;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TJ_SeanceOpcvm", schema = "Parametre")
public class SeanceOpcvm extends Base {
	@EmbeddedId
	private CleSeanceOpcvm idSeanceOpcvm;
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idOpcvm")
	@MapsId("idOpcvm")
	@JsonIgnore
	private Opcvm opcvm;
	private LocalDateTime dateOuverture;
	private LocalDateTime dateFermeture;
	private Boolean genere;
	private String typeSeance;
	@Column(precision = 18, scale = 6)
	private BigDecimal valeurLiquidative;
	private Boolean estEnCours;
	private Long niveau;
	private Boolean estEnCloture;
	private BigDecimal navBenchmark;
	private BigDecimal tauxEquiMarche;

	public SeanceOpcvm() {
	}

	public BigDecimal getTauxEquiMarche() {
		return tauxEquiMarche;
	}

	public void setTauxEquiMarche(BigDecimal tauxEquiMarche) {
		this.tauxEquiMarche = tauxEquiMarche;
	}

	public BigDecimal getNavBenchmark() {
		return navBenchmark;
	}

	public void setNavBenchmark(BigDecimal navBenchmark) {
		this.navBenchmark = navBenchmark;
	}

	public CleSeanceOpcvm getIdSeanceOpcvm() {
		return idSeanceOpcvm;
	}

	public void setIdSeanceOpcvm(CleSeanceOpcvm idSeanceOpcvm) {
		this.idSeanceOpcvm = idSeanceOpcvm;
	}

	public Opcvm getOpcvm() {
		return opcvm;
	}

	public void setOpcvm(Opcvm opcvm) {
		this.opcvm = opcvm;
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

	public String getTypeSeance() {
		return typeSeance;
	}

	public void setTypeSeance(String typeSeance) {
		this.typeSeance = typeSeance;
	}

	public Boolean getGenere() {
		return genere;
	}

	public void setGenere(Boolean genere) {
		this.genere = genere;
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
}
