package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.crm.CompteRendu;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_Alerte", schema = "Notification")
public class Alerte extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlerte;
    @Basic
    private LocalDateTime dateDebut;
    @Basic
    private LocalDateTime dateFin;
    @Basic
    private int frequence;
    @Basic
    @Column(length = 2500)
    private String destinataire;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idCR", referencedColumnName = "idCR")
    //@JsonBackReference
    private CompteRendu compteRendu;
    @ManyToOne
    @JoinColumn(name = "idPeriodicite")
    private Periodicite periodicite;
    @ManyToOne
    @JoinColumn(name = "idTypePlanification")
    private TypePlanification typePlanification;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "alerte")
    //@JsonManagedReference
    private Set<ProtoAlerte> protoAlertes = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "alerte")
    //@JsonManagedReference
    private Set<NbreJoursAlerte> nbreJoursAlertes = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "alerte")
    //@JsonManagedReference
    private Set<JoursAlerte> joursAlertes = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "alerte")
    //@JsonManagedReference
    private Set<TempsAlerte> tempsAlertes = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "alerte")
    //@JsonManagedReference
    private Set<MessageBox> messageBoxes = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "alerte")
    //@JsonManagedReference
    private Set<DiffusionAlerte> diffusionAlertes = new HashSet<>();

    private String typeAlerte = "Desktop";

    public Alerte() {
    }

    public Alerte(Long idAlerte, LocalDateTime dateDebut, LocalDateTime dateFin, CompteRendu compteRendu) {
        this.idAlerte = idAlerte;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.compteRendu = compteRendu;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public void ajouterProtoAlerte(ProtoAlerte protoAlerte)
    {
        this.protoAlertes.add(protoAlerte);
        protoAlerte.setAlerte(this);
    }

    public void ajouterJourAlerte(JoursAlerte joursAlerte)
    {
        this.joursAlertes.add(joursAlerte);
        joursAlerte.setAlerte(this);
    }

    public void ajouterNbrJourAlerte(NbreJoursAlerte nbreJoursAlerte)
    {
        this.nbreJoursAlertes.add(nbreJoursAlerte);
        nbreJoursAlerte.setAlerte(this);
    }

    public void ajouterTempsAlerte(TempsAlerte tempsAlerte)
    {
        this.tempsAlertes.add(tempsAlerte);
        tempsAlerte.setAlerte(this);
    }

    public Set<NbreJoursAlerte> getNbreJoursAlertes() {
        return nbreJoursAlertes;
    }

    public void setNbreJoursAlertes(Set<NbreJoursAlerte> nbreJoursAlertes) {
        this.nbreJoursAlertes = nbreJoursAlertes;
    }

    public Set<JoursAlerte> getJoursAlertes() {
        return joursAlertes;
    }

    public void setJoursAlertes(Set<JoursAlerte> joursAlertes) {
        this.joursAlertes = joursAlertes;
    }

    public Set<TempsAlerte> getTempsAlertes() {
        return tempsAlertes;
    }

    public void setTempsAlertes(Set<TempsAlerte> tempsAlertes) {
        this.tempsAlertes = tempsAlertes;
    }

    public Set<ProtoAlerte> getProtoAlertes() {
        return protoAlertes;
    }

    public void setProtoAlertes(Set<ProtoAlerte> protoAlertes) {
        this.protoAlertes = protoAlertes;
    }

    public Periodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
    }

    public TypePlanification getTypePlanification() {
        return typePlanification;
    }

    public void setTypePlanification(TypePlanification typePlanification) {
        this.typePlanification = typePlanification;
    }

    public Long getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(Long idAlerte) {
        this.idAlerte = idAlerte;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public CompteRendu getCompteRendu() {
        return compteRendu;
    }

    public void setCompteRendu(CompteRendu compteRendu) {
        this.compteRendu = compteRendu;
    }

    public String getTypeAlerte() {
        return typeAlerte;
    }

    public void setTypeAlerte(String typeAlerte) {
        this.typeAlerte = typeAlerte;
    }

    public Set<MessageBox> getMessageBoxes() {
        return messageBoxes;
    }

    public void setMessageBoxes(Set<MessageBox> messageBoxes) {
        this.messageBoxes = messageBoxes;
    }

    public Set<DiffusionAlerte> getDiffusionAlertes() {
        return diffusionAlertes;
    }

    public void setDiffusionAlertes(Set<DiffusionAlerte> diffusionAlertes) {
        this.diffusionAlertes = diffusionAlertes;
    }

    @Override
    public String toString() {
        return "Alerte{" +
                "idAlerte=" + idAlerte +
                ", frequence=" + frequence +
                ", compteRendu=" + compteRendu +
                ", periodicite=" + periodicite +
                ", typePlanification=" + typePlanification +
                '}';
    }
}
