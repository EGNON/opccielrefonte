package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dao.opcciel.comptabilite.ExerciceDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.PlanDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.dto.opcciel.PlanDto;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationDeclassementResultat;
import com.ged.entity.opcciel.comptabilite.CleExercice;
import com.ged.entity.opcciel.comptabilite.Exercice;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.standard.FormeJuridique;
import com.ged.mapper.opcciel.ExerciceMapper;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.opcciel.PlanMapper;
import com.ged.mapper.opcciel.SeanceOpcvmMapper;
import com.ged.projection.ExerciceProjection;
import com.ged.projection.SoldeCompteProjection;
import com.ged.projection.SoldeToutCompteProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.ExerciceService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciceImpl implements ExerciceService {
    private final ExerciceDao exerciceDao;
    private final ExerciceMapper exerciceMapper;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final SeanceOpcvmMapper seanceOpcvmMapper;
    private final OpcvmDao opcvmDao;
    private final PlanDao planDao;
    private final PlanMapper planMapper;
    private final NatureOperationDao natureOperationDao;
    private final OpcvmMapper opcvmMapper;
    private final LibraryDao libraryDao;
    @Autowired
    private  EntityManager em;
    public ExerciceImpl(ExerciceDao exerciceDao, LibraryDao libraryDao, ExerciceMapper exerciceMapper, SeanceOpcvmDao seanceOpcvmDao, SeanceOpcvmMapper seanceOpcvmMapper, OpcvmDao opcvmDao, PlanDao planDao, PlanMapper planMapper, NatureOperationDao natureOperationDao, OpcvmMapper opcvmMapper) {
        this.exerciceDao = exerciceDao;
        this.libraryDao = libraryDao;
        this.exerciceMapper = exerciceMapper;
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.seanceOpcvmMapper = seanceOpcvmMapper;
        this.opcvmDao = opcvmDao;
        this.planDao = planDao;
        this.planMapper = planMapper;
        this.natureOperationDao = natureOperationDao;
        this.opcvmMapper = opcvmMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.DESC,"codeExercice");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Exercice> exercicePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
//                ObjectMapper mapper = new ObjectMapper();
//                System.out.println("PARAMS -> " + mapper.writeValueAsString(parameters.getSearch()));
                exercicePage = exerciceDao.exerciceOpcvm(parameters.getSearch().getValue(),idOpcvm, pageable);
            }
            else {
                exercicePage = exerciceDao.exerciceOpcvm(idOpcvm,pageable);
            }
            List<ExerciceDto> content = exercicePage.getContent().stream().map(exerciceMapper::deExercice).collect(Collectors.toList());
            DataTablesResponse<ExerciceDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)exercicePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)exercicePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des exercice par page datatable",
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
    public List<ExerciceDto> afficherTous() {
        return exerciceDao.afficherExercice().stream().map(exerciceMapper::deExerciceProjection).collect(Collectors.toList());
    }

    @Override
    public List<ExerciceDto> afficherParOPcvm(Long idOpcvm) throws SQLException {
        return exerciceDao.exerciceOpcvm(idOpcvm).stream().map(exerciceMapper::deExercice).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> exerciceCourant(Long idOpcvm) {
        try {
            ExerciceDto exercice = exerciceMapper.deExercice(exerciceDao.exerciceCourant(idOpcvm));
            return ResponseHandler.generateResponse(
                    "Exercice courant",
                    HttpStatus.OK,
                    exercice);
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
    public ResponseEntity<Object> exerciceParOpcvmEtCode(Long idOpcvm, String code) {
        try {
            ExerciceDto exercice = exerciceMapper.deExercice(exerciceDao.exerciceOpcvmCode(idOpcvm,code));
            return ResponseHandler.generateResponse(
                    "Exercice opcvm code",
                    HttpStatus.OK,
                    exercice);
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
    public ResponseEntity<Object> exerciceEnCours(Long idOpcvm) {
        try {
            ExerciceProjection exercice = exerciceDao.exerciceEnCours(idOpcvm);

            return ResponseHandler.generateResponse(
                    "Exercice courant",
                    HttpStatus.OK,
                    exercice);
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
    public ResponseEntity<Object> creerExercice(ExerciceDto exerciceDto) {
        try {
            String sortie="";
            CleExercice cleExercice=new CleExercice();
            cleExercice.setCodeExercice(exerciceDto.getCodeExercice());
            cleExercice.setIdOpcvm(exerciceDto.getOpcvm().getIdOpcvm());
            Exercice exercice = exerciceMapper.deExerciceDto(exerciceDto);
            exercice.setIdExercie(cleExercice);
            Opcvm opcvm=opcvmDao.findById(exerciceDto.getOpcvm().getIdOpcvm()).orElseThrow();
            exercice.setOpcvm(opcvm);
            PlanDto plan=planMapper.dePlan(planDao.findById(exerciceDto.getPlan().getCodePlan()).orElseThrow());
            exercice.setPlan(planMapper.dePlanDto(plan));
            Exercice exerciceSaved=exerciceDao.save(exercice);

            //int numLigne = Integer.parseInt(message[message.length - 1].trim());

            // Passation de l'écriture de déclassement du résultat de l'exercice en cours
            BigDecimal soldeRANB = BigDecimal.ZERO;
            BigDecimal soldeRegulRANBSous = BigDecimal.ZERO;
            BigDecimal soldeRegulRANBRach = BigDecimal.ZERO;
            BigDecimal soldeRAND = BigDecimal.ZERO;
            BigDecimal soldeRegulRANDSous = BigDecimal.ZERO;
            BigDecimal soldeRegulRANDRach = BigDecimal.ZERO;
            BigDecimal beneficeInstanceAffec = BigDecimal.ZERO;
            BigDecimal perteInstanceAffec = BigDecimal.ZERO;

            SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeanceEnCours(exerciceDto.getOpcvm().getIdOpcvm()));
            List<SoldeCompteProjection> soldeCompteProjections = libraryDao.soldeCompte(
                    String.valueOf(exerciceDto.getOpcvm().getIdOpcvm()),
                    "59000", "10",
                    seanceOpcvmDto.getDateFermeture()
            );
            BigDecimal benefice= !soldeCompteProjections.isEmpty() ?soldeCompteProjections.get(0).getSolde():BigDecimal.ZERO;

            soldeCompteProjections = libraryDao.soldeCompte(
                    String.valueOf(exerciceDto.getOpcvm().getIdOpcvm()),
                    "59000", "20",
                    seanceOpcvmDto.getDateFermeture()
            );
            BigDecimal perte = !soldeCompteProjections.isEmpty() ?soldeCompteProjections.get(0).getSolde():BigDecimal.ZERO;

            if (benefice.add(perte).compareTo(BigDecimal.ZERO) != 0) {

                if (exerciceDto.getDeclassement().trim().equals("AVEC REPORT A NOUVEAU")) {

                    soldeCompteProjections = libraryDao.soldeCompte(
                            String.valueOf(exerciceDto.getOpcvm().getIdOpcvm()),
                            "58000", "20",
                            seanceOpcvmDto.getDateFermeture());
                    soldeRANB =!soldeCompteProjections.isEmpty() ? soldeCompteProjections.get(0).getSolde()
                    :BigDecimal.ZERO;

                 List<SoldeToutCompteProjection>   soldeToutCompteProjections = libraryDao.soldeToutCompte(
                            "PCIA",exerciceDto.getOpcvm().getIdOpcvm(),
                            "5522110",
                            seanceOpcvmDto.getDateFermeture());
                    soldeRegulRANBSous =!soldeToutCompteProjections.isEmpty() ? soldeToutCompteProjections.get(0).getSoldeReel():
                    BigDecimal.ZERO;

                    soldeCompteProjections = libraryDao.soldeCompte(
                            String.valueOf(exerciceDto.getOpcvm().getIdOpcvm()),
                            "58000", "30",
                            seanceOpcvmDto.getDateFermeture());
                    soldeRAND =!soldeCompteProjections.isEmpty() ? soldeCompteProjections.get(0).getSolde():BigDecimal.ZERO;

                    soldeToutCompteProjections = libraryDao.soldeToutCompte(
                            "PCIA",exerciceDto.getOpcvm().getIdOpcvm(),
                            "5522210",
                            seanceOpcvmDto.getDateFermeture());
                    soldeRegulRANDSous =!soldeToutCompteProjections.isEmpty() ? soldeToutCompteProjections.get(0).getSoldeReel():
                    BigDecimal.ZERO;

                    soldeToutCompteProjections = libraryDao.soldeToutCompte(
                            "PCIA",exerciceDto.getOpcvm().getIdOpcvm(),
                            "5522220",
                            seanceOpcvmDto.getDateFermeture());

                    soldeRegulRANDRach =!soldeToutCompteProjections.isEmpty() ?soldeToutCompteProjections.get(0).getSoldeReel():
                    BigDecimal.ZERO;
                }
                BigDecimal resultat = benefice
                        .add(soldeRANB)
                        .add(soldeRegulRANBSous)
                        .add(soldeRegulRANDRach)
                        .subtract(perte.add(soldeRAND)
                                .add(soldeRegulRANBRach)
                                .add(soldeRegulRANDSous)
                        );
                var q=em.createStoredProcedureQuery("[Operation].[PS_OperationDeclassementResultat_IP]");
                q.registerStoredProcedureParameter("idOperation",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idTransaction",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idSeance",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeNatureOperation",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateOperation",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("libelleOperation",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateSaisie",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("datePiece",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateValeur",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("referencePiece",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("montant",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("ranBeneficiaire",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("ranDeficitaire",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("benefice",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("perte",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("beneficeInstanceAffec",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("perteInstanceAffec",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurFormule",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurCodeAnalytique",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie",String.class, ParameterMode.IN);

                q.setParameter("idOperation",0);
                q.setParameter("idTransaction",0);
                q.setParameter("idSeance",seanceOpcvmDto.getIdSeanceOpcvm().getIdSeance());
                q.setParameter("idOpcvm",exerciceDto.getOpcvm().getIdOpcvm());
                q.setParameter("codeNatureOperation","DECL_RES_EXE_CLO");
                q.setParameter("dateOperation",seanceOpcvmDto.getDateFermeture());
                q.setParameter("libelleOperation","DECLASSEMENT DU RESULTAT DE L'EXERCICE " +
                        (new BigDecimal(exerciceDto.getCodeExercice()).subtract(BigDecimal.ONE)));
                q.setParameter("dateSaisie",LocalDateTime.now());
                q.setParameter("datePiece",seanceOpcvmDto.getDateFermeture());
                q.setParameter("dateValeur",seanceOpcvmDto.getDateFermeture());
                q.setParameter("referencePiece","");
                q.setParameter("montant",resultat.abs());
                q.setParameter("ranBeneficiaire",soldeRANB);
                q.setParameter("ranDeficitaire",soldeRAND);
                q.setParameter("benefice",benefice);
                q.setParameter("perte",perte);
                q.setParameter("beneficeInstanceAffec",BigDecimal.ZERO);
                q.setParameter("perteInstanceAffec",BigDecimal.ZERO);

                if (resultat.compareTo(BigDecimal.ZERO) < 0) {
                    q.setParameter("perteInstanceAffec",resultat.abs());
                    perteInstanceAffec=resultat.abs();
                } else {
                    q.setParameter("beneficeInstanceAffec",resultat);
                    beneficeInstanceAffec=resultat;
                }

                q.setParameter("valeurFormule","7:"   + benefice.toPlainString() +
                        ";45:" + perte.toPlainString() +
                        ";78:" + soldeRANB.toPlainString() +
                        ";24:" + beneficeInstanceAffec.toPlainString() +
                        ";102:"+ soldeRAND.toPlainString() +
                        ";103:"+ perteInstanceAffec.toPlainString() +
                        ";120:"+ soldeRegulRANDSous.toPlainString() +
                        ";119:"+ soldeRegulRANDRach.toPlainString() +
                        ";118:"+ soldeRegulRANBRach.toPlainString() +
                        ";117:"+ soldeRegulRANBSous.toPlainString());
                q.setParameter("valeurCodeAnalytique","OPC:" + exerciceDto.getOpcvm().getIdOpcvm());
                q.setParameter("userLogin",exerciceDto.getUserLogin());
                q.setParameter("dateDernModifClient",LocalDateTime.now());
                q.setParameter("CodeLangue","fr-FR");
                q.setParameter("Sortie",sortie);
                q.execute();
            }

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès.",
                    HttpStatus.OK,
                    exerciceSaved);
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
    public ResponseEntity<Object> modifierExercice(ExerciceDto exerciceDto) {
        try {
            Exercice exercice = exerciceMapper.deExerciceDto(exerciceDto);
            Opcvm opcvm=opcvmDao.findById(exerciceDto.getOpcvm().getIdOpcvm()).orElseThrow();
            exercice.setOpcvm(opcvm);
            PlanDto plan=planMapper.dePlan(planDao.findById(exerciceDto.getPlan().getCodePlan()).orElseThrow());
            exercice.setPlan(planMapper.dePlanDto(plan));
            Exercice exerciceSaved=exerciceDao.save(exercice);

            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès.",
                    HttpStatus.OK,
                    exerciceSaved);
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
    public ResponseEntity<Object> supprimerExercice(Long idOpcvm,String codeExercice) {
        try {
            exerciceDao.supprimer(idOpcvm, codeExercice);

            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès.",
                    HttpStatus.OK,
                    null);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
