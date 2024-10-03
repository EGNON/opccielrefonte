package com.ged.entity.standard.revuecompte;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_SousTypeClient", schema = "Nomenclature")
public class SousTypeClient extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousTypeClient;
    private String intitule;
    private String code;
    @ManyToOne
    @JoinColumn(name = "idTypeClient")
    private TypeClient typeClient;

    public SousTypeClient() {
    }

    public TypeClient getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(TypeClient typeClient) {
        this.typeClient = typeClient;
    }

    public Long getIdSousTypeClient() {
        return idSousTypeClient;
    }

    public void setIdSousTypeClient(Long idSousTypeClient) {
        this.idSousTypeClient = idSousTypeClient;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}