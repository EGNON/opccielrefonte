package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeEmission", schema = "Titre")
public class TypeEmission extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeEmission;
    private String libelleTypeEmission;
//    @CreationTimestamp
//    private LocalDateTime dateCreationServeur;
//    @UpdateTimestamp
//    private LocalDateTime dateDernModifServeur;
//    @UpdateTimestamp
//    private LocalDateTime dateDernModifClient;
//    private long numLigne;
//    @Column(columnDefinition = "BIT", length = 1)
//    private boolean supprimer;
//
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;
//    @OneToMany(mappedBy = "typeEmission", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<Opcvm> opcvms = new HashSet<>();

    public TypeEmission() {
    }

    public TypeEmission(String libelleTypeEmission) {
        this.libelleTypeEmission = libelleTypeEmission;
    }

    public Long getIdTypeEmission() {
        return idTypeEmission;
    }

    public void setIdTypeEmission(Long idTypeEmission) {
        this.idTypeEmission = idTypeEmission;
    }

    public String getLibelleTypeEmission() {
        return libelleTypeEmission;
    }

    public void setLibelleTypeEmission(String libelleTypeEmission) {
        this.libelleTypeEmission = libelleTypeEmission;
    }

//    public Set<Opcvm> getOpcvms() {
//        return opcvms;
//    }
//
//    public void setOpcvms(Set<Opcvm> opcvms) {
//        this.opcvms = opcvms;
//    }
}
