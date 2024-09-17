package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.standard.PaysDao;
import com.ged.dao.standard.PersonneMoraleDao;
import com.ged.dao.standard.PersonnePhysiqueDao;
import com.ged.dao.standard.VilleDao;
import com.ged.dao.titresciel.*;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.*;
import com.ged.entity.titresciel.FormeJuridiqueOpc;
import com.ged.entity.titresciel.TypeAffectationTitre;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OpcvmService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpcvmServiceImpl implements OpcvmService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    /*@Autowired
    @Qualifier("titrescielEntityManagerFactory")
    private EntityManager emTitre;*/
    private final OpcvmDao opcvmDao;
    private final OpcvmMapper opcvmMapper;
    private final PaysDao paysDao;
    private final VilleDao villeDao;
    private final ClassificationOPCDao classificationOPCDao;
    private final TypeAffectationTitreDao typeAffectationTitreDao;
    private final PersonnePhysiqueDao personnePhysiqueDao;
    private final FormeJuridiqueOpcDao formeJuridiqueOpcDao;
    private final TitreDao titreDao;
    private final PersonneMoraleDao personneMoraleDao;

    public OpcvmServiceImpl(OpcvmDao opcvmDao, OpcvmMapper opcvmMapper, PaysDao paysDao, VilleDao villeDao, ClassificationOPCDao classificationOPCDao, TypeAffectationTitreDao typeAffectationTitreDao, PersonnePhysiqueDao personnePhysiqueDao, FormeJuridiqueOpcDao formeJuridiqueOpcDao, TitreDao titreDao, PersonneMoraleDao personneMoraleDao) {
        this.opcvmDao = opcvmDao;
        this.opcvmMapper = opcvmMapper;
        this.paysDao = paysDao;
        this.villeDao = villeDao;
        this.classificationOPCDao = classificationOPCDao;
        this.typeAffectationTitreDao = typeAffectationTitreDao;
        this.personnePhysiqueDao = personnePhysiqueDao;
        this.formeJuridiqueOpcDao = formeJuridiqueOpcDao;
        this.titreDao = titreDao;
        this.personneMoraleDao = personneMoraleDao;
    }

    /*@Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object> createOpcvmFromOpcciel1() throws JsonProcessingException {
        List<Object> result = new ArrayList<>();
        List<Object[]> opcvms;
        //Se connecter à opcciel1 et récupérer les différentes opcvms
        try {
            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Parametre].[PS_Opcvm_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("idOpcvm", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("ibOpcvm", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("agrement", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateAgrement", java.time.LocalDateTime.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("registreDeCommerce", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("estCompteSysteme", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("idPersonneGestionnaire", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nomPersonneGestionnaire", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("codeFormeJuridique", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleFormeJuridique", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("idPersonneIntervenant", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleIntervenant", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("codeTypeAffectation", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleAffectation", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("idPersonneEmetteur", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleEmetteur", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("codeClassification", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleClassification", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("idTitre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationOpcvm", java.time.LocalDateTime.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("sigleOpcvm", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("denominationOpcvm", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nbrePartInitial", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nbrePartDebutExercice", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nbrePartActuelle", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("valeurLiquidativeOrigine", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("valeurLiquidativeActuelle", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("capitalInitialOpcvm", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("coursInitial", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("coursActuel", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dureeExerciceOpcvm", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("debutExerciceActuelOpcvm", java.time.LocalDateTime.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("uniteDureeExerciceOpcvm", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("periodiciteAffectationOpcvm", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("unitePeriodiciteAffectationOpcvm", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("derniereDateAffectationOpcvm", java.time.LocalDateTime.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("periodiciteCalculValeurLiquidativeOpcvm", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("unitePeriodiciteCalculValeurLiquidative", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("valeurMinimalPlacement", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dureeMinimalPlacement", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("uniteDureeMinimalPlacement", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateProchainCalculVL", java.time.LocalDateTime.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("appliqueeTVA", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("appliqueeTAF", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nombreDecimaux", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("estArrondiSupInf", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nombreDecimauxCompta", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("estArrondiSupInfCompta", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nombreDecimauxPart", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("tauxCommissionSouscription", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("tauxCommissionRachat", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("tauxTAF", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("tauxRetrocessionSouscription", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("tauxRetrocessionRachat", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("tauxFraisGestion", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("AppliquerSurActifNet", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("delaiLivraisonOpcvm", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("uniteDelaiLivraisonOpcvm", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nomContact", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("prenomContact", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("adresseContact", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("borneInferieureSensibilite", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("borneSuperieureSensibilite", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("inclusBorneInferieureSensibilite", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("inclusBorneSuperieureSensibilite", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("visaNoteInformation", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("verifier", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("verifierNiveau1", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("verifierNiveau2", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("verificateur1", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("verificateur2", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateVerifNiveau1", Character.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateVerifNiveau2", Character.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("cheminArchive", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("adresseComplete", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("telephoneFixe", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("telephoneMobile", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("codeSkype", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("siteweb", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("fax", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("email", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("boitePostale", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("codePays", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libellePays", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleVille", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("numLigne", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifClient", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("rowvers", byte[].class, ParameterMode.IN);
            //Fournir les différents paramètres
            query.setParameter("idOpcvm", null);
            query.setParameter("ibOpcvm", null);
            query.setParameter("agrement", null);
            query.setParameter("dateAgrement", null);
            query.setParameter("registreDeCommerce", null);
            query.setParameter("estCompteSysteme", false);
            query.setParameter("idPersonneGestionnaire", null);
            query.setParameter("nomPersonneGestionnaire", null);
            query.setParameter("codeFormeJuridique", null);
            query.setParameter("libelleFormeJuridique", null);
            query.setParameter("idPersonneIntervenant", null);
            query.setParameter("libelleIntervenant", null);
            query.setParameter("codeTypeAffectation", null);
            query.setParameter("libelleAffectation", null);
            query.setParameter("idPersonneEmetteur", null);
            query.setParameter("libelleEmetteur", null);
            query.setParameter("codeClassification", null);
            query.setParameter("libelleClassification", null);
            query.setParameter("idTitre", null);
            query.setParameter("dateCreationOpcvm", null);
            query.setParameter("sigleOpcvm", null);
            query.setParameter("denominationOpcvm", null);
            query.setParameter("nbrePartInitial", null);
            query.setParameter("nbrePartDebutExercice", null);
            query.setParameter("nbrePartActuelle", null);
            query.setParameter("valeurLiquidativeOrigine", null);
            query.setParameter("valeurLiquidativeActuelle", null);
            query.setParameter("capitalInitialOpcvm", null);
            query.setParameter("coursInitial", null);
            query.setParameter("coursActuel", null);
            query.setParameter("dureeExerciceOpcvm", null);
            query.setParameter("debutExerciceActuelOpcvm", null);
            query.setParameter("uniteDureeExerciceOpcvm", null);
            query.setParameter("periodiciteAffectationOpcvm", null);
            query.setParameter("unitePeriodiciteAffectationOpcvm", null);
            query.setParameter("derniereDateAffectationOpcvm", null);
            query.setParameter("periodiciteCalculValeurLiquidativeOpcvm", null);
            query.setParameter("unitePeriodiciteCalculValeurLiquidative", null);
            query.setParameter("valeurMinimalPlacement", null);
            query.setParameter("dureeMinimalPlacement", null);
            query.setParameter("uniteDureeMinimalPlacement", null);
            query.setParameter("dateProchainCalculVL", null);
            query.setParameter("appliqueeTVA", null);
            query.setParameter("appliqueeTAF", null);
            query.setParameter("nombreDecimaux", null);
            query.setParameter("estArrondiSupInf", null);
            query.setParameter("nombreDecimauxCompta", null);
            query.setParameter("estArrondiSupInfCompta", null);
            query.setParameter("nombreDecimauxPart", null);
            query.setParameter("tauxCommissionSouscription", null);
            query.setParameter("tauxCommissionRachat", null);
            query.setParameter("tauxTAF", null);
            query.setParameter("tauxRetrocessionSouscription", null);
            query.setParameter("tauxRetrocessionRachat", null);
            query.setParameter("tauxFraisGestion", null);
            query.setParameter("AppliquerSurActifNet", null);
            query.setParameter("delaiLivraisonOpcvm", null);
            query.setParameter("uniteDelaiLivraisonOpcvm", null);
            query.setParameter("nomContact", null);
            query.setParameter("prenomContact", null);
            query.setParameter("adresseContact", null);
            query.setParameter("borneInferieureSensibilite", null);
            query.setParameter("borneSuperieureSensibilite", null);
            query.setParameter("inclusBorneInferieureSensibilite", null);
            query.setParameter("inclusBorneSuperieureSensibilite", null);
            query.setParameter("visaNoteInformation", null);
            query.setParameter("verifier", null);
            query.setParameter("verifierNiveau1", null);
            query.setParameter("verifierNiveau2", null);
            query.setParameter("verificateur1", null);
            query.setParameter("verificateur2", null);
            query.setParameter("dateVerifNiveau1", null);
            query.setParameter("dateVerifNiveau2", null);
            query.setParameter("cheminArchive", null);
            query.setParameter("adresseComplete", null);
            query.setParameter("telephoneFixe", null);
            query.setParameter("telephoneMobile", null);
            query.setParameter("codeSkype", null);
            query.setParameter("siteweb", null);
            query.setParameter("fax", null);
            query.setParameter("email", null);
            query.setParameter("boitePostale", null);
            query.setParameter("codePays", null);
            query.setParameter("libellePays", null);
            query.setParameter("libelleVille", null);
            query.setParameter("numLigne", null);
            query.setParameter("dateCreationServeur", null);
            query.setParameter("dateDernModifServeur", null);
            query.setParameter("dateDernModifClient", null);
            query.setParameter("userLogin", null);
            query.setParameter("supprimer", false);
            query.setParameter("rowvers", null);
            query.execute();
            opcvms = query.getResultList();
            int cpt = 0;
            for (Object[] o: opcvms) {
                Opcvm opcvm = !opcvmDao.existsByAgrementIgnoreCase((String)o[2]) ? new Opcvm() : opcvmDao.findByAgrementIgnoreCase((String)o[2]);
                opcvm.setIbOpcvm(((String)o[1]).trim());
                opcvm.setAgrement(((String)o[2]).trim());
                opcvm.setDateAgrement(((Timestamp)o[3]).toLocalDateTime());
                opcvm.setRegistreDeCommerce(((String)o[4]).trim());
                opcvm.setEstCompteSysteme((Boolean)o[5]);
                opcvm.setPersonneGestionnaire(personnePhysiqueDao.findById(Long.valueOf(((String)o[6]).trim())).orElse(null));
                opcvm.setCodeFormeJuridique((String)o[8]);
                opcvm.setFormeJuridiqueOpc(formeJuridiqueOpcDao.findByCodeFormeJuridiqueOpcIgnoreCase(((String)o[8]).trim()).orElse(null));

                Query qRegistraire = emTitre.createNativeQuery("SELECT * FROM Titre.T_Registraire WHERE idRegistraire = ?");
                qRegistraire.setParameter(1, (BigDecimal.valueOf(Long.parseLong(((String)o[10]).trim()))).longValue());
                var oRegistraire = (Object[]) qRegistraire.getSingleResult();
                List<PersonneMorale> oListeRegistraire = personneMoraleDao.findBySigleContainsIgnoreCase(((String)oRegistraire[1]).trim());
                PersonneMorale registraire = null;
                if(oListeRegistraire != null && oListeRegistraire.size() > 0) {
                    registraire = oListeRegistraire.get(0);
                }
//                opcvm.setPersonneIntervenant(registraire);
                opcvm.setTypeAffectationTitre(typeAffectationTitreDao.findByLibelleTypeAffectationIgnoreCase(((String)o[12]).trim()).orElse(null));

                Query qEmetteur = emTitre.createNativeQuery("SELECT * FROM Titre.T_Emetteur WHERE idEmetteur = ?");
                qEmetteur.setParameter(1, (BigDecimal.valueOf(Long.parseLong(((String)o[14]).trim()))).longValue());
                var oEmetteur = (Object[]) qEmetteur.getSingleResult();
                List<PersonneMorale> oListeEmetteur = personneMoraleDao.findBySigleContainsIgnoreCase(((String)oEmetteur[1]).trim());
                PersonneMorale emetteur = null;
                if(oListeEmetteur != null && oListeEmetteur.size() > 0) {
                    emetteur = oListeEmetteur.get(0);
                }
//                opcvm.setPersonneEmetteur(emetteur);
//                opcvm.setClassification(classificationOPCDao.findByCodeClassificationIgnoreCase(((String)o[16]).trim()).orElse(null));

                Titre titre = titreDao.findByIdOcc((BigDecimal.valueOf(Long.parseLong(((String)o[18]).trim()))).longValue()).orElse(null);
//                opcvm.setTitre(titre);

                opcvm.setDateCreationOpcvm(((Timestamp)o[19]).toLocalDateTime());
                opcvm.setSigleOpcvm(((String)o[20]).trim());
                opcvm.setDenominationOpcvm(((String)o[21]).trim());
                opcvm.setNbrePartInitial((BigDecimal)o[22]);
                opcvm.setNbrePartDebutExercice((BigDecimal)o[23]);
                opcvm.setNbrePartActuelle((BigDecimal)o[24]);
                opcvm.setValeurLiquidativeOrigine((BigDecimal)o[25]);
                opcvm.setValeurLiquidativeActuelle((BigDecimal)o[26]);
                opcvm.setCapitalInitialOpcvm((BigDecimal)o[27]);
                opcvm.setCoursInitial(((String)o[28]).trim());
                opcvm.setCoursActuel(((String)o[29]).trim());
                opcvm.setDureeExerciceOpcvm((Short)o[30]);
                opcvm.setDebutExerciceActuelOpcvm(((Timestamp)o[31]).toLocalDateTime());
                opcvm.setUniteDureeExerciceOpcvm(((String)o[32]).trim());
                opcvm.setPeriodiciteAffectationOpcvm((Short)o[33]);
                opcvm.setUnitePeriodiciteAffectationOpcvm(((String)o[34]).trim());
                opcvm.setDerniereDateAffectationOpcvm(((Timestamp)o[35]).toLocalDateTime());
                opcvm.setPeriodiciteCalculValeurLiquidativeOpcvm((Short)o[36]);
                opcvm.setUnitePeriodiciteCalculValeurLiquidative(((String)o[37]).trim());
                opcvm.setValeurMinimalPlacement((BigDecimal)o[38]);
                opcvm.setDureeMinimalPlacement((Short)o[39]);
                opcvm.setUniteDureeMinimalPlacement(((String)o[40]).trim());
                opcvm.setDateProchainCalculVL(((Timestamp)o[41]).toLocalDateTime());
                opcvm.setAppliqueeTVA((Boolean)o[42]);
                opcvm.setAppliqueeTAF((Boolean)o[43]);
                opcvm.setNombreDecimaux((Integer)o[44]);
                opcvm.setEstArrondiSupInf((Boolean)o[45]);
                opcvm.setNombreDecimauxCompta((Integer)o[46]);
                opcvm.setEstArrondiSupInfCompta((Boolean)o[47]);
                opcvm.setNombreDecimauxPart((Integer)o[48]);
                opcvm.setTauxCommissionSouscription((BigDecimal)o[49]);
                opcvm.setTauxCommissionRachat((BigDecimal)o[50]);
                opcvm.setTauxTAF((BigDecimal)o[51]);
                opcvm.setTauxRetrocessionSouscription((BigDecimal)o[52]);
                opcvm.setTauxRetrocessionRachat((BigDecimal)o[53]);
                opcvm.setTauxFraisGestion((BigDecimal)o[54]);
                opcvm.setAppliquerSurActifNet((Boolean)o[55]);
                opcvm.setDelaiLivraisonOpcvm((Short)o[56]);
                opcvm.setUniteDelaiLivraisonOpcvm(((String)o[57]).trim());
                opcvm.setNomContact(((String)o[58]).trim());
                opcvm.setPrenomContact(((String)o[59]).trim());
                opcvm.setAdresseContact(((String)o[60]).trim());
                opcvm.setBorneInferieureSensibilite((BigDecimal)o[61]);
                opcvm.setBorneSuperieureSensibilite((BigDecimal)o[62]);
                opcvm.setInclusBorneInferieureSensibilite((Boolean)o[63]);
                opcvm.setInclusBorneSuperieureSensibilite((Boolean)o[64]);
                opcvm.setVisaNoteInformation(((String)o[65]).trim());
                opcvm.setVerifier((Boolean)o[66]);
                opcvm.setVerifierNiveau1((Boolean)o[67]);
                opcvm.setVerifierNiveau2((Boolean)o[68]);
                opcvm.setVerificateur1(((String)o[69]).trim());
                opcvm.setVerificateur2(((String)o[70]).trim());
                opcvm.setDateVerifNiveau1(((String)o[71]).trim());
                opcvm.setDateVerifNiveau2(((String)o[72]).trim());
                opcvm.setCheminArchive(((String)o[73]).trim());
                opcvm.setAdresseComplete(((String)o[74]).trim());
                opcvm.setTelephoneFixe(((String)o[75]).trim());
                opcvm.setTelephoneMobile(((String)o[76]).trim());
                opcvm.setCodeSkype(((String)o[77]).trim());
                opcvm.setSiteweb(((String)o[78]).trim());
                opcvm.setFax(((String)o[79]).trim());
                opcvm.setEmail(((String)o[80]).trim());
                opcvm.setBoitePostale(((String)o[81]).trim());
                opcvm.setPays(paysDao.findByCodePaysIgnoreCase((String)o[82]).orElse(null));
                opcvm.setVille(villeDao.findByLibelleVilleIgnoreCase(((String)o[83]).trim()).orElse(null));
                opcvm.setSupprimer(false);
                result.add(opcvmDao.save(opcvm));
                cpt++;
            }
        } catch (Exception e) {
            throw e;
        }

        return result;
    }*/

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"denominationOpcvm");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Opcvm> opcvmPage;
            opcvmPage = opcvmDao.findAll(pageable);
            List<OpcvmDto> content = opcvmPage.getContent().stream().map(opcvmMapper::deOpcvm).collect(Collectors.toList());
            DataTablesResponse<OpcvmDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)opcvmPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)opcvmPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des opcvms par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public Opcvm afficherSelonId(Long id) {
        return opcvmDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Opcvm.class, "id", id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Opcvm dont ID = " + id.toString(),
                    HttpStatus.OK,
                    opcvmMapper.deOpcvm(afficherSelonId(id)));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherListe() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste des opcvms" ,
                    HttpStatus.OK,
                    opcvmDao.findAll().stream().map(opcvmMapper::deOpcvm).collect(Collectors.toList()));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> creer(OpcvmDto opcvmDto) {
        try {
            Opcvm opcvm = opcvmMapper.deOpcvmDto(opcvmDto);
            /*if (opcvmDto.getClassification() != null && opcvmDto.getClassification().getCodeClassification() != null) {
                ClassificationOPC classificationOPC = classificationOPCDao.findById(opcvmDto.getClassification().getCodeClassification())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(ClassificationOPC.class, opcvmDto.getClassification().getCodeClassification()));
                opcvm.setClassification(classificationOPC);
            }*/

            if(opcvmDto.getTypeAffectationTitre() != null && opcvmDto.getTypeAffectationTitre().getIdTypeAffectation() != null) {
                TypeAffectationTitre typeAffectationTitre = typeAffectationTitreDao.findById(opcvmDto.getTypeAffectationTitre().getIdTypeAffectation())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(TypeAffectationTitre.class, "id", opcvmDto.getTypeAffectationTitre().getIdTypeAffectation().toString()));
                opcvm.setTypeAffectationTitre(typeAffectationTitre);
            }

            if(opcvmDto.getFormeJuridiqueOpc() != null && opcvmDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc() != null) {
                FormeJuridiqueOpc formeJuridiqueOpc = formeJuridiqueOpcDao.findById(opcvmDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(FormeJuridiqueOpc.class, "id", opcvmDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc().toString()));
                opcvm.setFormeJuridiqueOpc(formeJuridiqueOpc);
            }

            if(opcvmDto.getPays() != null && opcvmDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(opcvmDto.getPays().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, "id", opcvmDto.getPays().getIdPays().toString()));
                opcvm.setPays(pays);
            }

            if(opcvmDto.getVille() != null && opcvmDto.getVille().getIdVille() != null) {
                Ville ville = villeDao.findById(opcvmDto.getVille().getIdVille())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Ville.class, "id", opcvmDto.getVille().getIdVille().toString()));
                opcvm.setVille(ville);
            }

            /*if(opcvmDto.getPersonneEmetteur() != null && opcvmDto.getPersonneEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(opcvmDto.getPersonneEmetteur().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(PersonneMorale.class, "id", opcvmDto.getPersonneEmetteur().getIdPersonne().toString()));
                opcvm.setPersonneEmetteur(emetteur);
            }*/

            /*if(opcvmDto.getPersonneIntervenant() != null && opcvmDto.getPersonneIntervenant().getIdPersonne() != null) {
                PersonneMorale intervenant = personneMoraleDao.findById(opcvmDto.getPersonneIntervenant().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(PersonneMorale.class, "id", opcvmDto.getPersonneIntervenant().getIdPersonne().toString()));
                opcvm.setPersonneIntervenant(intervenant);
            }*/

            if(opcvmDto.getPersonneGestionnaire() != null && opcvmDto.getPersonneGestionnaire().getIdPersonne() != null) {
                PersonnePhysique gestionnaire = personnePhysiqueDao.findById(opcvmDto.getPersonneGestionnaire().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(PersonnePhysique.class, "id", opcvmDto.getPersonneGestionnaire().getIdPersonne().toString()));
                opcvm.setPersonneGestionnaire(gestionnaire);
            }
            opcvm = opcvmDao.save(opcvm);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    opcvmMapper.deOpcvm(opcvm));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> modifier(OpcvmDto opcvmDto) {
        try {
            if(!opcvmDao.existsById(opcvmDto.getIdOpcvm()))
                throw  new EntityNotFoundException(Opcvm.class, "id", opcvmDto.getIdOpcvm().toString());
            Opcvm opcvm = opcvmMapper.deOpcvmDto(opcvmDto);
            /*if (opcvmDto.getClassification() != null && opcvmDto.getClassification().getCodeClassification() != null) {
                ClassificationOPC classificationOPC = classificationOPCDao.findById(opcvmDto.getClassification().getCodeClassification())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(ClassificationOPC.class, opcvmDto.getClassification().getCodeClassification()));
                opcvm.setClassification(classificationOPC);
            }*/

            if(opcvmDto.getTypeAffectationTitre() != null && opcvmDto.getTypeAffectationTitre().getIdTypeAffectation() != null) {
                TypeAffectationTitre typeAffectationTitre = typeAffectationTitreDao.findById(opcvmDto.getTypeAffectationTitre().getIdTypeAffectation())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(TypeAffectationTitre.class, "id", opcvmDto.getTypeAffectationTitre().getIdTypeAffectation().toString()));
                opcvm.setTypeAffectationTitre(typeAffectationTitre);
            }

            if(opcvmDto.getFormeJuridiqueOpc() != null && opcvmDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc() != null) {
                FormeJuridiqueOpc formeJuridiqueOpc = formeJuridiqueOpcDao.findById(opcvmDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(FormeJuridiqueOpc.class, "id", opcvmDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc().toString()));
                opcvm.setFormeJuridiqueOpc(formeJuridiqueOpc);
            }

            if(opcvmDto.getPays() != null && opcvmDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(opcvmDto.getPays().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, "id", opcvmDto.getPays().getIdPays().toString()));
                opcvm.setPays(pays);
            }

            if(opcvmDto.getVille() != null && opcvmDto.getVille().getIdVille() != null) {
                Ville ville = villeDao.findById(opcvmDto.getVille().getIdVille())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Ville.class, "id", opcvmDto.getVille().getIdVille().toString()));
                opcvm.setVille(ville);
            }

            /*if(opcvmDto.getPersonneEmetteur() != null && opcvmDto.getPersonneEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(opcvmDto.getPersonneEmetteur().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(PersonneMorale.class, "id", opcvmDto.getPersonneEmetteur().getIdPersonne().toString()));
                opcvm.setPersonneEmetteur(emetteur);
            }*/

            /*if(opcvmDto.getPersonneIntervenant() != null && opcvmDto.getPersonneIntervenant().getIdPersonne() != null) {
                PersonneMorale intervenant = personneMoraleDao.findById(opcvmDto.getPersonneIntervenant().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(PersonneMorale.class, "id", opcvmDto.getPersonneIntervenant().getIdPersonne().toString()));
                opcvm.setPersonneIntervenant(intervenant);
            }*/

            if(opcvmDto.getPersonneGestionnaire() != null && opcvmDto.getPersonneGestionnaire().getIdPersonne() != null) {
                PersonnePhysique gestionnaire = personnePhysiqueDao.findById(opcvmDto.getPersonneGestionnaire().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(PersonnePhysique.class, "id", opcvmDto.getPersonneGestionnaire().getIdPersonne().toString()));
                opcvm.setPersonneGestionnaire(gestionnaire);
            }
            opcvm = opcvmDao.save(opcvm);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    opcvmMapper.deOpcvm(opcvm));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> supprimer(Long id) {
        try {
            opcvmDao.deleteById(id);
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
