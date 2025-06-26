package com.ged.entity.opcciel.comptabilite;

import com.ged.entity.Base;
import com.ged.entity.titresciel.TypeTitre;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_ModeleEcritureNatureOperation", schema = "Comptabilite")
public class ModeleEcritureNatureOperation extends Base {
    @EmbeddedId
    private CleModeleEcritureNatureOperation idModeleEcritureNatureOperation;
    @ManyToOne()
    @JoinColumn(name = "codeModeleEcriture")
    @MapsId("codeModeleEcriture")
    private ModeleEcriture modeleEcriture;
    @ManyToOne()
    @JoinColumn(name = "codeNatureOperation")
    @MapsId("codeNatureOperation")
    private  NatureOperation natureOperation;
    @ManyToOne()
    @JoinColumn(name = "codeTypeTitre")
    @MapsId("codeTypeTitre")
    private TypeTitre typeTitre;
	private int numeroOrdre;
	private String userLogin;

    public ModeleEcritureNatureOperation() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public CleModeleEcritureNatureOperation getIdModeleEcritureNatureOperation() {
        return idModeleEcritureNatureOperation;
    }

    public void setIdModeleEcritureNatureOperation(CleModeleEcritureNatureOperation idModeleEcritureNatureOperation) {
        this.idModeleEcritureNatureOperation = idModeleEcritureNatureOperation;
    }

    public ModeleEcriture getModeleEcriture() {
        return modeleEcriture;
    }

    public void setModeleEcriture(ModeleEcriture modeleEcriture) {
        this.modeleEcriture = modeleEcriture;
    }

    public NatureOperation getNatureOperation() {
        return natureOperation;
    }

    public void setNatureOperation(NatureOperation natureOperation) {
        this.natureOperation = natureOperation;
    }

    public TypeTitre getTypeTitre() {
        return typeTitre;
    }

    public void setTypeTitre(TypeTitre typeTitre) {
        this.typeTitre = typeTitre;
    }

    public int getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(int numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }
}
