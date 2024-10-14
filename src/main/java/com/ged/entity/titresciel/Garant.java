package com.ged.entity.titresciel;

import com.ged.entity.Base;
import com.ged.entity.standard.FormeJuridique;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@PrimaryKeyJoinColumn(name="idPersonne")
/*@DiscriminatorValue("RG")*/
@Table(name = "T_Garant", schema = "Titre")
public class Garant extends Base {
    //OPCCIEL 1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGarant;
    private Long idPersonne;
    //OPCCIEL1
    private String libelleTypePersonne;
    private String CodePays;
    private String CodeLangue;
    private String codeCategorieClient;
    private String codeSousTypeClient;
    //FIN OPCCIEL1
    private String numeroAgrementPersonneMorale;
    private String numeroINSAE;
    private String numRegistre;
    private String codeSecteur;
    @Column(nullable = true)
    private BigDecimal CapitalSocial;
    private String siglePersonneMorale;
    private String raisonSociale;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeFormeJuridique")
    private FormeJuridique formeJuridique;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePlace")
    private Place place;
    private String codeTypeGarant;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeTypeEmetteur")
    private TypeEmetteur typeEmetteur;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTypeGarant")
    private TypeGarant typeGarant;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOrganisme", referencedColumnName = "idOrganisme")
    private Organisme organisme;
    //FIN

    @Basic
    private String sigle;
    @Basic
    private String siteWeb;
    //FIN

    public Garant() {
    }

    public Long getIdGarant() {
        return idGarant;
    }

    public void setIdGarant(Long idGarant) {
        this.idGarant = idGarant;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getLibelleTypePersonne() {
        return libelleTypePersonne;
    }

    public void setLibelleTypePersonne(String libelleTypePersonne) {
        this.libelleTypePersonne = libelleTypePersonne;
    }

    public String getCodePays() {
        return CodePays;
    }

    public void setCodePays(String codePays) {
        CodePays = codePays;
    }

    public String getCodeLangue() {
        return CodeLangue;
    }

    public void setCodeLangue(String codeLangue) {
        CodeLangue = codeLangue;
    }

    public String getCodeCategorieClient() {
        return codeCategorieClient;
    }

    public void setCodeCategorieClient(String codeCategorieClient) {
        this.codeCategorieClient = codeCategorieClient;
    }

    public String getCodeSousTypeClient() {
        return codeSousTypeClient;
    }

    public void setCodeSousTypeClient(String codeSousTypeClient) {
        this.codeSousTypeClient = codeSousTypeClient;
    }

    public String getNumeroAgrementPersonneMorale() {
        return numeroAgrementPersonneMorale;
    }

    public void setNumeroAgrementPersonneMorale(String numeroAgrementPersonneMorale) {
        this.numeroAgrementPersonneMorale = numeroAgrementPersonneMorale;
    }

    public String getNumeroINSAE() {
        return numeroINSAE;
    }

    public void setNumeroINSAE(String numeroINSAE) {
        this.numeroINSAE = numeroINSAE;
    }

    public String getNumRegistre() {
        return numRegistre;
    }

    public void setNumRegistre(String numRegistre) {
        this.numRegistre = numRegistre;
    }

    public String getCodeSecteur() {
        return codeSecteur;
    }

    public void setCodeSecteur(String codeSecteur) {
        this.codeSecteur = codeSecteur;
    }

    public BigDecimal getCapitalSocial() {
        return CapitalSocial;
    }

    public void setCapitalSocial(BigDecimal capitalSocial) {
        CapitalSocial = capitalSocial;
    }

    public String getSiglePersonneMorale() {
        return siglePersonneMorale;
    }

    public void setSiglePersonneMorale(String siglePersonneMorale) {
        this.siglePersonneMorale = siglePersonneMorale;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public FormeJuridique getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(FormeJuridique formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public String getCodeTypeGarant() {
        return codeTypeGarant;
    }

    public void setCodeTypeGarant(String codeTypeGarant) {
        this.codeTypeGarant = codeTypeGarant;
    }

    public TypeEmetteur getTypeEmetteur() {
        return typeEmetteur;
    }

    public void setTypeEmetteur(TypeEmetteur typeEmetteur) {
        this.typeEmetteur = typeEmetteur;
    }

    public TypeGarant getTypeGarant() {
        return typeGarant;
    }

    public void setTypeGarant(TypeGarant typeGarant) {
        this.typeGarant = typeGarant;
    }

    public Organisme getOrganisme() {
        return organisme;
    }

    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    //OPCCIEL1

    //FIN
}
