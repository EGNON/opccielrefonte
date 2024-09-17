package com.ged.entity.titresciel;

import com.ged.entity.titresciel.ClasseTitre;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypeTitre", schema = "Titre")
public class
TypeTitre {
    @Id
    private String codeTypeTitre;
    private String libelleTypeTitre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeClasseTitre")
    private ClasseTitre classeTitre;
//    @CreationTimestamp
//    private LocalDateTime dateCreationServeur;
//    @UpdateTimestamp
//    private LocalDateTime dateDernModifServeur;
//    @UpdateTimestamp
//    private LocalDateTime dateDernModifClient;
//    private long numLigne;
//    @Column(columnDefinition = "BIT", length = 1)
//    private boolean supprimer;
//    @CreationTimestamp
//    @Column(name="rowvers", nullable = false, updatable = false, insertable = false)
//    private Timestamp rowvers;
//    @Basic
//    private String userLogin;
//    @OneToMany(mappedBy = "TypeTitre", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<Opcvm> opcvms = new HashSet<>();

    public TypeTitre() {
    }

    public String getCodeTypeTitre() {
        return codeTypeTitre;
    }

    public void setCodeTypeTitre(String codeTypeTitre) {
        this.codeTypeTitre = codeTypeTitre;
    }

    public ClasseTitre getClasseTitre() {
        return classeTitre;
    }

    public void setClasseTitre(ClasseTitre classeTitre) {
        this.classeTitre = classeTitre;
    }

    public TypeTitre(String libelleTypeTitre) {
        this.libelleTypeTitre = libelleTypeTitre;
    }

    public String getLibelleTypeTitre() {
        return libelleTypeTitre;
    }

    public void setLibelleTypeTitre(String libelleTypeTitre) {
        this.libelleTypeTitre = libelleTypeTitre;
    }

//    public Set<Opcvm> getOpcvms() {
//        return opcvms;
//    }
//
//    public void setOpcvms(Set<Opcvm> opcvms) {
//        this.opcvms = opcvms;
//    }
}
