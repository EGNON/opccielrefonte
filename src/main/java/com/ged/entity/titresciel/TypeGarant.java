package com.ged.entity.titresciel;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeGarant", schema = "Titre")
public class TypeGarant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeGarant;
    private String codeTypeGarant;
    private String libelleTypeGarant;
//    @OneToMany(mappedBy = "TypeGarant", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<Opcvm> opcvms = new HashSet<>();

    public TypeGarant() {
    }

    public TypeGarant(String codeTypeGarant, String libelleTypeGarant) {
        this.codeTypeGarant = codeTypeGarant;
        this.libelleTypeGarant = libelleTypeGarant;
    }

    public Long getIdTypeGarant() {
        return idTypeGarant;
    }

    public void setIdTypeGarant(Long idTypeGarant) {
        this.idTypeGarant = idTypeGarant;
    }

    public String getCodeTypeGarant() {
        return codeTypeGarant;
    }

    public void setCodeTypeGarant(String codeTypeGarant) {
        this.codeTypeGarant = codeTypeGarant;
    }

    public String getLibelleTypeGarant() {
        return libelleTypeGarant;
    }

    public void setLibelleTypeGarant(String libelleTypeGarant) {
        this.libelleTypeGarant = libelleTypeGarant;
    }

//    public Set<Opcvm> getOpcvms() {
//        return opcvms;
//    }
//
//    public void setOpcvms(Set<Opcvm> opcvms) {
//        this.opcvms = opcvms;
//    }
}
