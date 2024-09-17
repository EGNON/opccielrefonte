package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Langue", schema = "Parametre")
public class Langue extends Base {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLangue;
    //OPCCIEL1
    private String codeLangue;
    private String libelleLangue;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idPays")
//    private Pays pays;
    //FIN
    @OneToMany(mappedBy = "langue")
    private Set<PaysLangue> paysLangues=new HashSet<>();
    public Langue() {
    }

    public Set<PaysLangue> getPaysLangues() {
        return paysLangues;
    }

    public void setPaysLangues(Set<PaysLangue> paysLangues) {
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

//    public Pays getPays() {
//        return pays;
//    }
//
//    public void setPays(Pays pays) {
//        this.pays = pays;
//    }
}
