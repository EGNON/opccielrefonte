package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeEvenement", schema = "Titre")
public class TypeEvenement extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeEvenement;
    private String libelleTypeEvenement;
//    @OneToMany(mappedBy = "typeEvenement", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<Opcvm> opcvms = new HashSet<>();

    public TypeEvenement() {
    }

    public TypeEvenement(String libelleTypeEvenement) {
        this.libelleTypeEvenement = libelleTypeEvenement;
    }

    public Long getIdTypeEvenement() {
        return idTypeEvenement;
    }

    public void setIdTypeEvenement(Long idTypeEvenement) {
        this.idTypeEvenement = idTypeEvenement;
    }

    public String getLibelleTypeEvenement() {
        return libelleTypeEvenement;
    }

    public void setLibelleTypeEvenement(String libelleTypeEvenement) {
        this.libelleTypeEvenement = libelleTypeEvenement;
    }

//    public Set<Opcvm> getOpcvms() {
//        return opcvms;
//    }
//
//    public void setOpcvms(Set<Opcvm> opcvms) {
//        this.opcvms = opcvms;
//    }
}
