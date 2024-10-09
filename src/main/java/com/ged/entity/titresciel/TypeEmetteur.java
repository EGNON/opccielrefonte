package com.ged.entity.titresciel;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeEmetteur", schema = "Titre")
public class TypeEmetteur extends Base {
    @Id
    private String codeTypeEmetteur;
    private String libelleTypeEmetteur;
//    @Basic
//    private LocalDateTime dateCreationServeur;
//    @Basic
//    private LocalDateTime dateDernModifServeur;
//    @Basic
//    private LocalDateTime dateDernModifClient;
//    @Basic
//    private long numLigne;
//    @Basic
//    private boolean supprimer;
//    @CreationTimestamp
//    @Column(nullable = false, updatable = false, insertable = false)
//    private Timestamp rowvers;
//    @Basic
//    private String userLogin;
//    @OneToMany(mappedBy = "TypeEmetteur", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<Opcvm> opcvms = new HashSet<>();

    public TypeEmetteur() {
    }

    public TypeEmetteur(String codeTypeEmetteur, String libelleTypeEmetteur) {
        this.codeTypeEmetteur = codeTypeEmetteur;
        this.libelleTypeEmetteur = libelleTypeEmetteur;
    }

    public String getCodeTypeEmetteur() {
        return codeTypeEmetteur;
    }

    public void setCodeTypeEmetteur(String codeTypeEmetteur) {
        this.codeTypeEmetteur = codeTypeEmetteur;
    }

    public String getLibelleTypeEmetteur() {
        return libelleTypeEmetteur;
    }

    public void setLibelleTypeEmetteur(String libelleTypeEmetteur) {
        this.libelleTypeEmetteur = libelleTypeEmetteur;
    }

//    public Set<Opcvm> getOpcvms() {
//        return opcvms;
//    }
//
//    public void setOpcvms(Set<Opcvm> opcvms) {
//        this.opcvms = opcvms;
//    }
}
