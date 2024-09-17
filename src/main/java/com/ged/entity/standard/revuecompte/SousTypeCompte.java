package com.ged.entity.standard.revuecompte;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_SousTypeCompte", schema = "Nomenclature")
public class SousTypeCompte extends Base {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousTypeCompte;
    private String libelleSousTypeCompte;
    private String code;
    @ManyToOne
    @JoinColumn(name = "idTypeCompte")
    private TypeCompte typeCompte;
    public SousTypeCompte() {
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Long getIdSousTypeCompte() {
        return idSousTypeCompte;
    }

    public void setIdSousTypeCompte(Long idSousTypeCompte) {
        this.idSousTypeCompte = idSousTypeCompte;
    }

    public String getLibelleSousTypeCompte() {
        return libelleSousTypeCompte;
    }

    public void setLibelleSousTypeCompte(String libelleSousTypeCompte) {
        this.libelleSousTypeCompte = libelleSousTypeCompte;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}