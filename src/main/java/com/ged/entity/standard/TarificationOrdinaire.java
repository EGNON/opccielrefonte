package com.ged.entity.standard;

import com.ged.entity.Base;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.titresciel.ClasseTitre;
import com.ged.entity.titresciel.Place;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_TarificationOrdinaire", schema = "Tarification")
public class TarificationOrdinaire extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarificationOrdinaire;
    @Column(insertable = false,updatable = false)
    private String codeRole;
    @ManyToOne
    @JoinColumn(name = "codeClasseTitre")
    //@MapsId("codeClasseTitre")
    private ClasseTitre classeTitre;
    private String codePlace;
    @ManyToOne
    @JoinColumn(name = "codePlaceNew",referencedColumnName = "codePlace")
    //@MapsId("codePlace")
    private Place place;
    private long idDepositaire;
    @ManyToOne
    @JoinColumn(name = "idDepositaireNew",referencedColumnName = "idPersonne")
    //@MapsId("idDepositaireNew")
    private Personne depositaire;
    @ManyToOne
    @JoinColumn(name = "idOpcvm")
    private Opcvm opcvm;
    private long idRegistraire;
    @ManyToOne
    @JoinColumn(name = "idRegistraireNew",referencedColumnName = "idPersonne")
    //@MapsId("idRegistraireNew")
    private Personne registraire;

    private double borneInferieur;
    private double borneSuperieur;
    private double taux;
    private double forfait;

    public TarificationOrdinaire() {
    }

    public Long getIdTarificationOrdinaire() {
        return idTarificationOrdinaire;
    }

    public void setIdTarificationOrdinaire(Long idTarificationOrdinaire) {
        this.idTarificationOrdinaire = idTarificationOrdinaire;
    }

    public String getCodePlace() {
        return codePlace;
    }

    public void setCodePlace(String codePlace) {
        this.codePlace = codePlace;
    }

    public long getIdDepositaire() {
        return idDepositaire;
    }

    public void setIdDepositaire(long idDepositaire) {
        this.idDepositaire = idDepositaire;
    }

    public long getIdRegistraire() {
        return idRegistraire;
    }

    public void setIdRegistraire(long idRegistraire) {
        this.idRegistraire = idRegistraire;
    }

    public String getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    public ClasseTitre getClasseTitre() {
        return classeTitre;
    }

    public void setClasseTitre(ClasseTitre classeTitre) {
        this.classeTitre = classeTitre;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Personne getDepositaire() {
        return depositaire;
    }

    public void setDepositaire(Personne depositaire) {
        this.depositaire = depositaire;
    }

    public Opcvm getOpcvm() {
        return opcvm;
    }

    public void setOpcvm(Opcvm opcvm) {
        this.opcvm = opcvm;
    }

    public Personne getRegistraire() {
        return registraire;
    }

    public void setRegistraire(Personne registraire) {
        this.registraire = registraire;
    }

    public double getBorneInferieur() {
        return borneInferieur;
    }

    public void setBorneInferieur(double borneInferieur) {
        this.borneInferieur = borneInferieur;
    }

    public double getBorneSuperieur() {
        return borneSuperieur;
    }

    public void setBorneSuperieur(double borneSuperieur) {
        this.borneSuperieur = borneSuperieur;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getForfait() {
        return forfait;
    }

    public void setForfait(double forfait) {
        this.forfait = forfait;
    }
}
