package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.*;
import com.ged.dao.opcciel.comptabilite.ExerciceDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.OperationDao;
import com.ged.dao.titresciel.DatDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.OperationChargeAEtalerDto;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.entity.opcciel.OperationChargeAEtaler;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.mapper.opcciel.*;
import com.ged.mapper.titresciel.DatMapper;
import com.ged.projection.FT_GenererChargeProjection;
import com.ged.projection.OperationDifferenceEstimationProjection;
import com.ged.projection.SoldeCompteFormuleProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.opcciel.OperationChargeAEtalerService;
import com.ged.service.opcciel.OperationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@Transactional
public class OperationChargeAEtalerServiceImpl implements OperationChargeAEtalerService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;

    private final OperationChargeAEtalerDao operationChargeAEtalerDao;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final ExerciceDao exerciceDao;
    private final ExerciceMapper exerciceMapper;
    private final OpcvmDao opcvmDao;
    private final OpcvmMapper opcvmMapper;
    private final LibraryDao libraryDao;
    private final SeanceOpcvmMapper seanceOpcvmMapper;
    private final OperationMapper operationMapper;
    private final OperationService operationService;
    private final AvisOperationBourseMapper avisOperationBourseMapper;
    private final DepotRachatDao depotRachatDao;
    private final DepotRachatMapper depotRachatMapper;
    private final DatDao datDao;
    private final DatMapper datMapper;
    private final OperationChargeAEtalerMapper operationChargeAEtalerMapper;

    public OperationChargeAEtalerServiceImpl(FiltersSpecification<OperationChargeAEtaler> ChargeAEtalerFiltersSpecification, OperationChargeAEtalerDao operationChargeAEtalerDao, SeanceOpcvmDao seanceOpcvmDao, ExerciceDao exerciceDao, ExerciceMapper exerciceMapper, OpcvmDao opcvmDao, LibraryDao libraryDao, OperationExtourneVDEDao operationExtourneVDEDao, OperationExtourneVDEMapper operationExtourneVDEMapper, OperationMapper operationMapper, AvisOperationBourseDao avisOperationBourseDao, OpcvmMapper opcvmMapper, AvisOperationBourseMapper avisOperationBourseMapper, DepotRachatDao depotRachatDao, DepotRachatMapper depotRachatMapper, NatureOperationDao natureOperationDao, OperationDao operationDao, SeanceOpcvmMapper seanceOpcvmMapper, OperationService operationService, DatDao datDao, DatMapper datMapper, OperationChargeAEtalerMapper operationChargeAEtalerMapper){

        this.operationChargeAEtalerDao = operationChargeAEtalerDao;
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.exerciceDao = exerciceDao;
        this.exerciceMapper = exerciceMapper;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.operationMapper = operationMapper;
        this.opcvmMapper = opcvmMapper;
        this.seanceOpcvmMapper = seanceOpcvmMapper;
        this.operationService = operationService;
        this.avisOperationBourseMapper = avisOperationBourseMapper;
        this.depotRachatDao = depotRachatDao;
        this.depotRachatMapper = depotRachatMapper;
        this.datDao = datDao;
        this.datMapper = datMapper;

        this.operationChargeAEtalerMapper = operationChargeAEtalerMapper;
    }


//    @Override
//    public ResponseEntity<Object> genrerChargeAEtaler(Long idOpcvm, Boolean estEnCloture) {
//            try {
//                String message="";
//                Boolean estFinie=false;
//                ExerciceDto exerciceDto=exerciceMapper.deExercice(exerciceDao.exerciceCourant(idOpcvm));
//                if(exerciceDto==null){
//                    message="Veuillez définir l'exercice courant";
//                }
//                else
//                {
//                   List<VerificationMiseEnAffectationEnAttenteProjection> list=libraryDao.verificationMiseEnAffectationEnAttente(
//                           idOpcvm
//                   ) ;
//                   if(list.size()!=0){
//                       message="Veuillez éffectuer l'enrégistrement de la décision du conseil avant de continuer.";
//                   }
//                   else {
//                       SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
//                       if(seanceOpcvm.getEstEnCloture()==false){
//                          //controler si les extournes des VDE de la séance passée ont été faites
//                           List<OperationChargeAEtalerDto> lstOperationChargeAEtaler=operationChargeAEtalerDao.afficherListe(idOpcvm,
//                                   seanceOpcvm.getIdSeanceOpcvm().getIdSeance()-1).stream().map(operationChargeAEtalerMapper::deOperationChargeAEtaler).collect(Collectors.toList());
//
//                            List<OperationExtourneVDEDto> lstOperationExtourneVDE=operationExtourneVDEDao.afficherListe(idOpcvm,seanceOpcvm.getIdSeanceOpcvm().getIdSeance())
//                                    .stream().map(operationExtourneVDEMapper::deOperationExtourneVDE).collect(Collectors.toList());
//
//
//                           if (lstOperationExtourneVDE.size() != lstOperationChargeAEtaler.size())
//                           {
//                               message= "Les différentes d'estimation de la séance précédente n'ont pas encore été extournées.";
//
//                           }
//                           else
//                           {
//                               // controler si les extournes de VDE ont été vérifiées Niveau 1
//                               if (libraryDao.verifOpExtourneN1N2("VERIFEXTOURNE",seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),
//                                       idOpcvm,false, false))
//                               {
//
//                                   message= "Vous avez des extournes de VDE en attente de vérification Niveau 1";
//                               }
//                               // controler si les extournes de VDE ont été vérifiées Niveau 2
//                               else
//                                   if (libraryDao.verifOpExtourneN1N2("VERIFEXTOURNE",seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),
//                                           idOpcvm, true, false)) {
//
//                                       message= "Vous avez des extournes de VDE en attente de vérification Niveau 2";
//                                   }
//                                   // controler si les extournes de VDE ont été vérifiées Niveau 2
//                                   else
//                                       //controler si les écritures des extournes de VDE ont été vérifiées Niveau 1
//                                       if (libraryDao.verifOpExtourneN1N2("ECRITURES",seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),
//                                               idOpcvm, false, false)) {
//
//                                           message = "Vous avez des jeux d'écritures d' extournes de VDE en attente de vérification Niveau 1";
//                                       }
//                                       else
//                                           //controler si les écritures des extournes de VDE ont été vérifiées Niveau 1
//                                           if (libraryDao.verifOpExtourneN1N2("ECRITURES",seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),
//                                                   idOpcvm, true, false)) {
//
//                                               message = "Vous avez des jeux d'écritures d' extournes de VDE en attente de vérification Niveau 2";
//                                           }
//                                           else
//                                                // controler si la mise a jour des cours des titres a été faites pour la séance en cours
//                                               if (libraryDao.coursTitre("BRVM", seanceOpcvm.getDateFermeture()
//                                                       , null, null).size() == 0)
//                                               {
//                                                   message="Veuillez Mettre à jour le cours des titres pour la date de séance en cours.";
//
//                                               }
//                                               else
//                                               if (libraryDao.coursTitre("BRVM", seanceOpcvm.getDateFermeture(),
//                                                       true, null).size() == 0)
//                                               {
//                                                  message="Veuillez effectuer la vérification Niveau 1 de la liste des cours des titres.";
//
//                                               }
//                                               else
//                                               if (libraryDao.coursTitre("BRVM", seanceOpcvm.getDateFermeture(),
//                                                       true, true).size() == 0)
//                                               {
//                                                   message="Veuillez effectuer la vérification Niveau 2 de la liste des cours des titres.";
//                                               }
//                                               else {
//                                                   // Vérification des règlements livraison
//                                                   List<AvisOperationBourseDto> avisOperationBourseDtos=avisOperationBourseDao.verifierReglementAttente(
//                                                           idOpcvm,seanceOpcvm.getDateFermeture()
//                                                   ).stream().map(avisOperationBourseMapper::deAvisOperationBourse).collect(Collectors.toList());
//                                                   if (avisOperationBourseDtos.size() != 0) {
//                                                       message="Veuillez effectuer les règlements livraison en attente avant la cloture";
//                                                   }
//                                                   else
//                                                   {
//                                                       // Vérification des écritures
//                                                       if (libraryDao.listeVerificationEcriture(seanceOpcvm.getIdSeanceOpcvm().getIdOpcvm(),
//                                                               null, seanceOpcvm.getDateOuverture()
//                                                               , seanceOpcvm.getDateFermeture(), false, false,null).size() != 0)
//                                                       {
//                                                          message="vérification Niveau 1 des écritures en attente";
//
//                                                       }
//                                                       else
//                                                       if (libraryDao.listeVerificationEcriture(seanceOpcvm.getIdSeanceOpcvm().getIdOpcvm(),
//                                                               null, seanceOpcvm.getDateOuverture()
//                                                               , seanceOpcvm.getDateFermeture(), true, false,null).size() != 0)
//                                                       {
//                                                           message="vérification Niveau 2 des écritures en attente";
//                                                       }
//                                                       else
//                                                       {
//                                                           // Souscription
//
//                                                           List<DepotRachatDto> depotRachatDtos = depotRachatDao.tousLesDepotsSouscription(
//                                                                   idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), null, null
//                                                                           , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
//
//                                                           if (depotRachatDtos.size() != 0)
//                                                           {
//                                                               if (depotRachatDao.tousLesDepotsSouscription(
//                                                                       idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), false, null
//                                                                       , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
//                                                               {
//                                                                   message="vérification des dépots pour souscription en attente";
//                                                               }
//                                                               else
//                                                               if (
//                                                                       depotRachatDao.tousLesDepotsSouscription(
//                                                                               idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), true, false
//                                                                               , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
//                                                               {
//                                                                 message="vérification des dépots pour souscription Niveau 1 en attente";
//                                                               }
//                                                               else
//                                                               if (
//                                                                       depotRachatDao.tousLesDepotsSouscription(
//                                                                               idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), true, true
//                                                                               , false).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
//                                                               {
//                                                                   message="vérification des dépots pour souscription Niveau 2 en attente";
//                                                               }
//                                                                else
//                                                               if (
//                                                                       libraryDao.controlGenerationSousRach(idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), "SOUSCRIPTION") == false)
//                                                               {
//                                                                  message="Les souscriptions n'ont pas encore été générées";
//                                                               }
//                                                               else
//                                                               {
//                                                                   var q=em.createStoredProcedureQuery("[Operation].[PS_PrecalculRestitutionReliquat_new]");
//                                                                   q.registerStoredProcedureParameter("idOpcvm",Long.class,ParameterMode.IN);
//                                                                   q.registerStoredProcedureParameter("idSeance",Long.class,ParameterMode.IN);
//
//                                                                   q.setParameter("idOpcvm",idOpcvm);
//                                                                   q.setParameter("idSeance",seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
//                                                                   List<Object[]> objects=q.getResultList();
//
//                                                                   var q2=em.createStoredProcedureQuery("[Operation].[PS_OperationRestitutionReliquat_SP_New2]");
//                                                                   q2.registerStoredProcedureParameter("idSeance",Long.class,ParameterMode.IN);
//                                                                   q2.registerStoredProcedureParameter("idOpcvm",Long.class,ParameterMode.IN);
//                                                                   q2.registerStoredProcedureParameter("codeNatureOperation",String.class,ParameterMode.IN);
//                                                                   q2.registerStoredProcedureParameter("supprimer",Boolean.class,ParameterMode.IN);
//
//                                                                   q2.setParameter("idSeance",seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
//                                                                   q2.setParameter("idOpcvm",idOpcvm);
//                                                                   q2.setParameter("codeNatureOperation","REST_RELIQUATS");
//                                                                   q2.setParameter("supprimer",false);
//
//                                                                   List<Object[]> objects2=q.getResultList();
//                                                                   if (objects.size() != 0
//                                                                           && objects2.size() == 0)
//                                                                   {
//                                                                       message= "Vous avez des restitutions de reliquats en attente";
//                                                                   }
//                                                                   else
//                                                                   {
//                                                                       // Rachat
//                                                                       List<DepotRachatDto> rachats = depotRachatDao.tousLesRachats(
//                                                                               idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), null, null
//                                                                               , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
//
//                                                                       if (depotRachatDtos.size() != 0)
//                                                                       {
//                                                                           if (depotRachatDao.tousLesRachats(
//                                                                                   idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), false, null
//                                                                                   , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
//                                                                           {
//                                                                               message="vérification des intentions de rachat en attente";
//                                                                           }
//                                                                           else
//                                                                           if (
//                                                                                   depotRachatDao.tousLesRachats(
//                                                                                           idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), true, false
//                                                                                           , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
//                                                                           {
//                                                                               message="vérification des intentions de rachat Niveau 1 en attente";
//                                                                           }
//                                                                           else
//                                                                           if (
//                                                                                   depotRachatDao.tousLesRachats(
//                                                                                           idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), true, true
//                                                                                           , false).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
//                                                                           {
//                                                                               message="vérification des intentions de rachat Niveau 2 en attente";
//                                                                           }
//                                                                           else
//                                                                           if (
//                                                                                   libraryDao.controlGenerationSousRach(idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), "RACHAT") == false)
//                                                                           {
//                                                                               message="Les rachats n'ont pas encore été générées";
//                                                                           }
//                                                                           else
//                                                                           {
//                                                                               q=em.createStoredProcedureQuery("[Operation].[PS_OperationPaiementRachat_SP_New]");
//                                                                               q.registerStoredProcedureParameter("idSeance",Long.class,ParameterMode.IN);
//                                                                               q.registerStoredProcedureParameter("idOpcvm",Long.class,ParameterMode.IN);
//                                                                               q.registerStoredProcedureParameter("codeNatureOperation",String.class,ParameterMode.IN);
//                                                                               q.registerStoredProcedureParameter("supprimer",Boolean.class,ParameterMode.IN);
//
//                                                                               q.setParameter("idSeance",seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
//                                                                               q.setParameter("idOpcvm",idOpcvm);
//                                                                               q.setParameter("codeNatureOperation","PAI_RACH");
//                                                                               q.setParameter("supprimer",false);
//                                                                               List<Object[]> objects3=q.getResultList();
//
//                                                                               if (objects3.size() == 0)
//                                                                               {
//                                                                                   message= "Vous n'avez pas encore éffectués les paiments de rachat en attente";
//                                                                               }
//                                                                               else
//                                                                               {
//                                                                                   //controler si il y a des ESV en attente de détachement
//                                                                                   List<Object[]> objects1=new ArrayList<>();
//                                                                                   try
//                                                                                   {
//                                                                                       objects1= libraryDao.previsionnelRemboursement(idOpcvm, true, false, false, LocalDateTime.parse("1900/01/01"), seanceOpcvm.getDateFermeture());
//
//                                                                                   }
//                                                                                   catch (Exception ex)
//                                                                                   {
//
//                                                                                       message= ex.toString() ;
//                                                                                   }
//
//                                                                                   if (objects1.size() > 0)
//                                                                                   {
//                                                                                       message= "Vous avez des titres à échéance échus. Veuillez procéder à un détachement";
//                                                                                   }
//                                                                                   else
//                                                                                   {
//                                                                                       q=em.createStoredProcedureQuery("[Dividende].[PS_DecisionDistribution_SP_New]");
//                                                                                       q.registerStoredProcedureParameter("idOpcvm", Long.class,ParameterMode.IN);
//                                                                                       q.registerStoredProcedureParameter("dateDetachement", LocalDateTime.class,ParameterMode.IN);
//                                                                                       q.registerStoredProcedureParameter("supprimer", Boolean.class,ParameterMode.IN);
//
//                                                                                       q.setParameter("idOpcvm",idOpcvm);
//                                                                                       q.setParameter("dateDetachement",seanceOpcvm.getDateFermeture());
//                                                                                       q.setParameter("supprimer",false);
//
//                                                                                       List<Object[]> lstDecisionDistribution=q.getResultList();
//
//                                                                                       if (lstDecisionDistribution.size() != 0)
//                                                                                       {
//                                                                                           message="Vous avez " + lstDecisionDistribution.size() + " détachement(s) en attente.";
//
//                                                                                       }
//                                                                                       else
//                                                                                       {
//                                                                                           message="Lors de la cloture de séance,aucune autre opération ne pourra etre éffectuée.";
//
//                                                                                               /*Marquer le champ estEnCloture de la table TJ_SeanceOpcvm à true*/
//                                                                                           if(estEnCloture){
//                                                                                                seanceOpcvmDao.modifier(idOpcvm,seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),true);
//                                                                                                estFinie=true;
//                                                                                           }
//                                                                                       }
//                                                                                   }
//
//                                                                               }
//                                                                           }
//
//                                                                       }
//                                                                   }
//                                                               }
//
//                                                           }
//
//                                                       }
//                                                   }
//                                               }
//
//                           }
//                       }
//                   }
//                }
//                if(!estFinie)
//                    return ResponseHandler.generateResponse(
//                            "PortefeuilleOPCVM  ",
//                            HttpStatus.OK,
//                            message);
//                else
//                    return ResponseHandler.generateResponse(
//                            "PortefeuilleOPCVM  ",
//                            HttpStatus.OK,
//                            estFinie);
//            }
//            catch (Exception e)
//            {
//                return ResponseHandler.generateResponse(
//                        e.getMessage(),
//                        HttpStatus.MULTI_STATUS,
//                        e);
//            }
//        }
//private  String calculerExpressionTraitee(String expression)
//{
//    int i = 0;
//    boolean isdecimalPart = false;
//    char car = '\0';
//    BigDecimal nbreEnTempon =new BigDecimal(0);
//    Double ct = Double.parseDouble("1");
//    char opTp = '\0';
//    String expresPriorite = "";
//    int nbParantPrio = 0;
//    BigDecimal retour = new BigDecimal(0);
//    String Chaineretour = "";
//    try
//    {
//        //gestion des valeurs signées
//        if (expression.substring(0, 1).toLowerCase().equals("-"))
//            expression = "0" + expression;
//
//        // interprêtation et ajout du caractère de fin '.'
//        expression = expression.trim() + ".";
//
//
//        for (i = 0; i <= expression.length() - 1; i++)
//        {
//            car = expression.substring(i, i+1).toLowerCase().charAt(0);
//
//            if (isInteger(String.valueOf(car)))
//            {
//                if (isdecimalPart == false)
//                    nbreEnTempon =new BigDecimal(nbreEnTempon.doubleValue() * 10 + Double.parseDouble(String.valueOf(car)));
//                else
//                {
//                    if (ct < Double.parseDouble("100000000000000"))
//                    {
//                        ct *= 10;
//                        nbreEnTempon =new BigDecimal(nbreEnTempon.doubleValue() + Double.parseDouble(String.valueOf(car)) / ct);
//                    }
//                }
//            }
//
//
//            if (car == ',') isdecimalPart = true;
//
//
//            if (car == '/' || car == '*' || car == '+' || car == '-')
//            {
//                isdecimalPart = false;
//                switch (opTp)
//                {
//                    case '/':
//                    {
//                        if (nbreEnTempon !=new BigDecimal(0)) retour =new BigDecimal(retour.doubleValue() / nbreEnTempon.doubleValue());
//                        else
//                        {
//                            Chaineretour = "#Erreur: Division par zéro#";
//                        }
//                        break;
//
//                    }
//                    case '*': retour =new BigDecimal(retour.doubleValue() / nbreEnTempon.doubleValue());break;
//                    case '+', '-':          //gestion des priorités
//                    {
//                        if (car == '/' || car == '*')
//                        {
//                            //déterminination de l'expression à évaluer dans la priorité
//                            expresPriorite = nbreEnTempon.toString().trim() + car;
//                            int j = i;
//                            do
//                            {
//                                j++;
//                                if (expression.substring(j, j+1).toLowerCase().equals("(")) nbParantPrio++;
//                                if (expression.substring(j,j+ 1).toLowerCase().equals(")")) nbParantPrio--;
//                                if (!((expression.substring(j,j+ 1).toLowerCase().equals("+") || expression.substring(j, j+1).toLowerCase().equals("-")) && nbParantPrio == 0))
//                                    expresPriorite = expresPriorite + expression.substring(j, 1);
//                            } while ((j < expression.length() - 2) && !((expression.substring(j, j+1).toLowerCase().equals("+")
//                                    || expression.substring(j, j+1).toLowerCase().equals("-")) && nbParantPrio == 0));
//                            //évalution
//                            if (opTp == '+') retour =new BigDecimal(retour.doubleValue()+
//                                     Double.parseDouble(calculerExpressionTraitee(expresPriorite)));
//                            else retour =new BigDecimal(retour.doubleValue()-
//                                    Double.parseDouble(calculerExpressionTraitee(expresPriorite)));
//                            //actualisation  de la position , de car et de expresPriorite
//                            i = j;
//                            car =expression.substring(j, j+1).charAt(0);
//                            expresPriorite = "";
//                        }
//                        else if (opTp == '+') retour =new BigDecimal(retour.doubleValue()+ nbreEnTempon.doubleValue());
//                        else retour =new BigDecimal(retour.doubleValue()- nbreEnTempon.doubleValue());
//
//                    } break;
//                    default: retour = nbreEnTempon; break;
//                }
//                opTp = car;
//                nbreEnTempon =new BigDecimal(0);
//                isdecimalPart = false;
//                ct =Double.parseDouble("1");
//            }
//
//
//            if (car == '(')
//            {
//
//                //déterminination de l'expression à évaluer dans les paranthèses
//                int nbreParant = 1;
//                int j = i;
//                do
//                {
//                    j++;
//                    if (!(expression.substring(j, j+1).toLowerCase().equals(")") && nbreParant == 1))
//                        expresPriorite = expresPriorite + expression.substring(j, j+1);
//                    if (expression.substring(j, j+1).toLowerCase().equals("(")) nbreParant++;
//                    if (expression.substring(j, j+1).toLowerCase().equals(")")) nbreParant--;
//
//                } while ((j < expression.length() - 2) && nbreParant != 0);
//                //évalution
//                nbreEnTempon = new BigDecimal(calculerExpressionTraitee(expresPriorite));
//                //actualisation  de la position , de car et de expresPriorite
//                i = j;
//                expresPriorite = "";
//            }
//
//
//            if (car == '.')
//            {
//                switch (opTp)
//                {
//                    case '/':
//                    {
//                        if (nbreEnTempon !=new BigDecimal(0)) retour =new BigDecimal(retour.doubleValue() / nbreEnTempon.doubleValue());
//                        else
//                        {
//                            Chaineretour = "#Erreur: Division par zéro#";
//                        }
//                        break;
//
//                    }
//                    case '*': retour =new BigDecimal(retour.doubleValue() * nbreEnTempon.doubleValue()); break;
//                    case '+': retour =new BigDecimal(retour.doubleValue()+ nbreEnTempon.doubleValue()); break;
//                    case '-': retour =new BigDecimal(retour.doubleValue()- nbreEnTempon.doubleValue()); break;
//                    case '\0': retour = nbreEnTempon; break;
//                }
//
//            }
//
//        }
//
//
//    }
//    catch(Exception e)
//    { }
//    Chaineretour = retour.toString();
//    return Chaineretour;
//}
public String calculerExpressionTraitee(String expression) {
    int i = 0;
    boolean isDecimalPart = false;
    char car;
    BigDecimal nbreEnTempon = BigDecimal.ZERO;
    double ct = 1.0;
    char opTp = '\0';
    String expresPriorite = "";
    int nbParantPrio = 0;
    BigDecimal retour = BigDecimal.ZERO;
    String chaineRetour = "";

    try {
        // Gestion des valeurs signées
        if (expression.startsWith("-")) {
            expression = "0" + expression;
        }

        // Ajout du caractère de fin
        expression = expression.trim().replace('.',',') + ".";

        for (i = 0; i < expression.length(); i++) {
            car = Character.toLowerCase(expression.charAt(i));

            // Si c'est un chiffre
            if (Character.isDigit(car)) {
                if (!isDecimalPart) {
                    nbreEnTempon =new BigDecimal((nbreEnTempon.doubleValue()*BigDecimal.valueOf(10).doubleValue())+
                            BigDecimal.valueOf(Character.getNumericValue(car)).doubleValue());
                } else {
                    if (ct < 1e14) {
                        ct *= 10;
                        nbreEnTempon =new BigDecimal(nbreEnTempon.doubleValue()+BigDecimal.valueOf(Character.getNumericValue(car) / ct).doubleValue());
                    }
                }
            }

            // Partie décimale
//            car=Character.toLowerCase(String.valueOf(car).replace('.',',').charAt(0));
            if (car == ',') {
                isDecimalPart = true;
            }

            // Si c'est un opérateur
            if (car == '/' || car == '*' || car == '+' || car == '-') {
                isDecimalPart = false;
                switch (opTp) {
                    case '/':
                        if (nbreEnTempon.compareTo(BigDecimal.ZERO) != 0) {
                            retour = new BigDecimal(retour.doubleValue()/nbreEnTempon.doubleValue());
                        } else {
                            return "#Erreur: Division par zéro#";
                        }
                        break;
                    case '*':
                        retour = new BigDecimal(retour.doubleValue()*nbreEnTempon.doubleValue());
                        break;
                    case '+':
                    case '-':
                        if (car == '/' || car == '*') {
                            // Gestion de priorité
                            expresPriorite = nbreEnTempon.toString() + car;
                            int j = i;
                            nbParantPrio = 0;

                            do {
                                j++;
                                String current = expression.substring(j, j + 1);
                                if (current.equals("(")) nbParantPrio++;
                                if (current.equals(")")) nbParantPrio--;
                                expresPriorite += current;
                            } while ((j < expression.length() - 2) &&
                                    !((expression.charAt(j) == '+' || expression.charAt(j) == '-') && nbParantPrio == 0));

                            String eval = calculerExpressionTraitee(expresPriorite);
                            BigDecimal val = new BigDecimal(eval);
                            retour = (opTp == '+') ?  new BigDecimal(retour.doubleValue()+val.doubleValue()) : new BigDecimal(retour.doubleValue()-val.doubleValue());
                            i = j;
                            car = expression.charAt(j);
                            expresPriorite = "";
                        } else if (opTp == '+') {
                            retour =  new BigDecimal(retour.doubleValue()+nbreEnTempon.doubleValue());
                        } else {
                            retour =  new BigDecimal(retour.doubleValue()-nbreEnTempon.doubleValue());
                        }
                        break;
                    default:
                        retour = nbreEnTempon;
                        break;
                }

                opTp = car;
                nbreEnTempon = BigDecimal.ZERO;
                isDecimalPart = false;
                ct = 1.0;
            }

            // Gestion des parenthèses
            if (car == '(') {
                int nbreParant = 1;
                int j = i;
                expresPriorite = "";

                do {
                    j++;
                    String current = expression.substring(j, j + 1);
                    if (!(current.equals(")") && nbreParant == 1)) {
                        expresPriorite += current;
                    }
                    if (current.equals("(")) nbreParant++;
                    if (current.equals(")")) nbreParant--;
                } while (j < expression.length() - 2 && nbreParant != 0);

                nbreEnTempon = new BigDecimal(calculerExpressionTraitee(expresPriorite));
                i = j;
                expresPriorite = "";
            }

            // Fin d'expression
            if (car == '.') {
                switch (opTp) {
                    case '/':
                        if (nbreEnTempon.compareTo(BigDecimal.ZERO) != 0) {
                            retour =  new BigDecimal(retour.doubleValue()/nbreEnTempon.doubleValue());
                        } else {
                            return "#Erreur: Division par zéro#";
                        }
                        break;
                    case '*':
                        retour =  new BigDecimal(retour.doubleValue()*nbreEnTempon.doubleValue());
                        break;
                    case '+':
                        retour =  new BigDecimal(retour.doubleValue()+nbreEnTempon.doubleValue());
                        break;
                    case '-':
                        retour = new BigDecimal(retour.doubleValue()-nbreEnTempon.doubleValue());
                        break;
                    case '\0':
                        retour = nbreEnTempon;
                        break;
                }
            }
        }

    } catch (Exception e) {
        return "#Erreur: Exception Interne#";
    }

    chaineRetour = retour.stripTrailingZeros().toPlainString();
    return chaineRetour;
}

    public  String calculer(String expression, String codePlan, LocalDateTime dateEvalution,Long idOpcvm)
{
    String test = interpreter(expression, codePlan, dateEvalution,idOpcvm);
    try
    {
        if (!test.contains("#Erreur#"))
        {
            test = calculerExpressionTraitee(test.trim());
            if (test.contains("Erreur"))
            {
                test = "0";
            }
            test = new BigDecimal(test).setScale(4).toString();
        }
    }
    catch (Exception e ){ }

    return test;

}
    public boolean isInteger(String str) {
        if (str == null) return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public String interpreter(String expressionO, String codePlan, LocalDateTime dateEvaluation, Long idOpcvm) {
        String retour = "", codeVal = "", numCpte = "", carsAutorises = "0123456789,-+*/()";
        char car;
        int i, j, nbparant = 0;
        String codePoste = "", formuleCP = "", expression = "";

        try {
            // === Traitement des codes postes ===
            for (i = 0; i < expressionO.length(); i++) {
                car = Character.toLowerCase(expressionO.charAt(i));
                if (car == '_') {
                    j = i;
                    do {
                        codePoste += expressionO.substring(j, j + 1).trim();
                        j++;
                    } while (j < expressionO.length() && !"-+*/)".contains(expressionO.substring(j, j + 1)));

                    formuleCP = libraryDao.formuleDunCodePoste(codePoste);
                    codePoste = "";

                    String tempon = calculer(formuleCP, codePlan, dateEvaluation, idOpcvm);

                    if (!expression.isEmpty() && tempon.startsWith("-")) {
                        if (expression.endsWith("-")) {
                            expression = expression.substring(0, expression.length() - 1) + "+" + tempon.substring(1);
                        } else {
                            expression = expression.substring(0, expression.length() - 1) + tempon;
                        }
                    } else {
                        expression += tempon;
                    }
                    formuleCP = "";
                    i = j - 1;
                } else {
                    expression += String.valueOf(car).trim();
                }
            }

            // === Analyse de l'expression ===
            if (expression.isEmpty() || !"-+0123456789(ms".contains(expression.substring(0, 1).toLowerCase())) {
                return "#Erreur#0";
            }

            for (i = 0; i < expression.length(); i++) {
                car = Character.toLowerCase(expression.charAt(i));

                if ("0123456789,.-+*/()mcds".contains(String.valueOf(car))) {
                    if (Character.isDigit(car)) {
                        if (i + 1 < expression.length() && "(mcds".contains(String.valueOf(expression.charAt(i + 1)).toLowerCase())) {
                            return "#Erreur#" + i;
                        }
                    }

                    if (car == ',' || car=='.') {
                        if (i + 1 >= expression.length() || !Character.isDigit(expression.charAt(i + 1))) {
                            return "#Erreur#" + i;
                        }
                    }

                    if ("-+*/".contains(String.valueOf(car))) {
                        if (i + 1 >= expression.length() || !"0123456789ms(".contains(String.valueOf(expression.charAt(i + 1)).toLowerCase())) {
                            return "#Erreur#" + i;
                        }
                    }

                    if (car == 'm') {
                        if (i + 1 >= expression.length() || !"cd".contains(String.valueOf(expression.charAt(i + 1)).toLowerCase())) {
                            return "#Erreur#" + i;
                        }
                    }

                    if ("cds".contains(String.valueOf(car))) {
                        if (i + 1 >= expression.length() || !Character.isDigit(expression.charAt(i + 1))) {
                            return "#Erreur#" + i;
                        }
                    }

                    if (car == '(') nbparant++;
                    if (car == ')') nbparant--;
                } else {
                    return "#Erreur:caractère non autorisé#";
                }
            }

            if (nbparant != 0) {
                return "#Erreur:paranthèse(s) manquante(s)#";
            }

            // === Remplacement des opérations sur les comptes ===
            retour = "";
            i = 0;
            while (i < expression.length()) {
                car = expression.charAt(i);
                if (car == 'm' || car == 's') {
                    j = i;
                    codeVal = String.valueOf(car);
                    numCpte = "";

                    while (++j < expression.length()) {
                        char next = expression.charAt(j);
                        if ("+-/*)".contains(String.valueOf(next))) break;
                        if (codeVal.length() == 1 && car == 'm') {
                            codeVal += next;
                        } else {
                            numCpte += next;
                        }
                    }

//                    SoldeResult solde = soldeCompteService.getSolde(idOpcvm, numCpte.trim(), codePlan, dateEvaluation);
//                    if (solde.getSortie().contains("Erreur")) {
//                        return solde.getSortie();
//                    }
                    LocalDateTime dateTime = dateEvaluation;
                    Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                    List<SoldeCompteFormuleProjection> list = libraryDao.afficherSoldeCompteFormule(idOpcvm, numCpte, codePlan, null, date);
                    String sortie = (list.size() != 0) ? list.get(0).getSortie() : "";

                    String tempon = "";
                    switch (codeVal.toLowerCase()) {
                        case "mc":
                            tempon = String.valueOf(list.get(0).getSoldeCredit());
                            break;
                        case "md":
                            tempon = String.valueOf(list.get(0).getSoldeDebit());
                            break;
                        case "s":
                            tempon = String.valueOf(list.get(0).getSoldeReel());
                            break;
                        default:
                            return "#Erreur#" + i;
                    }

                    if (!retour.isEmpty() && tempon.startsWith("-") && retour.endsWith("-")) {
                        retour = retour.substring(0, retour.length() - 1) + "+" + tempon.substring(1);
                    } else {
                        retour += tempon;
                    }

                    codeVal = "";
                    numCpte = "";
                    i = j;
                } else {
                    retour += car;
                    i++;
                }
            }

        } catch (Exception e) {
            return "#Erreur Exception Interne#";
        }

        return retour.trim();
    }
//    public String interpreter(String expressionO, String codeplan, LocalDateTime dateEvalution, Long idOpcvm) {
//        String retour = "", codeVal = "", numCpte = "", expression = "";
//        String carsAutorises = "0123456789,-+*/()";
//        int i = 0, j = 0, nbparant = 0;
//        char car;
//        String codeposte = "", formuleCP = "";
//
//        try {
//            // 1. Traitement des codes postes
//            for (i = 0; i < expressionO.length(); i++) {
//                car = Character.toLowerCase(expressionO.charAt(i));
//
//                if (car == '_') {
//                    j = i;
//                    codeposte = "";
//                    while (j < expressionO.length() && !"-+*/)".contains(expressionO.substring(j, j + 1).trim())) {
//                        codeposte += expressionO.substring(j, j + 1).trim();
//                        j++;
//                    }
//
//                    formuleCP = libraryDao.formuleDunCodePoste(codeposte);
//                    codeposte = "";
//                    String tempon = calculer(formuleCP, codeplan, dateEvalution, idOpcvm);
//
//                    if (!expression.isEmpty() && tempon.startsWith("-")) {
//                        if (expression.endsWith("-")) {
//                            expression = expression.substring(0, expression.length() - 1) + "+" + tempon.substring(1);
//                        } else {
//                            expression = expression.substring(0, expression.length() - 1) + tempon;
//                        }
//                    } else {
//                        expression += tempon;
//                    }
//
//                    formuleCP = "";
//                    i = j - 1;
//                } else {
//                    expression += car;
//                }
//            }
//
//            // 2. Analyse de l'expression
//            if (expression.isEmpty() || !"-+0123456789(ms".contains(expression.substring(0, 1).toLowerCase())) {
//                return "#Erreur#0";
//            }
//
//            for (i = 0; i < expression.length(); i++) {
//                char current = Character.toLowerCase(expression.charAt(i));
//
//                if ("0123456789,-+*/()mcds".contains(String.valueOf(current))) {
//                    String next = (i + 1 < expression.length()) ? expression.substring(i + 1, i + 2).toLowerCase().trim() : "";
//
//                    if (Character.isDigit(current) && "(mcds".contains(next)) return "#Erreur#" + i;
//                    if (current == ',' && !next.matches("\\d")) return "#Erreur#" + i;
//                    if ("-+*/".contains(String.valueOf(current)) && !"0123456789ms(".contains(next)) return "#Erreur#" + i;
//                    if (current == 'm' && !"cd".contains(next)) return "#Erreur#" + i;
//                    if ("cds".contains(String.valueOf(current)) && !next.matches("\\d")) return "#Erreur#" + i;
//                    if (current == '(') {
//                        if (!"0123456789(ms".contains(next)) return "#Erreur#" + i;
//                        nbparant++;
//                    }
//                    if (current == ')') {
//                        if (!"-+*/)".contains(next)) return "#Erreur#" + i;
//                        nbparant--;
//                    }
//                } else {
//                    return "#Erreur:caractère non autorisé#";
//                }
//            }
//
//            if (nbparant != 0) return "#Erreur:paranthèse(s) manquante(s)#";
//
//            // 3. Remplacement des opérations sur comptes
//            car = '\0';
//            retour = "";
//
//            for (i = 0; i < expression.length(); i++) {
//                car = Character.toLowerCase(expression.charAt(i));
//                if (car == 'm' || car == 's') {
//                    j = i;
//                    codeVal = String.valueOf(car);
//                    numCpte = "";
//
//                    do {
//                        j++;
//                        if (j >= expression.length()) break;
//                        String c = expression.substring(j, j + 1).trim().toLowerCase();
//                        if (codeVal.length() == 1 && car == 'm') codeVal += c;
//                        else if (!"+-/*)".contains(c)) numCpte += c;
//                    } while (j < expression.length() && !"+-/*)".contains(expression.substring(j, j + 1)));
//
//                    LocalDateTime dateTime = dateEvalution;
//                    Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
//                    List<SoldeCompteFormuleProjection> list = libraryDao.afficherSoldeCompteFormule(idOpcvm, numCpte, codeplan, null, date);
//                    String sortie = (list.size() != 0) ? list.get(0).getSortie() : "";
//
//                    if (sortie.contains("Erreur")) {
//                        return sortie;
//                    }
//
//                    String tempon = "";
//                    switch (codeVal.toLowerCase()) {
//                        case "mc": tempon = list.get(0).getSoldeCredit().toString().trim(); break;
//                        case "md": tempon = list.get(0).getSoldeDebit().toString().trim(); break;
//                        case "s": tempon = list.get(0).getSoldeReel().toString().trim(); break;
//                        default: return "#Erreur#" + i;
//                    }
//
//                    if (!retour.isEmpty()) {
//                        if (tempon.startsWith("-") && retour.endsWith("-"))
//                            retour = retour.substring(0, retour.length() - 1) + "+" + tempon.substring(1);
//                        else retour += tempon;
//                    } else {
//                        if (tempon.startsWith("-")) retour += "0" + tempon;
//                        else retour += tempon;
//                    }
//
//                    i = j - 1;
//                    codeVal = "";
//                    numCpte = "";
//                } else {
//                    retour += car;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "#Erreur#Exception";
//        }
//
//        return retour.trim();
//    }

    @Override
    public ResponseEntity<Object> precalculChargeAEtaler(DifferenceEstimationRequest ChargeAEtalerRequest) {
        try {
            String etape=operationService.verifierEtape(ChargeAEtalerRequest.getNiveau(),ChargeAEtalerRequest.getIdOpcvm());
            if(etape.equals(""))
            {
                SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeance(ChargeAEtalerRequest.getIdOpcvm(),ChargeAEtalerRequest.getIdSeance());
                if (seanceOpcvm != null )
                {
                    //calcul de l'actif brut
                    ExerciceDto exerciceDto=exerciceMapper.deExercice(exerciceDao.exerciceCourant(ChargeAEtalerRequest.getIdOpcvm()));
                    BigDecimal actifBrut =new BigDecimal(0);
                    String valeurCodePoste = calculer(
                            "_CAP+_SDEC+_REC", exerciceDto.getPlan().getCodePlan().trim(), ChargeAEtalerRequest.getDateOperation(),ChargeAEtalerRequest.getIdOpcvm()).toString();
                    if (valeurCodePoste.contains("#Erreur"))
                    {
                        if (valeurCodePoste.contains("Division par zéro#") || valeurCodePoste.contains("#0"))
                        {

                        }
                        else
                        {

                        }
                    }
                    else
                    {
                        actifBrut = new BigDecimal(valeurCodePoste);
                    }
                    Duration duration = Duration.between(exerciceDto.getDateDebut(), exerciceDto.getDateFin());
                    Long usance = (duration).toDays();
                    //(Convert.ToDateTime("31/12/" + DateTime.Now.Year) -
                    //Convert.ToDateTime("01/01/" + DateTime.Now.Year)).Days;
                    duration = Duration.between(seanceOpcvm.getDateOuverture(), seanceOpcvm.getDateFermeture());
                    Long nbreJour = (duration).toDays();
                    if (seanceOpcvm.getDateOuverture().toLocalDate().isBefore(exerciceDto.getDateDebut().toLocalDate()))
                    {
                        duration = Duration.between(exerciceDto.getDateDebut(), seanceOpcvm.getDateFermeture());
                        nbreJour = (duration).toDays()+1;
                    }
                    LocalDateTime mDate = ChargeAEtalerRequest.getDateOperation();
                    if ((mDate.toLocalDate().equals(LocalDate.parse( mDate.getYear() +"-12-29"))||
                            mDate.toLocalDate().equals(LocalDate.parse( mDate.getYear() +"-12-30")) &
                                    mDate.getDayOfWeek() == DayOfWeek.FRIDAY))
                    {
                        mDate = LocalDateTime.parse(mDate.getYear()+"-12-31T00:00:00");
                        duration = Duration.between(seanceOpcvm.getDateOuverture(), mDate);
                        nbreJour = (duration).toDays();
                    }

//                Generer(actifBrut, nbreJour, usance);
//                if(seanceOpcvm.getEstEnCours()) {
//                    LocalDateTime mDate = ChargeAEtalerRequest.getDateSeance();
//                    if (((mDate.getMonthValue() == 12 && mDate.getDayOfMonth()==29) ||
//                            (mDate.getMonthValue() == 12 && mDate.getDayOfMonth()==30) ) &
//                            mDate.getDayOfWeek()== DayOfWeek.FRIDAY)
//                    {
//                        mDate = LocalDateTime.parse( mDate.getYear()+"/12/31T:00:00:00");
//                    }

                    DatatableParameters parameters=ChargeAEtalerRequest.getDatatableParameters();
                    Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
                    Pageable pageable = PageRequest.of(
                            parameters.getStart()/ parameters.getLength(), parameters.getLength());
                    Page<FT_GenererChargeProjection> operationChargeAEtalerPage;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                operationChargeAEtalerPage = operationChargeAEtalerDao.findByOpcvmAndSupprimer(opcvm,false,pageable);
//            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();

                    operationChargeAEtalerPage = libraryDao.genererCharge(
                            ChargeAEtalerRequest.getIdOpcvm()
                            ,ChargeAEtalerRequest.getIdSeance(),actifBrut
                            ,nbreJour,usance,pageable);
//            }

//            List<OperationChargeAEtalerDto> content = operationChargeAEtalerPage.getContent().stream().map(operationChargeAEtalerMapper::deOperationChargeAEtalerProjection).collect(Collectors.toList());
                    List<FT_GenererChargeProjection> content = operationChargeAEtalerPage.getContent().stream().collect(Collectors.toList());
                    DataTablesResponse<FT_GenererChargeProjection> dataTablesResponse = new DataTablesResponse<>();
                    dataTablesResponse.setDraw(parameters.getDraw());
                    dataTablesResponse.setRecordsFiltered((int)operationChargeAEtalerPage.getTotalElements());
                    dataTablesResponse.setRecordsTotal((int)operationChargeAEtalerPage.getTotalElements());
                    dataTablesResponse.setData(content);
                    return ResponseHandler.generateResponse(
                            "Liste des VDE par page datatable",
                            HttpStatus.OK,
                            dataTablesResponse);
                }
                else
                {
                    return ResponseHandler.generateResponse(
                            "Enregistrement effectué avec succès !",
                            HttpStatus.OK,
                            "Veuillez sélectionner la séance encours.1");
                }
            }
            else
            {
                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        etape+".1");
            }


        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> chargerOperation(DifferenceEstimationRequest ChargeAEtalerRequest) {

            try {

                DatatableParameters parameters=ChargeAEtalerRequest.getDatatableParameters();
                Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
                Pageable pageable = PageRequest.of(
                        parameters.getStart()/ parameters.getLength(), parameters.getLength());
                Page<FT_GenererChargeProjection> operationChargeAEtalerPage;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                operationChargeAEtalerPage = operationChargeAEtalerDao.findByOpcvmAndSupprimer(opcvm,false,pageable);
//            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();

                operationChargeAEtalerPage = libraryDao.afficherChargeAEtaler(
                        ChargeAEtalerRequest.getIdSeance()
                        ,ChargeAEtalerRequest.getIdOpcvm(),false
                        ,null,null,pageable);
//            }

//            List<OperationChargeAEtalerDto> content = operationChargeAEtalerPage.getContent().stream().map(operationChargeAEtalerMapper::deOperationChargeAEtalerProjection).collect(Collectors.toList());
                List<FT_GenererChargeProjection> content = operationChargeAEtalerPage.getContent().stream().collect(Collectors.toList());
                DataTablesResponse<FT_GenererChargeProjection> dataTablesResponse = new DataTablesResponse<>();
                dataTablesResponse.setDraw(parameters.getDraw());
                dataTablesResponse.setRecordsFiltered((int)operationChargeAEtalerPage.getTotalElements());
                dataTablesResponse.setRecordsTotal((int)operationChargeAEtalerPage.getTotalElements());
                dataTablesResponse.setData(content);
//
                return ResponseHandler.generateResponse(
                        "Liste des charges a etaler par page datatable",
                        HttpStatus.OK,
                        dataTablesResponse);


            } catch (Exception e) {
                return ResponseHandler.generateResponse(
                        e.getMessage(),
                        HttpStatus.MULTI_STATUS,
                        e);
            }

    }
    @Transactional
    public void modifier(Long idOpcvm,Long idSeance){
        seanceOpcvmDao.modifier(idOpcvm, idSeance, 6L);
    }
    @Override
    @Transactional
    public ResponseEntity<Object> creer(List<OperationChargeAEtalerDto> list) {
        try {
            String sortie="";
            Long idOpcvm=0L;
            Long idSeance=0L;
            for(OperationChargeAEtalerDto operationChargeAEtalerDto:list){
                var q=em.createStoredProcedureQuery("[Operation].[PS_OperationChargeAEtaler_IP]");
                q.registerStoredProcedureParameter("idOperation",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idTransaction",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idSeance",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idActionnaire",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeNatureOperation",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateOperation",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("libelleOperation",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateSaisie",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("datePiece",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateValeur",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("referencePiece",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("montant",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurFormule",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurCodeAnalytique",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("actifBrut",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeCharge",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("nbreJour",int.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("usance",int.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeModele",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie",String.class, ParameterMode.OUT);

                q.setParameter("idOperation",0);
                q.setParameter("idTransaction",0);
                q.setParameter("idSeance",operationChargeAEtalerDto.getIdSeance());
                q.setParameter("idActionnaire",0);
                q.setParameter("idOpcvm",operationChargeAEtalerDto.getOpcvm().getIdOpcvm());
                q.setParameter("codeNatureOperation",operationChargeAEtalerDto.getCodeModele());
                q.setParameter("dateOperation",operationChargeAEtalerDto.getDateOperation());
                q.setParameter("libelleOperation","AMORTISSEMENT " + operationChargeAEtalerDto.getDesignation().trim());
                q.setParameter("dateSaisie",LocalDateTime.now());
                q.setParameter("datePiece",operationChargeAEtalerDto.getDateOperation());
                q.setParameter("dateValeur",operationChargeAEtalerDto.getDateOperation());
                q.setParameter("referencePiece","");
                q.setParameter("montant",operationChargeAEtalerDto.getMontant());
                q.setParameter("valeurFormule",("2:" + Math.abs(operationChargeAEtalerDto.getMontant().doubleValue())).
                        toString().replace(',', '.'));
                q.setParameter("valeurCodeAnalytique","OPC:" + operationChargeAEtalerDto.getOpcvm().getIdOpcvm().toString() +
                        ";ACT:0");
                q.setParameter("actifBrut",operationChargeAEtalerDto.getActifBrut());
                q.setParameter("codeCharge",operationChargeAEtalerDto.getCodeCharge());
                q.setParameter("nbreJour",operationChargeAEtalerDto.getNbreJour());
                q.setParameter("usance",operationChargeAEtalerDto.getUsance());
                q.setParameter("codeModele",operationChargeAEtalerDto.getCodeModele());
                q.setParameter("userLogin",operationChargeAEtalerDto.getUserLogin());
                q.setParameter("dateDernModifClient",LocalDateTime.now());
                q.setParameter("CodeLangue","fr-FR");
                q.setParameter("Sortie",sortie);

                idOpcvm=operationChargeAEtalerDto.getOpcvm().getIdOpcvm();
                idSeance=operationChargeAEtalerDto.getIdSeance();
                q.execute();
            }
            seanceOpcvmDao.modifier(idOpcvm, idSeance, 6L);
//            modifier(idOpcvm, idSeance);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès",
                    HttpStatus.OK,
                    null);


        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    @Override
    @Transactional
    public ResponseEntity<Object> validationNiveau(DifferenceEstimationRequest differenceEstimationRequest) {
        try {
            if(differenceEstimationRequest.getNiveau()==1){
                List<FT_GenererChargeProjection> list=libraryDao.afficherChargeAEtaler(
                        differenceEstimationRequest.getIdSeance(),differenceEstimationRequest.getIdOpcvm(),false,false,false);

                for(FT_GenererChargeProjection o:list) {
                    var q=em.createStoredProcedureQuery("[Operation].[PS_OperationChargeAEtaler_UP_New]");
                    q.registerStoredProcedureParameter("idOperation",Long.class,ParameterMode.IN);
                    q.registerStoredProcedureParameter("estVerifie1",Boolean.class,ParameterMode.IN);
                    q.registerStoredProcedureParameter("userLoginVerificateur1",String.class,ParameterMode.IN);
                    q.registerStoredProcedureParameter("Niveau",Long.class,ParameterMode.IN);

                    q.setParameter("idOperation",o.getIdOperation());
                    q.setParameter("estVerifie1",true);
                    q.setParameter("userLoginVerificateur1",differenceEstimationRequest.getUserLogin1());
                    q.setParameter("Niveau",differenceEstimationRequest.getNiveau());
                    q.executeUpdate();
//                    operationChargeAEtalerDao.modifier1(o.getIdOperation(),
//                             differenceEstimationRequest.getUserLogin1(), LocalDateTime.now()
//                            , true);
                }
                seanceOpcvmDao.modifier(differenceEstimationRequest.getIdOpcvm(), differenceEstimationRequest.getIdSeance(), 7L);

                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        "Vérification effectuée avec succès");
            }
            else
            {
                List<FT_GenererChargeProjection> list=libraryDao.afficherChargeAEtaler(
                        differenceEstimationRequest.getIdSeance(),differenceEstimationRequest.getIdOpcvm(),false,true,false);

                for(FT_GenererChargeProjection o:list) {
                    var q=em.createStoredProcedureQuery("[Operation].[PS_OperationChargeAEtaler_UP_New]");
                    q.registerStoredProcedureParameter("idOperation",Long.class,ParameterMode.IN);
                    q.registerStoredProcedureParameter("estVerifie1",Boolean.class,ParameterMode.IN);
                    q.registerStoredProcedureParameter("userLoginVerificateur1",String.class,ParameterMode.IN);
                    q.registerStoredProcedureParameter("Niveau",Long.class,ParameterMode.IN);

                    q.setParameter("idOperation",o.getIdOperation());
                    q.setParameter("estVerifie1",true);
                    q.setParameter("userLoginVerificateur1",differenceEstimationRequest.getUserLogin1());
                    q.setParameter("Niveau",differenceEstimationRequest.getNiveau());
                    q.executeUpdate();
                }
                seanceOpcvmDao.modifier(differenceEstimationRequest.getIdOpcvm(), differenceEstimationRequest.getIdSeance(), 8L);

                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        "Vérification effectuée avec succès");
            }



        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    @Override
    public ResponseEntity<Object> verifier(Long idSeance,Long idOpcvm, HttpServletResponse response) throws IOException, JRException {
        List<FT_GenererChargeProjection> list=libraryDao.verifierChargeAEtaler(idSeance, idOpcvm);
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        File file = ResourceUtils.getFile("classpath:chargerAEtaler.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                list);
    }

    @Override
    public ResponseEntity<Object> verifier(Long idSeance, Long idOpcvm, Boolean estVerifie1, Boolean estVerifie2, Long niveau, HttpServletResponse response) throws IOException, JRException {
        List<FT_GenererChargeProjection> list=libraryDao.afficherChargeAEtaler(idSeance, idOpcvm, false,estVerifie1, estVerifie2);

        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        DateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String dateFormatee = date.format(formatter);
        String letterDate = dateFormatter.format(new Date());
        SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeance(idOpcvm,idSeance));
        parameters.put("letterDate", letterDate);
        parameters.put("niveau", niveau.toString());
        parameters.put("dateOuv", seanceOpcvmDto.getDateOuverture().format(formatter).toString());
        parameters.put("dateFerm", seanceOpcvmDto.getDateFermeture().format(formatter).toString());

        OpcvmDto opcvmDto=opcvmMapper.deOpcvm(opcvmDao.findById(idOpcvm).orElseThrow());
        parameters.put("denominationOpcvm", opcvmDto.getDenominationOpcvm());

        File file = ResourceUtils.getFile("classpath:verificationChargerAEtalerN1N2.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Extourne VDE",
                HttpStatus.OK,
                list);
    }

    @Override
    public ResponseEntity<Object> precalculChargeAEtalerListe(DifferenceEstimationRequest ChargeAEtalerRequest) {
        try {
            SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeance(ChargeAEtalerRequest.getIdOpcvm(),ChargeAEtalerRequest.getIdSeance());
            if (seanceOpcvm != null )
            {
                //calcul de l'actif brut
                ExerciceDto exerciceDto=exerciceMapper.deExercice(exerciceDao.exerciceCourant(ChargeAEtalerRequest.getIdOpcvm()));
                BigDecimal actifBrut =new BigDecimal(0);
                String valeurCodePoste = calculer(
                        "_CAP+_SDEC+_REC", exerciceDto.getPlan().getCodePlan(), ChargeAEtalerRequest.getDateOperation(),ChargeAEtalerRequest.getIdOpcvm()).toString();
                if (valeurCodePoste.contains("#Erreur"))
                {
                    if (valeurCodePoste.contains("Division par zéro#") || valeurCodePoste.contains("#0"))
                    {

                    }
                    else
                    {

                    }
                }
                else
                {
                    actifBrut = new BigDecimal(valeurCodePoste);
                }
                Duration duration = Duration.between(exerciceDto.getDateDebut(), exerciceDto.getDateFin());
                Long usance = (duration).toDays();
                //(Convert.ToDateTime("31/12/" + DateTime.Now.Year) -
                //Convert.ToDateTime("01/01/" + DateTime.Now.Year)).Days;
                duration = Duration.between(seanceOpcvm.getDateOuverture(), seanceOpcvm.getDateFermeture());
                Long nbreJour = (duration).toDays();
                if (seanceOpcvm.getDateOuverture().toLocalDate().isBefore(exerciceDto.getDateDebut().toLocalDate()))
                {
                    duration = Duration.between(exerciceDto.getDateDebut(), seanceOpcvm.getDateFermeture());
                    nbreJour = (duration).toDays()+1;
                }
                LocalDateTime mDate = ChargeAEtalerRequest.getDateOperation();
                if ((mDate.toLocalDate().equals(LocalDate.parse( mDate.getYear() +"/12/29"))||
                        mDate.toLocalDate().equals(LocalDate.parse( mDate.getYear() +"/12/30")) &
                                mDate.getDayOfWeek() == DayOfWeek.FRIDAY))
                {
                    mDate = LocalDateTime.parse(mDate.getYear()+"/12/31");
                    duration = Duration.between(seanceOpcvm.getDateOuverture(), mDate);
                    nbreJour = (duration).toDays();
                }

//                Generer(actifBrut, nbreJour, usance);
//                if(seanceOpcvm.getEstEnCours()) {
//                    LocalDateTime mDate = ChargeAEtalerRequest.getDateSeance();
//                    if (((mDate.getMonthValue() == 12 && mDate.getDayOfMonth()==29) ||
//                            (mDate.getMonthValue() == 12 && mDate.getDayOfMonth()==30) ) &
//                            mDate.getDayOfWeek()== DayOfWeek.FRIDAY)
//                    {
//                        mDate = LocalDateTime.parse( mDate.getYear()+"/12/31T:00:00:00");
//                    }

                DatatableParameters parameters=ChargeAEtalerRequest.getDatatableParameters();
                Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
                Pageable pageable = PageRequest.of(
                        parameters.getStart()/ parameters.getLength(), parameters.getLength());
                List<FT_GenererChargeProjection> operationChargeAEtaler;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                operationChargeAEtalerPage = operationChargeAEtalerDao.findByOpcvmAndSupprimer(opcvm,false,pageable);
//            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                operationChargeAEtaler = libraryDao.genererCharge(
                        ChargeAEtalerRequest.getIdOpcvm()
                        ,ChargeAEtalerRequest.getIdSeance(),actifBrut
                        ,nbreJour,usance);
//
                return ResponseHandler.generateResponse(
                        "Liste des VDE par page datatable",
                        HttpStatus.OK,
                        operationChargeAEtaler);
            }
            else
            {
                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        "Veuillez sélectionner la séance encours.");
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

//    @Override
//    public ResponseEntity<Object> validationNiveau(ChargeAEtalerRequest ChargeAEtalerRequest) {
//        try {
//            if(ChargeAEtalerRequest.getNiveau()==1){
//                List<OperationChargeAEtalerProjection> list=libraryDao.operationChargeAEtaler(
//                        ChargeAEtalerRequest.getIdSeance(),ChargeAEtalerRequest.getIdOpcvm(),false,false,false);
//
//                for(OperationChargeAEtalerProjection o:list) {
//                    operationChargeAEtalerDao.modifier1(ChargeAEtalerRequest.getIdOpcvm(), o.getIdTitre(),
//                            ChargeAEtalerRequest.getIdSeance(), ChargeAEtalerRequest.getUserLogin1(), LocalDateTime.now()
//                            , true);
//                }
//                seanceOpcvmDao.modifier(ChargeAEtalerRequest.getIdOpcvm(), ChargeAEtalerRequest.getIdSeance(), 2L);
//
//                return ResponseHandler.generateResponse(
//                        "Enregistrement effectué avec succès !",
//                        HttpStatus.OK,
//                        "Vérification effectuée avec succès");
//            }
//            else
//            {
//                List<OperationChargeAEtalerProjection> list=libraryDao.operationChargeAEtaler(
//                        ChargeAEtalerRequest.getIdSeance(),ChargeAEtalerRequest.getIdOpcvm(),true,false,false);
//
//                for(OperationChargeAEtalerProjection o:list) {
//                    operationChargeAEtalerDao.modifier2(ChargeAEtalerRequest.getIdOpcvm(),o.getIdTitre(),
//                            ChargeAEtalerRequest.getIdSeance(), ChargeAEtalerRequest.getUserLogin2(), LocalDateTime.now()
//                            , true);
//                }
//                seanceOpcvmDao.modifier(ChargeAEtalerRequest.getIdOpcvm(), ChargeAEtalerRequest.getIdSeance(), 3L);
//
//                return ResponseHandler.generateResponse(
//                        "Enregistrement effectué avec succès !",
//                        HttpStatus.OK,
//                        "Vérification effectuée avec succès");
//            }
//
//
//
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Object> creer(OperationChargeAEtalerDto operationChargeAEtalerDto) {
//        try {
//
//            String sortie="";
//            var q = em.createStoredProcedureQuery("[Comptabilite].[PS_ExtournerChargeAEtaler]");
//
//            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("codeLangue", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("sortie", String.class, ParameterMode.OUT);
//
//
////            q.setParameter("idOperation", 0);
//            q.setParameter("idOpcvm", operationChargeAEtalerDto.getOpcvm().getIdOpcvm());
//            q.setParameter("idSeance", operationChargeAEtalerDto.getIdSeance());
//            q.setParameter("dateValeur", operationChargeAEtalerDto.getDateValeur());
//            q.setParameter("dateDernModifClient",LocalDateTime.now());
//            q.setParameter("userLogin", operationChargeAEtalerDto.getUserLogin());
//            q.setParameter("codeLangue", "fr-FR");
//            q.setParameter("sortie",sortie);
//            String[] s=new String[20];
//            try
//            {
//                q.execute();
//                String result=(String) q.getOutputParameterValue("Sortie");
//                s=result.split("#");
//                //System.out.println("idOperation="+s[s.length-1]);
//            }
//            catch(Exception e){
//
//            }
//            return ResponseHandler.generateResponse(
//                    "Enregistrement effectué avec succès !",
//                    HttpStatus.OK,
//                    s[s.length-1]);
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }
//    public String toStr(ChargeAEtalerProjection ChargeAEtalerProjection,
//                        String codeNatureOperation,
//                        LocalDateTime dateOperation){
//        return "symbole="+ChargeAEtalerProjection.getSymbolTitre()+"\n"+
//                "designation="+ChargeAEtalerProjection.getDesignationTitre()+"\n"+
//                "CumpT="+ChargeAEtalerProjection.getCumpT()+"\n"+
//                "CumpReel"+ChargeAEtalerProjection.getCumpReel()+"\n"+
//                "Cours="+ChargeAEtalerProjection.getCours()+"\n"+
//                "IdOpCours="+ChargeAEtalerProjection.getIdOpCours()+"\n"+
//                "IdOpInteret="+ChargeAEtalerProjection.getIdOpInteret()+"\n"+
//                "IdTitre="+ChargeAEtalerProjection.getIdTitre()+"\n"+
//                "ValeurVDEInteret="+ChargeAEtalerProjection.getValeurVDEInteret()+"\n"+
//                "getValeurVDECours="+ChargeAEtalerProjection.getValeurVDECours()+"\n"+
//                "InteretCourus="+ChargeAEtalerProjection.getInteretCourus()+"\n"+
//                "PlusOuMoinsValue="+ChargeAEtalerProjection.getPlusOuMoinsValue()+"\n"+
//                "codena="+codeNatureOperation+"\n"+
//                "dateoper="+dateOperation;
//    }
//    @Override
//    public ResponseEntity<Object> enregistrerChargeAEtaler(ChargeAEtalerRequest  request) {
//        var q=em.createStoredProcedureQuery("[Operation].[PS_OperationChargeAEtaler_IP_New]");
//        try {
//            List<ChargeAEtalerProjection> operationChargeAEtalerDto=libraryDao.precalculChargeAEtaler(
//                    request.getIdSeance(), request.getIdOpcvm(), request.getDateSeance()
//            );
//            String sortie="";
//            Long idOpcvm=0L;
//
//            if (operationChargeAEtalerDto != null &&
//                    operationChargeAEtalerDto.size() != 0)
//            {
//
//                //suppression d'une éventuelle ancienne valorisation
//                //parcours pour enregistrement
//                int i=0;
//                for (ChargeAEtalerProjection obj : operationChargeAEtalerDto)
//                {
//
//                    if(i==0){
//                         q = em.createStoredProcedureQuery("[Operation].[PS_OperationChargeAEtaler_DP_ALL]");
////                         q = em.createStoredProcedureQuery("[Operation].[PS_OperationChargeAEtaler_DP_ALL]");
//                        q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
//                        q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
//
//                        q.setParameter("idSeance", obj.getIdSeance());
//                        q.setParameter("idOpcvm", obj.getIdOpcvm());
//                        q.execute();
//                    }
//                    i++;
//                    Dat dat=datDao.findByIdTitre(obj.getIdTitre());
//                    DatDto objTitre=new DatDto();
//                    if(dat!=null)
//                     objTitre =datMapper.deDat(dat);
//                    else
//                        objTitre=null;
//                    //pour les cours
//                    String valeurCodeAnalytique = "OPC:" + obj.getIdOpcvm().toString() +
//                            ";TIT:" + obj.getIdTitre().toString();
//                    String valeurFormule = "2:" + Math.abs(obj.getValeurVDECours().doubleValue()) ;
//                    valeurFormule=valeurFormule.toString().replace(',', '.');
//                    String codeNatureOp="";
//                    if (obj.getValeurVDECours().doubleValue() < 0)
//                    {
//                        codeNatureOp = "DE_TIT_MV";
//                    }
//                    else
//                    {
//                        codeNatureOp = "DE_TIT_PV";
//                    }
//                    String valeurCodeAnalytiqueInt="";
//                    String codeNatureOperationInt="";
//                    String valeurFormuleInt="";
//                    //pour les interets
//                    if (obj.getValeurVDEInteret().doubleValue() != 0)
//                    {
//                        if (objTitre == null)
//                        {
//                            valeurCodeAnalytiqueInt = "OPC:" + obj.getIdOpcvm().toString() +
//                                    ";TIT:" + obj.getIdTitre().toString();
//                            codeNatureOperationInt = "DE_int_TIT";
//                            valeurFormuleInt =
//                                    "2:" + obj.getValeurVDEInteret().toString().replace(',', '.') +
//                                            ";38:" + obj.getIrvm().toString().replace(',', '.');
//                        }
//                        else
//                        {
//                            valeurCodeAnalytiqueInt = "OPC:" + obj.getIdOpcvm().toString() +
//                                    ";TIT:" + obj.getIdTitre().toString();
//                            codeNatureOperationInt = "DE_INT_DAT";
//                            valeurFormuleInt =
//                                    "2:" + obj.getValeurVDEInteret().toString().replace(',', '.');
//                        }
//                    }
//                    else
//                    {
//                        valeurCodeAnalytiqueInt = "";
//                        codeNatureOperationInt = "";
//                        valeurFormuleInt = "";
//                    }
//                    System.out.println(toStr(obj,codeNatureOp,request.getDateOperation()));
////                    var q = em.createStoredProcedureQuery("[Operation].[PS_OperationChargeAEtaler_DP_ALL]");
//                    q=em.createStoredProcedureQuery("[Operation].[PS_OperationChargeAEtaler_IP_New]");
//                    q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("qteDetenue", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("cours", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("cumpT", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("cumpReel", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("plusOuMoinsValue", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("nbreJourCourus", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("interetCourus", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("valeurVDECours", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("valeurVDEInteret", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("idOpCours", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("idOpInteret", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("IRVM", BigDecimal.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("valeurFormuleInt", String.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("valeurCodeAnalytiqueInt", String.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("codeNatureOperationInt", String.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
//                    q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);
//
//                    q.setParameter("idSeance", obj.getIdSeance());
//                    q.setParameter("idTitre",obj.getIdTitre());
//                    System.out.println(obj.getIdTitre());
//                    q.setParameter("idOpcvm", obj.getIdOpcvm());
//                    q.setParameter("qteDetenue", obj.getQteDetenue());
//                    q.setParameter("cours", obj.getCours());
//                    q.setParameter("cumpT", obj.getCumpT());
//                    q.setParameter("cumpReel", obj.getCumpReel());
//                    q.setParameter("plusOuMoinsValue",obj.getPlusOuMoinsValue());
//                    q.setParameter("nbreJourCourus",obj.getNbreJourCourus());
//                    q.setParameter("interetCourus", obj.getInteretCourus());
//                    q.setParameter("valeurVDECours", obj.getValeurVDECours());
//                    q.setParameter("valeurVDEInteret", obj.getValeurVDEInteret());
//                    q.setParameter("idOpCours", obj.getIdOpCours());
//                    q.setParameter("idOpInteret", obj.getIdOpInteret());
//                    q.setParameter("IRVM", obj.getIrvm());
//                    q.setParameter("dateOperation",request.getDateOperation());
//                    q.setParameter("valeurFormule", valeurFormule);
//                    q.setParameter("valeurCodeAnalytique",valeurCodeAnalytique);
//                    q.setParameter("codeNatureOperation",codeNatureOp);
//                    q.setParameter("valeurFormuleInt", valeurFormuleInt);
//                    q.setParameter("valeurCodeAnalytiqueInt", valeurCodeAnalytiqueInt);
//                    q.setParameter("codeNatureOperationInt", codeNatureOperationInt);
//                    q.setParameter("userLogin", request.getUserLogin());
//                    q.setParameter("dateDernModifClient", LocalDateTime.now());
//                    q.setParameter("CodeLangue", "fr-FR");
//                    q.setParameter("Sortie", sortie);
//                    idOpcvm=obj.getIdOpcvm();
//                    q.execute();
//                    String[] s = new String[20];
//                    String result = (String) q.getOutputParameterValue("Sortie");
//                    s = result.split("#");
//                    em.close();
//                }
//                //mise à jour de l'étape de la séance
//                SeanceOpcvm lstSO = seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
//                if(lstSO!=null)
//                {
//                    if (lstSO.getIdSeanceOpcvm().getIdSeance() != 0)
//                    {
//                        seanceOpcvmDao.modifier(lstSO.getIdSeanceOpcvm().getIdOpcvm(),lstSO.getIdSeanceOpcvm().getIdSeance(),1L);
//                    }
//                }
//                return ResponseHandler.generateResponse(
//                        "Enregistrement effectué avec succès !",
//                        HttpStatus.OK,
//                        "Enregistrement effectué avec succès");
//            }
//            else
//            {
//                return ResponseHandler.generateResponse(
//                        "Enregistrement effectué avec succès !",
//                        HttpStatus.OK,
//                        "La grille est Vide");
//            }
//
//
//        } catch (Exception e) {
////            String[] s = new String[20];
////            String result = (String) q.getOutputParameterValue("Sortie");
////            s = result.split("#");
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Object> modifier(OperationChargeAEtalerDto operationChargeAEtalerDto) {
//        try {
//
//            String sortie="";
//            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationChargeAEtaler_UP_New]");
//
//            q.registerStoredProcedureParameter("IdChargeAEtaler", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("typeEvenement", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("typePayement", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("IdIntervenant_New", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("qteDetenue", BigDecimal.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("couponDividendeUnitaire", BigDecimal.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("montantBrut", BigDecimal.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("quantiteAmortie", BigDecimal.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("nominalRemb", BigDecimal.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("capitalRembourse", BigDecimal.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("montantTotalARecevoir", BigDecimal.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("estPaye", Boolean.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);
//
//            return ResponseHandler.generateResponse(
//                    "e.getMessage()",
//                    HttpStatus.MULTI_STATUS,
//                    null);
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Object> supprimer(String userLogin,
//                                            Long idChargeAEtaler ) {
//        try {
//            String sortie="";
//            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationChargeAEtaler_DP_New]");
//
//            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("idChargeAEtaler", Long.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
//            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);
//
//            q.setParameter("userLogin", userLogin);
//            q.setParameter("idChargeAEtaler",idChargeAEtaler);
//            q.setParameter("CodeLangue", "fr-FR");
//            q.setParameter("Sortie",sortie);
//
//            try
//            {
//                q.execute();
//                String result=(String) q.getOutputParameterValue("Sortie");
////                s=result.split("#");
//                //System.out.println("idOperation="+s[s.length-1]);
//            }
//            catch(Exception e){
//
//            }
////            operationChargeAEtalerDao.deleteById(id);
//            return ResponseHandler.generateResponse(
//                    "Suppression effectuée avec succès",
//                    HttpStatus.OK,
//                    null);
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }
}
