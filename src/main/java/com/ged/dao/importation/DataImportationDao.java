package com.ged.dao.importation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ged.dao.opcciel.FormeJuridiqueDao;
import com.ged.dao.opcciel.NormalAssimileDao;
import com.ged.dao.opcciel.SocieteDeGestionDao;
import com.ged.dao.opcciel.comptabilite.*;
import com.ged.dao.standard.*;
import com.ged.dao.titresciel.*;
import com.ged.entity.opcciel.SocieteDeGestion;
import com.ged.entity.opcciel.comptabilite.*;
import com.ged.entity.standard.*;
import com.ged.entity.titresciel.Place;
import com.ged.entity.titresciel.TypeEmission;
import com.ged.entity.titresciel.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
//@Transactional(value = "chainedTransactionManager")
public class DataImportationDao {
    @PersistenceContext
    EntityManager emRefonte;

    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emRefonte;*/

    @Autowired
    @Qualifier("titrescielEntityManagerFactory")
    private EntityManager emTitre;
    private final TypeGarantDao typeGarantDao;
    private final GarantDao garantDao;
    private final EmetteurDao emetteurDao;
    private final DepositaireDao depositaireDao;
    private final RegistraireDao registraireDao;
    private final VilleDao villeDao;
    private final PaysDao paysDao;
    private final FormeJuridiqueDao formeJuridiqueDao;
    private final FormuleDao formuleDao;
    private final ModeleEcritureDao modeleEcritureDao;
    private final DetailModeleDao detailModeleDao;
    private final ModeleEcritureFormuleDao modeleEcritureFormuleDao;
    private final PosteComptableDao posteComptableDao;
    private final ModeleEcritureNatureOperationDao modeleEcritureNatureOperationDao;
    private final TitreDao titreDao;
    private final PlaceDao placeDao;
    private final MonnaieDao monnaieDao;
    private final TypeTitreDao typeTitreDao;
    private final TypeEmissionDao typeEmissionDao;
    private final NormalAssimileDao normalAssimileDao;
    private final SecteurDao secteurDao;
    private final CompartimentDao compartimentDao;
    private final SocieteDeGestionDao societeDeGestionDao;
    private final OpcDao opcDao;
    private final FormeJuridiqueOpcDao formeJuridiqueOpcDao;
    private final ClassificationOPCDao classificationOPCDao;
    private final TypeAffectationTitreDao typeAffectationTitreDao;
    private final DatDao datDao;
    private final DroitDao droitDao;
    private final ActionDao actionDao;
    private final ObligationDao obligationDao;
    private final TypeActionDao typeActionDao;
    private final SousTypeActionDao sousTypeActionDao;
    private final ModeAmortissementDao modeAmortissementDao;
    private final TypeAmortissementDao typeAmortissementDao;
    private final TypeObligationDao typeObligationDao;
    private final NatureTcnDao natureTcnDao;
    private final TcnDao tcnDao;
    private final CoursTitreDao coursTitreDao;
    private final TypeOperationDao typeOperationDao;
    private final PlanDao planDao;
    private final CompteComptableDao compteComptableDao;
    private final TypeIbDao typeIbDao;
    private final ClasseIbDao classeIbDao;
    private final TypePositionDao typePositionDao;
    private final TypeRubriqueDao typeRubriqueDao;
    private final IbDao ibDao;
    private final CorrespondanceDao correspondanceDao;
    private final TypeFormuleDao typeFormuleDao;
    private final JournalDao journalDao;
    private final IbRubriqueDao ibRubriqueDao;
    private final IbRubriquePositionDao ibRubriquePositionDao;
    private final NatureOperationDao natureOperationDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final QualiteDao qualiteDao;
    private final ModeCalculInteretDao modeCalculInteretDao;

    public DataImportationDao(
            TypeGarantDao typeGarantDao, GarantDao garantDao, EmetteurDao emetteurDao, DepositaireDao depositaireDao, RegistraireDao registraireDao, VilleDao villeDao,
            PaysDao paysDao,
            FormeJuridiqueDao formeJuridiqueDao,
            FormuleDao formuleDao, ModeleEcritureDao modeleEcritureDao, DetailModeleDao detailModeleDao, ModeleEcritureFormuleDao modeleEcritureFormuleDao, PosteComptableDao posteComptableDao, ModeleEcritureNatureOperationDao modeleEcritureNatureOperationDao, TitreDao titreDao,
            PlaceDao placeDao,
            MonnaieDao monnaieDao,
            TypeTitreDao typeTitreDao,
            TypeEmissionDao typeEmissionDao,
            NormalAssimileDao normalAssimileDao,
            SecteurDao secteurDao,
            CompartimentDao compartimentDao,
            SocieteDeGestionDao societeDeGestionDao, OpcDao opcDao,
            FormeJuridiqueOpcDao formeJuridiqueOpcDao,
            ClassificationOPCDao classificationOPCDao,
            TypeAffectationTitreDao typeAffectationTitreDao,
            DatDao datDao,
            DroitDao droitDao,
            ActionDao actionDao,
            ObligationDao obligationDao,
            TypeActionDao typeActionDao,
            SousTypeActionDao sousTypeActionDao,
            ModeAmortissementDao modeAmortissementDao,
            TypeAmortissementDao typeAmortissementDao,
            TypeObligationDao typeObligationDao,
            NatureTcnDao natureTcnDao,
            TcnDao tcnDao,
            CoursTitreDao coursTitreDao,
            TypeOperationDao typeOperationDao,
            PlanDao planDao,
            CompteComptableDao compteComptableDao,
            TypeIbDao typeIbDao,
            ClasseIbDao classeIbDao,
            TypePositionDao typePositionDao,
            TypeRubriqueDao typeRubriqueDao,
            IbDao ibDao,
            CorrespondanceDao correspondanceDao, TypeFormuleDao typeFormuleDao, JournalDao journalDao,
            IbRubriqueDao ibRubriqueDao,
            IbRubriquePositionDao ibRubriquePositionDao, NatureOperationDao natureOperationDao, PersonneMoraleDao personneMoraleDao, QualiteDao qualiteDao, ModeCalculInteretDao modeCalculInteretDao) {
        this.typeGarantDao = typeGarantDao;
        this.garantDao = garantDao;
        this.emetteurDao = emetteurDao;
        this.depositaireDao = depositaireDao;
        this.registraireDao = registraireDao;
        this.villeDao = villeDao;
        this.paysDao = paysDao;
        this.formeJuridiqueDao = formeJuridiqueDao;
        this.formuleDao = formuleDao;
        this.modeleEcritureDao = modeleEcritureDao;
        this.detailModeleDao = detailModeleDao;
        this.modeleEcritureFormuleDao = modeleEcritureFormuleDao;
        this.posteComptableDao = posteComptableDao;
        this.modeleEcritureNatureOperationDao = modeleEcritureNatureOperationDao;
        this.titreDao = titreDao;
        this.placeDao = placeDao;
        this.monnaieDao = monnaieDao;
        this.typeTitreDao = typeTitreDao;
        this.typeEmissionDao = typeEmissionDao;
        this.normalAssimileDao = normalAssimileDao;
        this.secteurDao = secteurDao;
        this.compartimentDao = compartimentDao;
        this.societeDeGestionDao = societeDeGestionDao;
        this.opcDao = opcDao;
        this.formeJuridiqueOpcDao = formeJuridiqueOpcDao;
        this.classificationOPCDao = classificationOPCDao;
        this.typeAffectationTitreDao = typeAffectationTitreDao;
        this.datDao = datDao;
        this.droitDao = droitDao;
        this.actionDao = actionDao;
        this.obligationDao = obligationDao;
        this.typeActionDao = typeActionDao;
        this.sousTypeActionDao = sousTypeActionDao;
        this.modeAmortissementDao = modeAmortissementDao;
        this.typeAmortissementDao = typeAmortissementDao;
        this.typeObligationDao = typeObligationDao;
        this.natureTcnDao = natureTcnDao;
        this.tcnDao = tcnDao;
        this.coursTitreDao = coursTitreDao;
        this.typeOperationDao = typeOperationDao;
        this.planDao = planDao;
        this.compteComptableDao = compteComptableDao;
        this.typeIbDao = typeIbDao;
        this.classeIbDao = classeIbDao;
        this.typePositionDao = typePositionDao;
        this.typeRubriqueDao = typeRubriqueDao;
        this.ibDao = ibDao;
        this.correspondanceDao = correspondanceDao;
        this.typeFormuleDao = typeFormuleDao;
        this.journalDao = journalDao;
        this.ibRubriqueDao = ibRubriqueDao;
        this.ibRubriquePositionDao = ibRubriquePositionDao;
        this.natureOperationDao = natureOperationDao;
        this.personneMoraleDao = personneMoraleDao;
        this.qualiteDao = qualiteDao;
        this.modeCalculInteretDao = modeCalculInteretDao;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> emetteur() {
        List<Object[]> result;
        try {
            Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur");
            result = (List<Object[]>) qEmetteur.getResultList();
            for (Object[] oEmetteur : result) {
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oEmetteur[1]).trim(), ((String)oEmetteur[2]).trim());
                PersonneMorale emetteur = new PersonneMorale();
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
                emetteur.setSiglePersonneMorale((String) oEmetteur[1]);
                emetteur.setSigle((String) oEmetteur[1]);
                emetteur.setDenomination((String) oEmetteur[2]);
                emetteur.setRaisonSociale((String) oEmetteur[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("EMETTEUR").orElse(new Qualite());
                if (qualite.getIdQualite() == null) {
                    qualite.setLibelleQualite("EMETTEUR");
                    qualite.setEstPH(false);
                    qualite.setEstPM(true);
                    qualite = this.emRefonte.merge(qualite);
                }
                emetteur = this.emRefonte.merge(emetteur);
                Emetteur em = emetteurDao.findById(((BigDecimal)oEmetteur[0]).longValue()).orElse(new Emetteur());
                if(em.getIdEmetteur() != null) {
                    em.setIdPersonne(emetteur.getIdPersonne());
                    this.emRefonte.merge(em);
                }
                StatutPersonne statutPersonne = new StatutPersonne(emetteur, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(emetteur.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);
            }
        }
        catch (Exception e) {
            System.out.println("Message d'erreur : " + e.getMessage());
        }
        finally {
            emTitre.close();
            emRefonte.close();
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> registraire() {
        List<Object[]> result = null;
        try {
            Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire");
            result = (List<Object[]>) qRegistraire.getResultList();
            for (Object[] oRegistraire : result) {
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oRegistraire[1]).trim(), ((String)oRegistraire[2]).trim());
                PersonneMorale registraire = new PersonneMorale();
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
                registraire.setSiglePersonneMorale((String) oRegistraire[1]);
                registraire.setSigle((String) oRegistraire[1]);
                registraire.setDenomination((String) oRegistraire[2]);
                registraire.setRaisonSociale((String) oRegistraire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("REGISTRAIRE").orElse(new Qualite());
                if (qualite.getIdQualite() == null) {
                    qualite.setLibelleQualite("REGISTRAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                registraire = this.emRefonte.merge(registraire);
                Registraire reg = registraireDao.findById(((BigDecimal)oRegistraire[0]).longValue()).orElse(new Registraire());
                if(reg.getIdRegistraire() != null) {
                    reg.setIdPersonne(registraire.getIdPersonne());
                    this.emRefonte.merge(reg);
                }
                StatutPersonne statutPersonne = new StatutPersonne(registraire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(registraire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);
            }
        }
        catch (Exception e) {
            System.out.println("Message d'erreur : " + e.getMessage());
        }
        finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> depositaire() {
        List<Object[]> result = null;
        try {
            Query qDepositaire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Depositaire");
            result = (List<Object[]>) qDepositaire.getResultList();
            for (Object[] oDepositaire : result) {
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String) oDepositaire[1]).trim(), ((String) oDepositaire[2]).trim());
                PersonneMorale depositaire = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    depositaire = oListe.get(0);
                }
                //depositaire.setLibelleTypePersonne(depositaire.getLibelleTypePersonne() + ";DP");
                depositaire.setPlace(null);
                depositaire.setSiglePersonneMorale((String) oDepositaire[1]);
                depositaire.setSigle((String) oDepositaire[1]);
                depositaire.setDenomination((String) oDepositaire[2]);
                depositaire.setRaisonSociale((String) oDepositaire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("DEPOSITAIRE").orElse(new Qualite());
                if (qualite.getIdQualite() == null) {
                    qualite.setLibelleQualite("DEPOSITAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                depositaire = this.emRefonte.merge(depositaire);
                Depositaire dep = depositaireDao.findById(((BigDecimal)oDepositaire[0]).longValue()).orElse(new Depositaire());
                if(dep.getIdDepositaire() != null) {
                    dep.setIdPersonne(depositaire.getIdPersonne());
                    this.emRefonte.merge(dep);
                }
                StatutPersonne statutPersonne = new StatutPersonne(depositaire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(depositaire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);
            }
        }
        catch (Exception e) {
            System.out.println("Message d'erreur : " + e.getMessage());
        }
        finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> garant() {
        List<Object[]> result = null;
        try {
            Query q = emTitre.createNativeQuery("SELECT * FROM Titre.T_Garant");
            result = (List<Object[]>)q.getResultList();
            for (Object[] o : result) {
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)o[1]).trim(), ((String)o[2]).trim());
                PersonneMorale garant = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    garant = oListe.get(0);
                }
                //depositaire.setLibelleTypePersonne(depositaire.getLibelleTypePersonne() + ";DP");
                garant.setPlace(null);
                garant.setSiglePersonneMorale((String) o[1]);
                garant.setSigle((String) o[1]);
                garant.setDenomination((String) o[2]);
                garant.setRaisonSociale((String) o[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("GARANT").orElse(new Qualite());
                if (qualite.getIdQualite() == null) {
                    qualite.setLibelleQualite("GARANT");
                    qualite = this.emRefonte.merge(qualite);
                }
                TypeGarant typeGarant = typeGarantDao.findByCodeTypeGarantIgnoreCase((String)o[4]).orElse(null);
                garant.setTypeGarant(typeGarant);
                Pays pays = paysDao.findByCodePaysIgnoreCase((String)o[3]).orElse(null);
                garant.setPaysResidence(pays);
                garant = this.emRefonte.merge(garant);
                Garant gar = garantDao.findById(((BigDecimal)o[0]).longValue()).orElse(new Garant());
                if(gar.getIdGarant() != null) {
                    gar.setIdPersonne(garant.getIdPersonne());
                    this.emRefonte.merge(gar);
                }
                StatutPersonne statutPersonne = new StatutPersonne(garant, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(garant.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);
            }
        }
        catch (Exception e) {
            System.out.println("Message d'erreur : " + e.getMessage());
        }
        finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    public List<Object[]> titre() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_Titre");
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idTitre = (BigDecimal) res[0];
                var symbolTitre = ((String) res[1]).trim();
                var designationTitre = ((String) res[2]).trim();
                var codePlace = ((String) res[3]).trim();
                var idDepositaire = (BigDecimal) res[4];
                var libelleTypeEmission = ((String) res[5]).trim();
                var codeTypeTitre = ((String) res[6]).trim();
                var libelleSecteurBoursier = ((String) res[7]).trim();
                var libelleCompartiment = ((String) res[8]).trim();
                var codeNominalAssimile = ((String) res[9]).trim();
                var codePays = ((String) res[10]).trim();
                var idRegistraire = (BigDecimal) res[11];
                var idEmetteur = (BigDecimal) res[12];
                var lotMinimum = (Integer) res[13];
                var nominal = (BigDecimal) res[14];
                var codeIsin = ((String) res[15]).trim();
                var dateOuverture = ((Timestamp) res[16]).toLocalDateTime();
                var dateEmission = ((Timestamp) res[17]).toLocalDateTime();
                var dateCloture = ((Timestamp) res[18]).toLocalDateTime();
                var estActif = (Boolean) res[19];
                var appliqueFiscaliteLocale = (Boolean) res[20];
                var tauxFiscaliteLocale = (Double) res[21];
                var tauxFiscalitePays = (Double) res[22];
                var estReglementaire = (Boolean) res[23];
                var borneInferieurFluctuation = (Double) res[24];
                var borneSuperieurFluctuation = (Double) res[25];
                var irvm = (Double) res[26];
                var libelleCotation = ((String) res[27]).trim();

                Pays pays = paysDao.findByCodePaysIgnoreCase(codePays).orElse(null);
                TypeEmission typeEmission = typeEmissionDao.findByLibelleTypeEmissionIgnoreCase(libelleTypeEmission).orElse(null);
                if (typeEmission == null) {
                    typeEmission = new TypeEmission();
                    typeEmission.setLibelleTypeEmission(libelleTypeEmission);
                }
                Place place = placeDao.findById(codePlace).orElse(null);
                if (place == null) {
                    place = new Place();
                    place.setCodePlace(codePlace);
                }
                TypeTitre typeTitre = typeTitreDao.findById(codeTypeTitre).orElse(null);
                if (typeTitre == null) {
                    typeTitre = new TypeTitre();
                    typeTitre.setCodeTypeTitre(codeTypeTitre);
                    typeTitre.setLibelleTypeTitre(null);
                }
                NormalAssimile normalAssimile = normalAssimileDao.findById(codeNominalAssimile).orElse(null);
                if (normalAssimile == null) {
                    normalAssimile = new NormalAssimile();
                    normalAssimile.setCodeNormalAssimile(codeNominalAssimile);
                }
                Query qDepositaire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Depositaire WHERE idDepositaire = ?");
                qDepositaire.setParameter(1, idDepositaire);
                var oDepositaire = (Object[]) qDepositaire.getSingleResult();
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String) oDepositaire[1]).trim(), ((String) oDepositaire[2]).trim());
                PersonneMorale depositaire = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    depositaire = oListe.get(0);
                }
                depositaire.setIdOcc(idDepositaire.longValue());
                //depositaire.setLibelleTypePersonne(depositaire.getLibelleTypePersonne() + ";DP");
                depositaire.setPlace(null);
                depositaire.setSiglePersonneMorale((String) oDepositaire[1]);
                depositaire.setSigle((String) oDepositaire[1]);
                depositaire.setDenomination((String) oDepositaire[2]);
                depositaire.setRaisonSociale((String) oDepositaire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("DEPOSITAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("DEPOSITAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                depositaire = this.emRefonte.merge(depositaire);
                StatutPersonne statutPersonne = new StatutPersonne(depositaire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(depositaire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire WHERE idRegistraire = ?");
                qRegistraire.setParameter(1, idRegistraire);
                var oRegistraire = (Object[]) qRegistraire.getSingleResult();
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oRegistraire[1]).trim(), ((String)oRegistraire[2]).trim());
                PersonneMorale registraire = new PersonneMorale();
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
                registraire.setIdOcc(idRegistraire.longValue());
                registraire.setSiglePersonneMorale((String) oRegistraire[1]);
                registraire.setSigle((String) oRegistraire[1]);
                registraire.setDenomination((String) oRegistraire[2]);
                registraire.setRaisonSociale((String) oRegistraire[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("REGISTRAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("REGISTRAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                registraire = this.emRefonte.merge(registraire);
                statutPersonne = new StatutPersonne(registraire, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(registraire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur WHERE idEmetteur = ?");
                qEmetteur.setParameter(1, idEmetteur);
                var oEmetteur = (Object[]) qEmetteur.getSingleResult();
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oEmetteur[1]).trim(), ((String)oEmetteur[2]).trim());
                PersonneMorale emetteur = new PersonneMorale();
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
                emetteur.setIdOcc(idEmetteur.longValue());
                emetteur.setSiglePersonneMorale((String) oEmetteur[1]);
                emetteur.setSigle((String) oEmetteur[1]);
                emetteur.setDenomination((String) oEmetteur[2]);
                emetteur.setRaisonSociale((String) oEmetteur[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("EMETTEUR").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("EMETTEUR");
                    qualite = this.emRefonte.merge(qualite);
                }
                emetteur = this.emRefonte.merge(emetteur);
                statutPersonne = new StatutPersonne(emetteur, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(emetteur.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);
                Secteur secteur = secteurDao.findByLibelleSecteurIgnoreCase(libelleSecteurBoursier).orElse(null);
                if (secteur == null) {
                    secteur = new Secteur();
                    secteur.setLibelleSecteur(libelleSecteurBoursier);
                }
                Compartiment compartiment = compartimentDao.findByLibelleCompartimentIgnoreCase(libelleCompartiment).orElse(null);
                if (compartiment == null) {
                    compartiment = new Compartiment();
                    compartiment.setLibelleCompartiment(libelleCompartiment);
                }

                Titre titre = new Titre();
                var old = titreDao.findByIdOcc(idTitre.longValue()).orElse(null);
                if (old != null)
                    titre = old;
                titre.setIdOcc(idTitre.longValue());
                titre.setSymbolTitre(symbolTitre);
                titre.setDesignationTitre(designationTitre);
                titre.setCodeIsin(codeIsin);
                titre.setEmetteur(emetteur);
                titre.setCompartiment(compartiment);
                titre.setTypeTitre(typeTitre);
                titre.setDepositaire(depositaire);
                titre.setDateCloture(dateCloture);
                titre.setDateEmission(dateEmission);
                titre.setDateOuverture(dateOuverture);
                titre.setPlace(place);
                titre.setTypeEmission(typeEmission);
                titre.setNormalAssimile(normalAssimile);
                titre.setRegistraire(registraire);
                titre.setNominal(nominal);
                titre.setLotMinimum(lotMinimum);
                titre.setEstActif(estActif);
                titre.setAppliqueFiscaliteLocale(appliqueFiscaliteLocale);
                titre.setTauxFiscaliteLocale(tauxFiscaliteLocale);
                titre.setTauxFiscalitePays(tauxFiscalitePays);
                titre.setEstReglementaire(estReglementaire);
                titre.setBorneInferieurFluctuation(borneInferieurFluctuation);
                titre.setBorneSuperieurFluctuation(borneSuperieurFluctuation);
                titre.setIrvm(irvm);
                titre.setLibelleCotation(libelleCotation);
                titre.setSecteur(secteur);
                titre.setPays(pays);

                titre = emRefonte.merge(titre);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> opc() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_OPC");
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idTitre = (BigDecimal) res[0];
                var symbolTitre = ((String) res[1]).trim();
                var designationTitre = ((String) res[2]).trim();
                var codePlace = ((String) res[3]).trim();
                var idDepositaire = (BigDecimal) res[4];
                var libelleTypeEmission = ((String) res[5]).trim();
                var codeTypeTitre = ((String) res[6]).trim();
                var libelleSecteurBoursier = ((String) res[7]).trim();
                var libelleCompartiment = ((String) res[8]).trim();
                var codeNominalAssimile = ((String) res[9]).trim();
                var codePays = ((String) res[10]).trim();
                var idRegistraire = (BigDecimal) res[11];
                var idEmetteur = (BigDecimal) res[12];
                var lotMinimum = (Integer) res[13];
                var nominal = (BigDecimal) res[14];
                var codeIsin = ((String) res[15]).trim();
                var dateOuverture = ((Timestamp) res[16]).toLocalDateTime();
                var dateEmission = ((Timestamp) res[17]).toLocalDateTime();
                var dateCloture = ((Timestamp) res[18]).toLocalDateTime();
                var estActif = (Boolean) res[19];
                var appliqueFiscaliteLocale = (Boolean) res[20];
                var tauxFiscaliteLocale = (Double) res[21];
                var tauxFiscalitePays = (Double) res[22];
                var estReglementaire = (Boolean) res[23];
                var borneInferieurFluctuation = (Double) res[24];
                var borneSuperieurFluctuation = (Double) res[25];
                var irvm = (Double) res[26];
                var libelleCotation = ((String) res[27]).trim();
                var codeFormeJuridiqueOpc = ((String) res[28]).trim();
                var vlOrigine = (BigDecimal) res[29];
                var periodiciteVlNbre = (Integer) res[30];
                var periodiciteVlUnite = ((String) res[31]).trim();
                var codeClassificationOpc = ((String) res[32]).trim();
                var libelleTypeAffectationVl = ((String) res[33]).trim();

                Pays pays = paysDao.findByCodePaysIgnoreCase(codePays).orElse(null);
                TypeEmission typeEmission = typeEmissionDao.findByLibelleTypeEmissionIgnoreCase(libelleTypeEmission).orElse(null);
                if (typeEmission == null) {
                    typeEmission = new TypeEmission();
                    typeEmission.setLibelleTypeEmission(libelleTypeEmission);
                }
                Place place = placeDao.findById(codePlace).orElse(null);
                if (place == null) {
                    place = new Place();
                    place.setCodePlace(codePlace);
                }
                TypeTitre typeTitre = typeTitreDao.findById(codeTypeTitre).orElse(null);
                if (typeTitre == null) {
                    typeTitre = new TypeTitre();
                    typeTitre.setCodeTypeTitre(codeTypeTitre);
                    typeTitre.setLibelleTypeTitre(null);
                }
                NormalAssimile normalAssimile = normalAssimileDao.findById(codeNominalAssimile).orElse(null);
                if (normalAssimile == null) {
                    normalAssimile = new NormalAssimile();
                    normalAssimile.setCodeNormalAssimile(codeNominalAssimile);
                }

                Query qDepositaire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Depositaire WHERE idDepositaire = ?");
                qDepositaire.setParameter(1, idDepositaire);
                var oDepositaire = (Object[]) qDepositaire.getSingleResult();
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oDepositaire[1]).trim(), ((String)oDepositaire[2]).trim());
                PersonneMorale depositaire = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    depositaire = oListe.get(0);
                }
                System.out.println("OPC Dépositaire : " + ((String) oDepositaire[1]).trim());
                depositaire.setIdOcc(idDepositaire.longValue());
                depositaire.setPlace(null);
                depositaire.setSiglePersonneMorale((String) oDepositaire[1]);
                depositaire.setSigle((String) oDepositaire[1]);
                depositaire.setDenomination((String) oDepositaire[2]);
                depositaire.setRaisonSociale((String) oDepositaire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("DEPOSITAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("DEPOSITAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                depositaire = this.emRefonte.merge(depositaire);
                StatutPersonne statutPersonne = new StatutPersonne(depositaire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(depositaire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire WHERE idRegistraire = ?");
                qRegistraire.setParameter(1, idRegistraire);
                var oRegistraire = (Object[]) qRegistraire.getSingleResult();
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oRegistraire[1]).trim(), ((String)oRegistraire[2]).trim());
                PersonneMorale registraire = new PersonneMorale();
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
                registraire.setIdOcc(idRegistraire.longValue());
                registraire.setSiglePersonneMorale((String) oRegistraire[1]);
                registraire.setSigle((String) oRegistraire[1]);
                registraire.setDenomination((String) oRegistraire[2]);
                registraire.setRaisonSociale((String) oRegistraire[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("REGISTRAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("REGISTRAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                registraire = this.emRefonte.merge(registraire);
                statutPersonne = new StatutPersonne(registraire, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(registraire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur WHERE idEmetteur = ?");
                qEmetteur.setParameter(1, idEmetteur);
                var oEmetteur = (Object[]) qEmetteur.getSingleResult();
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oEmetteur[1]).trim(), ((String)oEmetteur[2]).trim());
                PersonneMorale emetteur = new PersonneMorale();
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
                emetteur.setIdOcc(idEmetteur.longValue());
                emetteur.setSiglePersonneMorale((String) oEmetteur[1]);
                emetteur.setSigle((String) oEmetteur[1]);
                emetteur.setDenomination((String) oEmetteur[2]);
                emetteur.setRaisonSociale((String) oEmetteur[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("EMETTEUR").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("EMETTEUR");
                    qualite = this.emRefonte.merge(qualite);
                }
                emetteur = this.emRefonte.merge(emetteur);
                statutPersonne = new StatutPersonne(emetteur, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(emetteur.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Secteur secteur = secteurDao.findByLibelleSecteurIgnoreCase(libelleSecteurBoursier).orElse(null);
                if (secteur == null) {
                    secteur = new Secteur();
                    secteur.setLibelleSecteur(libelleSecteurBoursier);
                }
                Compartiment compartiment = compartimentDao.findByLibelleCompartimentIgnoreCase(libelleCompartiment).orElse(null);
                if (compartiment == null) {
                    compartiment = new Compartiment();
                    compartiment.setLibelleCompartiment(libelleCompartiment);
                }

                FormeJuridiqueOpc formeJuridiqueOpc = formeJuridiqueOpcDao.findByCodeFormeJuridiqueOpcIgnoreCase(codeFormeJuridiqueOpc).orElse(new FormeJuridiqueOpc());
                Query qFormeOpc = emTitre.createNativeQuery("SELECT * FROM Titre.T_FormeJuridiqueOPC WHERE codeFormeJuridiqueOPC = ?");
                qFormeOpc.setParameter(1, codeFormeJuridiqueOpc);
                var oFormeOpc = (Object[]) qFormeOpc.getSingleResult();
                formeJuridiqueOpc.setCodeFormeJuridiqueOpc((String) oFormeOpc[0]);
                formeJuridiqueOpc.setLibelleFormeJuridiqueOpc((String) oFormeOpc[1]);

                ClassificationOPC classificationOPC = classificationOPCDao.findByCodeClassificationIgnoreCase(codeClassificationOpc).orElse(new ClassificationOPC());
                Query qClassicationOpc = emTitre.createNativeQuery("SELECT * FROM Titre.T_ClassificationOPC WHERE codeClassificationOPC = ?");
                qClassicationOpc.setParameter(1, codeClassificationOpc);
                var oClassicationOpc = (Object[]) qClassicationOpc.getSingleResult();
                classificationOPC.setCodeClassification((String) oClassicationOpc[0]);
                classificationOPC.setLibelleClassification((String) oClassicationOpc[1]);

                TypeAffectationVL typeAffectationTitre = typeAffectationTitreDao.findByLibelleTypeAffectationVLIgnoreCase(libelleTypeAffectationVl).orElse(null);
                Query qTypeAffectationTitre = emTitre.createNativeQuery("SELECT * FROM Titre.T_TypeAffectationVL WHERE libelleTypeAffectationVL = ?");
                qTypeAffectationTitre.setParameter(1, libelleTypeAffectationVl);
                var oTypeAffectationTitre = (Object[]) qTypeAffectationTitre.getSingleResult();
                assert typeAffectationTitre != null;
                typeAffectationTitre.setLibelleTypeAffectationVL((String) oTypeAffectationTitre[0]);

                Opc titre = new Opc();
                var old = opcDao.findByTypeVMAndIdOcc("OPC", idTitre.longValue()).orElse(null);
                if (old != null)
                    titre = old;
                titre.setIdOcc(idTitre.longValue());
                titre.setSymbolTitre(symbolTitre);
                titre.setDesignationTitre(designationTitre);
                titre.setCodeIsin(codeIsin);
                titre.setEmetteur(emetteur);
                titre.setCompartiment(compartiment);
                titre.setTypeTitre(typeTitre);
                titre.setDepositaire(depositaire);
                titre.setDateCloture(dateCloture);
                titre.setDateEmission(dateEmission);
                titre.setDateOuverture(dateOuverture);
                titre.setPlace(place);
                titre.setTypeEmission(typeEmission);
                titre.setNormalAssimile(normalAssimile);
                titre.setRegistraire(registraire);
                titre.setNominal(nominal);
                titre.setLotMinimum(lotMinimum);
                titre.setEstActif(estActif);
                titre.setAppliqueFiscaliteLocale(appliqueFiscaliteLocale);
                titre.setTauxFiscaliteLocale(tauxFiscaliteLocale);
                titre.setTauxFiscalitePays(tauxFiscalitePays);
                titre.setEstReglementaire(estReglementaire);
                titre.setBorneInferieurFluctuation(borneInferieurFluctuation);
                titre.setBorneSuperieurFluctuation(borneSuperieurFluctuation);
                titre.setIrvm(irvm);
                titre.setLibelleCotation(libelleCotation);
                titre.setSecteur(secteur);
                titre.setPays(pays);
                titre.setFormeJuridiqueOpc(formeJuridiqueOpc);
                titre.setVlOrigine(vlOrigine);
                titre.setPeriodiciteVlNbre(periodiciteVlNbre);
                titre.setPeriodiciteVlUnite(periodiciteVlUnite);
                titre.setClassificationOPC(classificationOPC);
                titre.setTypeAffectationTitre(typeAffectationTitre);

                titre = emRefonte.merge(titre);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> dat() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_DAT");
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idTitre = (BigDecimal) res[0];
                var symbolTitre = ((String) res[1]).trim();
                var designationTitre = ((String) res[2]).trim();
                var codePlace = ((String) res[3]).trim();
                var idDepositaire = (BigDecimal) res[4];
                var libelleTypeEmission = ((String) res[5]).trim();
                var codeTypeTitre = ((String) res[6]).trim();
                var libelleSecteurBoursier = ((String) res[7]).trim();
                var libelleCompartiment = ((String) res[8]).trim();
                var codeNominalAssimile = ((String) res[9]).trim();
                var codePays = ((String) res[10]).trim();
                var idRegistraire = (BigDecimal) res[11];
                var idEmetteur = (BigDecimal) res[12];
                var lotMinimum = (Integer) res[13];
                var nominal = (BigDecimal) res[14];
                var codeIsin = ((String) res[15]).trim();
                var dateOuverture = ((Timestamp) res[16]).toLocalDateTime();
                var dateEmission = ((Timestamp) res[17]).toLocalDateTime();
                var dateCloture = ((Timestamp) res[18]).toLocalDateTime();
                var estActif = (Boolean) res[19];
                var appliqueFiscaliteLocale = (Boolean) res[20];
                var tauxFiscaliteLocale = (Double) res[21];
                var tauxFiscalitePays = (Double) res[22];
                var estReglementaire = (Boolean) res[23];
                var borneInferieurFluctuation = (Double) res[24];
                var borneSuperieurFluctuation = (Double) res[25];
                var irvm = (Double) res[26];
                var libelleCotation = ((String) res[27]).trim();
                var datePremierPaiement = ((Timestamp) res[28]).toLocalDateTime();
                var dateDernierPaiement = ((Timestamp) res[29]).toLocalDateTime();
                var dateJouissance = ((Timestamp) res[30]).toLocalDateTime();
                var dureeNbre = (Integer) res[31];
                var dureeUnite = ((String) res[32]).trim();
                var periodiciteNbre = (Integer) res[33];
                var periodiciteUnite = ((String) res[34]).trim();
                var usance = (Integer) res[35];
                var tauxBrut = (Double) res[36];
                var tauxNet = (Double) res[37];
                var typeDAT = ((String) res[38]).trim();
                var codeBanque = ((String) res[39]).trim();
            /*var codeFormeJuridiqueOpc = ((String)res[28]).trim();
            var vlOrigine = (BigDecimal)res[29];
            var codeClassificationOpc = ((String)res[32]).trim();
            var libelleTypeAffectationVl = ((String)res[33]).trim();*/

                Pays pays = paysDao.findByCodePaysIgnoreCase(codePays).orElse(null);
                TypeEmission typeEmission = typeEmissionDao.findByLibelleTypeEmissionIgnoreCase(libelleTypeEmission).orElse(null);
                if (typeEmission == null) {
                    typeEmission = new TypeEmission();
                    typeEmission.setLibelleTypeEmission(libelleTypeEmission);
                }
                Place place = placeDao.findById(codePlace).orElse(null);
                if (place == null) {
                    place = new Place();
                    place.setCodePlace(codePlace);
                }
                TypeTitre typeTitre = typeTitreDao.findById(codeTypeTitre).orElse(null);
                if (typeTitre == null) {
                    typeTitre = new TypeTitre();
                    typeTitre.setCodeTypeTitre(codeTypeTitre);
                    typeTitre.setLibelleTypeTitre(null);
                }
                NormalAssimile normalAssimile = normalAssimileDao.findById(codeNominalAssimile).orElse(null);
                if (normalAssimile == null) {
                    normalAssimile = new NormalAssimile();
                    normalAssimile.setCodeNormalAssimile(codeNominalAssimile);
                }

                Query qDepositaire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Depositaire WHERE idDepositaire = ?");
                qDepositaire.setParameter(1, idDepositaire);
                var oDepositaire = (Object[]) qDepositaire.getSingleResult();
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String) oDepositaire[1]).trim(), ((String) oDepositaire[2]).trim());
                PersonneMorale depositaire = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    depositaire = oListe.get(0);
                }
                depositaire.setIdOcc(idDepositaire.longValue());
                depositaire.setPlace(null);
                depositaire.setSiglePersonneMorale((String) oDepositaire[1]);
                depositaire.setSigle((String) oDepositaire[1]);
                depositaire.setDenomination((String) oDepositaire[2]);
                depositaire.setRaisonSociale((String) oDepositaire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("DEPOSITAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("DEPOSITAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                depositaire = this.emRefonte.merge(depositaire);
                StatutPersonne statutPersonne = new StatutPersonne(depositaire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(depositaire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire WHERE idRegistraire = ?");
                qRegistraire.setParameter(1, idRegistraire);
                var oRegistraire = (Object[]) qRegistraire.getSingleResult();
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oRegistraire[1]).trim(), ((String)oRegistraire[2]).trim());
                PersonneMorale registraire = new PersonneMorale();
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
                registraire.setIdOcc(idRegistraire.longValue());
                registraire.setSiglePersonneMorale((String) oRegistraire[1]);
                registraire.setSigle((String) oRegistraire[1]);
                registraire.setDenomination((String) oRegistraire[2]);
                registraire.setRaisonSociale((String) oRegistraire[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("REGISTRAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("REGISTRAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                registraire = this.emRefonte.merge(registraire);
                statutPersonne = new StatutPersonne(registraire, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(registraire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur WHERE idEmetteur = ?");
                qEmetteur.setParameter(1, idEmetteur);
                var oEmetteur = (Object[]) qEmetteur.getSingleResult();
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oEmetteur[1]).trim(), ((String)oEmetteur[2]).trim());
                PersonneMorale emetteur = new PersonneMorale();
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
                emetteur.setIdOcc(idEmetteur.longValue());
                emetteur.setSiglePersonneMorale((String) oEmetteur[1]);
                emetteur.setSigle((String) oEmetteur[1]);
                emetteur.setDenomination((String) oEmetteur[2]);
                emetteur.setRaisonSociale((String) oEmetteur[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("EMETTEUR").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("EMETTEUR");
                    qualite = this.emRefonte.merge(qualite);
                }
                emetteur = this.emRefonte.merge(emetteur);
                statutPersonne = new StatutPersonne(emetteur, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(emetteur.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Secteur secteur = secteurDao.findByLibelleSecteurIgnoreCase(libelleSecteurBoursier).orElse(null);
                if (secteur == null) {
                    secteur = new Secteur();
                    secteur.setLibelleSecteur(libelleSecteurBoursier);
                }
                Compartiment compartiment = compartimentDao.findByLibelleCompartimentIgnoreCase(libelleCompartiment).orElse(null);
                if (compartiment == null) {
                    compartiment = new Compartiment();
                    compartiment.setLibelleCompartiment(libelleCompartiment);
                }
            /*FormeJuridiqueOpc formeJuridiqueOpc = formeJuridiqueOpcDao.findByCodeFormeJuridiqueOpcIgnoreCase(codeFormeJuridiqueOpc).orElse(null);
            if(formeJuridiqueOpc == null)
            {
                formeJuridiqueOpc = new FormeJuridiqueOpc();
                formeJuridiqueOpc.setCodeFormeJuridiqueOpc(codeFormeJuridiqueOpc);
            }
            else
            {
                Query q = emTitre.createNativeQuery("SELECT * FROM Titre.T_FormeJuridiqueOPC WHERE codeFormeJuridiqueOPC = ?");
                q.setParameter(1, codeFormeJuridiqueOpc);
                var o = (Object[])q.getSingleResult();
                formeJuridiqueOpc.setCodeFormeJuridiqueOpc((String)o[0]);
                formeJuridiqueOpc.setLibelleFormeJuridiqueOpc((String)o[1]);
            }
            ClassificationOPC classificationOPC = classificationOPCDao.findByCodeClassificationIgnoreCase(codeClassificationOpc).orElse(null);
            if(classificationOPC == null)
            {
                classificationOPC = new ClassificationOPC();
                classificationOPC.setCodeClassification(codeClassificationOpc);
            }
            else
            {
                Query q = emTitre.createNativeQuery("SELECT * FROM Titre.T_ClassificationOPC WHERE codeClassificationOPC = ?");
                q.setParameter(1, codeClassificationOpc);
                var o = (Object[])q.getSingleResult();
                classificationOPC.setCodeClassification((String)o[0]);
                classificationOPC.setLibelleClassification((String)o[1]);
            }
            TypeAffectationTitre typeAffectationTitre = typeAffectationTitreDao.findByLibelleTypeAffectationIgnoreCase(libelleTypeAffectationVl).orElse(null);
            if(typeAffectationTitre == null)
            {
                typeAffectationTitre = new TypeAffectationTitre();
                typeAffectationTitre.setLibelleTypeAffectation(libelleTypeAffectationVl);
            }
            else
            {
                Query q = emTitre.createNativeQuery("SELECT * FROM Titre.T_TypeAffectationVL WHERE libelleTypeAffectationVL = ?");
                q.setParameter(1, libelleTypeAffectationVl);
                var o = (Object[])q.getSingleResult();
                typeAffectationTitre.setLibelleTypeAffectation((String)o[0]);
            }*/

                Dat titre = new Dat();
                var old = datDao.findByTypeVMAndIdOcc("DAT", idTitre.longValue()).orElse(null);
                if (old != null)
                    titre = old;
                titre.setIdOcc(idTitre.longValue());
                titre.setSymbolTitre(symbolTitre);
                titre.setDesignationTitre(designationTitre);
                titre.setCodeIsin(codeIsin);
                titre.setEmetteur(emetteur);
                titre.setCompartiment(compartiment);
                titre.setTypeTitre(typeTitre);
                titre.setDepositaire(depositaire);
                titre.setDateCloture(dateCloture);
                titre.setDateEmission(dateEmission);
                titre.setDateOuverture(dateOuverture);
                titre.setPlace(place);
                titre.setTypeEmission(typeEmission);
                titre.setNormalAssimile(normalAssimile);
                titre.setRegistraire(registraire);
                titre.setNominal(nominal);
                titre.setLotMinimum(lotMinimum);
                titre.setEstActif(estActif);
                titre.setAppliqueFiscaliteLocale(appliqueFiscaliteLocale);
                titre.setTauxFiscaliteLocale(tauxFiscaliteLocale);
                titre.setTauxFiscalitePays(tauxFiscalitePays);
                titre.setEstReglementaire(estReglementaire);
                titre.setBorneInferieurFluctuation(borneInferieurFluctuation);
                titre.setBorneSuperieurFluctuation(borneSuperieurFluctuation);
                titre.setIrvm(irvm);
                titre.setLibelleCotation(libelleCotation);
                titre.setSecteur(secteur);
                titre.setPays(pays);
                titre.setPeriodiciteNbre(periodiciteNbre);
                titre.setPeriodiciteUnite(periodiciteUnite);
                titre.setDateJouissance(dateJouissance);
                titre.setDatePremierPaiement(datePremierPaiement);
                titre.setDateDernierPaiement(dateDernierPaiement);
                titre.setDureeNbre(dureeNbre);
                titre.setDureeUnite(dureeUnite);
                titre.setUsance(usance);
                titre.setTauxBrut(tauxBrut);
                titre.setTauxNet(tauxNet);
                titre.setTypeDAT(typeDAT);
                titre.setCodeBanque(codeBanque);
            /*titre.setFormeJuridiqueOpc(formeJuridiqueOpc);
            titre.setVlOrigine(vlOrigine);
            titre.setClassificationOPC(classificationOPC);
            titre.setTypeAffectationTitre(typeAffectationTitre);*/

                titre = emRefonte.merge(titre);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> droit() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_Droit");
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idTitre = (BigDecimal) res[0];
                var symbolTitre = ((String) res[1]).trim();
                var designationTitre = ((String) res[2]).trim();
                var codePlace = ((String) res[3]).trim();
                var idDepositaire = (BigDecimal) res[4];
                var libelleTypeEmission = ((String) res[5]).trim();
                var codeTypeTitre = ((String) res[6]).trim();
                var libelleSecteurBoursier = ((String) res[7]).trim();
                var libelleCompartiment = ((String) res[8]).trim();
                var codeNominalAssimile = ((String) res[9]).trim();
                var codePays = ((String) res[10]).trim();
                var idRegistraire = (BigDecimal) res[11];
                var idEmetteur = (BigDecimal) res[12];
                var lotMinimum = (Integer) res[13];
                var nominal = (BigDecimal) res[14];
                var codeIsin = ((String) res[15]).trim();
                var dateOuverture = ((Timestamp) res[16]).toLocalDateTime();
                var dateEmission = ((Timestamp) res[17]).toLocalDateTime();
                var dateCloture = ((Timestamp) res[18]).toLocalDateTime();
                var estActif = (Boolean) res[19];
                var appliqueFiscaliteLocale = (Boolean) res[20];
                var tauxFiscaliteLocale = (Double) res[21];
                var tauxFiscalitePays = (Double) res[22];
                var estReglementaire = (Boolean) res[23];
                var borneInferieurFluctuation = (Double) res[24];
                var borneSuperieurFluctuation = (Double) res[25];
                var irvm = (Double) res[26];
                var libelleCotation = ((String) res[27]).trim();
                var idActionLiee = (BigDecimal) res[28];
                var dateDebutNegociation = ((Timestamp) res[29]).toLocalDateTime();
                var dateFinNegociation = ((Timestamp) res[30]).toLocalDateTime();
                var idNouvelleAction = (BigDecimal) res[31];
                var dateJouissance = ((Timestamp) res[32]).toLocalDateTime();
                var pariteAncienNbre = (Integer) res[33];
                var pariteAncienCours = (BigDecimal) res[34];
                var pariteNouveauNbre = (Integer) res[35];
                var pariteNouveauCours = (BigDecimal) res[36];
                var coursTheorique = (BigDecimal) res[37];
                var prixUnitaireSouscription = (BigDecimal) res[38];
                var coursActionExDroit = (BigDecimal) res[39];

                Pays pays = paysDao.findByCodePaysIgnoreCase(codePays).orElse(null);
                TypeEmission typeEmission = typeEmissionDao.findByLibelleTypeEmissionIgnoreCase(libelleTypeEmission).orElse(null);
                if (typeEmission == null) {
                    typeEmission = new TypeEmission();
                    typeEmission.setLibelleTypeEmission(libelleTypeEmission);
                }
                Place place = placeDao.findById(codePlace).orElse(null);
                if (place == null) {
                    place = new Place();
                    place.setCodePlace(codePlace);
                }
                TypeTitre typeTitre = typeTitreDao.findById(codeTypeTitre).orElse(null);
                if (typeTitre == null) {
                    typeTitre = new TypeTitre();
                    typeTitre.setCodeTypeTitre(codeTypeTitre);
                    typeTitre.setLibelleTypeTitre(null);
                }
                NormalAssimile normalAssimile = normalAssimileDao.findById(codeNominalAssimile).orElse(null);
                if (normalAssimile == null) {
                    normalAssimile = new NormalAssimile();
                    normalAssimile.setCodeNormalAssimile(codeNominalAssimile);
                }

                Query qDepositaire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Depositaire WHERE idDepositaire = ?");
                qDepositaire.setParameter(1, idDepositaire);
                var oDepositaire = (Object[]) qDepositaire.getSingleResult();
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String) oDepositaire[1]).trim(), ((String) oDepositaire[2]).trim());
                PersonneMorale depositaire = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    depositaire = oListe.get(0);
                }
                depositaire.setIdOcc(idDepositaire.longValue());
                depositaire.setPlace(null);
                depositaire.setSiglePersonneMorale((String) oDepositaire[1]);
                depositaire.setSigle((String) oDepositaire[1]);
                depositaire.setDenomination((String) oDepositaire[2]);
                depositaire.setRaisonSociale((String) oDepositaire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("DEPOSITAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("DEPOSITAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                depositaire = this.emRefonte.merge(depositaire);
                StatutPersonne statutPersonne = new StatutPersonne(depositaire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(depositaire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire WHERE idRegistraire = ?");
                qRegistraire.setParameter(1, idRegistraire);
                var oRegistraire = (Object[]) qRegistraire.getSingleResult();
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oRegistraire[1]).trim(), ((String)oRegistraire[2]).trim());
                PersonneMorale registraire = new PersonneMorale();
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
                registraire.setIdOcc(idRegistraire.longValue());
                registraire.setSiglePersonneMorale((String) oRegistraire[1]);
                registraire.setSigle((String) oRegistraire[1]);
                registraire.setDenomination((String) oRegistraire[2]);
                registraire.setRaisonSociale((String) oRegistraire[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("REGISTRAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("REGISTRAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                registraire = this.emRefonte.merge(registraire);
                statutPersonne = new StatutPersonne(registraire, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(registraire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur WHERE idEmetteur = ?");
                qEmetteur.setParameter(1, idEmetteur);
                var oEmetteur = (Object[]) qEmetteur.getSingleResult();
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oEmetteur[1]).trim(), ((String)oEmetteur[2]).trim());
                PersonneMorale emetteur = new PersonneMorale();
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
                emetteur.setIdOcc(idEmetteur.longValue());
                emetteur.setSiglePersonneMorale((String) oEmetteur[1]);
                emetteur.setSigle((String) oEmetteur[1]);
                emetteur.setDenomination((String) oEmetteur[2]);
                emetteur.setRaisonSociale((String) oEmetteur[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("EMETTEUR").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("EMETTEUR");
                    qualite = this.emRefonte.merge(qualite);
                }
                emetteur = this.emRefonte.merge(emetteur);
                statutPersonne = new StatutPersonne(emetteur, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(emetteur.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Secteur secteur = secteurDao.findByLibelleSecteurIgnoreCase(libelleSecteurBoursier).orElse(null);
                if (secteur == null) {
                    secteur = new Secteur();
                    secteur.setLibelleSecteur(libelleSecteurBoursier);
                }
                Compartiment compartiment = compartimentDao.findByLibelleCompartimentIgnoreCase(libelleCompartiment).orElse(null);
                if (compartiment == null) {
                    compartiment = new Compartiment();
                    compartiment.setLibelleCompartiment(libelleCompartiment);
                }

                Droit titre = new Droit();
                var old = droitDao.findByTypeVMAndIdOcc("DRT", idTitre.longValue()).orElse(null);
                if (old != null)
                    titre = old;
                titre.setIdOcc(idTitre.longValue());
                titre.setSymbolTitre(symbolTitre);
                titre.setDesignationTitre(designationTitre);
                titre.setCodeIsin(codeIsin);
                titre.setEmetteur(emetteur);
                titre.setCompartiment(compartiment);
                titre.setTypeTitre(typeTitre);
                titre.setDepositaire(depositaire);
                titre.setDateCloture(dateCloture);
                titre.setDateEmission(dateEmission);
                titre.setDateOuverture(dateOuverture);
                titre.setPlace(place);
                titre.setTypeEmission(typeEmission);
                titre.setNormalAssimile(normalAssimile);
                titre.setRegistraire(registraire);
                titre.setNominal(nominal);
                titre.setLotMinimum(lotMinimum);
                titre.setEstActif(estActif);
                titre.setAppliqueFiscaliteLocale(appliqueFiscaliteLocale);
                titre.setTauxFiscaliteLocale(tauxFiscaliteLocale);
                titre.setTauxFiscalitePays(tauxFiscalitePays);
                titre.setEstReglementaire(estReglementaire);
                titre.setBorneInferieurFluctuation(borneInferieurFluctuation);
                titre.setBorneSuperieurFluctuation(borneSuperieurFluctuation);
                titre.setIrvm(irvm);
                titre.setLibelleCotation(libelleCotation);
                titre.setSecteur(secteur);
                titre.setPays(pays);
//                titre.setActionLiee(idActionLiee);
                titre.setDateJouissance(dateJouissance);
                titre.setDateDebutNegociation(dateDebutNegociation);
                titre.setDateFinNegociation(dateFinNegociation);
//                titre.setNouvelleAction(idNouvelleAction);
                titre.setPariteAncienNbre(pariteAncienNbre);
                titre.setPariteAncienCours(pariteAncienCours);
                titre.setPariteNouveauNbre(pariteNouveauNbre);
                titre.setPariteNouveauCours(pariteNouveauCours);
                titre.setCoursTheorique(coursTheorique);
                titre.setPrixUnitaireSouscription(prixUnitaireSouscription);
                titre.setCoursActionExDroit(coursActionExDroit);

                titre = emRefonte.merge(titre);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> action() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_Action");
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idTitre = (BigDecimal) res[0];
                var symbolTitre = ((String) res[1]).trim();
                var designationTitre = ((String) res[2]).trim();
                var codePlace = ((String) res[3]).trim();
                var idDepositaire = (BigDecimal) res[4];
                var libelleTypeEmission = ((String) res[5]).trim();
                var codeTypeTitre = ((String) res[6]).trim();
                var libelleSecteurBoursier = ((String) res[7]).trim();
                var libelleCompartiment = ((String) res[8]).trim();
                var codeNominalAssimile = ((String) res[9]).trim();
                var codePays = ((String) res[10]).trim();
                var idRegistraire = (BigDecimal) res[11];
                var idEmetteur = (BigDecimal) res[12];
                var lotMinimum = (Integer) res[13];
                var nominal = (BigDecimal) res[14];
                var codeIsin = ((String) res[15]).trim();
                var dateOuverture = ((Timestamp) res[16]).toLocalDateTime();
                var dateEmission = ((Timestamp) res[17]).toLocalDateTime();
                var dateCloture = ((Timestamp) res[18]).toLocalDateTime();
                var estActif = (Boolean) res[19];
                var appliqueFiscaliteLocale = (Boolean) res[20];
                var tauxFiscaliteLocale = (Double) res[21];
                var tauxFiscalitePays = (Double) res[22];
                var estReglementaire = (Boolean) res[23];
                var borneInferieurFluctuation = (Double) res[24];
                var borneSuperieurFluctuation = (Double) res[25];
                var irvm = (Double) res[26];
                var libelleCotation = ((String) res[27]).trim();
                var per = (BigDecimal) res[28];
                var codeTypeAction = ((String) res[29]).trim();
                var codeSousTypeAction = ((String) res[30]).trim();
                var nominalNonVerse = (BigDecimal) res[31];

                Pays pays = paysDao.findByCodePaysIgnoreCase(codePays).orElse(null);
                TypeEmission typeEmission = typeEmissionDao.findByLibelleTypeEmissionIgnoreCase(libelleTypeEmission).orElse(null);
                if (typeEmission == null) {
                    typeEmission = new TypeEmission();
                    typeEmission.setLibelleTypeEmission(libelleTypeEmission);
                }
                Place place = placeDao.findById(codePlace).orElse(null);
                if (place == null) {
                    place = new Place();
                    place.setCodePlace(codePlace);
                }
                TypeTitre typeTitre = typeTitreDao.findById(codeTypeTitre).orElse(null);
                if (typeTitre == null) {
                    typeTitre = new TypeTitre();
                    typeTitre.setCodeTypeTitre(codeTypeTitre);
                    typeTitre.setLibelleTypeTitre(null);
                }
                NormalAssimile normalAssimile = normalAssimileDao.findById(codeNominalAssimile).orElse(null);
                if (normalAssimile == null) {
                    normalAssimile = new NormalAssimile();
                    normalAssimile.setCodeNormalAssimile(codeNominalAssimile);
                }

                Query qDepositaire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Depositaire WHERE idDepositaire = ?");
                qDepositaire.setParameter(1, idDepositaire);
                var oDepositaire = (Object[]) qDepositaire.getSingleResult();
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oDepositaire[1]).trim(), ((String)oDepositaire[2]).trim());
                PersonneMorale depositaire = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    depositaire = oListe.get(0);
                }
                depositaire.setIdOcc(idDepositaire.longValue());
                depositaire.setPlace(null);
                depositaire.setSiglePersonneMorale((String) oDepositaire[1]);
                depositaire.setSigle((String) oDepositaire[1]);
                depositaire.setDenomination((String) oDepositaire[2]);
                depositaire.setRaisonSociale((String) oDepositaire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("DEPOSITAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("DEPOSITAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                depositaire = this.emRefonte.merge(depositaire);
                StatutPersonne statutPersonne = new StatutPersonne(depositaire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(depositaire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire WHERE idRegistraire = ?");
                qRegistraire.setParameter(1, idRegistraire);
                var oRegistraire = (Object[]) qRegistraire.getSingleResult();
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oRegistraire[1]).trim(), ((String)oRegistraire[2]).trim());
                PersonneMorale registraire = new PersonneMorale();
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
                registraire.setIdOcc(idRegistraire.longValue());
                registraire.setSiglePersonneMorale((String) oRegistraire[1]);
                registraire.setSigle((String) oRegistraire[1]);
                registraire.setDenomination((String) oRegistraire[2]);
                registraire.setRaisonSociale((String) oRegistraire[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("REGISTRAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("REGISTRAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                registraire = this.emRefonte.merge(registraire);
                statutPersonne = new StatutPersonne(registraire, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(registraire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur WHERE idEmetteur = ?");
                qEmetteur.setParameter(1, idEmetteur);
                var oEmetteur = (Object[]) qEmetteur.getSingleResult();
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oEmetteur[1]).trim(), ((String)oEmetteur[2]).trim());
                PersonneMorale emetteur = new PersonneMorale();
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
                emetteur.setIdOcc(idEmetteur.longValue());
                emetteur.setSiglePersonneMorale((String) oEmetteur[1]);
                emetteur.setSigle((String) oEmetteur[1]);
                emetteur.setDenomination((String) oEmetteur[2]);
                emetteur.setRaisonSociale((String) oEmetteur[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("EMETTEUR").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("EMETTEUR");
                    qualite = this.emRefonte.merge(qualite);
                }
                emetteur = this.emRefonte.merge(emetteur);
                statutPersonne = new StatutPersonne(emetteur, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(emetteur.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Secteur secteur = secteurDao.findByLibelleSecteurIgnoreCase(libelleSecteurBoursier).orElse(null);
                if (secteur == null) {
                    secteur = new Secteur();
                    secteur.setLibelleSecteur(libelleSecteurBoursier);
                }

                Compartiment compartiment = compartimentDao.findByLibelleCompartimentIgnoreCase(libelleCompartiment).orElse(null);
                if (compartiment == null) {
                    compartiment = new Compartiment();
                    compartiment.setLibelleCompartiment(libelleCompartiment);
                }

                TypeAction typeAction = typeActionDao.findByCodeTypeActionIgnoreCase(codeTypeAction).orElse(new TypeAction());
                Query qTypeAction = emTitre.createNativeQuery("SELECT * FROM Titre.T_TypeAction WHERE codeTypeAction = ?");
                qTypeAction.setParameter(1, codeTypeAction);
                var oTypeAction = (Object[]) qTypeAction.getSingleResult();
                typeAction.setCodeTypeAction((String) oTypeAction[0]);
                typeAction.setLibelleTypeAction((String) oTypeAction[1]);

                SousTypeAction sousTypeAction = sousTypeActionDao.findByCodeSousTypeActionIgnoreCase(codeSousTypeAction).orElse(new SousTypeAction());
                Query qSousTypeAction = emTitre.createNativeQuery("SELECT * FROM Titre.T_SousTypeAction WHERE codeSousTypeAction = ?");
                qSousTypeAction.setParameter(1, codeSousTypeAction);
                var oSousTypeAction = (Object[]) qSousTypeAction.getSingleResult();
                sousTypeAction.setCodeSousTypeAction((String) oSousTypeAction[0]);
                sousTypeAction.setLibelleSousTypeAction((String) oSousTypeAction[1]);

                Action titre = new Action();
                var old = actionDao.findByTypeVMAndIdOcc("ACT", idTitre.longValue()).orElse(null);
                if (old != null)
                    titre = old;
                titre.setIdOcc(idTitre.longValue());
                titre.setSymbolTitre(symbolTitre);
                titre.setDesignationTitre(designationTitre);
                titre.setCodeIsin(codeIsin);
                titre.setEmetteur(emetteur);
                titre.setCompartiment(compartiment);
                titre.setTypeTitre(typeTitre);
                titre.setDepositaire(depositaire);
                titre.setDateCloture(dateCloture);
                titre.setDateEmission(dateEmission);
                titre.setDateOuverture(dateOuverture);
                titre.setPlace(place);
                titre.setTypeEmission(typeEmission);
                titre.setNormalAssimile(normalAssimile);
                titre.setRegistraire(registraire);
                titre.setNominal(nominal);
                titre.setLotMinimum(lotMinimum);
                titre.setEstActif(estActif);
                titre.setAppliqueFiscaliteLocale(appliqueFiscaliteLocale);
                titre.setTauxFiscaliteLocale(tauxFiscaliteLocale);
                titre.setTauxFiscalitePays(tauxFiscalitePays);
                titre.setEstReglementaire(estReglementaire);
                titre.setBorneInferieurFluctuation(borneInferieurFluctuation);
                titre.setBorneSuperieurFluctuation(borneSuperieurFluctuation);
                titre.setIrvm(irvm);
                titre.setLibelleCotation(libelleCotation);
                titre.setSecteur(secteur);
                titre.setPays(pays);
                titre.setPer(per);
                titre.setTypeAction(typeAction);
                titre.setSousTypeAction(sousTypeAction);
                titre.setNominalNonVerse(nominalNonVerse);

                titre = emRefonte.merge(titre);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> obligation() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_Obligation");
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idTitre = (BigDecimal) res[0];
                var symbolTitre = ((String) res[1]).trim();
                var designationTitre = ((String) res[2]).trim();
                var codePlace = ((String) res[3]).trim();
                var idDepositaire = (BigDecimal) res[4];
                var libelleTypeEmission = ((String) res[5]).trim();
                var codeTypeTitre = ((String) res[6]).trim();
                var libelleSecteurBoursier = ((String) res[7]).trim();
                var libelleCompartiment = ((String) res[8]).trim();
                var codeNominalAssimile = ((String) res[9]).trim();
                var codePays = ((String) res[10]).trim();
                var idRegistraire = (BigDecimal) res[11];
                var idEmetteur = (BigDecimal) res[12];
                var lotMinimum = (Integer) res[13];
                var nominal = (BigDecimal) res[14];
                var codeIsin = ((String) res[15]).trim();
                var dateOuverture = ((Timestamp) res[16]).toLocalDateTime();
                var dateEmission = ((Timestamp) res[17]).toLocalDateTime();
                var dateCloture = ((Timestamp) res[18]).toLocalDateTime();
                var estActif = (Boolean) res[19];
                var appliqueFiscaliteLocale = (Boolean) res[20];
                var tauxFiscaliteLocale = (Double) res[21];
                var tauxFiscalitePays = (Double) res[22];
                var estReglementaire = (Boolean) res[23];
                var borneInferieurFluctuation = (Double) res[24];
                var borneSuperieurFluctuation = (Double) res[25];
                var irvm = (Double) res[26];
                var libelleCotation = ((String) res[27]).trim();
                var datePremierPaiement = ((Timestamp) res[28]).toLocalDateTime();
                var dateDernierPaiement = ((Timestamp) res[29]).toLocalDateTime();
                var dateJouissance = ((Timestamp) res[30]).toLocalDateTime();
                var dureeNbre = (Integer) res[31];
                var dureeUnite = ((String) res[32]).trim();
                var periodiciteNbre = (Integer) res[33];
                var periodiciteUnite = ((String) res[34]).trim();
                var usance = (Integer) res[35];
                var differeNbre = (Integer) res[36];
                var differeUnite = ((String) res[37]).trim();
                var libelleModeAmortissement = ((String) res[38]).trim();
                var codeTypeAmortissement = ((String) res[39]).trim();
                var tauxBrut = (Double) res[40];
                var tauxNet = (Double) res[41];
                var nombreTitre = (Integer) res[42];
                var codeTypeObligation = ((String) res[43]).trim();
                var estParAdjudication = (Boolean) res[44];
                var numeroIdentification = ((String) res[45]).trim();
                var denominationEmission = ((String) res[46]).trim();

                Pays pays = paysDao.findByCodePaysIgnoreCase(codePays).orElse(null);
                TypeEmission typeEmission = typeEmissionDao.findByLibelleTypeEmissionIgnoreCase(libelleTypeEmission).orElse(null);
                if (typeEmission == null) {
                    typeEmission = new TypeEmission();
                    typeEmission.setLibelleTypeEmission(libelleTypeEmission);
                }
                Place place = placeDao.findById(codePlace).orElse(null);
                if (place == null) {
                    place = new Place();
                    place.setCodePlace(codePlace);
                }
                TypeTitre typeTitre = typeTitreDao.findById(codeTypeTitre).orElse(null);
                if (typeTitre == null) {
                    typeTitre = new TypeTitre();
                    typeTitre.setCodeTypeTitre(codeTypeTitre);
                    typeTitre.setLibelleTypeTitre(null);
                }
                NormalAssimile normalAssimile = normalAssimileDao.findById(codeNominalAssimile).orElse(null);
                if (normalAssimile == null) {
                    normalAssimile = new NormalAssimile();
                    normalAssimile.setCodeNormalAssimile(codeNominalAssimile);
                }

                Query qDepositaire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Depositaire WHERE idDepositaire = ?");
                qDepositaire.setParameter(1, idDepositaire);
                var oDepositaire = (Object[]) qDepositaire.getSingleResult();
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oDepositaire[1]).trim(), ((String)oDepositaire[2]).trim());
                PersonneMorale depositaire = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    depositaire = oListe.get(0);
                }
                depositaire.setIdOcc(idDepositaire.longValue());
                depositaire.setPlace(null);
                depositaire.setSiglePersonneMorale((String) oDepositaire[1]);
                depositaire.setSigle((String) oDepositaire[1]);
                depositaire.setDenomination((String) oDepositaire[2]);
                depositaire.setRaisonSociale((String) oDepositaire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("DEPOSITAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("DEPOSITAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                depositaire = this.emRefonte.merge(depositaire);
                StatutPersonne statutPersonne = new StatutPersonne(depositaire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(depositaire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire WHERE idRegistraire = ?");
                qRegistraire.setParameter(1, idRegistraire);
                var oRegistraire = (Object[]) qRegistraire.getSingleResult();
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oRegistraire[1]).trim(), ((String)oRegistraire[2]).trim());
                PersonneMorale registraire = new PersonneMorale();
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
                registraire.setIdOcc(idRegistraire.longValue());
                registraire.setSiglePersonneMorale((String) oRegistraire[1]);
                registraire.setSigle((String) oRegistraire[1]);
                registraire.setDenomination((String) oRegistraire[2]);
                registraire.setRaisonSociale((String) oRegistraire[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("REGISTRAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("REGISTRAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                registraire = this.emRefonte.merge(registraire);
                statutPersonne = new StatutPersonne(registraire, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(registraire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur WHERE idEmetteur = ?");
                qEmetteur.setParameter(1, idEmetteur);
                var oEmetteur = (Object[]) qEmetteur.getSingleResult();
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oEmetteur[1]).trim(), ((String)oEmetteur[2]).trim());
                PersonneMorale emetteur = new PersonneMorale();
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
                emetteur.setIdOcc(idEmetteur.longValue());
                emetteur.setSiglePersonneMorale((String) oEmetteur[1]);
                emetteur.setSigle((String) oEmetteur[1]);
                emetteur.setDenomination((String) oEmetteur[2]);
                emetteur.setRaisonSociale((String) oEmetteur[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("EMETTEUR").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("EMETTEUR");
                    qualite = this.emRefonte.merge(qualite);
                }
                emetteur = this.emRefonte.merge(emetteur);
                statutPersonne = new StatutPersonne(emetteur, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(emetteur.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Secteur secteur = secteurDao.findByLibelleSecteurIgnoreCase(libelleSecteurBoursier).orElse(null);
                if (secteur == null) {
                    secteur = new Secteur();
                    secteur.setLibelleSecteur(libelleSecteurBoursier);
                }

                Compartiment compartiment = compartimentDao.findByLibelleCompartimentIgnoreCase(libelleCompartiment).orElse(null);
                if (compartiment == null) {
                    compartiment = new Compartiment();
                    compartiment.setLibelleCompartiment(libelleCompartiment);
                }

                ModeAmortissement modeAmortissement = modeAmortissementDao.findByLibelleModeAmortissementIgnoreCase(libelleModeAmortissement).orElse(new ModeAmortissement());
                Query qModeAmortissement = emTitre.createNativeQuery("SELECT * FROM Titre.T_ModeAmortissement WHERE libelleModeAmortissement = ?");
                qModeAmortissement.setParameter(1, libelleModeAmortissement);
                var oModeAmortissement = (Object[]) qModeAmortissement.getSingleResult();
                modeAmortissement.setLibelleModeAmortissement((String) oModeAmortissement[0]);

                TypeAmortissement typeAmortissement = typeAmortissementDao.findByCodeTypeAmortissementIgnoreCase(codeTypeAmortissement).orElse(new TypeAmortissement());
                Query qTypeAmortissement = emTitre.createNativeQuery("SELECT * FROM Titre.T_TypeAmortissement WHERE codeTypeAmortissement = ?");
                qTypeAmortissement.setParameter(1, codeTypeAmortissement);
                var oTypeAmortissement = (Object[]) qTypeAmortissement.getSingleResult();
                typeAmortissement.setCodeTypeAmortissement((String) oTypeAmortissement[0]);
                typeAmortissement.setLibelleTypeAmortissement((String) oTypeAmortissement[1]);

                TypeObligation typeObligation = typeObligationDao.findByCodeTypeObligationIgnoreCase(codeTypeObligation).orElse(new TypeObligation());
                Query qTypeObligation = emTitre.createNativeQuery("SELECT * FROM Titre.T_TypeObligation WHERE codeTypeObligation = ?");
                qTypeObligation.setParameter(1, codeTypeObligation);
                var oTypeObligation = (Object[]) qTypeObligation.getSingleResult();
                typeObligation.setCodeTypeObligation((String) oTypeObligation[0]);
                typeObligation.setLibelleTypeObligation((String) oTypeObligation[1]);

                Obligation titre = new Obligation();
                var old = obligationDao.findByTypeVMAndIdOcc("OBL", idTitre.longValue()).orElse(null);
                if (old != null)
                    titre = old;
                titre.setIdOcc(idTitre.longValue());
                titre.setSymbolTitre(symbolTitre);
                titre.setDesignationTitre(designationTitre);
                titre.setCodeIsin(codeIsin);
                titre.setEmetteur(emetteur);
                titre.setCompartiment(compartiment);
                titre.setTypeTitre(typeTitre);
                titre.setDepositaire(depositaire);
                titre.setDateCloture(dateCloture);
                titre.setDateEmission(dateEmission);
                titre.setDateOuverture(dateOuverture);
                titre.setPlace(place);
                titre.setTypeEmission(typeEmission);
                titre.setNormalAssimile(normalAssimile);
                titre.setRegistraire(registraire);
                titre.setNominal(nominal);
                titre.setLotMinimum(lotMinimum);
                titre.setEstActif(estActif);
                titre.setAppliqueFiscaliteLocale(appliqueFiscaliteLocale);
                titre.setTauxFiscaliteLocale(tauxFiscaliteLocale);
                titre.setTauxFiscalitePays(tauxFiscalitePays);
                titre.setEstReglementaire(estReglementaire);
                titre.setBorneInferieurFluctuation(borneInferieurFluctuation);
                titre.setBorneSuperieurFluctuation(borneSuperieurFluctuation);
                titre.setIrvm(irvm);
                titre.setLibelleCotation(libelleCotation);
                titre.setSecteur(secteur);
                titre.setPays(pays);
                titre.setPeriodiciteNbre(periodiciteNbre);
                titre.setPeriodiciteUnite(periodiciteUnite);
                titre.setDateJouissance(dateJouissance);
                titre.setDatePremierPaiement(datePremierPaiement);
                titre.setDateDernierPaiement(dateDernierPaiement);
                titre.setDureeNbre(dureeNbre);
                titre.setDureeUnite(dureeUnite);
                titre.setUsance(usance);
                titre.setTauxBrut(tauxBrut);
                titre.setTauxNet(tauxNet);
                titre.setNombreTitre(nombreTitre);
                titre.setModeAmortissement(modeAmortissement);
                titre.setTypeAmortissement(typeAmortissement);
                titre.setTypeObligation(typeObligation);
                titre.setDiffereNbre(differeNbre);
                titre.setDiffereUnite(differeUnite);
                titre.setEstParAdjudication(estParAdjudication);
                titre.setNumeroIdentification(numeroIdentification);
                titre.setDenominationEmission(denominationEmission);

                titre = emRefonte.merge(titre);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> tcn() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_TCN");
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idTitre = (BigDecimal) res[0];
                var symbolTitre = ((String) res[1]).trim();
                var designationTitre = ((String) res[2]).trim();
                var codePlace = ((String) res[3]).trim();
                var idDepositaire = (BigDecimal) res[4];
                var libelleTypeEmission = ((String) res[5]).trim();
                var codeTypeTitre = ((String) res[6]).trim();
                var libelleSecteurBoursier = ((String) res[7]).trim();
                var libelleCompartiment = ((String) res[8]).trim();
                var codeNominalAssimile = ((String) res[9]).trim();
                var codePays = ((String) res[10]).trim();
                var idRegistraire = (BigDecimal) res[11];
                var idEmetteur = (BigDecimal) res[12];
                var lotMinimum = (Integer) res[13];
                var nominal = (BigDecimal) res[14];
                var codeIsin = ((String) res[15]).trim();
                var dateOuverture = ((Timestamp) res[16]).toLocalDateTime();
                var dateEmission = ((Timestamp) res[17]).toLocalDateTime();
                var dateCloture = ((Timestamp) res[18]).toLocalDateTime();
                var estActif = (Boolean) res[19];
                var appliqueFiscaliteLocale = (Boolean) res[20];
                var tauxFiscaliteLocale = (Double) res[21];
                var tauxFiscalitePays = (Double) res[22];
                var estReglementaire = (Boolean) res[23];
                var borneInferieurFluctuation = (Double) res[24];
                var borneSuperieurFluctuation = (Double) res[25];
                var irvm = (Double) res[26];
                var libelleCotation = ((String) res[27]).trim();
                var datePremierPaiement = ((Timestamp) res[28]).toLocalDateTime();
                var dateDernierPaiement = ((Timestamp) res[29]).toLocalDateTime();
                var dateJouissance = ((Timestamp) res[30]).toLocalDateTime();
                var dureeNbre = (Integer) res[31];
                var dureeUnite = ((String) res[32]).trim();
                var periodiciteNbre = (Integer) res[33];
                var periodiciteUnite = ((String) res[34]).trim();
                var usance = (Integer) res[35];
                var differeNbre = (Integer) res[36];
                var differeUnite = ((String) res[37]).trim();
                var libelleModeAmortissement = ((String) res[38]).trim();
                var codeTypeAmortissement = ((String) res[39]).trim();
                var tauxBrut = (Double) res[40];
                var tauxNet = (Double) res[41];
                var nombreTitre = (Integer) res[42];
                var estParAdjudication = (Boolean) res[43];
                var numeroIdentification = ((String) res[44]).trim();
                var denominationEmission = ((String) res[45]).trim();
                var codeNatureTcn = ((String) res[46]).trim();
                var formulePrecomptee = ((String) res[47]).trim();

                Pays pays = paysDao.findByCodePaysIgnoreCase(codePays).orElse(null);
                TypeEmission typeEmission = typeEmissionDao.findByLibelleTypeEmissionIgnoreCase(libelleTypeEmission).orElse(null);
                if (typeEmission == null) {
                    typeEmission = new TypeEmission();
                    typeEmission.setLibelleTypeEmission(libelleTypeEmission);
                }
                Place place = placeDao.findById(codePlace).orElse(null);
                if (place == null) {
                    place = new Place();
                    place.setCodePlace(codePlace);
                }
                TypeTitre typeTitre = typeTitreDao.findById(codeTypeTitre).orElse(null);
                if (typeTitre == null) {
                    typeTitre = new TypeTitre();
                    typeTitre.setCodeTypeTitre(codeTypeTitre);
                    typeTitre.setLibelleTypeTitre(null);
                }
                NormalAssimile normalAssimile = normalAssimileDao.findById(codeNominalAssimile).orElse(null);
                if (normalAssimile == null) {
                    normalAssimile = new NormalAssimile();
                    normalAssimile.setCodeNormalAssimile(codeNominalAssimile);
                }

                Query qDepositaire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Depositaire WHERE idDepositaire = ?");
                qDepositaire.setParameter(1, idDepositaire);
                var oDepositaire = (Object[]) qDepositaire.getSingleResult();
                List<PersonneMorale> oListe = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oDepositaire[1]).trim(), ((String)oDepositaire[2]).trim());
                PersonneMorale depositaire = new PersonneMorale();
                if(oListe != null && oListe.size() > 0) {
                    depositaire = oListe.get(0);
                }
                depositaire.setIdOcc(idDepositaire.longValue());
                depositaire.setPlace(null);
                depositaire.setSiglePersonneMorale((String) oDepositaire[1]);
                depositaire.setSigle((String) oDepositaire[1]);
                depositaire.setDenomination((String) oDepositaire[2]);
                depositaire.setRaisonSociale((String) oDepositaire[2]);
                Qualite qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("DEPOSITAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("DEPOSITAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                depositaire = this.emRefonte.merge(depositaire);
                StatutPersonne statutPersonne = new StatutPersonne(depositaire, qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(depositaire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire WHERE idRegistraire = ?");
                qRegistraire.setParameter(1, idRegistraire);
                var oRegistraire = (Object[]) qRegistraire.getSingleResult();
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oRegistraire[1]).trim(), ((String)oRegistraire[2]).trim());
                PersonneMorale registraire = new PersonneMorale();
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
                registraire.setIdOcc(idRegistraire.longValue());
                registraire.setSiglePersonneMorale((String) oRegistraire[1]);
                registraire.setSigle((String) oRegistraire[1]);
                registraire.setDenomination((String) oRegistraire[2]);
                registraire.setRaisonSociale((String) oRegistraire[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("REGISTRAIRE").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("REGISTRAIRE");
                    qualite = this.emRefonte.merge(qualite);
                }
                registraire = this.emRefonte.merge(registraire);
                statutPersonne = new StatutPersonne(registraire, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(registraire.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur WHERE idEmetteur = ?");
                qEmetteur.setParameter(1, idEmetteur);
                var oEmetteur = (Object[]) qEmetteur.getSingleResult();
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(((String)oEmetteur[1]).trim(), ((String)oEmetteur[2]).trim());
                PersonneMorale emetteur = new PersonneMorale();
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
                emetteur.setIdOcc(idEmetteur.longValue());
                emetteur.setSiglePersonneMorale((String) oEmetteur[1]);
                emetteur.setSigle((String) oEmetteur[1]);
                emetteur.setDenomination((String) oEmetteur[2]);
                emetteur.setRaisonSociale((String) oEmetteur[2]);
                qualite = this.qualiteDao.findByLibelleQualiteIgnoreCase("EMETTEUR").orElse(new Qualite());
                if (Long.valueOf(qualite.getIdQualite()) == null) {
                    qualite.setLibelleQualite("EMETTEUR");
                    qualite = this.emRefonte.merge(qualite);
                }
                emetteur = this.emRefonte.merge(emetteur);
                statutPersonne = new StatutPersonne(emetteur, qualite);
                cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(emetteur.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                this.emRefonte.merge(statutPersonne);

                Secteur secteur = secteurDao.findByLibelleSecteurIgnoreCase(libelleSecteurBoursier).orElse(null);
                if (secteur == null) {
                    secteur = new Secteur();
                    secteur.setLibelleSecteur(libelleSecteurBoursier);
                }

                Compartiment compartiment = compartimentDao.findByLibelleCompartimentIgnoreCase(libelleCompartiment).orElse(null);
                if (compartiment == null) {
                    compartiment = new Compartiment();
                    compartiment.setLibelleCompartiment(libelleCompartiment);
                }

                ModeAmortissement modeAmortissement = modeAmortissementDao.findByLibelleModeAmortissementIgnoreCase(libelleModeAmortissement).orElse(new ModeAmortissement());
                Query qModeAmortissement = emTitre.createNativeQuery("SELECT * FROM Titre.T_ModeAmortissement WHERE libelleModeAmortissement = ?");
                qModeAmortissement.setParameter(1, libelleModeAmortissement);
                var oModeAmortissement = (Object[]) qModeAmortissement.getSingleResult();
                modeAmortissement.setLibelleModeAmortissement((String) oModeAmortissement[0]);

                TypeAmortissement typeAmortissement = typeAmortissementDao.findByCodeTypeAmortissementIgnoreCase(codeTypeAmortissement).orElse(new TypeAmortissement());
                Query qTypeAmortissement = emTitre.createNativeQuery("SELECT * FROM Titre.T_TypeAmortissement WHERE codeTypeAmortissement = ?");
                qTypeAmortissement.setParameter(1, codeTypeAmortissement);
                var oTypeAmortissement = (Object[]) qTypeAmortissement.getSingleResult();
                typeAmortissement.setCodeTypeAmortissement((String) oTypeAmortissement[0]);
                typeAmortissement.setLibelleTypeAmortissement((String) oTypeAmortissement[1]);

                NatureTcn natureTcn = natureTcnDao.findByCodeNatureTcnIgnoreCase(codeNatureTcn).orElse(new NatureTcn());
                Query qNatureTcn = emTitre.createNativeQuery("SELECT * FROM Titre.T_NatureTcn WHERE codeNatureTcn = ?");
                qNatureTcn.setParameter(1, codeNatureTcn);
                var oNatureTcn = (Object[]) qNatureTcn.getSingleResult();
                natureTcn.setCodeNatureTcn((String) oNatureTcn[0]);
                natureTcn.setLibelleNatureTcn((String) oNatureTcn[1]);

                ModeCalculInteret modeCalculInteret = modeCalculInteretDao.findByCodeModeCalculInteretIgnoreCase(formulePrecomptee).orElse(new ModeCalculInteret());
                Query qModeCalculInteret = emTitre.createNativeQuery("SELECT * FROM Titre.T_ModeCalculInteret WHERE formulePrecomptee = ?");
                qNatureTcn.setParameter(1, codeNatureTcn);
                var oModeCalculInteret = (Object[]) qModeCalculInteret.getSingleResult();
                modeCalculInteret.setCodeModeCalculInteret((String)oModeCalculInteret[0]);
                modeCalculInteret.setLibelleModeCalculInteret((String)oModeCalculInteret[1]);

                Tcn titre = new Tcn();
                var old = tcnDao.findByTypeVMAndIdOcc("TCN", idTitre.longValue()).orElse(null);
                if (old != null)
                    titre = old;
                titre.setIdOcc(idTitre.longValue());
                titre.setSymbolTitre(symbolTitre);
                titre.setDesignationTitre(designationTitre);
                titre.setCodeIsin(codeIsin);
                titre.setEmetteur(emetteur);
                titre.setCompartiment(compartiment);
                titre.setTypeTitre(typeTitre);
                titre.setDepositaire(depositaire);
                titre.setDateCloture(dateCloture);
                titre.setDateEmission(dateEmission);
                titre.setDateOuverture(dateOuverture);
                titre.setPlace(place);
                titre.setTypeEmission(typeEmission);
                titre.setNormalAssimile(normalAssimile);
                titre.setRegistraire(registraire);
                titre.setNominal(nominal);
                titre.setLotMinimum(lotMinimum);
                titre.setEstActif(estActif);
                titre.setAppliqueFiscaliteLocale(appliqueFiscaliteLocale);
                titre.setTauxFiscaliteLocale(tauxFiscaliteLocale);
                titre.setTauxFiscalitePays(tauxFiscalitePays);
                titre.setEstReglementaire(estReglementaire);
                titre.setBorneInferieurFluctuation(borneInferieurFluctuation);
                titre.setBorneSuperieurFluctuation(borneSuperieurFluctuation);
                titre.setIrvm(irvm);
                titre.setLibelleCotation(libelleCotation);
                titre.setSecteur(secteur);
                titre.setPays(pays);
                titre.setPeriodiciteNbre(periodiciteNbre);
                titre.setPeriodiciteUnite(periodiciteUnite);
                titre.setDateJouissance(dateJouissance);
                titre.setDatePremierPaiement(datePremierPaiement);
                titre.setDateDernierPaiement(dateDernierPaiement);
                titre.setDureeNbre(dureeNbre);
                titre.setDureeUnite(dureeUnite);
                titre.setUsance(usance);
                titre.setTauxBrut(tauxBrut);
                titre.setTauxNet(tauxNet);
                titre.setNombreTitre(nombreTitre);
                titre.setModeAmortissement(modeAmortissement);
                titre.setTypeAmortissement(typeAmortissement);
                titre.setDiffereNbre(differeNbre);
                titre.setDiffereUnite(differeUnite);
                titre.setEstParAdjudication(estParAdjudication);
                titre.setNumeroIdentification(numeroIdentification);
                titre.setDenominationEmission(denominationEmission);
                titre.setNatureTcn(natureTcn);
                titre.setFormulePrecomptee(modeCalculInteret);

                titre = emRefonte.merge(titre);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> coursTitre() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.TJ_CoursTitre");
            result = (List<Object[]>) query.getResultList();
            for (int cpt = 25001; cpt <= result.size(); cpt++) {
                Object[] res = result.get(cpt);
            /*if(cpt == 1399)
                break;*/
                var idTitre = (BigDecimal) res[0];
                var dateCours = ((Timestamp) res[1]).toLocalDateTime();
                var coursVeille = (BigDecimal) res[2];
                var ouverture = (BigDecimal) res[3];
                var haut = (BigDecimal) res[4];
                var bas = (BigDecimal) res[5];
                var coursSeance = (BigDecimal) res[6];
                var variation = (BigDecimal) res[7];
                var nbreTrans = (Integer) res[8];
                var volTransiger = (Integer) res[9];
                var valTransiger = (BigDecimal) res[10];
                var resteOffre = (Integer) res[11];
                var resteDemande = (Integer) res[12];
                var coursReference = (BigDecimal) res[13];
                var estValider = (Boolean) res[14];
                var dateCreationServeur = ((Timestamp) res[15]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[16]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[17]).toLocalDateTime();
                var userLogin = ((String) res[18]).trim();
                var numLigne = (BigDecimal) res[19];
                var supprimer = (Boolean) res[20];
                var rowvers = (byte[]) res[21];
                var estVerifie1 = res[22] != null ? (Boolean) res[22] : false;
                var dateVerification1 = ((Timestamp) res[23]).toLocalDateTime();
                var userLoginVerificateur1 = ((String) res[24]).trim();
                var estVerifie2 = res[25] != null ? (Boolean) res[25] : false;
                var dateVerification2 = ((Timestamp) res[26]).toLocalDateTime();
                var userLoginVerificateur2 = ((String) res[27]).trim();

                Titre titre = titreDao.findByIdOcc(idTitre.longValue()).orElse(null);
                if (titre != null && titre.getIdTitre() != null) {
//                    System.out.println(cpt + " => Cours === " + idTitre.longValue());
                    CoursTitre coursTitre = new CoursTitre();
                    CleCoursTitre cleCoursTitre = new CleCoursTitre();
                    cleCoursTitre.setIdTitre(titre.getIdTitre());
                    cleCoursTitre.setDateCours(dateCours);
                    var old = coursTitreDao.findById(cleCoursTitre).orElse(null);
                    if (old != null)
                        coursTitre = old;
                    /*else
                        coursTitre.setRowvers(rowvers);*/
                    coursTitre.setIdCoursTitre(cleCoursTitre);
                    coursTitre.setTitre(titre);
                    coursTitre.setCoursVeille(coursVeille);
                    coursTitre.setOuverture(ouverture);
                    coursTitre.setHaut(haut);
                    coursTitre.setBas(bas);
                    coursTitre.setCoursSeance(coursSeance);
                    coursTitre.setVariation(variation);
                    coursTitre.setNbreTrans(nbreTrans);
                    coursTitre.setVolTransiger(volTransiger);
                    coursTitre.setValTransiger(valTransiger);
                    coursTitre.setResteOffre(resteOffre);
                    coursTitre.setResteDemande(resteDemande);
                    coursTitre.setCoursReference(coursReference);
                    coursTitre.setEstValider(estValider);
                    coursTitre.setDateCreationServeur(dateCreationServeur);
                    coursTitre.setDateDernModifServeur(dateDernModifServeur);
                    coursTitre.setDateDernModifClient(dateDernModifClient);
                    /*coursTitre.setUserLogin(userLogin);
                    coursTitre.setNumLigne(numLigne.longValue());*/
                    coursTitre.setSupprimer(supprimer);
                    coursTitre.setEstVerifie1(estVerifie1);
                    coursTitre.setDateVerification1(dateVerification1);
                    coursTitre.setUserLoginVerificateur1(userLoginVerificateur1);
                    coursTitre.setEstVerifie2(estVerifie2);
                    coursTitre.setDateVerification2(dateVerification2);
                    coursTitre.setUserLoginVerificateur2(userLoginVerificateur2);
                    emRefonte.merge(coursTitre);
                }
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> tabAmortissement() {
        List<Object[]> result;
        try {
            Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_TableauAmortissement");
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idTitre = (BigDecimal) res[0];
                var numeroEcheance = (Short) res[1];
                var dateEcheance = ((Timestamp) res[2]).toLocalDateTime();
                var tauxAmortissement = (Double) res[3];
                var nombreTitre = (BigDecimal) res[4];
                var capital = (BigDecimal) res[5];
                var interet = (BigDecimal) res[6];
                var nombreTitreAmorti = (BigDecimal) res[7];
                var montantRembourse = (BigDecimal) res[8];
                var annuiteTotale = (BigDecimal) res[9];
                var montantFinPeriode = (BigDecimal) res[10];
                var estGenere = (Boolean) res[11];
                var numLigne = (BigDecimal) res[12];
                var dateCreationServeur = ((Timestamp) res[13]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[14]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[15]).toLocalDateTime();
                var userLogin = ((String) res[16]).trim();
                var supprimer = (Boolean) res[17];
                var rowvers = (byte[]) res[18];

                Titre titre = titreDao.findByIdOcc(idTitre.longValue()).orElse(null);
                if (titre != null && titre.getIdTitre() != null) {
//                    System.out.println("TAB === " + titre.getIdOcc() + "---" + dateEcheance.toString());
                    TableauAmortissement tableauAmortissement = new TableauAmortissement();
                    CleTableauAmortissement cleTableauAmortissement = new CleTableauAmortissement();
                    cleTableauAmortissement.setIdTitre(titre.getIdTitre());
                    cleTableauAmortissement.setDateEcheance(dateEcheance);
                    tableauAmortissement.setIdTabAmortissement(cleTableauAmortissement);
                    tableauAmortissement.setNumeroEcheance(numeroEcheance);
                    tableauAmortissement.setTitre(titre);
                    tableauAmortissement.setTauxAmortissement(tauxAmortissement);
                    tableauAmortissement.setNombreTitre(nombreTitre);
                    tableauAmortissement.setNombreTitreAmorti(nombreTitreAmorti);
                    tableauAmortissement.setCapital(capital);
                    tableauAmortissement.setInteret(interet);
                    tableauAmortissement.setMontantRembourse(montantRembourse);
                    tableauAmortissement.setAnnuiteTotale(annuiteTotale);
                    tableauAmortissement.setMontantFinPeriode(montantFinPeriode);
                    tableauAmortissement.setEstGenere(estGenere);
                    tableauAmortissement.setDateCreationServeur(dateCreationServeur);
                    tableauAmortissement.setDateDernModifServeur(dateDernModifServeur);
                    tableauAmortissement.setDateDernModifClient(dateDernModifClient);
                    /*tableauAmortissement.setUserLogin(userLogin);
                    tableauAmortissement.setNumLigne(numLigne.longValue());*/
                    tableauAmortissement.setSupprimer(supprimer);
//                    tableauAmortissement.setRowvers(rowvers);
                    emRefonte.merge(tableauAmortissement);
                }
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> typeTitre() {
        Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_TypeTitre");
        List<Object[]> result = (List<Object[]>) query.getResultList();
        for (Object[] res : result) {
            var codeTypeTitre = (String) res[0];
            var codeClasseTitre = (String) res[1];
            var libelleTypeTitre = (String) res[2];
            TypeTitre typeTitre = new TypeTitre();
            var old = typeTitreDao.findById(codeTypeTitre).orElse(null);
            if (old != null)
                typeTitre = old;
            typeTitre.setCodeTypeTitre(codeTypeTitre);
            typeTitre.setClasseTitre(null);
            typeTitre.setLibelleTypeTitre(libelleTypeTitre);
            emRefonte.persist(typeTitre);
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> formeJuridique() {
        Query query = emTitre.createNativeQuery("SELECT * FROM Titre.T_FormeJuridique");
        List<Object[]> result = (List<Object[]>) query.getResultList();
        for (Object[] res : result) {
            var codeFormeJuridique = (String) res[0];
            var libelleFormeJuridique = (String) res[1];
            FormeJuridique formeJuridique = new FormeJuridique();
            var old = formeJuridiqueDao.findById(codeFormeJuridique).orElse(null);
            if (old != null)
                formeJuridique = old;
            formeJuridique.setCodeFormeJuridique(codeFormeJuridique);
            formeJuridique.setLibelleFormeJuridique(libelleFormeJuridique);
            emRefonte.persist(formeJuridique);
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> societeDeGestion() {
        Query query = emRefonte.createNativeQuery("SELECT * FROM Parametre.T_SocieteDeGestion");
        //query.setParameter(1, id);
        List<Object[]> result = (List<Object[]>) query.getResultList();
        for (Object[] res : result) {
            var numAgrement = ((String) res[0]).trim();
            var dateAgrement = ((Timestamp) res[1]).toLocalDateTime();
            var dateCreation = ((Timestamp) res[2]).toLocalDateTime();
            var codeFormeJuridique = ((String) res[3]).trim();
            var numRegistre = ((String) res[4]).trim();
            var capital = ((BigDecimal) res[5]);
            var sigle = ((String) res[6]).trim();
            var codePays = ((String) res[7]).trim();
            var libelleVille = ((String) res[8]).trim();
            var adresseComplete = ((String) res[9]).trim();
            var raisonSociale = ((String) res[10]).trim();
            var indexTel1 = ((String) res[11]).trim();
            var telephoneFixe1 = ((String) res[12]).trim();
            var indexTel2 = ((String) res[13]).trim();
            var telephoneFixe2 = ((String) res[14]).trim();
            var indexMobile1 = ((String) res[15]).trim();
            var telephoneMobile1 = ((String) res[16]).trim();
            var indexMobile2 = ((String) res[17]).trim();
            var telephoneMobile2 = ((String) res[18]).trim();
            var codeSkype = ((String) res[19]).trim();
            var siteWeb = ((String) res[20]).trim();
            var indexFax = ((String) res[21]).trim();
            var fax = ((String) res[22]).trim();
            var email = ((String) res[23]).trim();
            var boitePostale = ((String) res[24]).trim();
            var logo = ((String) res[25]).trim();
            var numLigne = (BigDecimal) res[26];

            Ville ville = villeDao.findByLibelleVilleIgnoreCase(libelleVille).orElse(null);
            Pays pays = paysDao.findByCodePaysIgnoreCase(codePays).orElse(null);
            FormeJuridique formeJuridique = formeJuridiqueDao.findById(codeFormeJuridique).orElse(null);

            SocieteDeGestion soc = new SocieteDeGestion();
            //Vérifier si une occurence existe déjà
            var old = societeDeGestionDao.findByTypePersonneAndIdOcc("SG", numLigne.longValue()).orElse(null);
            if (old != null)
                soc = old;
//            soc.setFormeJuridique(formeJuridique);
            soc.setNumRegistre(numRegistre);
            soc.setNumeroAgrementPersonneMorale(numAgrement);
            soc.setDateAgrement(dateAgrement);
            soc.setDateCreationPM(dateCreation);
            soc.setDenomination(raisonSociale);
            soc.setIdOcc(numLigne.longValue());
            soc.setSigle(sigle);
            soc.setCapitalSocial(capital);
            soc.setVille(ville);
//            soc.setCodePays(codePays);
            soc.setPaysResidence(pays);
            soc.setAdresseComplete(adresseComplete);
            soc.setIndexFax(indexFax);
            soc.setIndexFixe1(indexTel1);
            soc.setIndexFixe2(indexTel2);
            soc.setTelephoneFixe1(telephoneFixe1);
            soc.setTelephoneFixe2(telephoneFixe2);
            soc.setIndexMobile1(indexMobile1);
            soc.setIndexMobile2(indexMobile2);
            soc.setTelephoneMobile1(telephoneMobile1);
            soc.setTelephoneMobile2(telephoneMobile2);
            soc.setCodeSkype(codeSkype);
            soc.setFax(fax);
            soc.setEmail(email);
            soc.setBoitePostale(boitePostale);
            soc.setSiteWeb(siteWeb);

            emRefonte.persist(soc);
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> typeOperationCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_TypeOperation");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeTypeOperation = ((String) res[0]).trim();
                var libelleTypeOperation = ((String) res[1]).trim();
                var numLigne = (BigDecimal) res[2];
                var dateCreationServeur = ((Timestamp) res[3]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[4]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[5]).toLocalDateTime();
                var userLogin = ((String) res[6]).trim();
                var supprimer = (Boolean) res[7];
                var rowvers = (byte[]) res[8];

                TypeOperation typeOperation = new TypeOperation();
                //Vérifier si une occurence existe déjà
                var old = typeOperationDao.findByCodeTypeOperationIgnoreCase(codeTypeOperation).orElse(null);
                if (old != null)
                    typeOperation = old;
                typeOperation.setCodeTypeOperation(codeTypeOperation);
                typeOperation.setLibelleTypeOperation(libelleTypeOperation);
                typeOperation.setDateCreationServeur(dateCreationServeur);
                typeOperation.setDateDernModifClient(dateDernModifClient);
                typeOperation.setDateDernModifServeur(dateDernModifServeur);
                typeOperation.setSupprimer(supprimer);

                emRefonte.persist(typeOperation);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> planCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_Plan");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codePlan = ((String) res[0]).trim();
                var libellePlan = ((String) res[1]).trim();
                var numCompteBenefice = ((String) res[2]).trim();
                var numCompteCapital = ((String) res[3]).trim();
                var numComptePerte = ((String) res[4]).trim();
                var numCompteResInsDistribution = ((String) res[5]).trim();
                var numLigne = (BigDecimal) res[6];
                var dateCreationServeur = ((Timestamp) res[7]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[8]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[9]).toLocalDateTime();
                var userLogin = ((String) res[10]).trim();
                var supprimer = (Boolean) res[11];
                var rowvers = (byte[]) res[12];

                Plan plan = new Plan();
                //Vérifier si une occurence existe déjà
                var old = planDao.findByCodePlanIgnoreCase(codePlan).orElse(null);
                if (old != null)
                    plan = old;
                plan.setCodePlan(codePlan);
                plan.setLibellePlan(libellePlan);
                plan.setNumCompteCapital(numCompteCapital);
                plan.setNumCompteBenefice(numCompteBenefice);
                plan.setNumComptePerte(numComptePerte);
                plan.setNumCompteResInsDistribution(numCompteResInsDistribution);
                plan.setDateCreationServeur(dateCreationServeur);
                plan.setDateDernModifClient(dateDernModifClient);
                plan.setDateDernModifServeur(dateDernModifServeur);
                plan.setSupprimer(supprimer);

                emRefonte.persist(plan);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> numCompteCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.TJ_CompteComptable");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var numCompteComptable = ((String) res[0]).trim();
                var codePlan = ((String) res[1]).trim();
                var libelleCompteComptable = ((String) res[2]).trim();
                var sensMvt = (Character) res[3];
                var estMvt = (Boolean) res[4];
                var bilanHorsBilan = ((String) res[5]).trim();
                var type = ((String) res[6]).trim();
                var numLigne = (BigDecimal) res[7];
                var dateCreationServeur = ((Timestamp) res[8]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[9]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[10]).toLocalDateTime();
                var userLogin = ((String) res[11]).trim();
                var supprimer = (Boolean) res[12];
                var rowvers = (byte[]) res[13];

                Plan plan = planDao.findByCodePlanIgnoreCase(codePlan).orElse(null);
                if (plan == null) {
                    plan = new Plan();
                    plan.setCodePlan(codePlan);
                    plan.setSupprimer(false);
                } else {
                    try {
                        Query q = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_Plan where codePlan = ?");
                        q.setParameter(1, codePlan);
                        var o = (Object[]) q.getSingleResult();
                        if (o != null) {
                            plan.setCodePlan(((String) o[0]).trim());
                            plan.setLibellePlan(((String) o[1]).trim());
                            plan.setNumCompteCapital(((String) o[2]).trim());
                            plan.setNumCompteBenefice(((String) o[3]).trim());
                            plan.setNumComptePerte(((String) o[4]).trim());
                            plan.setNumCompteResInsDistribution(((String) o[5]).trim());
                            plan.setDateCreationServeur(((Timestamp) o[7]).toLocalDateTime());
                            plan.setDateDernModifServeur(((Timestamp) o[8]).toLocalDateTime());
                            plan.setDateDernModifClient(((Timestamp) o[9]).toLocalDateTime());
                            plan.setSupprimer((Boolean) o[11]);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        break;
                    }
                }

                CompteComptable compteComptable = new CompteComptable();
                //Vérifier si une occurence existe déjà
                CleCompteComptable cleCompteComptable = new CleCompteComptable();
                cleCompteComptable.setCodePlan(codePlan);
                cleCompteComptable.setNumCompteComptable(numCompteComptable);
                var old = compteComptableDao.findByIdCompteComptable(cleCompteComptable).orElse(null);
                if (old != null)
                    compteComptable = old;
                compteComptable.setIdCompteComptable(cleCompteComptable);
                compteComptable.setLibelleCompteComptable(libelleCompteComptable);
                compteComptable.setPlan(plan);
                compteComptable.setType(type);
                compteComptable.setEstMvt(estMvt);
                compteComptable.setBilanHorsBilan(bilanHorsBilan);
                compteComptable.setSensMvt(sensMvt);
                compteComptable.setDateCreationServeur(dateCreationServeur);
                compteComptable.setDateDernModifClient(dateDernModifClient);
                compteComptable.setDateDernModifServeur(dateDernModifServeur);
                compteComptable.setSupprimer(supprimer);

                emRefonte.persist(compteComptable);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> typeIBCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_TypeIb");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeTypeIb = ((String) res[0]).trim();
                var libelleTypeIB = ((String) res[1]).trim();
                var codeClasseIb = ((String) res[2]).trim();
                var referenceIBSystem = (Boolean) res[3];
                var dateCreationServeur = ((Timestamp) res[4]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[5]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[6]).toLocalDateTime();
                var numLigne = (BigDecimal) res[7];
                var rowvers = (byte[]) res[8];
                var supprimer = (Boolean) res[9];
                var userLogin = ((String) res[10]).trim();

                ClasseIb classeIb = classeIbDao.findByCodeClasseIbIgnoreCase(codeClasseIb).orElse(null);
                if (classeIb == null) {
                    classeIb = new ClasseIb();
                    classeIb.setCodeClasseIb(codeClasseIb);
                    classeIb.setSupprimer(false);
                } else {
                    var q = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_ClasseIb WHERE codeClasseIb = ?");
                    q.setParameter(1, codeClasseIb);
                    var o = (Object[]) q.getSingleResult();
                    if (o != null) {
                        classeIb.setCodeClasseIb(((String) o[0]).trim());
                        classeIb.setLibelleClasseIb(((String) o[1]));
                        classeIb.setDateCreationServeur(((Timestamp) o[2]).toLocalDateTime());
                        classeIb.setDateDernModifClient(((Timestamp) o[3]).toLocalDateTime());
                        classeIb.setDateDernModifServeur(((Timestamp) o[4]).toLocalDateTime());
                        classeIb.setSupprimer((Boolean) o[7]);
                    }
                }

                TypeIb typeIb = new TypeIb();
                //Vérifier si une occurence existe déjà
                var old = typeIbDao.findByCodeTypeIbIgnoreCase(codeTypeIb).orElse(null);
                if (old != null)
                    typeIb = old;
                typeIb.setCodeTypeIb(codeTypeIb);
                typeIb.setLibelleTypeIB(libelleTypeIB);
                typeIb.setClasseIb(classeIb);
                typeIb.setReferencerIBSysteme(referenceIBSystem);
                typeIb.setDateCreationServeur(dateCreationServeur);
                typeIb.setDateDernModifClient(dateDernModifClient);
                typeIb.setDateDernModifServeur(dateDernModifServeur);
                typeIb.setSupprimer(supprimer);

                emRefonte.persist(typeIb);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> typePositionCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_TypePosition");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeTypePosition = ((String) res[0]).trim();
                var libelleTypePosition = ((String) res[1]).trim();
                var dateCreationServeur = ((Timestamp) res[2]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[3]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[4]).toLocalDateTime();
                var numLigne = (BigDecimal) res[5];
                var rowvers = (byte[]) res[6];
                var supprimer = (Boolean) res[7];
                var userLogin = ((String) res[8]).trim();

                TypePosition typePosition = new TypePosition();
                //Vérifier si une occurence existe déjà
                var old = typePositionDao.findByCodeTypePositionIgnoreCase(codeTypePosition).orElse(null);
                if (old != null)
                    typePosition = old;
                typePosition.setCodeTypePosition(codeTypePosition);
                typePosition.setLibelleTypePosition(libelleTypePosition);
                typePosition.setDateCreationServeur(dateCreationServeur);
                typePosition.setDateDernModifClient(dateDernModifClient);
                typePosition.setDateDernModifServeur(dateDernModifServeur);
                typePosition.setSupprimer(supprimer);

                emRefonte.persist(typePosition);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> typeRubriqueCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_TypeRubrique");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeTypeRubrique = ((String) res[0]).trim();
                var dateCreationServeur = ((Timestamp) res[1]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[2]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[3]).toLocalDateTime();
                var numLigne = (BigDecimal) res[4];
                var rowvers = (byte[]) res[5];
                var supprimer = (Boolean) res[6];
                var userLogin = ((String) res[7]).trim();

                TypeRubrique typeRubrique = new TypeRubrique();
                //Vérifier si une occurence existe déjà
                var old = typeRubriqueDao.findByCodeTypeRubriqueIgnoreCase(codeTypeRubrique).orElse(null);
                if (old != null)
                    typeRubrique = old;
                typeRubrique.setCodeTypeRubrique(codeTypeRubrique);
                typeRubrique.setDateCreationServeur(dateCreationServeur);
                typeRubrique.setDateDernModifClient(dateDernModifClient);
                typeRubrique.setDateDernModifServeur(dateDernModifServeur);
                typeRubrique.setSupprimer(supprimer);

                emRefonte.persist(typeRubrique);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> ibCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_Ib");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeIB = ((String) res[0]).trim();
                var libelleIb = ((String) res[1]).trim();
                var codeTypeIB = ((String) res[2]).trim();
                var estIbSysteme = (Boolean) res[3];
                var dateCreationServeur = ((Timestamp) res[4]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[5]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[6]).toLocalDateTime();
                var numLigne = (BigDecimal) res[7];
                var rowvers = (byte[]) res[8];
                var supprimer = (Boolean) res[9];
                var userLogin = ((String) res[10]).trim();

                TypeIb typeIb = typeIbDao.findByCodeTypeIbIgnoreCase(codeTypeIB).orElse(null);
                if (typeIb == null) {
                    typeIb = new TypeIb();
                    typeIb.setCodeTypeIb(codeTypeIB);
                    typeIb.setSupprimer(false);
                } else {
                    try {
                        Query q = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_TypeIb WHERE codeTypeIb = ?");
                        q.setParameter(1, codeTypeIB);
                        var o = (Object[]) q.getSingleResult();
                        if (o != null) {
                            typeIb.setCodeTypeIb(((String) o[0]).trim());
                            /*typeIb.setLibelleTypeIB(((String)o[1]).trim());
                            typeIb.setNumLigne(((BigDecimal)o[6]).longValue());
                            typeIb.setDateCreationServeur(((Timestamp)o[7]).toLocalDateTime());
                            typeIb.setDateDernModifServeur(((Timestamp)o[8]).toLocalDateTime());
                            typeIb.setDateDernModifClient(((Timestamp)o[9]).toLocalDateTime());
                            typeIb.setUserLogin(((String)o[10]).trim());
                            typeIb.setSupprimer((Boolean)o[11]);*/
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        break;
                    }
                }

                Ib ib = new Ib();
                //Vérifier si une occurence existe déjà
                var old = ibDao.findByCodeIBIgnoreCase(codeIB).orElse(null);
                if (old != null)
                    ib = old;
                ib.setCodeIB(codeIB);
                ib.setLibelleIb(libelleIb);
                ib.setEstIbSysteme(estIbSysteme);
                ib.setTypeIb(typeIb);
                ib.setDateCreationServeur(dateCreationServeur);
                ib.setDateDernModifClient(dateDernModifClient);
                ib.setDateDernModifServeur(dateDernModifServeur);
                ib.setSupprimer(supprimer);

                emRefonte.persist(ib);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> journalCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_Journal");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeJournal = ((String) res[0]).trim();
                var libelleJournal = ((String) res[1]).trim();
                var codePlan = ((String) res[2]).trim();
                var numCompteComptable = ((String) res[3]).trim();
                var numLigne = (BigDecimal) res[4];
                var dateCreationServeur = ((Timestamp) res[5]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[6]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[7]).toLocalDateTime();
                var userLogin = ((String) res[8]).trim();
                var supprimer = (Boolean) res[9];
                var rowvers = (byte[]) res[10];

                Plan plan = planDao.findByCodePlanIgnoreCase(codePlan).orElse(new Plan());
                try {
                    Query q = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_Plan WHERE codePlan = ?");
                    q.setParameter(1, codePlan);
                    var o = (Object[]) q.getSingleResult();
                    if (o != null) {
                        plan.setCodePlan(((String) o[0]).trim());
                        plan.setLibellePlan(((String) o[1]).trim());
                        plan.setNumCompteCapital(((String) o[2]).trim());
                        plan.setNumCompteBenefice(((String) o[3]).trim());
                        plan.setNumComptePerte(((String) o[4]).trim());
                        plan.setNumCompteResInsDistribution(((String) o[5]).trim());
                        plan.setDateCreationServeur(((Timestamp) o[7]).toLocalDateTime());
                        plan.setDateDernModifServeur(((Timestamp) o[8]).toLocalDateTime());
                        plan.setDateDernModifClient(((Timestamp) o[9]).toLocalDateTime());
                        plan.setSupprimer((Boolean) o[11]);
                    }
                } catch (Exception e) {
                    plan = null;
                    System.out.println(e);
                }

//                System.out.println("Journal => " + libelleJournal);

                Journal journal = new Journal();
                //Vérifier si une occurence existe déjà
                var old = journalDao.findByCodeJournalIgnoreCase(codeJournal).orElse(null);
                if (old != null)
                    journal = old;
                journal.setCodeJournal(codeJournal);
                journal.setLibelleJournal(libelleJournal);
                journal.setPlan(plan);
                journal.setNumCompteComptable(numCompteComptable);
                journal.setDateCreationServeur(dateCreationServeur);
                journal.setDateDernModifClient(dateDernModifClient);
                journal.setDateDernModifServeur(dateDernModifServeur);
                journal.setSupprimer(supprimer);

                emRefonte.persist(journal);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> ibRubriqueCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.TJ_IbRubrique");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeIb = ((String) res[0]).trim();
                var codeRubrique = ((String) res[1]).trim();
                var libelleRubrique = ((String) res[2]).trim();
                var codeTypeRubrique = ((String) res[3]).trim();
                var dateCreationServeur = ((Timestamp) res[4]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[5]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[6]).toLocalDateTime();
                var numLigne = (BigDecimal) res[7];
                var rowvers = (byte[]) res[8];
                var supprimer = (Boolean) res[9];
                var userLogin = ((String) res[10]).trim();

                Ib ib = ibDao.findByCodeIBIgnoreCase(codeIb).orElse(null);
                if (ib == null) {
                    ib = new Ib();
                    ib.setCodeIB(codeIb);
                    ib.setSupprimer(false);
                }

                TypeRubrique typeRubrique = typeRubriqueDao.findByCodeTypeRubriqueIgnoreCase(codeTypeRubrique).orElse(null);
                if (typeRubrique == null) {
                    typeRubrique = new TypeRubrique();
                    typeRubrique.setCodeTypeRubrique(codeTypeRubrique);
                    typeRubrique.setSupprimer(false);
                }

                IbRubrique ibRubrique = new IbRubrique();
                //Vérifier si une occurence existe déjà
                CleIbRubrique cleIbRubrique = new CleIbRubrique();
                cleIbRubrique.setCodeRubrique(codeRubrique);
                cleIbRubrique.setCodeIB(codeIb);
                var old = ibRubriqueDao.findByIdIbRubrique(cleIbRubrique).orElse(null);
                if (old != null)
                    ibRubrique = old;
                ibRubrique.setIdIbRubrique(cleIbRubrique);
                ibRubrique.setLibelleRubrique(libelleRubrique);
                ibRubrique.setTypeRubrique(typeRubrique);
                ibRubrique.setIb(ib);
                ibRubrique.setDateCreationServeur(dateCreationServeur);
                ibRubrique.setDateDernModifClient(dateDernModifClient);
                ibRubrique.setDateDernModifServeur(dateDernModifServeur);
                ibRubrique.setSupprimer(supprimer);

                emRefonte.persist(ibRubrique);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> ibRubriquePositionCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.TJ_IbRubriquePosition");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeIb = ((String) res[0]).trim();
                var codeRubrique = ((String) res[1]).trim();
                var codePosition = ((String) res[2]).trim();
                var libellePosition = ((String) res[3]).trim();
                var typeValeur = ((String) res[4]).trim();
                var valeur = ((BigDecimal) res[5]);
                var totalBlocage = ((BigDecimal) res[6]);
                var estModele = ((Boolean) res[7]);
                var dateCreationServeur = ((Timestamp) res[8]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[9]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[10]).toLocalDateTime();
                var numLigne = (BigDecimal) res[11];
                var rowvers = (byte[]) res[12];
                var supprimer = (Boolean) res[13];
                var userLogin = ((String) res[14]).trim();

                Ib ib = ibDao.findByCodeIBIgnoreCase(codeIb).orElse(null);
                if (ib == null) {
                    ib = new Ib();
                    ib.setCodeIB(codeIb);
                    ib.setSupprimer(false);

                }

                IbRubriquePosition ibRubriquePosition = new IbRubriquePosition();
                //Vérifier si une occurence existe déjà
                CleIbRubriquePosition cleIbRubriquePosition = new CleIbRubriquePosition();
                cleIbRubriquePosition.setCodeRubrique(codeRubrique);
                cleIbRubriquePosition.setCodeIB(codeIb);
                cleIbRubriquePosition.setCodePosition(codePosition);
                var old = ibRubriquePositionDao.findById(cleIbRubriquePosition).orElse(null);
                if (old != null)
                    ibRubriquePosition = old;
                ibRubriquePosition.setIdIbRubriquePosition(cleIbRubriquePosition);
                ibRubriquePosition.setLibellePosition(libellePosition);
                ibRubriquePosition.setTypeValeur(typeValeur);
                ibRubriquePosition.setValeur(valeur.doubleValue());
                ibRubriquePosition.setEstModele(estModele);
                ibRubriquePosition.setTotalBlocage(totalBlocage);
                ibRubriquePosition.setIb(ib);
                ibRubriquePosition.setDateCreationServeur(dateCreationServeur);
                ibRubriquePosition.setDateDernModifClient(dateDernModifClient);
                ibRubriquePosition.setDateDernModifServeur(dateDernModifServeur);
                ibRubriquePosition.setSupprimer(supprimer);

                emRefonte.persist(ibRubriquePosition);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> formuleCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_Formule");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var idFormule = ((BigDecimal) res[0]);
                var libelleFormule = ((String) res[1]).trim();
                var codeTypeFormule = ((String) res[2]).trim();
                var numLigne = (BigDecimal) res[3];
                var dateCreationServeur = ((Timestamp) res[4]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[5]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[6]).toLocalDateTime();
                var userLogin = ((String) res[7]).trim();
                var supprimer = (Boolean) res[8];
                var rowvers = (byte[]) res[9];

                TypeFormule typeFormule = null;
                try {
                    /*Query qTypeFormule = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_TypeFormule WHERE codeTypeFormule = ?");
                    qTypeFormule.setParameter(1, codeTypeFormule);
                    var oTypeFormule = (Object[])qTypeFormule.getSingleResult();*/
                    typeFormule = typeFormuleDao.findByCodeTypeFormuleIgnoreCase(codeTypeFormule).orElse(new TypeFormule());
                    if (!(typeFormule.getCodeTypeFormule() != null)) {
                        typeFormule.setCodeTypeFormule(codeTypeFormule);
                        typeFormule.setSupprimer(false);
                        typeFormuleDao.save(typeFormule);
                    }
                }
                catch (Exception e){
                    System.out.println("Impossible de trouver ce type Formule...");
                }

                Formule formule = new Formule();
                //Vérifier si une occurence existe déjà
                var old = formuleDao.findByLibelleFormuleIgnoreCase(libelleFormule);
                if (old != null)
                    formule = old;
                formule.setLibelleFormule(libelleFormule);
                formule.setIdOcc(idFormule.longValue());
                formule.setTypeFormule(typeFormule);
                formule.setDateCreationServeur(dateCreationServeur);
                formule.setDateDernModifClient(dateDernModifClient);
                formule.setDateDernModifServeur(dateDernModifServeur);
                formule.setSupprimer(supprimer);

                emRefonte.persist(formule);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> modeleEcritureCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_ModeleEcriture ");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeModeleEcriture = ((String) res[0]).trim();
                var libelleModeleEcriture = ((String) res[1]).trim();
                var numLigne = (BigDecimal) res[2];
                var dateCreationServeur = ((Timestamp) res[3]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[4]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[5]).toLocalDateTime();
                var userLogin = ((String) res[6]).trim();
                var supprimer = (Boolean) res[7];
                var rowvers = (byte[]) res[8];

                ModeleEcriture modeleEcriture = new ModeleEcriture();
                //Vérifier si une occurence existe déjà
                var old = modeleEcritureDao.findById(codeModeleEcriture).orElse(null);
                if (old != null)
                    modeleEcriture = old;
                modeleEcriture.setCodeModeleEcriture(codeModeleEcriture);
                modeleEcriture.setLibelleModeleEcriture(libelleModeleEcriture);
                modeleEcriture.setDateCreationServeur(dateCreationServeur);
                modeleEcriture.setDateDernModifClient(dateDernModifClient);
                modeleEcriture.setDateDernModifServeur(dateDernModifServeur);
                modeleEcriture.setSupprimer(supprimer);

                emRefonte.persist(modeleEcriture);

                Query queryDetail = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.TJ_DetailModele where coodeModeleEcriture=? ");
                queryDetail.setParameter(1, codeModeleEcriture);
                List<Object[]> resultDetail;
                resultDetail = (List<Object[]>) queryDetail.getResultList();
                for (Object[] resDetail : resultDetail) {
                    var numCompteComptable = ((String) resDetail[1]).trim();
                    var numeroOrdre = ((Integer) resDetail[2]);
                    var sensMvt = ((String) resDetail[3]).trim();
                    var idFormule = ((BigDecimal) resDetail[4]);
                    var numLigne2 = (BigDecimal) resDetail[5];
                    var dateCreationServeur2 = ((Timestamp) resDetail[6]).toLocalDateTime();
                    var dateDernModifServeur2 = ((Timestamp) resDetail[7]).toLocalDateTime();
                    var dateDernModifClient2 = ((Timestamp) resDetail[8]).toLocalDateTime();
                    var userLogin2 = ((String) resDetail[9]).trim();
                    var supprimer2 = (Boolean) resDetail[10];
                    var rowvers2 = (byte[]) resDetail[11];


                    Formule formule = new Formule();
                    formule = formuleDao.findByIdOcc(idFormule.longValue());

                    DetailModele detailModele = new DetailModele();
                    CleDetailModele cleDetailModele = new CleDetailModele();
                    cleDetailModele.setCoodeModeleEcriture(codeModeleEcriture);
                    cleDetailModele.setNumeroOrdre(numeroOrdre);
                    cleDetailModele.setNumCompteComptable(numCompteComptable);
                    //Vérifier si une occurence existe déjà
                    var oldDetail = detailModeleDao.findById(cleDetailModele).orElse(null);
                    if (oldDetail != null)
                        detailModele = oldDetail;
                    detailModele.setIdDetailModele(cleDetailModele);
                    detailModele.setModeleEcriture(modeleEcriture);
                    detailModele.setNumCompteComptable(numCompteComptable);
                    detailModele.setSensMvt(sensMvt);
                    detailModele.setFormule(formule);
                    detailModele.setNumeroOrdre(numeroOrdre.intValue());
                    detailModele.setDateCreationServeur(dateCreationServeur2);
                    detailModele.setDateDernModifClient(dateDernModifClient2);
                    detailModele.setDateDernModifServeur(dateDernModifServeur2);
                    detailModele.setSupprimer(supprimer2);
                    detailModeleDao.save(detailModele);
                }
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> modeleEcritureFormuleCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT m.codeModeleEcriture," +
                    "m.idFormule,m.numLIgne,m.dateCreationServeur,m.dateDernModifServeur," +
                    "m.dateDernModifClient,m.userLOgin,m.supprimer,m.rowvers,mo.libelleModeleEcriture," +
                    "f.libelleFormule " +
                    "FROM Comptabilite.TJ_ModeleEcritureFormule m " +
                    "INNER JOIN Comptabilite.T_ModeleEcriture mo on mo.codeModeleEcriture=m.codeModeleEcriture " +
                    "INNER JOIN Comptabilite.T_Formule f on f.idFormule=m.idFormule " +
                    "");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeModeleEcriture = ((String) res[0]).trim();
                var idFormule = ((BigDecimal) res[1]);
                var numLigne = (BigDecimal) res[2];
                var dateCreationServeur = ((Timestamp) res[3]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[4]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[5]).toLocalDateTime();
                var userLogin = ((String) res[6]).trim();
                var supprimer = (Boolean) res[7];
                var rowvers = (byte[]) res[8];
                var libelleModeleEcriture = ((String) res[9]).trim();
                var libelleFormule = ((String) res[10]).trim();

                ModeleEcriture modeleEcriture = modeleEcritureDao.findById(codeModeleEcriture).orElse(null);
                if (modeleEcriture == null) {
                    modeleEcriture = new ModeleEcriture();
                    modeleEcriture.setCodeModeleEcriture(codeModeleEcriture);
                    modeleEcriture.setLibelleModeleEcriture(libelleModeleEcriture);
                    modeleEcriture.setSupprimer(false);
                }

                Formule formule = formuleDao.findByLibelleFormuleIgnoreCase(libelleFormule);
                if (formule == null) {
                    formule = new Formule();
                    formule.setLibelleFormule(libelleFormule);
                    formule.setSupprimer(false);
                }

                ModeleEcritureFormule modeleEcritureFormule = new ModeleEcritureFormule();
                //Vérifier si une occurence existe déjà
                CleModeleEcritureFormule cleModeleEcritureFormule = new CleModeleEcritureFormule();
                cleModeleEcritureFormule.setCodeModeleEcriture(codeModeleEcriture);
                Formule formule1 = formuleDao.findByIdOcc(idFormule.longValue());
                cleModeleEcritureFormule.setIdFormule(formule1.getIdFormule());
                var old = modeleEcritureFormuleDao.findById(cleModeleEcritureFormule).orElse(null);
                if (old != null)
                    modeleEcritureFormule = old;
                modeleEcritureFormule.setIdModeleEcritureFormule(cleModeleEcritureFormule);
                modeleEcritureFormule.setModeleEcriture(modeleEcriture);
                modeleEcritureFormule.setFormule(formule);
                modeleEcritureFormule.setDateCreationServeur(dateCreationServeur);
                modeleEcritureFormule.setDateDernModifClient(dateDernModifClient);
                modeleEcritureFormule.setDateDernModifServeur(dateDernModifServeur);
                modeleEcritureFormule.setSupprimer(supprimer);

                emRefonte.persist(modeleEcritureFormule);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> modeleEcritureNatureOperationCompta() {
        List<Object[]> result = null;
        /*try {
            Query query = emRefonte.createNativeQuery("SELECT m.codeModeleEcriture," +
                    "m.codeNatureOperation,m.codeTypeTitre,m.numLIgne,m.dateCreationServeur,m.dateDernModifServeur," +
                    "m.dateDernModifClient,m.userLOgin,m.supprimer,m.rowvers,mo.libelleModeleEcriture," +
                    "n.libelleNatureOperation,t.libelleTypeTitre " +
                    "FROM Comptabilite.TJ_ModeleEcritureNatureOperation m " +
                    "INNER JOIN Comptabilite.T_ModeleEcriture mo on mo.codeModeleEcriture=m.codeModeleEcriture " +
                    "INNER JOIN Comptabilite.T_NatureOperation n on n.codeNatureOperation=m.codeNatureOperation " +
                    "INNER JOIN DB_TITRESCIEL.Titre.T_TypeTitre t on t.codeTypeTitre=m.codeTypeTitre ");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeModeleEcriture = ((String) res[0]).trim();
                var codeNatureOperation = ((String) res[1]).trim();
                var codeTypeTitre = ((String) res[2]).trim();
                var numLigne = (BigDecimal) res[3];
                var dateCreationServeur = ((Timestamp) res[4]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[5]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[6]).toLocalDateTime();
                var userLogin = ((String) res[7]).trim();
                var supprimer = (Boolean) res[8];
                var rowvers = (byte[]) res[9];
                var libelleModeleEcriture = ((String) res[10]).trim();
                var libelleNatureOperation = ((String) res[11]).trim();
                var libelleTypeTitre = ((String) res[12]).trim();

                ModeleEcriture modeleEcriture = modeleEcritureDao.findById(codeModeleEcriture).orElse(null);
                if (modeleEcriture == null) {
                    modeleEcriture = new ModeleEcriture();
                    modeleEcriture.setCodeModeleEcriture(codeModeleEcriture);
                    modeleEcriture.setLibelleModeleEcriture(libelleModeleEcriture);
                    modeleEcriture.setSupprimer(false);
                }
//                System.out.println("code "+codeNatureOperation);
                NatureOperation natureOperation = natureOperationDao.findByCodeNatureOperationIgnoreCase(codeNatureOperation).orElse(null);
                if (natureOperation == null) {
//                    System.out.println("pass");
                    natureOperation = new NatureOperation();
                    natureOperation.setLibelleNatureOperation(libelleNatureOperation);
                    natureOperation.setCodeNatureOperation(codeNatureOperation);
                    natureOperation.setSupprimer(false);
                }

                TypeTitre typeTitre = typeTitreDao.findById(codeTypeTitre).orElse(null);
                if (typeTitre == null) {
                    typeTitre = new TypeTitre();
                    typeTitre.setLibelleTypeTitre(libelleTypeTitre);
                    typeTitre.setCodeTypeTitre(codeTypeTitre);
                    //typeTitre.setSupprimer(false);
                    //typeTitre.setNumLigne(0L);
                }

                ModeleEcritureNatureOperation modeleEcritureNatureOperation = new ModeleEcritureNatureOperation();
                //Vérifier si une occurence existe déjà
                var old = modeleEcritureNatureOperationDao.findByModeleEcritureAndNatureOperationAndTypeTitre(modeleEcriture, natureOperation, typeTitre);
                if (old != null)
                    modeleEcritureNatureOperation = old;
                modeleEcritureNatureOperation.setModeleEcriture(modeleEcriture);
                modeleEcritureNatureOperation.setTypeTitre(typeTitre);
                modeleEcritureNatureOperation.setNatureOperation(natureOperation);
                modeleEcritureNatureOperation.setDateCreationServeur(dateCreationServeur);
                modeleEcritureNatureOperation.setDateDernModifClient(dateDernModifClient);
                modeleEcritureNatureOperation.setDateDernModifServeur(dateDernModifServeur);
                modeleEcritureNatureOperation.setSupprimer(supprimer);

                emRefonte.persist(modeleEcritureNatureOperation);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }*/
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> natureOperationCompta() {
        List<Object[]> result = new ArrayList<>();
        try {
            Query query = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_NatureOperation");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codeNatureOperation = ((String) res[0]).trim();
                var libelleNatureOperation = ((String) res[1]).trim();
                var codeTypeOperation = ((String) res[2]).trim();
                var codeJournal = ((String) res[3]).trim();
                var numLigne = (BigDecimal) res[4];
                var dateCreationServeur = ((Timestamp) res[5]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[6]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[7]).toLocalDateTime();
                var userLogin = ((String) res[8]).trim();
                var supprimer = (Boolean) res[9];
                var rowvers = (byte[]) res[10];

                TypeOperation typeOperation = null;
                try {
                    Query qTypeOperation = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_TypeOperation WHERE codeTypeOperation = ?");
                    qTypeOperation.setParameter(1, codeTypeOperation);
                    var oTypeOperation = (Object[])qTypeOperation.getSingleResult();
                    typeOperation = typeOperationDao.findByCodeTypeOperationIgnoreCase(codeTypeOperation).orElse(new TypeOperation());
                    if(!(typeOperation.getCodeTypeOperation() != null)) {
                        typeOperation.setCodeTypeOperation(codeTypeOperation);
                        typeOperation.setLibelleTypeOperation(((String)oTypeOperation[2]).trim());
                        typeOperation.setSupprimer(false);
                        typeOperationDao.save(typeOperation);
                    }
                }
                catch (Exception e) {
                    System.out.println("Impossible de ce type Opération...");
                }

                /*TypeOperation typeOperation = typeOperationDao.findByCodeTypeOperationIgnoreCase(codeTypeOperation).orElse(null);
                if (typeOperation == null) {
//                    System.out.println("typeoperation=="+codeTypeOperation);
                    typeOperation = new TypeOperation();
                    typeOperation.setCodeTypeOperation(codeTypeOperation);
                    typeOperation.setSupprimer(false);
                }*/

                Journal journal = journalDao.findByCodeJournalIgnoreCase(codeJournal).orElse(null);
                if (journal == null) {
                    List<Object[]> resultBase;
                    Query query2 = emRefonte.createNativeQuery("SELECT * FROM Comptabilite.T_Journal where codeJournal = ?");
                    query2.setParameter(1, codeJournal);
                    resultBase = (List<Object[]>) query2.getResultList();
                    if (resultBase.size() != 0) {
                        journal = new Journal();
//                        System.out.println("codeJournal==" + codeJournal);
                        journal.setCodeJournal(codeJournal);
                        journal.setSupprimer(false);
                    }
                }

                NatureOperation natureOperation = new NatureOperation();
                //Vérifier si une occurence existe déjà
                var old = natureOperationDao.findByCodeNatureOperationIgnoreCase(codeNatureOperation).orElse(null);
                if (old != null)
                    natureOperation = old;
                natureOperation.setCodeNatureOperation(codeNatureOperation);
                natureOperation.setLibelleNatureOperation(libelleNatureOperation);
                natureOperation.setTypeOperation(typeOperation);
                natureOperation.setJournal(journal);
                natureOperation.setDateCreationServeur(dateCreationServeur);
                natureOperation.setDateDernModifClient(dateDernModifClient);
                natureOperation.setDateDernModifServeur(dateDernModifServeur);
                natureOperation.setSupprimer(supprimer);

                emRefonte.persist(natureOperation);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            emRefonte.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> posteComptableCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT p.codePosteComptable," +
                    "p.codePlan,p.libellePosteComptable,p.formule,p.type," +
                    "p.numLIgne,p.dateCreationServeur,p.dateDernModifServeur," +
                    "p.dateDernModifClient,p.userLOgin,p.supprimer,p.rowvers,pl.libellePLan " +
                    "FROM Comptabilite.TJ_PosteComptable p " +
                    "INNER JOIN Comptabilite.T_Plan pl on pl.codePLan=p.codePlan ");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codePosteComptable = ((String) res[0]).trim();
                var codePlan = ((String) res[1]).trim();
                var libellePosteComptable = ((String) res[2]).trim();
                var formule = ((String) res[3]).trim();
                var type = ((String) res[4]).trim();
                var numLigne = (BigDecimal) res[5];
                var dateCreationServeur = ((Timestamp) res[6]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[7]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[8]).toLocalDateTime();
                var userLogin = ((String) res[9]).trim();
                var supprimer = (Boolean) res[10];
                var rowvers = (byte[]) res[11];
                var libellePlan = ((String) res[12]).trim();

                Plan plan = planDao.findById(codePlan).orElse(null);
                if (plan == null) {
                    plan = new Plan();
                    plan.setCodePlan(codePlan);
                    plan.setLibellePlan(libellePlan);
                    plan.setSupprimer(false);
                }

                PosteComptable posteComptable = new PosteComptable();
                //Vérifier si une occurence existe déjà
                ClePosteComptable clePosteComptable = new ClePosteComptable();
                clePosteComptable.setCodePosteComptable(codePosteComptable);
                clePosteComptable.setCodePlan(codePlan);
                var old = posteComptableDao.findById(clePosteComptable).orElse(null);
                if (old != null)
                    posteComptable = old;
                posteComptable.setIdPosteComptable(clePosteComptable);
                posteComptable.setPlan(plan);
                posteComptable.setFormule(formule);
                posteComptable.setType(type);
                posteComptable.setCodePosteComptable(codePosteComptable);
                posteComptable.setLibellePosteComptable(libellePosteComptable);
                posteComptable.setDateCreationServeur(dateCreationServeur);
                posteComptable.setDateDernModifClient(dateDernModifClient);
                posteComptable.setDateDernModifServeur(dateDernModifServeur);
                posteComptable.setSupprimer(supprimer);

                emRefonte.persist(posteComptable);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object[]> correspondanceCompta() {
        List<Object[]> result;
        try {
            Query query = emRefonte.createNativeQuery("SELECT  c.codePlan," +
                    "c.numeroCompteComptable,c.codeIb,c.codeRubrique,c.codePosition," +
                    "c.totalBlocage,c.valeur,c.numLIgne,c.dateCreationServeur,c.dateDernModifServeur," +
                    "c.dateDernModifClient,c.userLOgin,c.supprimer,c.rowvers,pl.libellePLan," +
                    "i.libelleIb " +
                    "FROM Comptabilite.TJ_Correspondance c " +
                    "INNER JOIN Comptabilite.T_Plan pl on pl.codePLan=c.codePlan " +
                    "INNER JOIN Comptabilite.T_Ib i on i.codeIb=c.codeIb ");
            //query.setParameter(1, id);
            result = (List<Object[]>) query.getResultList();
            for (Object[] res : result) {
                var codePlan = ((String) res[0]).trim();
                var numeroCompteComptable = ((String) res[1]).trim();
                var codeIb = ((String) res[2]).trim();
                var codeRubrique = ((String) res[3]).trim();
                var codePosition = ((String) res[4]).trim();
                var totalBlocage = ((BigDecimal) res[5]);
                var valeur = ((BigDecimal) res[6]);
                var numLigne = (BigDecimal) res[7];
                var dateCreationServeur = ((Timestamp) res[8]).toLocalDateTime();
                var dateDernModifServeur = ((Timestamp) res[9]).toLocalDateTime();
                var dateDernModifClient = ((Timestamp) res[10]).toLocalDateTime();
                var userLogin = ((String) res[11]).trim();
                var supprimer = (Boolean) res[12];
                var rowvers = (byte[]) res[13];
                var libellePlan = ((String) res[14]).trim();
                var libelleIb = ((String) res[15]).trim();

                Plan plan = planDao.findById(codePlan).orElse(null);
                if (plan == null) {
                    plan = new Plan();
                    plan.setCodePlan(codePlan);
                    plan.setLibellePlan(libellePlan);
                    plan.setSupprimer(false);
                }

                Ib ib = ibDao.findById(codeIb).orElse(null);
                if (ib == null) {
                    ib = new Ib();
                    ib.setCodeIB(codeIb);
                    ib.setLibelleIb(libelleIb);
                    ib.setSupprimer(false);

                }

                Correspondance correspondance = new Correspondance();
                //Vérifier si une occurence existe déjà
                CleCorrespondance cleCorrespondance = new CleCorrespondance();
                cleCorrespondance.setCodeIB(codeIb);
                cleCorrespondance.setCodePosition(codePosition);
                cleCorrespondance.setCodeRubrique(codeRubrique);
                cleCorrespondance.setCodePlan(codePlan);
//                cleCorrespondance.setNumCompteComptable(numeroCompteComptable);
                var old = correspondanceDao.findById(cleCorrespondance).orElse(null);
                if (old != null)
                    correspondance = old;
                correspondance.setIdCorrespondance(cleCorrespondance);
                correspondance.setPlan(plan);
                correspondance.setIb(ib);
                correspondance.setCodeRubrique(codeRubrique);
                correspondance.setCodePosition(codePosition);
//                correspondance.setNumCompteComptable(numeroCompteComptable);
                correspondance.setTotalBlocage(totalBlocage);
                correspondance.setValeur(valeur);
                correspondance.setDateCreationServeur(dateCreationServeur);
                correspondance.setDateDernModifClient(dateDernModifClient);
                correspondance.setDateDernModifServeur(dateDernModifServeur);
                correspondance.setSupprimer(supprimer);

                emRefonte.persist(correspondance);
            }
        } finally {
            emTitre.close();
            emRefonte.close();
        }
        return result;
    }

    public String toString() {
        String serialized = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            serialized = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return serialized;
    }

    //pays
    public void insertNewPays() {
        try {
            FileInputStream file = new FileInputStream(new File("src\\main\\resources\\excel\\Pays.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0); // Deuxième feuille (index 1)

            int i = 0;
            for (Row row : sheet) {
                i++;
                if (i > 1) {
//                    System.out.println("Code Pays === " + row.getCell(6).getStringCellValue());
                    Pays pays = paysDao.findByLibelleFrIgnoreCase(row.getCell(6).getStringCellValue()).orElse(new Pays());
                    pays.setCodePays(row.getCell(1).getStringCellValue());
                    pays.setEstGafi(pays.getIdPays() != null ? pays.isEstGafi() : false);
                    pays.setEstUEMOA(pays.getIdPays() != null ? pays.isEstUEMOA() : false);
                    pays.setIndicatif(Integer.valueOf((int) row.getCell(4).getNumericCellValue()));
                    pays.setLibelleEn(row.getCell(5).getStringCellValue());
                    pays.setLibelleFr(row.getCell(6).getStringCellValue());
                    if (row.getCell(8).getStringCellValue() != null) {
//                        System.out.println("Code Monnaie === " + row.getCell(8).getStringCellValue());
                        Monnaie monnaie = monnaieDao.findByCodeMonnaie(row.getCell(8).getStringCellValue());
                        pays.setMonnaie(monnaie);
                    }
                    paysDao.save(pays);


                    /*if (paysExist == null) {
                        System.out.println("Nouveau pays=="+row.getCell(6).getStringCellValue());
                        Pays pays = new Pays();
                        pays.setCodePays(row.getCell(1).getStringCellValue());
                        pays.setEstGafi(false);
                        pays.setEstUEMOA(false);
                        pays.setIndicatif(Integer.valueOf((int) row.getCell(4).getNumericCellValue()));
                        pays.setLibelleEn(row.getCell(5).getStringCellValue());
                        pays.setLibelleFr(row.getCell(6).getStringCellValue());
                        if(row.getCell(8).getStringCellValue()!=null)
                        {
                            Monnaie monnaie = monnaieDao.findByCodeMonnaie(row.getCell(8).getStringCellValue());
                            pays.setMonnaie(monnaie);
                        }
                        paysDao.save(pays);
                    }
                    else
                    {
                        System.out.println("Pays existant=="+row.getCell(6).getStringCellValue());
                        Pays pays = new Pays();
                        pays.setIdPays(paysExist.getIdPays());
                        pays.setCodePays(row.getCell(1).getStringCellValue());
                        pays.setEstGafi(paysExist.isEstGafi());
                        pays.setEstUEMOA(paysExist.isEstUEMOA());
                        pays.setIndicatif(Integer.valueOf((int) row.getCell(4).getNumericCellValue()));
                        pays.setLibelleEn(row.getCell(5).getStringCellValue());
                        pays.setLibelleFr(row.getCell(6).getStringCellValue());
                        if(row.getCell(8).getStringCellValue()!=null)
                        {
                            Monnaie monnaie = monnaieDao.findByCodeMonnaie(row.getCell(8).getStringCellValue());
                            pays.setMonnaie(monnaie);
                        }
                        paysDao.save(pays);
                    }*/
                }
            }
        } catch (IOException e) {
            // Gérer les erreurs de lecture du fichier Excel
            System.out.println(e.getMessage());
        }
    }
}
