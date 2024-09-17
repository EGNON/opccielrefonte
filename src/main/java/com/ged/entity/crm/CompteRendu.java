package com.ged.entity.crm;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.Alerte;
import com.ged.entity.Base;
import com.ged.entity.standard.Document;
import com.ged.entity.standard.Produit;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_CompteRendu", schema = "RDV")
public class CompteRendu extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCR;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime dateCR;
    @Temporal(TemporalType.TIME)
    private LocalTime heureDebCR;
    private LocalTime heureFinCR;
    private String objetCR;
    private String appreciation;
    private String description;
    private LocalDateTime dateProchainRDV;
    private double montantPromesse;
    private double montantRealisation;
    private String promesse;
    private String realisation;
    private LocalDateTime dateEffectivePromesse;
    private Boolean estRappel = false;
    private String elementRappel;
    @Column(columnDefinition = "BIT", length = 1)
    private Boolean estValide;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProdASouscrire", referencedColumnName = "idProd")
    //@JsonBackReference(value = "produitasouscrire-cdr")
    private Produit produitASouscrire;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProdSouscrit", referencedColumnName = "idProd")
    //@JsonBackReference(value = "produitsouscrit-cdr")
    private Produit produitSouscrit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOpcvmASouscrire", referencedColumnName = "idOpcvm")
    //@JsonBackReference(value = "produitasouscrire-cdr")
    private Opcvm opcvmASouscrire;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOpcvmSouscrit", referencedColumnName = "idOpcvm")
    //@JsonBackReference(value = "produitsouscrit-cdr")
    private Opcvm opcvmSouscrit;
    @OneToOne(mappedBy = "compteRendu")
    //@JsonManagedReference
    private Alerte alerte;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRdv", referencedColumnName = "idRdv")
    //@JsonBackReference
    private RDV rdv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compteRendu")
    //@JsonManagedReference
    private Set<Document> documents = new HashSet<>();

    public CompteRendu() {
    }

    public CompteRendu(LocalDateTime dateCR, LocalTime heureDebCR, String objetCR, String appreciation, String description, Alerte alerte) {
        this.dateCR = dateCR;
        this.heureDebCR = heureDebCR;
        this.objetCR = objetCR;
        this.appreciation = appreciation;
        this.description = description;
        this.alerte = alerte;
    }

    public void ajouterDocument(Document document)
    {
        this.documents.add(document);
        document.setCompteRendu(this);
    }

    public Opcvm getOpcvmASouscrire() {
        return opcvmASouscrire;
    }

    public void setOpcvmASouscrire(Opcvm opcvmASouscrire) {
        this.opcvmASouscrire = opcvmASouscrire;
    }

    public Opcvm getOpcvmSouscrit() {
        return opcvmSouscrit;
    }

    public void setOpcvmSouscrit(Opcvm opcvmSouscrit) {
        this.opcvmSouscrit = opcvmSouscrit;
    }

    public Long getId()
    {
        return idCR;
    }

    public Long getIdCR() {
        return idCR;
    }

    public void setIdCR(Long idCR) {
        this.idCR = idCR;
    }

    public LocalDateTime getDateCR() {
        return dateCR;
    }

    public void setDateCR(LocalDateTime dateCR) {
        this.dateCR = dateCR;
    }

    public LocalTime getHeureDebCR() {
        return heureDebCR;
    }

    public void setHeureDebCR(LocalTime heureDebCR) {
        this.heureDebCR = heureDebCR;
    }

    public LocalTime getHeureFinCR() {
        return heureFinCR;
    }

    public void setHeureFinCR(LocalTime heureFinCR) {
        this.heureFinCR = heureFinCR;
    }

    public String getObjetCR() {
        return objetCR;
    }

    public void setObjetCR(String objetCR) {
        this.objetCR = objetCR;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateProchainRDV() {
        return dateProchainRDV;
    }

    public void setDateProchainRDV(LocalDateTime dateProchainRDV) {
        this.dateProchainRDV = dateProchainRDV;
    }

    public double getMontantPromesse() {
        return montantPromesse;
    }

    public void setMontantPromesse(double montantPromesse) {
        this.montantPromesse = montantPromesse;
    }

    public double getMontantRealisation() {
        return montantRealisation;
    }

    public void setMontantRealisation(double montantRealisation) {
        this.montantRealisation = montantRealisation;
    }

    public String getPromesse() {
        return promesse;
    }

    public void setPromesse(String promesse) {
        this.promesse = promesse;
    }

    public String getRealisation() {
        return realisation;
    }

    public void setRealisation(String realisation) {
        this.realisation = realisation;
    }

    public LocalDateTime getDateEffectivePromesse() {
        return dateEffectivePromesse;
    }

    public void setDateEffectivePromesse(LocalDateTime dateEffectivePromesse) {
        this.dateEffectivePromesse = dateEffectivePromesse;
    }

    public Boolean getEstRappel() {
        return estRappel;
    }

    public void setEstRappel(Boolean estRappel) {
        this.estRappel = estRappel;
    }

    public String getElementRappel() {
        return elementRappel;
    }

    public void setElementRappel(String elementRappel) {
        this.elementRappel = elementRappel;
    }

    public Produit getProduitASouscrire() {
        return produitASouscrire;
    }

    public void setProduitASouscrire(Produit produitASouscrire) {
        this.produitASouscrire = produitASouscrire;
    }

    public Produit getProduitSouscrit() {
        return produitSouscrit;
    }

    public void setProduitSouscrit(Produit produitSouscrit) {
        this.produitSouscrit = produitSouscrit;
    }

    public Alerte getAlerte() {
        return alerte;
    }

    public void setAlerte(Alerte alerte) {
        this.alerte = alerte;
    }

    public RDV getRdv() {
        return rdv;
    }

    public void setRdv(RDV rdv) {
        this.rdv = rdv;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Boolean getEstValide() {
        return estValide;
    }

    public void setEstValide(Boolean estValide) {
        this.estValide = estValide;
    }

    @Override
    public String toString() {
        return "CompteRendu{" +
                "idCR=" + idCR +
                ", dateCR=" + dateCR +
                ", heureDebCR=" + heureDebCR +
                ", heureFinCR=" + heureFinCR +
                ", objetCR='" + objetCR + '\'' +
                ", appreciation='" + appreciation + '\'' +
                ", description='" + description + '\'' +
                ", dateProchainRDV=" + dateProchainRDV +
                ", montantPromesse=" + montantPromesse +
                ", montantRealisation=" + montantRealisation +
                ", promesse='" + promesse + '\'' +
                ", realisation='" + realisation + '\'' +
                ", dateEffectivePromesse=" + dateEffectivePromesse +
                ", produitASouscrire=" + produitASouscrire +
                ", produitSouscrit=" + produitSouscrit +
                ", alerte=" + alerte +
                ", rdv=" + rdv +
                ", documents=" + documents +
                '}';
    }
}
