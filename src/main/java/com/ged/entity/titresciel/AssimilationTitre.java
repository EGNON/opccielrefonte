package com.ged.entity.titresciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.*;

@Entity
@Table(name = "T_AssimilationTitre", schema = "OperationCapital")
public class AssimilationTitre extends Operation {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTitreAncien",referencedColumnName = "idTitre")
    private Titre titreAncien;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTitreAncienNouveau",referencedColumnName = "idTitre")
    private Titre titreAncienNouveau;
    private Double qteAncienTitre;
    private Double qteNouveauuTitre;
    private Double qteApresOperation;

    public AssimilationTitre() {
    }

    public Titre getTitreAncien() {
        return titreAncien;
    }

    public void setTitreAncien(Titre titreAncien) {
        this.titreAncien = titreAncien;
    }

    public Titre getTitreAncienNouveau() {
        return titreAncienNouveau;
    }

    public void setTitreAncienNouveau(Titre titreAncienNouveau) {
        this.titreAncienNouveau = titreAncienNouveau;
    }

    public Double getQteAncienTitre() {
        return qteAncienTitre;
    }

    public void setQteAncienTitre(Double qteAncienTitre) {
        this.qteAncienTitre = qteAncienTitre;
    }

    public Double getQteNouveauuTitre() {
        return qteNouveauuTitre;
    }

    public void setQteNouveauuTitre(Double qteNouveauuTitre) {
        this.qteNouveauuTitre = qteNouveauuTitre;
    }

    public Double getQteApresOperation() {
        return qteApresOperation;
    }

    public void setQteApresOperation(Double qteApresOperation) {
        this.qteApresOperation = qteApresOperation;
    }
}
