package com.ged.entity.opcciel;

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
	private Opcvm opcvm;
	private LocalDateTime dateOuverture;
	private LocalDateTime dateFermeture;
	private boolean genere;
	private String typeSeance;
	private Double valeurLiquidative;
	private boolean estEnCours;
	private Long niveau;
	private boolean estEnCloture;
	private BigDecimal navBenchmark ;
	private BigDecimal tauxEquiMarche  ;

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

	public boolean isGenere() {
		return genere;
	}

	public void setGenere(boolean genere) {
		this.genere = genere;
	}

	public String getTypeSeance() {
		return typeSeance;
	}

	public void setTypeSeance(String typeSeance) {
		this.typeSeance = typeSeance;
	}

	public Double getValeurLiquidative() {
		return valeurLiquidative;
	}

	public void setValeurLiquidative(Double valeurLiquidative) {
		this.valeurLiquidative = valeurLiquidative;
	}

	public boolean isEstEnCours() {
		return estEnCours;
	}

	public void setEstEnCours(boolean estEnCours) {
		this.estEnCours = estEnCours;
	}

	public long getNiveau() {
		return niveau;
	}

	public void setNiveau(long niveau) {
		this.niveau = niveau;
	}

	public boolean isEstEnCloture() {
		return estEnCloture;
	}

	public void setEstEnCloture(boolean estEnCloture) {
		this.estEnCloture = estEnCloture;
	}
}
