package com.ged.entity.crm;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeAffectation", schema = "Titre")
public class TypeAffectation extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeAffectation;
    private String libelleTypeAffectation;
//    @OneToMany(mappedBy = "typeAffectation", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<Opcvm> opcvms = new HashSet<>();

    public TypeAffectation() {
    }

    public TypeAffectation(String libelleTypeAffectation) {
        this.libelleTypeAffectation = libelleTypeAffectation;
    }

    public Long getIdTypeAffectation() {
        return idTypeAffectation;
    }

    public void setIdTypeAffectation(Long idTypeAffectation) {
        this.idTypeAffectation = idTypeAffectation;
    }

    public String getLibelleTypeAffectation() {
        return libelleTypeAffectation;
    }

    public void setLibelleTypeAffectation(String libelleTypeAffectation) {
        this.libelleTypeAffectation = libelleTypeAffectation;
    }
//
//    public Set<Opcvm> getOpcvms() {
//        return opcvms;
//    }
//
//    public void setOpcvms(Set<Opcvm> opcvms) {
//        this.opcvms = opcvms;
//    }
}
