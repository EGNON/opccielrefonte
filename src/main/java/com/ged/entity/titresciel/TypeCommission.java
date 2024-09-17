package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_TypeCommission", schema = "Parametre")
public class TypeCommission extends Base {
    @Id
    @Column(length = 17)
    private String codeTypeCommission;
    private String libelleTypeCommission;
//    @OneToMany(mappedBy = "TypeCommission", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<Opcvm> opcvms = new HashSet<>();

    public TypeCommission() {
    }

    public TypeCommission(String codeTypeCommission, String libelleTypeCommission) {
        this.codeTypeCommission = codeTypeCommission;
        this.libelleTypeCommission = libelleTypeCommission;
    }

    public String getCodeTypeCommission() {
        return codeTypeCommission;
    }

    public void setCodeTypeCommission(String codeTypeCommission) {
        this.codeTypeCommission = codeTypeCommission;
    }

    public String getLibelleTypeCommission() {
        return libelleTypeCommission;
    }

    public void setLibelleTypeCommission(String libelleTypeCommission) {
        this.libelleTypeCommission = libelleTypeCommission;
    }

//    public Set<Opcvm> getOpcvms() {
//        return opcvms;
//    }
//
//    public void setOpcvms(Set<Opcvm> opcvms) {
//        this.opcvms = opcvms;
//    }
}
