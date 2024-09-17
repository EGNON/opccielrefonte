package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TJ_TypeModeleMessage", schema = "Parametre")
public class TypeModeleMessage extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeModeleMessage;
    @Basic
    private boolean defaut;
    @ManyToOne
    @JoinColumn(name = "idTypeModele")
    private TypeModele typeModele;
    @ManyToOne
    @JoinColumn(name = "idModeleMsgAlerte")
    private ModeleMsgAlerte modeleMsgAlerte;
    private LocalDateTime dateTypeModeleMessage;
    public TypeModeleMessage() {
    }

    public Long getIdTypeModeleMessage() {
        return idTypeModeleMessage;
    }

    public void setIdTypeModeleMessage(Long idTypeModeleMessage) {
        this.idTypeModeleMessage = idTypeModeleMessage;
    }

    public LocalDateTime getDateTypeModeleMessage() {
        return dateTypeModeleMessage;
    }

    public void setDateTypeModeleMessage(LocalDateTime dateTypeModeleMessage) {
        this.dateTypeModeleMessage = dateTypeModeleMessage;
    }

    public boolean isDefaut() {
        return defaut;
    }

    public void setDefaut(boolean defaut) {
        this.defaut = defaut;
    }

    public TypeModele getTypeModele() {
        return typeModele;
    }

    public void setTypeModele(TypeModele typeModele) {
        this.typeModele = typeModele;
    }

    public ModeleMsgAlerte getModeleMsgAlerte() {
        return modeleMsgAlerte;
    }

    public void setModeleMsgAlerte(ModeleMsgAlerte modeleMsgAlerte) {
        this.modeleMsgAlerte = modeleMsgAlerte;
    }
}
