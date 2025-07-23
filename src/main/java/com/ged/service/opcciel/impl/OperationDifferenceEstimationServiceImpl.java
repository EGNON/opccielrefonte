package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.*;
import com.ged.dao.opcciel.comptabilite.ExerciceDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.titresciel.DatDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.AvisOperationBourseDto;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.OperationDifferenceEstimationDto;
import com.ged.dto.opcciel.OperationExtourneVDEDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.dto.titresciel.DatDto;
import com.ged.entity.opcciel.CleOperationDifferenceEstimation;
import com.ged.entity.opcciel.OperationDifferenceEstimation;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.entity.titresciel.Dat;
import com.ged.helpers.LocalDateTimeDeserializer;
import com.ged.mapper.opcciel.*;
import com.ged.mapper.titresciel.DatMapper;
import com.ged.projection.DifferenceEstimationProjection;
import com.ged.projection.OperationDifferenceEstimationProjection;
import com.ged.projection.PortefeuilleOpcvmProjection;
import com.ged.projection.VerificationMiseEnAffectationEnAttenteProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.opcciel.OperationDifferenceEstimationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional
public class OperationDifferenceEstimationServiceImpl implements OperationDifferenceEstimationService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final FiltersSpecification<OperationDifferenceEstimation> DifferenceEstimationFiltersSpecification;
    private final OperationDifferenceEstimationDao operationDifferenceEstimationDao;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final ExerciceDao exerciceDao;
    private final ExerciceMapper exerciceMapper;
    private final OpcvmDao opcvmDao;
    private final LibraryDao libraryDao;
    private final OperationExtourneVDEDao operationExtourneVDEDao;
    private final OperationExtourneVDEMapper operationExtourneVDEMapper;
    private final AvisOperationBourseDao avisOperationBourseDao;
    private final AvisOperationBourseMapper avisOperationBourseMapper;
    private final DepotRachatDao depotRachatDao;
    private final DepotRachatMapper depotRachatMapper;
    private final DatDao datDao;
    private final DatMapper datMapper;
    private final OperationDifferenceEstimationMapper operationDifferenceEstimationMapper;

    public OperationDifferenceEstimationServiceImpl(FiltersSpecification<OperationDifferenceEstimation> DifferenceEstimationFiltersSpecification, OperationDifferenceEstimationDao operationDifferenceEstimationDao, SeanceOpcvmDao seanceOpcvmDao, ExerciceDao exerciceDao, ExerciceMapper exerciceMapper, OpcvmDao opcvmDao, LibraryDao libraryDao, OperationExtourneVDEDao operationExtourneVDEDao, OperationExtourneVDEMapper operationExtourneVDEMapper, AvisOperationBourseDao avisOperationBourseDao, AvisOperationBourseMapper avisOperationBourseMapper, DepotRachatDao depotRachatDao, DepotRachatMapper depotRachatMapper, NatureOperationDao natureOperationDao, DatDao datDao, DatMapper datMapper, OperationDifferenceEstimationMapper operationDifferenceEstimationMapper){
        this.DifferenceEstimationFiltersSpecification = DifferenceEstimationFiltersSpecification;
        this.operationDifferenceEstimationDao = operationDifferenceEstimationDao;
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.exerciceDao = exerciceDao;
        this.exerciceMapper = exerciceMapper;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.operationExtourneVDEDao = operationExtourneVDEDao;
        this.operationExtourneVDEMapper = operationExtourneVDEMapper;
        this.avisOperationBourseDao = avisOperationBourseDao;
        this.avisOperationBourseMapper = avisOperationBourseMapper;
        this.depotRachatDao = depotRachatDao;
        this.depotRachatMapper = depotRachatMapper;
        this.datDao = datDao;
        this.datMapper = datMapper;

        this.operationDifferenceEstimationMapper = operationDifferenceEstimationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(ConstatationChargeListeRequest request) {
        try {
            DatatableParameters parameters=request.getDatatableParameters();
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationDifferenceEstimation> operationDifferenceEstimationPage;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                operationDifferenceEstimationPage = operationDifferenceEstimationDao.findByOpcvmAndSupprimer(opcvm,false,pageable);
//            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                operationDifferenceEstimationPage = operationDifferenceEstimationDao.afficherListe(request.getIdOpcvm()
                        ,request.getIdSeance(),pageable);
//            }

//            List<OperationDifferenceEstimationDto> content = operationDifferenceEstimationPage.getContent().stream().map(operationDifferenceEstimationMapper::deOperationDifferenceEstimationProjection).collect(Collectors.toList());
            List<OperationDifferenceEstimationDto> content = operationDifferenceEstimationPage.getContent().stream().map(operationDifferenceEstimationMapper::deOperationDifferenceEstimation).collect(Collectors.toList());
            DataTablesResponse<OperationDifferenceEstimationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationDifferenceEstimationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationDifferenceEstimationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des VDE par page datatable",
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
    public ResponseEntity<Object> afficherTous(Long idOpcvm,Long idSeance) {
        try {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
//            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            List<OperationDifferenceEstimationDto> operationDifferenceEstimationDtos =
                    operationDifferenceEstimationDao.afficherListe(idOpcvm,idSeance).stream().map(operationDifferenceEstimationMapper::deOperationDifferenceEstimation).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                        "Liste des VDE ",
                    HttpStatus.OK,
                    operationDifferenceEstimationDtos);
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
    public OperationDifferenceEstimation afficherSelonId(CleOperationDifferenceEstimation id) {
        return operationDifferenceEstimationDao.findById(id).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleOperationDifferenceEstimation id) {
        try {
//            System.out.println(id);
            OperationDifferenceEstimation operationDifferenceEstimation=afficherSelonId(id);
            return ResponseHandler.generateResponse(
                    "Operation DifferenceEstimation dont ID = " + id,
                    HttpStatus.OK,
                    operationDifferenceEstimationMapper.deOperationDifferenceEstimation(operationDifferenceEstimation));
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
    public ResponseEntity<Object> afficherTitre(Long idOpcvm, LocalDateTime dateEstimation, String typeEvenement) {
        try {
//            List<PortefeuilleOpcvmProjection> portefeuilleOpcvmProjections=new ArrayList<>();//libraryDao.portefeuilleOPCVM(idOpcvm, dateEstimation);
            List<PortefeuilleOpcvmProjection> list=new ArrayList<>();
            if(typeEvenement.equals("DIVIDENDE")){
                list=libraryDao.portefeuilleOPCVMAction(idOpcvm, dateEstimation);
            }
            else
            {
                list=libraryDao.portefeuilleOPCVMAutres(idOpcvm, dateEstimation);
            }

            return ResponseHandler.generateResponse(
                    "PortefeuilleOPCVM  ",
                    HttpStatus.OK,
                    list);
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
    public ResponseEntity<Object> genrerDifferenceEstimation(Long idOpcvm, Boolean estEnCloture) {
            try {
                String message="";
                Boolean estFinie=false;
                ExerciceDto exerciceDto=exerciceMapper.deExercice(exerciceDao.exerciceCourant(idOpcvm));
                if(exerciceDto==null){
                    message="Veuillez définir l'exercice courant";
                }
                else
                {
                   List<VerificationMiseEnAffectationEnAttenteProjection> list=libraryDao.verificationMiseEnAffectationEnAttente(
                           idOpcvm
                   ) ;
                   if(list.size()!=0){
                       message="Veuillez éffectuer l'enrégistrement de la décision du conseil avant de continuer.";
                   }
                   else {
                       SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
                       if(seanceOpcvm.getEstEnCloture()==false){
                          //controler si les extournes des VDE de la séance passée ont été faites
                           List<OperationDifferenceEstimationDto> lstOperationDifferenceEstimation=operationDifferenceEstimationDao.afficherListe(idOpcvm,
                                   seanceOpcvm.getIdSeanceOpcvm().getIdSeance()-1).stream().map(operationDifferenceEstimationMapper::deOperationDifferenceEstimation).collect(Collectors.toList());

                            List<OperationExtourneVDEDto> lstOperationExtourneVDE=operationExtourneVDEDao.afficherListe(idOpcvm,seanceOpcvm.getIdSeanceOpcvm().getIdSeance())
                                    .stream().map(operationExtourneVDEMapper::deOperationExtourneVDE).collect(Collectors.toList());


                           if (lstOperationExtourneVDE.size() != lstOperationDifferenceEstimation.size())
                           {
                               message= "Les différentes d'estimation de la séance précédente n'ont pas encore été extournées.";

                           }
                           else
                           {
                               // controler si les extournes de VDE ont été vérifiées Niveau 1
                               if (libraryDao.verifOpExtourneN1N2("VERIFEXTOURNE",seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),
                                       idOpcvm,false, false))
                               {

                                   message= "Vous avez des extournes de VDE en attente de vérification Niveau 1";
                               }
                               // controler si les extournes de VDE ont été vérifiées Niveau 2
                               else
                                   if (libraryDao.verifOpExtourneN1N2("VERIFEXTOURNE",seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),
                                           idOpcvm, true, false)) {

                                       message= "Vous avez des extournes de VDE en attente de vérification Niveau 2";
                                   }
                                   // controler si les extournes de VDE ont été vérifiées Niveau 2
                                   else
                                       //controler si les écritures des extournes de VDE ont été vérifiées Niveau 1
                                       if (libraryDao.verifOpExtourneN1N2("ECRITURES",seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),
                                               idOpcvm, false, false)) {

                                           message = "Vous avez des jeux d'écritures d' extournes de VDE en attente de vérification Niveau 1";
                                       }
                                       else
                                           //controler si les écritures des extournes de VDE ont été vérifiées Niveau 1
                                           if (libraryDao.verifOpExtourneN1N2("ECRITURES",seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),
                                                   idOpcvm, true, false)) {

                                               message = "Vous avez des jeux d'écritures d' extournes de VDE en attente de vérification Niveau 2";
                                           }
                                           else
                                                // controler si la mise a jour des cours des titres a été faites pour la séance en cours
                                               if (libraryDao.coursTitre("BRVM", seanceOpcvm.getDateFermeture()
                                                       , null, null).size() == 0)
                                               {
                                                   message="Veuillez Mettre à jour le cours des titres pour la date de séance en cours.";

                                               }
                                               else
                                               if (libraryDao.coursTitre("BRVM", seanceOpcvm.getDateFermeture(),
                                                       true, null).size() == 0)
                                               {
                                                  message="Veuillez effectuer la vérification Niveau 1 de la liste des cours des titres.";

                                               }
                                               else
                                               if (libraryDao.coursTitre("BRVM", seanceOpcvm.getDateFermeture(),
                                                       true, true).size() == 0)
                                               {
                                                   message="Veuillez effectuer la vérification Niveau 2 de la liste des cours des titres.";
                                               }
                                               else {
                                                   // Vérification des règlements livraison
                                                   List<AvisOperationBourseDto> avisOperationBourseDtos=avisOperationBourseDao.verifierReglementAttente(
                                                           idOpcvm,seanceOpcvm.getDateFermeture()
                                                   ).stream().map(avisOperationBourseMapper::deAvisOperationBourse).collect(Collectors.toList());
                                                   if (avisOperationBourseDtos.size() != 0) {
                                                       message="Veuillez effectuer les règlements livraison en attente avant la cloture";
                                                   }
                                                   else
                                                   {
                                                       // Vérification des écritures
                                                       if (libraryDao.listeVerificationEcriture(seanceOpcvm.getIdSeanceOpcvm().getIdOpcvm(),
                                                               null, seanceOpcvm.getDateOuverture()
                                                               , seanceOpcvm.getDateFermeture(), false, false,null).size() != 0)
                                                       {
                                                          message="vérification Niveau 1 des écritures en attente";

                                                       }
                                                       else
                                                       if (libraryDao.listeVerificationEcriture(seanceOpcvm.getIdSeanceOpcvm().getIdOpcvm(),
                                                               null, seanceOpcvm.getDateOuverture()
                                                               , seanceOpcvm.getDateFermeture(), true, false,null).size() != 0)
                                                       {
                                                           message="vérification Niveau 2 des écritures en attente";
                                                       }
                                                       else
                                                       {
                                                           // Souscription

                                                           List<DepotRachatDto> depotRachatDtos = depotRachatDao.tousLesDepotsSouscription(
                                                                   idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), null, null
                                                                           , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());

                                                           if (depotRachatDtos.size() != 0)
                                                           {
                                                               if (depotRachatDao.tousLesDepotsSouscription(
                                                                       idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), false, null
                                                                       , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
                                                               {
                                                                   message="vérification des dépots pour souscription en attente";
                                                               }
                                                               else
                                                               if (
                                                                       depotRachatDao.tousLesDepotsSouscription(
                                                                               idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), true, false
                                                                               , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
                                                               {
                                                                 message="vérification des dépots pour souscription Niveau 1 en attente";
                                                               }
                                                               else
                                                               if (
                                                                       depotRachatDao.tousLesDepotsSouscription(
                                                                               idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), true, true
                                                                               , false).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
                                                               {
                                                                   message="vérification des dépots pour souscription Niveau 2 en attente";
                                                               }
                                                                else
                                                               if (
                                                                       libraryDao.controlGenerationSousRach(idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), "SOUSCRIPTION") == false)
                                                               {
                                                                  message="Les souscriptions n'ont pas encore été générées";
                                                               }
                                                               else
                                                               {
                                                                   var q=em.createStoredProcedureQuery("[Operation].[PS_PrecalculRestitutionReliquat_new]");
                                                                   q.registerStoredProcedureParameter("idOpcvm",Long.class,ParameterMode.IN);
                                                                   q.registerStoredProcedureParameter("idSeance",Long.class,ParameterMode.IN);

                                                                   q.setParameter("idOpcvm",idOpcvm);
                                                                   q.setParameter("idSeance",seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
                                                                   List<Object[]> objects=q.getResultList();

                                                                   var q2=em.createStoredProcedureQuery("[Operation].[PS_OperationRestitutionReliquat_SP_New2]");
                                                                   q2.registerStoredProcedureParameter("idSeance",Long.class,ParameterMode.IN);
                                                                   q2.registerStoredProcedureParameter("idOpcvm",Long.class,ParameterMode.IN);
                                                                   q2.registerStoredProcedureParameter("codeNatureOperation",String.class,ParameterMode.IN);
                                                                   q2.registerStoredProcedureParameter("supprimer",Boolean.class,ParameterMode.IN);

                                                                   q2.setParameter("idSeance",seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
                                                                   q2.setParameter("idOpcvm",idOpcvm);
                                                                   q2.setParameter("codeNatureOperation","REST_RELIQUATS");
                                                                   q2.setParameter("supprimer",false);

                                                                   List<Object[]> objects2=q.getResultList();
                                                                   if (objects.size() != 0
                                                                           && objects2.size() == 0)
                                                                   {
                                                                       message= "Vous avez des restitutions de reliquats en attente";
                                                                   }
                                                                   else
                                                                   {
                                                                       // Rachat
                                                                       List<DepotRachatDto> rachats = depotRachatDao.tousLesRachats(
                                                                               idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), null, null
                                                                               , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());

                                                                       if (depotRachatDtos.size() != 0)
                                                                       {
                                                                           if (depotRachatDao.tousLesRachats(
                                                                                   idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), false, null
                                                                                   , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
                                                                           {
                                                                               message="vérification des intentions de rachat en attente";
                                                                           }
                                                                           else
                                                                           if (
                                                                                   depotRachatDao.tousLesRachats(
                                                                                           idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), true, false
                                                                                           , null).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
                                                                           {
                                                                               message="vérification des intentions de rachat Niveau 1 en attente";
                                                                           }
                                                                           else
                                                                           if (
                                                                                   depotRachatDao.tousLesRachats(
                                                                                           idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), true, true
                                                                                           , false).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList()).size() != 0)
                                                                           {
                                                                               message="vérification des intentions de rachat Niveau 2 en attente";
                                                                           }
                                                                           else
                                                                           if (
                                                                                   libraryDao.controlGenerationSousRach(idOpcvm, seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), "RACHAT") == false)
                                                                           {
                                                                               message="Les rachats n'ont pas encore été générées";
                                                                           }
                                                                           else
                                                                           {
                                                                               q=em.createStoredProcedureQuery("[Operation].[PS_OperationPaiementRachat_SP_New]");
                                                                               q.registerStoredProcedureParameter("idSeance",Long.class,ParameterMode.IN);
                                                                               q.registerStoredProcedureParameter("idOpcvm",Long.class,ParameterMode.IN);
                                                                               q.registerStoredProcedureParameter("codeNatureOperation",String.class,ParameterMode.IN);
                                                                               q.registerStoredProcedureParameter("supprimer",Boolean.class,ParameterMode.IN);

                                                                               q.setParameter("idSeance",seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
                                                                               q.setParameter("idOpcvm",idOpcvm);
                                                                               q.setParameter("codeNatureOperation","PAI_RACH");
                                                                               q.setParameter("supprimer",false);
                                                                               List<Object[]> objects3=q.getResultList();

                                                                               if (objects3.size() == 0)
                                                                               {
                                                                                   message= "Vous n'avez pas encore éffectués les paiments de rachat en attente";
                                                                               }
                                                                               else
                                                                               {
                                                                                   //controler si il y a des ESV en attente de détachement
                                                                                   List<Object[]> objects1=new ArrayList<>();
                                                                                   try
                                                                                   {
                                                                                       objects1= libraryDao.previsionnelRemboursement(idOpcvm, true, false, false, LocalDateTime.parse("1900/01/01"), seanceOpcvm.getDateFermeture());

                                                                                   }
                                                                                   catch (Exception ex)
                                                                                   {

                                                                                       message= ex.toString() ;
                                                                                   }

                                                                                   if (objects1.size() > 0)
                                                                                   {
                                                                                       message= "Vous avez des titres à échéance échus. Veuillez procéder à un détachement";
                                                                                   }
                                                                                   else
                                                                                   {
                                                                                       q=em.createStoredProcedureQuery("[Dividende].[PS_DecisionDistribution_SP_New]");
                                                                                       q.registerStoredProcedureParameter("idOpcvm", Long.class,ParameterMode.IN);
                                                                                       q.registerStoredProcedureParameter("dateDetachement", LocalDateTime.class,ParameterMode.IN);
                                                                                       q.registerStoredProcedureParameter("supprimer", Boolean.class,ParameterMode.IN);

                                                                                       q.setParameter("idOpcvm",idOpcvm);
                                                                                       q.setParameter("dateDetachement",seanceOpcvm.getDateFermeture());
                                                                                       q.setParameter("supprimer",false);

                                                                                       List<Object[]> lstDecisionDistribution=q.getResultList();

                                                                                       if (lstDecisionDistribution.size() != 0)
                                                                                       {
                                                                                           message="Vous avez " + lstDecisionDistribution.size() + " détachement(s) en attente.";

                                                                                       }
                                                                                       else
                                                                                       {
                                                                                           message="Lors de la cloture de séance,aucune autre opération ne pourra etre éffectuée.";

                                                                                               /*Marquer le champ estEnCloture de la table TJ_SeanceOpcvm à true*/
                                                                                           if(estEnCloture){
                                                                                                seanceOpcvmDao.modifier(idOpcvm,seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),true);
                                                                                                estFinie=true;
                                                                                           }
                                                                                       }
                                                                                   }

                                                                               }
                                                                           }

                                                                       }
                                                                   }
                                                               }

                                                           }

                                                       }
                                                   }
                                               }

                           }
                       }
                   }
                }
                if(!estFinie)
                    return ResponseHandler.generateResponse(
                            "PortefeuilleOPCVM  ",
                            HttpStatus.OK,
                            message);
                else
                    return ResponseHandler.generateResponse(
                            "PortefeuilleOPCVM  ",
                            HttpStatus.OK,
                            estFinie);
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
    public ResponseEntity<Object> precalculDifferenceEstimation(DifferenceEstimationRequest differenceEstimationRequest) {
        try {
            SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeance(differenceEstimationRequest.getIdOpcvm(),differenceEstimationRequest.getIdSeance());
            if (seanceOpcvm != null )
            {
                if(seanceOpcvm.getEstEnCours()) {
                    LocalDateTime mDate = differenceEstimationRequest.getDateSeance();
                    if (((mDate.getMonthValue() == 12 && mDate.getDayOfMonth()==29) ||
                            (mDate.getMonthValue() == 12 && mDate.getDayOfMonth()==30) ) &
                            mDate.getDayOfWeek()== DayOfWeek.FRIDAY)
                    {
                        mDate = LocalDateTime.parse( mDate.getYear()+"/12/31");
                    }

                    DatatableParameters parameters=differenceEstimationRequest.getDatatableParameters();
                    Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
                    Pageable pageable = PageRequest.of(
                            parameters.getStart()/ parameters.getLength(), parameters.getLength());
                    Page<DifferenceEstimationProjection> operationDifferenceEstimationPage;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                operationDifferenceEstimationPage = operationDifferenceEstimationDao.findByOpcvmAndSupprimer(opcvm,false,pageable);
//            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                    operationDifferenceEstimationPage = libraryDao.precalculDifferenceEstimation(
                            differenceEstimationRequest.getIdSeance()
                            ,differenceEstimationRequest.getIdOpcvm(),differenceEstimationRequest.getDateSeance(),pageable);
//            }

//            List<OperationDifferenceEstimationDto> content = operationDifferenceEstimationPage.getContent().stream().map(operationDifferenceEstimationMapper::deOperationDifferenceEstimationProjection).collect(Collectors.toList());
                    List<DifferenceEstimationProjection> content = operationDifferenceEstimationPage.getContent().stream().collect(Collectors.toList());
                    DataTablesResponse<DifferenceEstimationProjection> dataTablesResponse = new DataTablesResponse<>();
                    dataTablesResponse.setDraw(parameters.getDraw());
                    dataTablesResponse.setRecordsFiltered((int)operationDifferenceEstimationPage.getTotalElements());
                    dataTablesResponse.setRecordsTotal((int)operationDifferenceEstimationPage.getTotalElements());
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
                            "Veuillez sélectionner la séance encours pour la valorisation.");
                }
            }
            else
            {
                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        "Veuillez sélectionner la séance encours pour la valorisation.");
            }





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
                List<OperationDifferenceEstimationProjection> list=libraryDao.operationDifferenceEstimation(
                        differenceEstimationRequest.getIdSeance(),differenceEstimationRequest.getIdOpcvm(),false,false,false);

                for(OperationDifferenceEstimationProjection o:list) {
                    operationDifferenceEstimationDao.modifier1(differenceEstimationRequest.getIdOpcvm(), o.getIdTitre(),
                            differenceEstimationRequest.getIdSeance(), differenceEstimationRequest.getUserLogin1(), LocalDateTime.now()
                            , true);
                }
                seanceOpcvmDao.modifier(differenceEstimationRequest.getIdOpcvm(), differenceEstimationRequest.getIdSeance(), 2L);

                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        "Vérification effectuée avec succès");
            }
            else
            {
                List<OperationDifferenceEstimationProjection> list=libraryDao.operationDifferenceEstimation(
                        differenceEstimationRequest.getIdSeance(),differenceEstimationRequest.getIdOpcvm(),true,false,false);

                for(OperationDifferenceEstimationProjection o:list) {
                    operationDifferenceEstimationDao.modifier2(differenceEstimationRequest.getIdOpcvm(),o.getIdTitre(),
                            differenceEstimationRequest.getIdSeance(), differenceEstimationRequest.getUserLogin2(), LocalDateTime.now()
                            , true);
                }
                seanceOpcvmDao.modifier(differenceEstimationRequest.getIdOpcvm(), differenceEstimationRequest.getIdSeance(), 3L);

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
    public ResponseEntity<Object> creer(OperationDifferenceEstimationDto operationDifferenceEstimationDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[Comptabilite].[PS_ExtournerDifferenceEstimation]");

            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("sortie", String.class, ParameterMode.OUT);


//            q.setParameter("idOperation", 0);
            q.setParameter("idOpcvm", operationDifferenceEstimationDto.getOpcvm().getIdOpcvm());
            q.setParameter("idSeance", operationDifferenceEstimationDto.getIdSeance());
            q.setParameter("dateValeur", operationDifferenceEstimationDto.getDateValeur());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("userLogin", operationDifferenceEstimationDto.getUserLogin());
            q.setParameter("codeLangue", "fr-FR");
            q.setParameter("sortie",sortie);
            String[] s=new String[20];
            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    s[s.length-1]);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public String toStr(DifferenceEstimationProjection differenceEstimationProjection,
                        String codeNatureOperation,
                        LocalDateTime dateOperation){
        return "symbole="+differenceEstimationProjection.getSymbolTitre()+"\n"+
                "designation="+differenceEstimationProjection.getDesignationTitre()+"\n"+
                "CumpT="+differenceEstimationProjection.getCumpT()+"\n"+
                "CumpReel"+differenceEstimationProjection.getCumpReel()+"\n"+
                "Cours="+differenceEstimationProjection.getCours()+"\n"+
                "IdOpCours="+differenceEstimationProjection.getIdOpCours()+"\n"+
                "IdOpInteret="+differenceEstimationProjection.getIdOpInteret()+"\n"+
                "IdTitre="+differenceEstimationProjection.getIdTitre()+"\n"+
                "ValeurVDEInteret="+differenceEstimationProjection.getValeurVDEInteret()+"\n"+
                "getValeurVDECours="+differenceEstimationProjection.getValeurVDECours()+"\n"+
                "InteretCourus="+differenceEstimationProjection.getInteretCourus()+"\n"+
                "PlusOuMoinsValue="+differenceEstimationProjection.getPlusOuMoinsValue()+"\n"+
                "codena="+codeNatureOperation+"\n"+
                "dateoper="+dateOperation;
    }
    @Override
    public ResponseEntity<Object> enregistrerDifferenceEstimation(DifferenceEstimationRequest  request) {
        var q=em.createStoredProcedureQuery("[Operation].[PS_OperationDifferenceEstimation_IP_New]");
        try {
            List<DifferenceEstimationProjection> operationDifferenceEstimationDto=libraryDao.precalculDifferenceEstimation(
                    request.getIdSeance(), request.getIdOpcvm(), request.getDateSeance()
            );
            String sortie="";
            Long idOpcvm=0L;

            if (operationDifferenceEstimationDto != null &&
                    operationDifferenceEstimationDto.size() != 0)
            {

                //suppression d'une éventuelle ancienne valorisation
                //parcours pour enregistrement
                int i=0;
                for (DifferenceEstimationProjection obj : operationDifferenceEstimationDto)
                {

                    if(i==0){
                         q = em.createStoredProcedureQuery("[Operation].[PS_OperationDifferenceEstimation_DP_ALL]");
//                         q = em.createStoredProcedureQuery("[Operation].[PS_OperationDifferenceEstimation_DP_ALL]");
                        q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
                        q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);

                        q.setParameter("idSeance", obj.getIdSeance());
                        q.setParameter("idOpcvm", obj.getIdOpcvm());
                        q.execute();
                    }
                    i++;
                    Dat dat=datDao.findByIdTitre(obj.getIdTitre());
                    DatDto objTitre=new DatDto();
                    if(dat!=null)
                     objTitre =datMapper.deDat(dat);
                    else
                        objTitre=null;
                    //pour les cours
                    String valeurCodeAnalytique = "OPC:" + obj.getIdOpcvm().toString() +
                            ";TIT:" + obj.getIdTitre().toString();
                    String valeurFormule = "2:" + Math.abs(obj.getValeurVDECours().doubleValue()) ;
                    valeurFormule=valeurFormule.toString().replace(',', '.');
                    String codeNatureOp="";
                    if (obj.getValeurVDECours().doubleValue() < 0)
                    {
                        codeNatureOp = "DE_TIT_MV";
                    }
                    else
                    {
                        codeNatureOp = "DE_TIT_PV";
                    }
                    String valeurCodeAnalytiqueInt="";
                    String codeNatureOperationInt="";
                    String valeurFormuleInt="";
                    //pour les interets
                    if (obj.getValeurVDEInteret().doubleValue() != 0)
                    {
                        if (objTitre == null)
                        {
                            valeurCodeAnalytiqueInt = "OPC:" + obj.getIdOpcvm().toString() +
                                    ";TIT:" + obj.getIdTitre().toString();
                            codeNatureOperationInt = "DE_int_TIT";
                            valeurFormuleInt =
                                    "2:" + obj.getValeurVDEInteret().toString().replace(',', '.') +
                                            ";38:" + obj.getIrvm().toString().replace(',', '.');
                        }
                        else
                        {
                            valeurCodeAnalytiqueInt = "OPC:" + obj.getIdOpcvm().toString() +
                                    ";TIT:" + obj.getIdTitre().toString();
                            codeNatureOperationInt = "DE_INT_DAT";
                            valeurFormuleInt =
                                    "2:" + obj.getValeurVDEInteret().toString().replace(',', '.');
                        }
                    }
                    else
                    {
                        valeurCodeAnalytiqueInt = "";
                        codeNatureOperationInt = "";
                        valeurFormuleInt = "";
                    }
                    System.out.println(toStr(obj,codeNatureOp,request.getDateOperation()));
//                    var q = em.createStoredProcedureQuery("[Operation].[PS_OperationDifferenceEstimation_DP_ALL]");
                    q=em.createStoredProcedureQuery("[Operation].[PS_OperationDifferenceEstimation_IP_New]");
                    q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("qteDetenue", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("cours", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("cumpT", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("cumpReel", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("plusOuMoinsValue", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("nbreJourCourus", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("interetCourus", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("valeurVDECours", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("valeurVDEInteret", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("idOpCours", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("idOpInteret", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("IRVM", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("valeurFormuleInt", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("valeurCodeAnalytiqueInt", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("codeNatureOperationInt", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

                    q.setParameter("idSeance", obj.getIdSeance());
                    q.setParameter("idTitre",obj.getIdTitre());
                    System.out.println(obj.getIdTitre());
                    q.setParameter("idOpcvm", obj.getIdOpcvm());
                    q.setParameter("qteDetenue", obj.getQteDetenue());
                    q.setParameter("cours", obj.getCours());
                    q.setParameter("cumpT", obj.getCumpT());
                    q.setParameter("cumpReel", obj.getCumpReel());
                    q.setParameter("plusOuMoinsValue",obj.getPlusOuMoinsValue());
                    q.setParameter("nbreJourCourus",obj.getNbreJourCourus());
                    q.setParameter("interetCourus", obj.getInteretCourus());
                    q.setParameter("valeurVDECours", obj.getValeurVDECours());
                    q.setParameter("valeurVDEInteret", obj.getValeurVDEInteret());
                    q.setParameter("idOpCours", obj.getIdOpCours());
                    q.setParameter("idOpInteret", obj.getIdOpInteret());
                    q.setParameter("IRVM", obj.getIrvm());
                    q.setParameter("dateOperation",request.getDateOperation());
                    q.setParameter("valeurFormule", valeurFormule);
                    q.setParameter("valeurCodeAnalytique",valeurCodeAnalytique);
                    q.setParameter("codeNatureOperation",codeNatureOp);
                    q.setParameter("valeurFormuleInt", valeurFormuleInt);
                    q.setParameter("valeurCodeAnalytiqueInt", valeurCodeAnalytiqueInt);
                    q.setParameter("codeNatureOperationInt", codeNatureOperationInt);
                    q.setParameter("userLogin", request.getUserLogin());
                    q.setParameter("dateDernModifClient", LocalDateTime.now());
                    q.setParameter("CodeLangue", "fr-FR");
                    q.setParameter("Sortie", sortie);
                    idOpcvm=obj.getIdOpcvm();
                    q.execute();
                    String[] s = new String[20];
                    String result = (String) q.getOutputParameterValue("Sortie");
                    s = result.split("#");
                    em.close();
                }
                //mise à jour de l'étape de la séance
                SeanceOpcvm lstSO = seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
                if(lstSO!=null)
                {
                    if (lstSO.getIdSeanceOpcvm().getIdSeance() != 0)
                    {
                        seanceOpcvmDao.modifier(lstSO.getIdSeanceOpcvm().getIdOpcvm(),lstSO.getIdSeanceOpcvm().getIdSeance(),1L);
                    }
                }
                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        "Enregistrement effectué avec succès");
            }
            else
            {
                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        "La grille est Vide");
            }


        } catch (Exception e) {
//            String[] s = new String[20];
//            String result = (String) q.getOutputParameterValue("Sortie");
//            s = result.split("#");
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OperationDifferenceEstimationDto operationDifferenceEstimationDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationDifferenceEstimation_UP_New]");

            q.registerStoredProcedureParameter("IdDifferenceEstimation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("typeEvenement", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("typePayement", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdIntervenant_New", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("qteDetenue", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("couponDividendeUnitaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantBrut", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteAmortie", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("nominalRemb", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("capitalRembourse", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantTotalARecevoir", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estPaye", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            return ResponseHandler.generateResponse(
                    "e.getMessage()",
                    HttpStatus.MULTI_STATUS,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String userLogin,
                                            Long idDifferenceEstimation ) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationDifferenceEstimation_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idDifferenceEstimation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            q.setParameter("userLogin", userLogin);
            q.setParameter("idDifferenceEstimation",idDifferenceEstimation);
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);

            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
//                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

            }
//            operationDifferenceEstimationDao.deleteById(id);
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
