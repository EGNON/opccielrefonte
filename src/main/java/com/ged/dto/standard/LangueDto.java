package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.entity.Base;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LangueDto extends Base {
    private Long idLangue;
    //OPCCIEL1
    private String codeLangue;
    private String libelleLangue;
//    private PaysDto pays;
    //FIN
    private Set<PaysLangueDto> paysLangues=new HashSet<>();
    public LangueDto() {
    }

    public Set<PaysLangueDto> getPaysLangues() {
        return paysLangues;
    }

    public void setPaysLangues(Set<PaysLangueDto> paysLangues) {
        this.paysLangues = paysLangues;
    }

    public Long getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(Long idLangue) {
        this.idLangue = idLangue;
    }

    public String getCodeLangue() {
        return codeLangue;
    }

    public void setCodeLangue(String codeLangue) {
        this.codeLangue = codeLangue;
    }

    public String getLibelleLangue() {
        return libelleLangue;
    }

    public void setLibelleLangue(String libelleLangue) {
        this.libelleLangue = libelleLangue;
    }

//    public PaysDto getPays() {
//        return pays;
//    }
//
//    public void setPays(PaysDto pays) {
//        this.pays = pays;
//    }
}
