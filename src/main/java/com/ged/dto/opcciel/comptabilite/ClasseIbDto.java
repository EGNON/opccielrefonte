package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClasseIbDto extends Base {
    private String codeClasseIb;
	private String libelleClasseIb;

    public ClasseIbDto() {
    }

    public String getCodeClasseIb() {
        return codeClasseIb;
    }

    public void setCodeClasseIb(String codeClasseIb) {
        this.codeClasseIb = codeClasseIb;
    }

    public String getLibelleClasseIb() {
        return libelleClasseIb;
    }

    public void setLibelleClasseIb(String libelleClasseIb) {
        this.libelleClasseIb = libelleClasseIb;
    }
}
