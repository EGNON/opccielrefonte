package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "TJ_StatutTitre",schema = "Titre")
public class StatutTitre {
    @EmbeddedId
    private CleStatutTitre idStatutTitre;
    @ManyToOne
    @JoinColumn(name = "idTitre")
    @MapsId("idTitre")
    //@JsonBackReference
    private Titre titre;
    @ManyToOne
    @JoinColumn(name = "idQualite")
    @MapsId("idQualite")
    private QualiteTitre qualiteTitre;

    public StatutTitre() {
    }

    public CleStatutTitre getIdStatutTitre() {
        return idStatutTitre;
    }

    public void setIdStatutTitre(CleStatutTitre idStatutTitre) {
        this.idStatutTitre = idStatutTitre;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    public QualiteTitre getQualiteTitre() {
        return qualiteTitre;
    }

    public void setQualiteTitre(QualiteTitre qualiteTitre) {
        this.qualiteTitre = qualiteTitre;
    }
}
