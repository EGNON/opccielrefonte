package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.crm.RDV;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Pays", schema = "Parametre")
public class Pays extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPays;
    //OPCCIEL1
    private String codePays;
    private String nomPays;
    @Column(nullable =true)
    private boolean estUEMOA = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codeZone", referencedColumnName = "codeZone")
    private Zone zone;
    //FIN
    private String libelleFr;
    private String libelleEn;
    private int indicatif;
    private boolean estGafi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codeMonnaie", referencedColumnName = "codeMonnaie")
    //@JsonBackReference
    private Monnaie monnaie;
    @OneToMany(mappedBy = "pays", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<RDV> rdvs = new HashSet<>();
    @OneToMany(mappedBy = "pays")
    //@JsonManagedReference
    private Set<PersonnePhysiquePays> personnePhysiquePays;
    @OneToMany(mappedBy = "paysResidence")
    //@JsonManagedReference
    private Set<Personne> personnes = new HashSet<>();
    @OneToMany(mappedBy = "paysPere")
    //@JsonManagedReference
    private Set<PersonnePhysique> paysPerePersonnePhysiques = new HashSet<>();
    @OneToMany(mappedBy = "paysMere")
    //@JsonManagedReference
    private Set<PersonnePhysique> paysMerePersonnePhysiques = new HashSet<>();
    @OneToMany(mappedBy = "paysConjoint")
    //@JsonManagedReference
    private Set<PersonnePhysique> paysConjointPersonnePhysiques = new HashSet<>();
    @OneToMany(mappedBy = "paysNationalite")
    //@JsonManagedReference
    private Set<PersonnePhysique> paysNationalitePersonnePhysiques = new HashSet<>();

    public Pays() {
    }

    public Pays(String libelleFr, String libelleEn, int indicatif, Monnaie monnaie) {
        this.libelleFr = libelleFr;
        this.libelleEn = libelleEn;
        this.indicatif = indicatif;
        this.monnaie = monnaie;
    }

    public Set<PersonnePhysiquePays> getPersonnePhysiquePays() {
        return personnePhysiquePays;
    }

    public void setPersonnePhysiquePays(Set<PersonnePhysiquePays> personnePhysiquePays) {
        this.personnePhysiquePays = personnePhysiquePays;
    }

    public Long getIdPays() {
        return idPays;
    }

    public void setIdPays(Long idPays) {
        this.idPays = idPays;
    }

    public String getLibelleFr() {
        return libelleFr;
    }

    public void setLibelleFr(String libelleFr) {
        this.libelleFr = libelleFr;
    }

    public String getLibelleEn() {
        return libelleEn;
    }

    public void setLibelleEn(String libelleEn) {
        this.libelleEn = libelleEn;
    }

    public int getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(int indicatif) {
        this.indicatif = indicatif;
    }

    public boolean isEstGafi() {
        return estGafi;
    }

    public void setEstGafi(boolean estGafi) {
        this.estGafi = estGafi;
    }

    public Monnaie getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(Monnaie monnaie) {
        this.monnaie = monnaie;
    }

    public Set<RDV> getRdvs() {
        return rdvs;
    }

    public void setRdvs(Set<RDV> rdvs) {
        this.rdvs = rdvs;
    }

    public Set<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }

    public Set<PersonnePhysique> getPaysPerePersonnePhysiques() {
        return paysPerePersonnePhysiques;
    }

    public void setPaysPerePersonnePhysiques(Set<PersonnePhysique> paysPerePersonnePhysiques) {
        this.paysPerePersonnePhysiques = paysPerePersonnePhysiques;
    }

    public Set<PersonnePhysique> getPaysMerePersonnePhysiques() {
        return paysMerePersonnePhysiques;
    }

    public void setPaysMerePersonnePhysiques(Set<PersonnePhysique> paysMerePersonnePhysiques) {
        this.paysMerePersonnePhysiques = paysMerePersonnePhysiques;
    }

    public Set<PersonnePhysique> getPaysConjointPersonnePhysiques() {
        return paysConjointPersonnePhysiques;
    }

    public void setPaysConjointPersonnePhysiques(Set<PersonnePhysique> paysConjointPersonnePhysiques) {
        this.paysConjointPersonnePhysiques = paysConjointPersonnePhysiques;
    }

    public Set<PersonnePhysique> getPaysNationalitePersonnePhysiques() {
        return paysNationalitePersonnePhysiques;
    }

    public void setPaysNationalitePersonnePhysiques(Set<PersonnePhysique> paysNationalitePersonnePhysiques) {
        this.paysNationalitePersonnePhysiques = paysNationalitePersonnePhysiques;
    }

    //OPCCIEL

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public boolean isEstUEMOA() {
        return estUEMOA;
    }

    public void setEstUEMOA(boolean estUEMOA) {
        this.estUEMOA = estUEMOA;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    //FIN
}
