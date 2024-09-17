package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeModeleMessageDto extends Base {

    private Long idTypeModeleMessage;

    private boolean defaut;
    private LocalDateTime dateTypeModeleMessage;
    private TypeModeleDto typeModele;

    private ModeleMsgAlerteDto modeleMsgAlerte;

    public TypeModeleMessageDto() {
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

    public TypeModeleDto getTypeModele() {
        return typeModele;
    }

    public void setTypeModele(TypeModeleDto typeModele) {
        this.typeModele = typeModele;
    }

    public ModeleMsgAlerteDto getModeleMsgAlerte() {
        return modeleMsgAlerte;
    }

    public void setModeleMsgAlerte(ModeleMsgAlerteDto modeleMsgAlerte) {
        this.modeleMsgAlerte = modeleMsgAlerte;
    }
}
