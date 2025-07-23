package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_ParametreJourFerie", schema = "Parametre")
public class ParametreJourFerie extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numLigne;
    //OPCCIEL1
    private String code;
    private LocalDateTime date;
	private String description;
    private boolean estAnnuel;
    public ParametreJourFerie() {
    }

    public Long getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEstAnnuel() {
        return estAnnuel;
    }

    public void setEstAnnuel(boolean estAnnuel) {
        this.estAnnuel = estAnnuel;
    }

    //FIN
}
