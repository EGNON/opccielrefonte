package com.ged.dto.standard;

import com.ged.entity.Base;
import com.ged.entity.standard.ClePaysLangue;

public class PaysLangueDto extends Base {

    private ClePaysLangue idPaysLangue;
    private LangueDto langue;
    private PaysDto pays;

    public PaysLangueDto() {
    }

    public ClePaysLangue getIdPaysLangue() {
        return idPaysLangue;
    }

    public void setIdPaysLangue(ClePaysLangue idPaysLangue) {
        this.idPaysLangue = idPaysLangue;
    }

    public LangueDto getLangue() {
        return langue;
    }

    public void setLangue(LangueDto langue) {
        this.langue = langue;
    }

    public PaysDto getPays() {
        return pays;
    }

    public void setPays(PaysDto pays) {
        this.pays = pays;
    }

}
