package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Qualite",schema = "Parametre")
public class Qualite extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQualite;
    private String libelleQualite;
    @Column(columnDefinition = "BIT")
    private Boolean estPH;
    @Column(columnDefinition = "BIT")
    private Boolean estPM;
    @OneToMany(
            mappedBy = "qualite",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private Set<StatutPersonne> statutPersonnes = new HashSet<>();

    public Qualite(){

    }

    public Qualite(String libelleQualite) {
        this.libelleQualite = libelleQualite;
    }

    public Set<StatutPersonne> getStatutPersonnes() {
        return statutPersonnes;
    }

    public void setStatutPersonnes(Set<StatutPersonne> statutPersonnes) {
        this.statutPersonnes = statutPersonnes;
    }

    public Long getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(Long idQualite) {
        this.idQualite = idQualite;
    }

    public String getLibelleQualite() {
        return libelleQualite;
    }

    public void setLibelleQualite(String libelleQualite) {
        this.libelleQualite = libelleQualite;
    }

    public Boolean getEstPH() {
        return estPH;
    }

    public void setEstPH(Boolean estPH) {
        this.estPH = estPH;
    }

    public Boolean getEstPM() {
        return estPM;
    }

    public void setEstPM(Boolean estPM) {
        this.estPM = estPM;
    }

    @Override
    public String toString() {
        return "Qualite{" +
                "idQualite=" + idQualite +
                ", libelleQualite='" + libelleQualite + '\'' +
                ", estPH=" + estPH +
                ", estPM=" + estPM +
                '}';
    }
}
