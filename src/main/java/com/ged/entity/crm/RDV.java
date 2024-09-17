package com.ged.entity.crm;

import com.ged.entity.Base;
import com.ged.entity.standard.*;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRdv", scope = RDV.class)
@Table(name = "T_Rdv", schema = "RDV")
public class RDV extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRdv;
    @Basic
    private Date dateDebRdv;
    @Basic
    private Date dateFinRdv;
    @Basic
    private Time heureFinRdv;
    private Time heureDebutRdv;
    @Basic
    private String objetRdv;
    @Transient
    private String denomination;
    @Basic
    private Date dateDebReelle;
    @Basic
    private Time heureDebReelle;
    @Basic
    private Date dateFinReelle;
    @Basic
    private Time heureFinReelle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPays", referencedColumnName = "idPays")
    //@JsonBackReference
    private Pays pays;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuartier", referencedColumnName = "idQuartier")
    private Quartier quartier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonne", referencedColumnName = "idPersonne")
    private Personne personne;
    @OneToMany(mappedBy = "rdv", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonManagedReference
    private Set<AgentConcerne> agentConcernes = new HashSet<>();
    @OneToMany(mappedBy = "rdv", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@JsonManagedReference
    private Set<Document> documents = new HashSet<>();
//    @OneToMany(mappedBy = "rdv", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    //@JsonManagedReference
//    private Set<Mail> mails = new HashSet<>();
    @OneToOne(mappedBy = "rdv",cascade = CascadeType.ALL)
    //@JsonManagedReference
    private CompteRendu compteRendu;
    @ManyToOne()
    @JoinColumn(name = "idModeleMsgAlerte")
    private ModeleMsgAlerte modeleMsgAlerte;

    public RDV() {

    }

    public RDV(Date dateDebRdv, String objetRdv, Pays pays, Personne personne, CompteRendu compteRendu) {
        this.dateDebRdv = dateDebRdv;
        this.objetRdv = objetRdv;
        this.pays = pays;
        this.personne = personne;
        this.compteRendu = compteRendu;
    }

    public Time getHeureDebutRdv() {
        return heureDebutRdv;
    }

    public void setHeureDebutRdv(Time heureDebutRdv) {
        this.heureDebutRdv = heureDebutRdv;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Date getDateDebReelle() {
        return dateDebReelle;
    }

    public void setDateDebReelle(Date dateDebReelle) {
        this.dateDebReelle = dateDebReelle;
    }

    public ModeleMsgAlerte getModeleMsgAlerte() {
        return modeleMsgAlerte;
    }

    public void setModeleMsgAlerte(ModeleMsgAlerte modeleMsgAlerte) {
        this.modeleMsgAlerte = modeleMsgAlerte;
    }

    public Time getHeureDebReelle() {
        return heureDebReelle;
    }

    public void setHeureDebReelle(Time heureDebReelle) {
        this.heureDebReelle = heureDebReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public Time getHeureFinReelle() {
        return heureFinReelle;
    }

    public void setHeureFinReelle(Time heureFinReelle) {
        this.heureFinReelle = heureFinReelle;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Quartier getQuartier() {
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public Long getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(Long idRdv) {
        this.idRdv = idRdv;
    }

    public Date getDateDebRdv() {
        return dateDebRdv;
    }

    public void setDateDebRdv(Date dateDebRdv) {
        this.dateDebRdv = dateDebRdv;
    }

    public Date getDateFinRdv() {
        return dateFinRdv;
    }

    public void setDateFinRdv(Date dateFinRdv) {
        this.dateFinRdv = dateFinRdv;
    }

    public Time getHeureFinRdv() {
        return heureFinRdv;
    }

    public void setHeureFinRdv(Time heureFinRdv) {
        this.heureFinRdv = heureFinRdv;
    }

    public String getObjetRdv() {
        return objetRdv;
    }

    public void setObjetRdv(String objetRdv) {
        this.objetRdv = objetRdv;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Set<AgentConcerne> getAgentConcernes() {
        return agentConcernes;
    }

    public void setAgentConcernes(Set<AgentConcerne> agentConcernes) {
        this.agentConcernes = agentConcernes;
    }

    public CompteRendu getCompteRendu() {
        return compteRendu;
    }

    public void setCompteRendu(CompteRendu compteRendu) {
        this.compteRendu = compteRendu;
    }

    @Override
    public String toString() {
        return "RDV{" +
                "idRdv=" + idRdv +
                ", dateDebRdv=" + dateDebRdv +
                ", dateFinRdv=" + dateFinRdv +
                ", heureFinRdv=" + heureFinRdv +
                ", objetRdv='" + objetRdv + '\'' +
                ", denomination='" + denomination + '\'' +
                '}';
    }
}
